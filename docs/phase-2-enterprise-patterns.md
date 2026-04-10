# Phase 2 — Expand from GoF to enterprise patterns

This phase extends the repository beyond classic GoF patterns into modern enterprise resilience, data consistency, and architecture migration patterns.

## Status overview

| Area | Goal | Current implementation | Status |
|---|---|---|---|
| Enterprise integration patterns | Add reliability controls for distributed systems | Added `RetryExecutor`, `CircuitBreaker`, `Bulkhead`, `TimeoutExecutor`, `SimpleRateLimiter`, `IdempotencyKeyStore`, `DeadLetterQueue`, `ExponentialBackoffStrategy` with tests | ✅ Completed |
| Data consistency patterns | Cover transaction boundaries and eventual consistency orchestration | Added `TransactionalOutbox`, `SagaOrchestrator`, `ChoreographySaga`, `UnitOfWork`, `Repository`, `Specification` (+ in-memory examples) with tests | ✅ Completed |
| Architecture patterns | Add modern architecture boundary/migration samples | Added Hexagonal example (`OrderService` + `PaymentPort`), Clean Architecture use case (`SubmitOrderUseCase`), Strangler Fig router, and Anti-Corruption Layer adapter with tests | ✅ Completed |
| Phase tracking updates | Keep phase-state documentation current | Added this Phase 2 tracker and linked it from `README.md` | ✅ Completed |

## Notes

- Implementations are intentionally lightweight and test-oriented to preserve readability.
- Each new pattern includes focused JUnit coverage under `src/test/java/example`.
