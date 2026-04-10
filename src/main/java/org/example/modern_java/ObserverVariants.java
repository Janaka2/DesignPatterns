package org.example.modern_java;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.Flow;
import java.util.concurrent.SubmissionPublisher;
import java.util.function.Consumer;

public final class ObserverVariants {
    private ObserverVariants() {
    }

    public interface Observer {
        void onEvent(String event);
    }

    public static final class ClassicSubject {
        private final List<Observer> observers = new CopyOnWriteArrayList<>();

        public void subscribe(final Observer observer) {
            observers.add(observer);
        }

        public void publish(final String event) {
            observers.forEach(observer -> observer.onEvent(event));
        }
    }

    public static final class FunctionalSubject {
        private final List<Consumer<String>> observers = new CopyOnWriteArrayList<>();

        public void subscribe(final Consumer<String> observer) {
            observers.add(observer);
        }

        public void publish(final String event) {
            observers.forEach(observer -> observer.accept(event));
        }
    }

    public static final class ModernPublisher implements AutoCloseable {
        private final SubmissionPublisher<String> publisher = new SubmissionPublisher<>();

        public void subscribe(final Flow.Subscriber<String> subscriber) {
            publisher.subscribe(subscriber);
        }

        public void publish(final String event) {
            publisher.submit(event);
        }

        @Override
        public void close() {
            publisher.close();
        }
    }
}
