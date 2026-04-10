# Phase 5 — AI-era additions (high practical value)

This phase introduces practical AI application patterns that teams can use to structure prompt workflows, retrieval pipelines, model orchestration, policy enforcement, and repeatable evaluation.

## AI application patterns module

Package: `org.example.ai`

### 1) Prompt Builder pattern
- `PromptBuilder` composes reusable prompt templates with parameter placeholders.
- Keeps prompt assembly explicit and testable.

### 2) Retrieval pipeline (RAG-lite) orchestration
- `RetrievalPipeline` separates retrieval and reranking steps.
- Builds bounded context windows suitable for lightweight RAG flows.

### 3) Tool/Agent orchestration facade
- `ToolAgentFacade` exposes one entrypoint for tool calls and LLM-based responses.
- Supports a simple `tool:<name>:<input>` command route plus LLM answering path.

### 4) Fallback model strategy
- `FallbackModelStrategy` tries model clients in order.
- Moves to the next model when previous responses fail or return empty output.

### 5) Policy & safety wrappers
- `PolicyEnforcedLlmClient` decorates `LlmClient` with:
  - input validation (`NonEmptyInputPolicy`, `MaxLengthInputPolicy`),
  - PII masking (`PiiMaskingInputPolicy`),
  - output filtering (`BasicOutputFilterPolicy`).

### 6) Evaluation harness
- `EvaluationHarness` provides:
  - deterministic golden set checks (accuracy + failure diagnostics),
  - statistical score aggregation (average/min/max).

## Validation

`AiApplicationPatternsTest` demonstrates end-to-end usage of:
- prompt + RAG-lite + facade orchestration,
- fallback model behavior,
- policy/safety wrappers,
- deterministic and statistical evaluation patterns.
