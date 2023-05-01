package org.example.reactive;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public class ReactiveProcessor {

    public Mono<Integer> square(int value) {
        return Mono.just(value * value);
    }

    public Flux<Integer> squares(Flux<Integer> values) {
        return values.map(value -> value * value);
    }
}

