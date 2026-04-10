[![Java CI with Maven](https://github.com/Janaka2/DesignPatterns/actions/workflows/maven.yml/badge.svg)](https://github.com/Janaka2/DesignPatterns/actions/workflows/maven.yml)

# Java Design Patterns & Architecture Playground

A clean, practical collection of **design pattern** and **architecture principle** examples in Java.

This project is designed for learning-by-reading and learning-by-testing:
- each topic is implemented in small, focused classes,
- each topic is covered by JUnit tests,
- package structure mirrors conceptual categories.

---

## What is included

### 1) Creational patterns
Package: `src/main/java/org/example/creational`

- **Singleton** (`singalton` package name in current codebase)
- **Factory Method**
- **Abstract Factory**
- **Builder**
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
- **JDK 17+**
- **Maven 3.9+**

### Run all tests

```bash
mvn test
```

### Run a single test class

```bash
mvn -Dtest=SingletonTest test
```

---

## Learning path (suggested)

If you are new to patterns, a practical order is:

1. Singleton, Factory Method, Builder
2. Adapter, Decorator, Facade
3. Strategy, Observer, Command
4. Chain of Responsibility, State, Visitor
5. SOLID + CQRS + Event Sourcing

---

## Notes

- The repository intentionally keeps examples compact to maximize readability.
- In this codebase, the Singleton package is currently named `singalton` (typo preserved for compatibility with existing tests/imports).

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
