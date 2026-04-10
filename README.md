[![Java CI with Maven](https://github.com/Janaka2/DesignPatterns/actions/workflows/maven.yml/badge.svg)](https://github.com/Janaka2/DesignPatterns/actions/workflows/maven.yml)

# Java Design Patterns & Architecture Playground

A practical collection of **design pattern** and **architecture principle** examples in Java, designed to be a reusable reference for day-to-day engineering work.

This project is intentionally optimized for learning-by-reading and learning-by-testing:
- each topic is implemented in focused classes,
- each topic is validated by JUnit tests,
- package structure mirrors conceptual categories.

---

## What is included

### 1) Creational patterns
Package: `src/main/java/org/example/creational`

- **Singleton** (`singalton` legacy package retained for compatibility)
- **Factory Method**
- **Abstract Factory**
- **Builder** (`CheeseBurger`, `VeggieBurger`)
- **Prototype**

### 2) Structural patterns
Package: `src/main/java/org/example/structural`

- **Adapter**
- **Bridge**
- **Composite**
- **Decorator**
- **Facade**
- **Flyweight**
- **Proxy**

### 3) Behavioral patterns
Package: `src/main/java/org/example/behavioral`

- **Chain of Responsibility**
- **Command**
- **Interpreter**
- **Iterator**
- **Mediator**
- **Memento**
- **Observer**
- **State**
- **Strategy**
- **Template Method**
- **Visitor**

### 4) SOLID principles
Package: `src/main/java/org/example/solid`

- **S**: Single Responsibility Principle
- **O**: Open/Closed Principle
- **L**: Liskov Substitution Principle
- **I**: Interface Segregation Principle
- **D**: Dependency Inversion Principle

### 5) Additional architecture/style examples
Packages under `src/main/java/org/example`

- **CQRS** (`command_query_responsibility_segregation`)
- **Event Sourcing** (`event_sourcing_pattern`)
- **Immutable Object Pattern** (`immutable_pattern`)
- **Fluent API Pattern** (`fluent_api_pattern`)
- **Reactive Processing** (`reactive`)

---

## Pattern index matrix

| Pattern | Intent | When to use | Trade-offs | Real-world example | Test class |
|---|---|---|---|---|---|
| Singleton | Ensure one shared instance | Shared in-memory config/cache objects | Harder testing, hidden global state risk | Application configuration registry | `SingletonTest`, `SingletonModernPackageTest` |
| Factory Method | Create objects behind interface | Runtime selection of implementation | Extra abstraction layer | Pluggable exporter/parser creation | `FactoryMethodTest` |
| Abstract Factory | Produce related object families | Multiple UI/platform or region-specific families | More classes/interfaces | Cross-platform UI toolkit | `AbstractFactoryTest` |
| Builder | Step-by-step object construction | Complex object setup with optional fields | Verbose for small objects | HTTP client/request builders | `BuilderTest` |
| Prototype | Clone preconfigured instances | Many similar object instances | Clone correctness complexity | Template documents/shapes | `PrototypeTest` |
| Adapter | Bridge incompatible APIs | Integrating legacy or third-party API | Extra conversion/indirection | Wrapping vendor SDK to app contract | `AdapterTest` |
| Bridge | Decouple abstraction & implementation | Multiple orthogonal variation dimensions | More initial design effort | Theme + platform rendering split | `BridgeTest` |
| Composite | Treat part-whole uniformly | Tree structures (UI, file systems) | Harder to constrain child types | DOM/file hierarchy | `CompositeTest`, `ShapeTest` |
| Decorator | Add behavior dynamically | Cross-cutting features around components | Deep wrapper chains can reduce clarity | Stream wrappers, middleware chain | `DecoratorTest` |
| Facade | Simplify subsystem usage | Expose simple API over complex internals | Can become god-object if overgrown | Payment/booking orchestration façade | `FacadeTest` |
| Flyweight | Share intrinsic state | Large numbers of similar lightweight objects | Requires careful state separation | Glyph rendering pools | `FlyweightTest` |
| Proxy | Control access/lifecycle remotely | Lazy loading, access control, remote calls | Additional latency/complexity | JPA lazy proxies, API client proxy | `ProxyTest` |
| Chain of Responsibility | Pass request through handlers | Rule pipelines, policy chains | Execution flow less explicit | Auth/validation filters | `ChainOfResponsibilityTest` |
| Command | Encapsulate requests as objects | Queueing, retries, undo/redo | Command class proliferation | Job queue and UI actions | `CommandTest` |
| Interpreter | Evaluate language/expressions | Small DSLs and rule expressions | Doesn’t scale for complex grammar | Alert/query expression engine | `InterpreterTest`, `InterpreterTest2` |
| Iterator | Traverse collection without exposing internals | Custom data structure traversal | Usually unnecessary for simple lists | Cursor over custom aggregate | `IteratorTest` |
| Mediator | Centralize interactions | Many-to-many object communication | Mediator can grow too complex | Chat room/event bus coordination | `MediatorTest` |
| Memento | Capture and restore state snapshots | Undo/history features | Memory overhead for snapshots | Document editor undo stack | `MementoTest` |
| Observer | Publish/subscribe notifications | Event-driven in-process updates | Ordering and lifecycle complexity | Domain events, UI listeners | `ObserverTest` |
| State | Change behavior by internal state | Workflow and lifecycle transitions | Many state classes | Order/payment status machine | `StateTest` |
| Strategy | Swap algorithms at runtime | Different pricing/calculation policies | Client must choose strategy | Pricing, routing, ranking policies | `StrategyTest`, `CalculatorTest` |
| Template Method | Fixed algorithm skeleton with variable steps | Shared workflow with custom steps | Inheritance coupling | Data import pipelines | `TemplateMethodTest` |
| Visitor | Add operations without changing element classes | Stable structure, frequently changing operations | Hard to evolve element hierarchy | AST transformations | `VisitorTest` |
| CQRS | Separate write/read models | High-read workloads and audit-friendly systems | Operational complexity and eventual consistency | E-commerce order command/read paths | `CQRSTest` |
| Event Sourcing | Persist state as event sequence | Auditability and temporal debugging | Replay complexity, schema evolution | Ledger-like domain events | `EventSourcingPatternTest` |
| Immutable Object | Prevent mutation after construction | Thread-safe value objects | Requires copy-on-change approach | Money/date/value objects | `ImmutablePatternTest` |
| Fluent API | Improve readability with chained calls | Builder-like DSL and query APIs | Can hide invalid states if not designed well | Query/build DSLs | `FluentApiPatternTest` |
| Reactive Processing | Async stream composition | Backpressure-aware pipelines | Steeper learning curve, debugging complexity | Reactive messaging/data pipelines | `ReactiveProcessorTest` |
| SOLID: SRP | One responsibility per class | Keeping modules cohesive | More classes | Service decomposition | `BookTest`, `BookPrinterTest` |
| SOLID: OCP | Extend behavior without modifying existing code | Plugin/rule additions | Interface/abstraction overhead | Adding new operations safely | `CalculatorTest` |
| SOLID: LSP | Subtypes must substitute base types safely | Inheritance hierarchies | Requires careful contracts | Shape/domain polymorphism | `ShapeTest` |
| SOLID: ISP | Split large interfaces into focused ones | Different clients need different capabilities | More interfaces | Printer/scanner capability split | `MultiFunctionPrinterTest` |
| SOLID: DIP | Depend on abstractions, not concretions | Testable, replaceable dependencies | More wiring/DI setup | Storage abstraction in services | `DependencyInversionTest` |

---

## Project structure

```text
src/
├── main/java/org/example/
│   ├── behavioral/
│   ├── creational/
│   ├── structural/
│   ├── solid/
│   ├── command_query_responsibility_segregation/
│   ├── event_sourcing_pattern/
│   ├── fluent_api_pattern/
│   ├── immutable_pattern/
│   └── reactive/
└── test/java/example/
    └── *Test.java
```

---

## How to run

### Prerequisites
- **JDK 21+**
- **Maven 3.9+**

### Run all tests

```bash
mvn test
```

### Run quality gates (style, bug detection, coverage, tests)

```bash
mvn verify
```

### Run a single test class

```bash
mvn -Dtest=SingletonTest test
```

---

## Package naming standardization plan (`singalton` typo cleanup)

To avoid breaking existing consumers/tests while moving to the corrected package name:

1. **Now (compatible):** keep `org.example.creational.singalton.Singleton` and mark it deprecated.
2. **Now (preferred):** introduce `org.example.creational.singleton.Singleton` for all new usage.
3. **Transition window:** update tests/imports/docs gradually to `singleton` package.
4. **Future major version:** remove `singalton` package.

---

## Build troubleshooting (enterprise/proxy environments)

If Maven fails to download plugins/dependencies (403, timeout, or transfer failed), use one of these fixes.

### 1) Configure proxy in `~/.m2/settings.xml`

```xml
<settings>
  <proxies>
    <proxy>
      <id>corp-proxy</id>
      <active>true</active>
      <protocol>http</protocol>
      <host>proxy.company.local</host>
      <port>8080</port>
      <nonProxyHosts>localhost|127.*|*.company.local</nonProxyHosts>
    </proxy>
  </proxies>
</settings>
```

### 2) Use company mirror (recommended)

```xml
<settings>
  <mirrors>
    <mirror>
      <id>company-mirror</id>
      <name>Company Maven Mirror</name>
      <url>https://artifactory.company.local/maven-public</url>
      <mirrorOf>*</mirrorOf>
    </mirror>
  </mirrors>
</settings>
```

### 3) Diagnose effective config

```bash
mvn -X help:effective-settings
```

### 4) Typical failure causes

- Proxy allows browser traffic but blocks Maven `CONNECT` tunnels.
- Missing TLS/intercept certificate in JDK trust store.
- Mirror URL requires credentials or has permission restrictions.
- Corporate firewall blocks `repo.maven.apache.org` directly.

---

## Learning path (industry demand first)

Priority is based on practical usage frequency in production systems, interview relevance, and long-term value for backend/platform engineers.

1. **SOLID principles (SRP, OCP, LSP, ISP, DIP)**  
   Foundational for maintainable, testable, and scalable code in almost every Java codebase.
2. **Strategy**  
   Extremely common for pricing/routing/business-rule variation and clean replacement of `if/else` chains.
3. **Factory Method + Abstract Factory**  
   Widely used for dependency creation, pluggable modules, and framework extension points.
4. **Builder**  
   High day-to-day value for complex object creation, immutability, and readable APIs.
5. **Observer**  
   Core for event-driven design, domain events, and reactive-style in-process communication.
6. **Adapter**  
   Critical in real systems when integrating third-party SDKs, legacy services, and external APIs.
7. **Decorator**  
   Frequently used for adding cross-cutting behavior (logging, metrics, retries, security) without changing core classes.
8. **Facade**  
   Valuable for simplifying complex subsystems and presenting clean service boundaries.
9. **Command + Chain of Responsibility**  
   Important for workflow pipelines, queue/handler orchestration, middleware/filter chains, and policy processing.
10. **State**  
    Useful in lifecycle-heavy domains (orders, payments, onboarding, approvals).
11. **Proxy**  
    Common in enterprise frameworks (lazy loading, remoting, access control, AOP-like wrapping).
12. **CQRS + Event Sourcing**  
    High market value in distributed/event-heavy systems, though applied in more specialized contexts.
13. **Template Method, Composite, Iterator, Mediator, Memento, Visitor, Bridge, Flyweight, Prototype, Interpreter, Singleton**  
    Important to recognize and understand; used less frequently directly in modern business applications (or often hidden inside frameworks).

---

## Contributing

Contributions are welcome.

If you add a new pattern/example, please also add:
1. a focused test class in `src/test/java/example`, and
2. a README update in the relevant section.

---

## Author

**Janaka Premathilaka**

For feedback or collaboration, please open an issue or pull request.
