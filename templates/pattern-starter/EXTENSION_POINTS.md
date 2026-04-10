# Extension Points

Document how teams should adapt the pattern safely.

## Required extension points

1. **Domain policy hook**
   - Where business validation rules are injected.
2. **Integration adapter hook**
   - Where external system calls are abstracted.
3. **Observability hook**
   - Where tracing/metrics/logging can be added.
4. **Failure strategy hook**
   - Where retry/timeout/circuit-break behavior can be configured.

## Workplace adaptation checklist

- Fintech: add idempotency and immutable audit IDs.
- E-commerce: add timeout budgets and stock consistency rules.
- Healthcare: add privacy filters and access-control checks.
- Logistics: add routing strategy and asynchronous compensation hooks.
