# Phase 6 — Documentation UX for workplace reuse

This phase makes the repository faster to navigate and reuse in real delivery teams by adding decision guides, domain-first recipes, and copy-paste starter templates.

## Status overview

| Area | Goal | Deliverable | Status |
|---|---|---|---|
| Pattern decision trees | Help engineers answer “Choose this pattern when…” quickly | Decision trees for API/backend, data pipelines, event-driven systems, AI-powered systems | ✅ Completed |
| Quick decision guides by domain | Reduce choice paralysis for common system types | Domain-specific pattern shortlist + anti-pattern warnings | ✅ Completed |
| Copy-paste starter templates | Standardize how new pattern examples are added | Starter template bundle with README + code + test + extension points | ✅ Completed |
| Domain recipes | Show adaptation to enterprise verticals | Fintech, e-commerce, healthcare, logistics tailoring playbooks | ✅ Completed |

---

## Choose this pattern when… (decision trees)

### 1) API/backend systems

1. Need to protect unstable dependencies?
   - Yes → Start with **Circuit Breaker + Retry + Timeout + Backoff + Idempotency Key**.
2. Need clean integration boundaries?
   - Yes → Prefer **Hexagonal Architecture** + **Repository**.
3. Need read/write scaling and separate models?
   - Yes → Add **CQRS** (and optionally **Event Sourcing** if full audit/time-travel is required).
4. Need phased migration from legacy API/domain?
   - Yes → Use **Strangler Fig** + **Anti-Corruption Layer**.

### 2) Data pipelines

1. Need high-throughput asynchronous flow?
   - Yes → **Reactive Processing** + **Bulkhead** isolation.
2. Need reliable downstream event publication from database state changes?
   - Yes → **Transactional Outbox**.
3. Need compensating actions across multiple processing stages?
   - Yes → **Saga** (orchestration first, choreography when domains are stable).
4. Need filter/composition logic across many business rules?
   - Yes → **Specification** + **Strategy**.

### 3) Event-driven systems

1. Need in-process broadcast to multiple listeners?
   - Yes → **Observer**.
2. Need durable event history/audit replay?
   - Yes → **Event Sourcing**.
3. Need message failure handling at scale?
   - Yes → **Dead Letter Queue** + **Retry with Backoff**.
4. Need idempotent at-least-once delivery handling?
   - Yes → **Idempotency Key Store** + explicit dedupe checks.

### 4) AI-powered systems

1. Need consistent prompt construction?
   - Yes → **Prompt Builder**.
2. Need grounding via enterprise knowledge?
   - Yes → **Retrieval Pipeline (RAG-lite)**.
3. Need tool routing and unified AI entrypoint?
   - Yes → **Tool/Agent Facade**.
4. Need robustness across model outages/quality variability?
   - Yes → **Fallback Model Strategy**.
5. Need policy/safety controls and repeatable quality scoring?
   - Yes → **Policy-Enforced LLM Client** + **Evaluation Harness**.

---

## Quick decision guides by domain

### API/backend

**Start here:**
- Reliability baseline: Retry, Timeout, Circuit Breaker, Idempotency Key.
- Architecture baseline: Hexagonal + Repository.
- Migration baseline: Strangler + ACL.

**Avoid:**
- Introducing CQRS/Event Sourcing before reliability + observability are in place.

### Data pipelines

**Start here:**
- Reactive Processing, Transactional Outbox, Bulkhead, DLQ.
- Add Saga when pipeline steps cross service or data ownership boundaries.

**Avoid:**
- Choreography-first Sagas without clear event contracts and trace IDs.

### Event-driven systems

**Start here:**
- Observer (local), Outbox + DLQ (distributed), Idempotency (delivery safety).
- Prefer explicit schema/version policy for events.

**Avoid:**
- Assuming exactly-once delivery semantics without deduplication design.

### AI-powered systems

**Start here:**
- Prompt Builder + Retrieval Pipeline + Policy-Enforced LLM Client.
- Add Fallback Model Strategy for availability.
- Use Evaluation Harness to gate prompt/model changes.

**Avoid:**
- Shipping AI flows without policy checks, redaction, and offline evaluation sets.

---

## Copy-paste starter templates

Use `templates/pattern-starter/` as the canonical scaffold whenever introducing a new pattern sample.

Template contents:
- `README.md` — pattern intent, when to use, trade-offs, and rollout guidance.
- `src/main/java/.../PatternTemplate.java` — minimal production-style implementation.
- `src/test/java/.../PatternTemplateTest.java` — executable behavior checks.
- `EXTENSION_POINTS.md` — explicit customization hooks for workplace adaptation.

This standard is intended to be replicated **for every new pattern contribution**.

---

## Domain recipes (adaptation playbooks)

### Fintech
- Prioritize: Idempotency Key, Event Sourcing, Saga, Retry with bounded backoff.
- Add: strict audit trails, PII masking policies, deterministic evaluation harnesses for AI-assisted decisions.
- Guardrails: monetary operations must be replay-safe and duplicate-resistant.

### E-commerce
- Prioritize: CQRS, Saga orchestration, Circuit Breaker, Cache-friendly read models.
- Add: inventory reservation compensation strategies, burst traffic rate limits.
- Guardrails: protect checkout path with latency budgets and timeout defaults.

### Healthcare
- Prioritize: ACL, Repository + Specification, Policy-Enforced AI wrappers.
- Add: traceability and privacy-first defaults (PII masking, least-privilege data flow).
- Guardrails: immutable audit events and deterministic rollback/compensation for critical workflows.

### Logistics
- Prioritize: Event-driven choreography/orchestration, Bulkhead, DLQ, Retry.
- Add: geo/route strategy modules and state-machine based workflow transitions.
- Guardrails: isolate partner integration failures and preserve eventually consistent shipment state.

---

## Suggested acceptance criteria for this phase

- Domain leads can select a starting pattern set in under 5 minutes.
- New contributors can scaffold a pattern example from template assets in under 15 minutes.
- Reviewers can verify extension points and tests exist before approving new pattern additions.
