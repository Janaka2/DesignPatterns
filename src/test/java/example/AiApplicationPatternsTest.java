package example;

import org.example.ai.EvaluationHarness;
import org.example.ai.FallbackModelStrategy;
import org.example.ai.LlmClient;
import org.example.ai.PolicyEnforcedLlmClient;
import org.example.ai.RetrievalPipeline;
import org.example.ai.ToolAgentFacade;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicReference;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class AiApplicationPatternsTest {

    @Test
    public void ragLiteAndToolFacadeOrchestrateCorrectly() {
        RetrievalPipeline pipeline = new RetrievalPipeline(
                (query, topK) -> List.of(
                        new RetrievalPipeline.RetrievedDocument("d1", "Java uses classes and objects.", 0.9),
                        new RetrievalPipeline.RetrievedDocument("d2", "Design patterns improve maintainability.", 0.8)
                ),
                (query, docs) -> docs
        );

        LlmClient llm = prompt -> prompt.contains("Design patterns") ? "Patterns are reusable solutions" : "unknown";

        ToolAgentFacade facade = new ToolAgentFacade(
                pipeline,
                llm,
                Map.of("uppercase", String::toUpperCase)
        );

        String answer = facade.answer("How do patterns help?");
        assertEquals("Patterns are reusable solutions", answer);

        String toolResult = facade.answer("tool:uppercase:hello");
        assertEquals("HELLO", toolResult);
    }

    @Test
    public void fallbackModelStrategyUsesSecondaryModelOnPrimaryFailure() {
        LlmClient primary = prompt -> {
            throw new RuntimeException("primary unavailable");
        };
        LlmClient secondary = prompt -> "secondary response";

        FallbackModelStrategy strategy = new FallbackModelStrategy(List.of(primary, secondary));
        assertEquals("secondary response", strategy.complete("Explain retry strategy"));
    }

    @Test
    public void policyWrapperValidatesMasksAndFilters() {
        AtomicReference<String> seenPrompt = new AtomicReference<>();
        LlmClient base = prompt -> {
            seenPrompt.set(prompt);
            return "Use password=secret for admin";
        };

        PolicyEnforcedLlmClient secured = new PolicyEnforcedLlmClient(
                base,
                List.of(
                        new PolicyEnforcedLlmClient.NonEmptyInputPolicy(),
                        new PolicyEnforcedLlmClient.MaxLengthInputPolicy(200)
                ),
                List.of(new PolicyEnforcedLlmClient.BasicOutputFilterPolicy()),
                new PolicyEnforcedLlmClient.PiiMaskingInputPolicy()
        );

        String output = secured.complete("Email me at user@example.com");
        assertTrue(seenPrompt.get().contains("[EMAIL_MASKED]"));
        assertEquals("Use password:[REDACTED] for admin", output);
        assertThrows(IllegalArgumentException.class, () -> secured.complete(" "));
    }

    @Test
    public void evaluationHarnessSupportsGoldenAndStatisticalEvaluation() {
        EvaluationHarness.GoldenSetResult golden = EvaluationHarness.evaluateGoldenSet(
                List.of(
                        new EvaluationHarness.GoldenExample("A", "yes"),
                        new EvaluationHarness.GoldenExample("B", "no")
                ),
                input -> input.equals("A") ? "yes" : "maybe"
        );

        assertEquals(0.5, golden.accuracy());
        assertEquals(1, golden.failures().size());

        EvaluationHarness.StatisticalResult statistical = EvaluationHarness.evaluateStatistically(
                List.of("tiny", "bigger", "largest"),
                input -> Math.min(1.0, input.length() / 10.0)
        );

        assertEquals(0.5666666666666667, statistical.averageScore());
        assertEquals(0.4, statistical.minScore());
        assertEquals(0.7, statistical.maxScore());
    }
}
