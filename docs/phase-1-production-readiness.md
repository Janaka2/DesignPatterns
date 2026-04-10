# Phase 1 — Production-ready reference baseline

This tracker converts the initial improvement plan into concrete repository checks.

## Status overview

| Area | Goal | Current implementation | Status |
|---|---|---|---|
| Pattern index matrix | Keep pattern intent/usage/trade-off lookup in one place | Matrix in `README.md` with pattern + test mapping | ✅ Done |
| Package naming standardization | Migrate `singalton` typo safely | Deprecated legacy package + preferred `singleton` package + migration policy in README | ✅ In progress (compatibility mode) |
| Quality gates baseline | Enforce style, static analysis, tests, and coverage in CI | Checkstyle + SpotBugs + JaCoCo in `pom.xml`; CI runs `mvn verify` | ✅ Done |
| Environment portability docs | Help enterprise/proxy users build reliably | Proxy/mirror troubleshooting section in README | ✅ Done |
| Runtime baseline gate | Fail fast on unsupported toolchain | Maven Enforcer requires Java 17+ and Maven 3.9+ | ✅ Done |

## Remaining work for typo migration (`singalton` → `singleton`)

1. Keep both packages for the current major version.
2. Update existing imports in examples/tests to the new `singleton` package gradually.
3. Add a release-note warning before removal.
4. Remove `singalton` package in next major release.

## Suggested entry criteria for Phase 2

- Raise JaCoCo line coverage threshold from 35% to at least 50%.
- Add package-level/module-level coverage rules for critical packages.
- Add dependency/license scanning (for example, OWASP Dependency-Check).
