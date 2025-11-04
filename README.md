[![Java CI with Maven](https://github.com/Janaka2/DesignPatterns/actions/workflows/maven.yml/badge.svg)](https://github.com/Janaka2/DesignPatterns/actions/workflows/maven.yml)

# Java Design Patterns

This repository demonstrates a variety of classic **creational**, **structural** and **behavioral** design patterns implemented in Java.  Each example is self‑contained and accompanied by a JUnit test to illustrate expected behaviour.  The goal of this project is educational: explore the differences between patterns, understand when to apply them and see how they can improve the structure of your code.

## About the examples

All source files live in the root of the repository and follow a simple naming convention: each design pattern has a single Java source file (for example, `Singleton.java`) and a corresponding test file (`SingletonTest.java`).  The tests use JUnit 5 and can be run with Maven.

### Creational patterns

Creational patterns encapsulate the logic of object creation.  They hide the details of how objects are created so that code can remain independent from the concrete classes that need to be instantiated.

| Pattern | Description | Implementation / Test |
| --- | --- | --- |
| **Singleton** | Ensures a class has only one instance and provides a global access point to it. | [`Singleton.java`](./Singleton.java) / [`SingletonTest.java`](./SingletonTest.java) |
| **Factory Method** | Defines an interface for creating an object but lets subclasses choose which class to instantiate. | [`FactoryMethod.java`](./FactoryMethod.java) / [`FactoryMethodTest.java`](./FactoryMethodTest.java) |
| **Abstract Factory** | Creates families of related objects without specifying their concrete classes. | [`AbstractFactory.java`](./AbstractFactory.java) / [`AbstractFactoryTest.java`](./AbstractFactoryTest.java) |
| **Builder** | Separates object construction from its representation, allowing different representations to be built in a step‑by‑step manner. | [`Builder.java`](./Builder.java) / [`BuilderTest.java`](./BuilderTest.java) |
| **Prototype** | Specifies the kinds of objects to create using a prototypical instance, and creates new objects by cloning this prototype. | [`Prototype.java`](./Prototype.java) / [`PrototypeTest.java`](./PrototypeTest.java) |

### Structural patterns

Structural patterns compose classes and objects to form larger structures while keeping these structures flexible and efficient.

| Pattern | Description | Implementation / Test |
| --- | --- | --- |
| **Adapter** | Allows classes with incompatible interfaces to work together by wrapping one class with a new interface. | [`Adapter.java`](./Adapter.java) / [`AdapterTest.java`](./AdapterTest.java) |
| **Bridge** | Decouples an abstraction from its implementation so that the two can vary independently. | [`Bridge.java`](./Bridge.java) / [`BridgeTest.java`](./BridgeTest.java) |
| **Composite** | Treats individual objects and compositions of objects uniformly. | [`Composite.java`](./Composite.java) / [`CompositeTest.java`](./CompositeTest.java) |
| **Decorator** | Dynamically attaches additional responsibilities to an object.  Decorators provide a flexible alternative to subclassing for extending functionality. | [`Decorator.java`](./Decorator.java) / [`DecoratorTest.java`](./DecoratorTest.java) |
| **Facade** | Provides a unified interface to a set of interfaces in a subsystem, simplifying its usage. | [`Facade.java`](./Facade.java) / [`FacadeTest.java`](./FacadeTest.java) |
| **Flyweight** | Shares as much data as possible with similar objects to reduce memory usage. | [`Flyweight.java`](./Flyweight.java) / [`FlyweightTest.java`](./FlyweightTest.java) |
| **Proxy** | Provides a surrogate or placeholder for another object to control access to it. | [`Proxy.java`](./Proxy.java) / [`ProxyTest.java`](./ProxyTest.java) |

### Behavioral patterns

Behavioral patterns define common communication patterns between objects and classes.  They help manage complex control flows and responsibilities.

| Pattern | Description | Implementation / Test |
| --- | --- | --- |
| **Chain of Responsibility** | Passes a request along a chain of handlers until one of them handles the request. | [`ChainOfResponsibility.java`](./ChainOfResponsibility.java) / [`ChainOfResponsibilityTest.java`](./ChainOfResponsibilityTest.java) |
| **Command** | Encapsulates a request as an object, allowing clients to parameterize with different requests, queue or log requests, and support undoable operations. | [`Command.java`](./Command.java) / [`CommandTest.java`](./CommandTest.java) |
| **Interpreter** | Represents a grammar and provides an interpreter to process this grammar. | [`Interpreter.java`](./Interpreter.java) / [`InterpreterTest.java`](./InterpreterTest.java) |
| **Iterator** | Provides a way to sequentially access the elements of an aggregate object without exposing its underlying representation. | [`Iterator.java`](./Iterator.java) / [`IteratorTest.java`](./IteratorTest.java) |
| **Mediator** | Defines an object that encapsulates how a set of objects interact, promoting loose coupling by keeping objects from referring to each other explicitly. | [`Mediator.java`](./Mediator.java) / [`MediatorTest.java`](./MediatorTest.java) |
| **Memento** | Captures and externalizes an object's internal state so that it can be restored later. | [`Memento.java`](./Memento.java) / [`MementoTest.java`](./MementoTest.java) |
| **Observer** | Establishes a one‑to‑many dependency so that when one object changes state, all of its dependents are notified and updated automatically. | [`Observer.java`](./Observer.java) / [`ObserverTest.java`](./ObserverTest.java) |
| **State** | Allows an object to alter its behavior when its internal state changes.  The object will appear to change its class. | [`State.java`](./State.java) / [`StateTest.java`](./StateTest.java) |
| **Strategy** | Defines a family of algorithms, encapsulates each one, and makes them interchangeable.  Strategy lets the algorithm vary independently from the clients that use it. | [`Strategy.java`](./Strategy.java) / [`StrategyTest.java`](./StrategyTest.java) |
| **Template Method** | Defines the skeleton of an algorithm in an operation, deferring some steps to subclasses. | [`TemplateMethod.java`](./TemplateMethod.java) / [`TemplateMethodTest.java`](./TemplateMethodTest.java) |
| **Visitor** | Represents an operation to be performed on the elements of an object structure without changing the classes on which it operates. | [`Visitor.java`](./Visitor.java) / [`VisitorTest.java`](./VisitorTest.java) |

## Running the tests

This project uses Maven.  To compile the code and execute all tests, run the following command from the project root:


mvn test
You need a JDK 17 or higher installed on your machine. The project uses JUnit 5 and Mockito for testing; these dependencies are declared in the pom.xml.

## Contributing
If you spot an error or have a suggestion for improvement, feel free to open an issue or submit a pull request. Contributions are welcome!

## Author
I'm Janaka Premathilaka. If you have questions or feedback, you can reach me at janaka2@gmail.com, connect with me on LinkedIn, or give me a call at +41 76 224 84 45.

ruby

If you’d like the file itself, you can still download it here:  :agentCitation{citationIndex='0'}
