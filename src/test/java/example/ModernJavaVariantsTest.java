package example;

import org.example.modern_java.CommandVariants;
import org.example.modern_java.ConcurrencyPatternsPack;
import org.example.modern_java.ObserverVariants;
import org.example.modern_java.StrategyVariants;
import org.junit.jupiter.api.Test;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.Flow;
import java.util.concurrent.TimeUnit;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class ModernJavaVariantsTest {

    @Test
    void strategyVariantsShouldWorkAcrossStyles() {
        final StrategyVariants.ClassicCalculator classic =
                new StrategyVariants.ClassicCalculator((left, right) -> left + right);
        final StrategyVariants.FunctionalCalculator functional =
                new StrategyVariants.FunctionalCalculator((left, right) -> left * right);
        final StrategyVariants.ModernCalculator modern = new StrategyVariants.ModernCalculator();

        assertEquals(7, classic.calculate(3, 4));
        assertEquals(12, functional.calculate(3, 4));
        assertEquals(11, modern.calculate(new StrategyVariants.Add(5, 6)));
    }

    @Test
    void commandVariantsShouldExecute() {
        final CommandVariants.ClassicInvoker classic = new CommandVariants.ClassicInvoker();
        classic.enqueue(() -> "classic");

        final CommandVariants.FunctionalInvoker functional = new CommandVariants.FunctionalInvoker();
        functional.enqueue(() -> "functional");

        final CommandVariants.ModernInvoker modern = new CommandVariants.ModernInvoker();

        assertEquals(List.of("classic"), classic.runAll());
        assertEquals(List.of("functional"), functional.runAll());
        assertEquals("created:u1", modern.run(new CommandVariants.CreateUser("u1")));
    }

    @Test
    void observerVariantsShouldPublish() throws InterruptedException {
        final List<String> classicEvents = new ArrayList<>();
        final ObserverVariants.ClassicSubject classic = new ObserverVariants.ClassicSubject();
        classic.subscribe(classicEvents::add);
        classic.publish("classic-event");

        final List<String> functionalEvents = new ArrayList<>();
        final ObserverVariants.FunctionalSubject functional = new ObserverVariants.FunctionalSubject();
        functional.subscribe(functionalEvents::add);
        functional.publish("functional-event");

        final CountDownLatch latch = new CountDownLatch(1);
        final List<String> modernEvents = new ArrayList<>();
        try (ObserverVariants.ModernPublisher modern = new ObserverVariants.ModernPublisher()) {
            modern.subscribe(new Flow.Subscriber<>() {
                private Flow.Subscription subscription;

                @Override
                public void onSubscribe(final Flow.Subscription incomingSubscription) {
                    this.subscription = incomingSubscription;
                    this.subscription.request(1);
                }

                @Override
                public void onNext(final String item) {
                    modernEvents.add(item);
                    latch.countDown();
                    subscription.cancel();
                }

                @Override
                public void onError(final Throwable throwable) {
                    latch.countDown();
                }

                @Override
                public void onComplete() {
                }
            });

            modern.publish("modern-event");
            assertTrue(latch.await(2, TimeUnit.SECONDS));
        }

        assertEquals(List.of("classic-event"), classicEvents);
        assertEquals(List.of("functional-event"), functionalEvents);
        assertEquals(List.of("modern-event"), modernEvents);
    }

    @Test
    void concurrencyPackShouldSupportModernPatterns() throws InterruptedException {
        final List<Integer> consumedAndSum = ConcurrencyPatternsPack.producerConsumerWithVirtualThreads(5);
        assertEquals(List.of(5, 15), consumedAndSum);

        final ConcurrencyPatternsPack.BoundedBackpressureQueue<Integer> bounded =
                new ConcurrencyPatternsPack.BoundedBackpressureQueue<>(1);
        assertTrue(bounded.tryPublish(1, Duration.ofMillis(50)));
        assertEquals(1, bounded.size());
        assertEquals(1, bounded.take());

        final List<ConcurrencyPatternsPack.ImmutableMessage> seen = new ArrayList<>();
        try (ConcurrencyPatternsPack.ThreadConfinedMailbox<ConcurrencyPatternsPack.ImmutableMessage> mailbox =
                     new ConcurrencyPatternsPack.ThreadConfinedMailbox<>(seen::add)) {
            mailbox.submitImmutable(new ConcurrencyPatternsPack.ImmutableMessage("order", "created"));
            Thread.sleep(100);
        }

        assertEquals(1, seen.size());
        assertEquals("order", seen.get(0).topic());
    }
}
