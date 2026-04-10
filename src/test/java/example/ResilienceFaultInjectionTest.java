package example;

import org.example.enterprise.resilience.ExponentialBackoffStrategy;
import org.example.enterprise.resilience.RetryExecutor;
import org.example.enterprise.resilience.TimeoutExecutor;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.testcontainers.DockerClientFactory;
import org.testcontainers.containers.GenericContainer;
import org.testcontainers.containers.wait.strategy.Wait;
import org.testcontainers.utility.DockerImageName;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.Duration;
import java.util.concurrent.atomic.AtomicInteger;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assumptions.assumeTrue;

public class ResilienceFaultInjectionTest {

    private final TimeoutExecutor timeoutExecutor = new TimeoutExecutor();

    @AfterEach
    public void cleanup() {
        timeoutExecutor.shutdown();
    }

    @Test
    public void wiremockFaultInjectionShowsTimeoutsAndRetries() throws Exception {
        assumeTrue(DockerClientFactory.instance().isDockerAvailable(), "Docker is required for Testcontainers");

        try (GenericContainer<?> wiremock = new GenericContainer<>(DockerImageName.parse("wiremock/wiremock:3.13.1"))
                .withExposedPorts(8080)
                .waitingFor(Wait.forHttp("/__admin"))) {
            wiremock.start();

            String baseUrl = "http://" + wiremock.getHost() + ":" + wiremock.getMappedPort(8080);
            configureTimeoutStub(baseUrl);
            configureFlakyScenario(baseUrl);

            HttpClient client = HttpClient.newBuilder().connectTimeout(Duration.ofSeconds(1)).build();

            assertThrows(RuntimeException.class, () -> timeoutExecutor.execute(() -> httpGet(client, baseUrl + "/timeout"),
                    Duration.ofMillis(300)));

            RetryExecutor retryExecutor = new RetryExecutor(3, new ExponentialBackoffStrategy(0, 1));
            AtomicInteger attempts = new AtomicInteger();
            String body = retryExecutor.execute(() -> {
                attempts.incrementAndGet();
                return httpGet(client, baseUrl + "/inventory");
            });

            assertEquals("ok", body);
            assertEquals(3, attempts.get());
        }
    }

    private void configureTimeoutStub(String baseUrl) throws Exception {
        String payload = """
                {
                  "request": {"method": "GET", "url": "/timeout"},
                  "response": {"status": 200, "fixedDelayMilliseconds": 2000, "body": "late"}
                }
                """;
        postMapping(baseUrl, payload);
    }

    private void configureFlakyScenario(String baseUrl) throws Exception {
        String firstFailure = """
                {
                  "scenarioName": "Inventory Retry",
                  "requiredScenarioState": "Started",
                  "newScenarioState": "second-failure",
                  "request": {"method": "GET", "url": "/inventory"},
                  "response": {"status": 503, "body": "temporary-failure"}
                }
                """;
        String secondFailure = """
                {
                  "scenarioName": "Inventory Retry",
                  "requiredScenarioState": "second-failure",
                  "newScenarioState": "healthy",
                  "request": {"method": "GET", "url": "/inventory"},
                  "response": {"status": 500, "body": "partial-failure"}
                }
                """;
        String success = """
                {
                  "scenarioName": "Inventory Retry",
                  "requiredScenarioState": "healthy",
                  "request": {"method": "GET", "url": "/inventory"},
                  "response": {"status": 200, "body": "ok"}
                }
                """;

        postMapping(baseUrl, firstFailure);
        postMapping(baseUrl, secondFailure);
        postMapping(baseUrl, success);
    }

    private void postMapping(String baseUrl, String payload) throws Exception {
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(baseUrl + "/__admin/mappings"))
                .timeout(Duration.ofSeconds(5))
                .header("Content-Type", "application/json")
                .POST(HttpRequest.BodyPublishers.ofString(payload))
                .build();

        HttpResponse<String> response;
        try {
            response = client.send(request, HttpResponse.BodyHandlers.ofString());
        } catch (IOException | InterruptedException e) {
            throw new IllegalStateException("Failed to configure WireMock mapping", e);
        }

        if (response.statusCode() >= 300) {
            throw new IllegalStateException("WireMock mapping rejected: " + response.body());
        }
    }

    private String httpGet(HttpClient client, String url) {
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .timeout(Duration.ofSeconds(4))
                .GET()
                .build();
        try {
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            if (response.statusCode() >= 500) {
                throw new IllegalStateException("Upstream failure " + response.statusCode());
            }
            return response.body();
        } catch (IOException | InterruptedException e) {
            throw new IllegalStateException("HTTP call failed", e);
        }
    }
}
