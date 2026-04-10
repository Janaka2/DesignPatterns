package org.example.modern_java;

import java.time.Duration;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.Consumer;

public final class ConcurrencyPatternsPack {
    private ConcurrencyPatternsPack() {
    }

    public static List<Integer> producerConsumerWithVirtualThreads(final int itemCount) {
        final BlockingQueue<Integer> queue = new LinkedBlockingQueue<>();
        final AtomicInteger sum = new AtomicInteger();

        try (var executor = Executors.newVirtualThreadPerTaskExecutor()) {
            final Future<Void> producer = executor.submit(() -> {
                for (int i = 1; i <= itemCount; i++) {
                    queue.put(i);
                }
                queue.put(0);
                return null;
            });

            final Future<Integer> consumer = executor.submit(() -> {
                int consumed = 0;
                while (true) {
                    final int value = queue.take();
                    if (value == 0) {
                        break;
                    }
                    consumed++;
                    sum.addAndGet(value);
                }
                return consumed;
            });

            producer.get();
            return List.of(consumer.get(), sum.get());
        } catch (InterruptedException interruptedException) {
            Thread.currentThread().interrupt();
            throw new IllegalStateException("Interrupted while processing queue", interruptedException);
        } catch (ExecutionException executionException) {
            throw new IllegalStateException("Producer-consumer execution failed", executionException);
        }
    }

    public static final class BoundedBackpressureQueue<T> {
        private final ArrayBlockingQueue<T> queue;

        public BoundedBackpressureQueue(final int capacity) {
            this.queue = new ArrayBlockingQueue<>(capacity);
        }

        public boolean tryPublish(final T value, final Duration timeout) throws InterruptedException {
            Objects.requireNonNull(timeout, "timeout must not be null");
            return queue.offer(value, timeout.toMillis(), TimeUnit.MILLISECONDS);
        }

        public T take() throws InterruptedException {
            return queue.take();
        }

        public int size() {
            return queue.size();
        }
    }

    public static final class ThreadConfinedMailbox<T> implements AutoCloseable {
        private final BlockingQueue<T> queue = new LinkedBlockingQueue<>();
        private final ExecutorService executorService = Executors.newSingleThreadExecutor();
        private final Future<?> worker;

        public ThreadConfinedMailbox(final Consumer<T> consumer) {
            final Callable<Void> task = () -> {
                while (!Thread.currentThread().isInterrupted()) {
                    final T item = queue.take();
                    consumer.accept(item);
                }
                return null;
            };
            this.worker = executorService.submit(task);
        }

        public void submitImmutable(final T value) throws InterruptedException {
            queue.put(value);
        }

        @Override
        public void close() {
            worker.cancel(true);
            executorService.shutdownNow();
        }
    }

    public record ImmutableMessage(String topic, String payload) {
    }
}
