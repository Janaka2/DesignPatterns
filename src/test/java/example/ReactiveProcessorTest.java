package example;

import org.example.reactive.ReactiveProcessor;
import org.junit.jupiter.api.Test;
import reactor.core.publisher.Flux;
import reactor.test.StepVerifier;

class ReactiveProcessorTest {

    private final ReactiveProcessor reactiveProcessor = new ReactiveProcessor();

    @Test
    void testSquare() {
        StepVerifier.create(reactiveProcessor.square(4))
                .expectNext(16)
                .verifyComplete();
    }

    @Test
    void testSquares() {
        StepVerifier.create(reactiveProcessor.squares(Flux.just(1, 2, 3, 4, 5)))
                .expectNext(1, 4, 9, 16, 25)
                .verifyComplete();
    }
}
