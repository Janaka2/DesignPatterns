# Phase 4 — Cloud-native + observability examples

This phase adds observability-first examples and deployment-ready scaffolding for running selected pattern demos in containerized environments.

## Observability-first pattern examples

Package: `org.example.cloudnative.observability`

### 1) Command latency trace
- `ObservedCommandExecutor` wraps command execution with an OpenTelemetry span (`command.execute`).
- Records latency metric `pattern.command.latency.ms` to make command performance visible in dashboards.

### 2) Circuit breaker metrics
- `ObservedCircuitBreaker` wraps `CircuitBreaker` and emits counter metric `pattern.circuit_breaker.executions`.
- Counter outcome labels: `success`, `failure`, `rejected` (open circuit).

### 3) Saga correlation IDs
- `CorrelatedSagaOrchestrator` accepts a `correlationId` and propagates it through:
  - saga orchestration span,
  - per-step action span,
  - compensation spans on rollback.
- `CorrelatedSagaStep` keeps saga step contracts explicit and correlation-aware.

## Resilience test scenarios

`ResilienceFaultInjectionTest` introduces fault injection via **Testcontainers + WireMock**:

- timeout behavior (`/timeout` fixed delay)
- partial/transient failures (`/inventory` stateful scenario)
- retries via `RetryExecutor`

This verifies that resilience patterns remain understandable under stress and failure conditions.

## Deployment-ready examples

Minimal deployment assets are included under `deploy/`:

- `deploy/docker-compose.yml`
  - command observability demo
  - resilience fault demo
  - WireMock dependency simulator
  - OpenTelemetry Collector + Jaeger
- `deploy/k8s/command-observability-demo.yaml`
- `deploy/k8s/resilience-fault-demo.yaml`

These manifests are intentionally minimal so teams can copy them as a starting point for real service demos.
