# Phase 3 — Modern Java implementation variants

This phase adds side-by-side examples to show how the same pattern can be implemented in three styles:

1. Classic OO implementation
2. Functional/lambda-first implementation
3. Java 21+ friendly implementation (records, sealed interfaces, switch pattern matching, virtual threads)

## Classic vs Modern Java examples

Package: `org.example.modern_java`

### Strategy
- `StrategyVariants.ClassicCalculator`: interface-based strategy injection.
- `StrategyVariants.FunctionalCalculator`: `IntBinaryOperator` strategy via lambda.
- `StrategyVariants.ModernCalculator`: sealed operation hierarchy (`Add`, `Multiply`) with pattern matching switch.

### Observer
- `ObserverVariants.ClassicSubject`: concrete observer interface list.
- `ObserverVariants.FunctionalSubject`: `Consumer<String>` subscribers.
- `ObserverVariants.ModernPublisher`: `Flow.Publisher` via `SubmissionPublisher`.

### Command
- `CommandVariants.ClassicInvoker`: command objects implementing `execute`.
- `CommandVariants.FunctionalInvoker`: queued `Supplier<String>` commands.
- `CommandVariants.ModernInvoker`: sealed command records and switch dispatch.

## Concurrency patterns pack

Implemented in `ConcurrencyPatternsPack`:

1. **Producer-Consumer with virtual threads**
   - `producerConsumerWithVirtualThreads(int itemCount)` uses `Executors.newVirtualThreadPerTaskExecutor()`.
2. **Bounded queue/backpressure**
   - `BoundedBackpressureQueue<T>` wraps `ArrayBlockingQueue` and provides timed `tryPublish`.
3. **Thread confinement / immutable handoff**
   - `ThreadConfinedMailbox<T>` runs consumption on a dedicated single thread.
   - `ImmutableMessage` record models immutable handoff data.

## Validation

- `ModernJavaVariantsTest` validates all new variants end-to-end.
