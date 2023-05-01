# Java Design Patterns

This repository contains examples of Java design patterns along with their unit tests, categorized into Creational, Structural, and Behavioral patterns.

## Creational Patterns

Creational design patterns are concerned with the process of object creation. They simplify object creation by encapsulating object creation logic.

1. [Singleton](./Singleton.java) - Ensures that a class has only one instance and provides a global point of access to that instance. ([Unit Test](./SingletonTest.java))
2. [Factory Method](./FactoryMethod.java) - Defines an interface for creating an object, but lets subclasses decide which class to instantiate. ([Unit Test](./FactoryMethodTest.java))
3. [Abstract Factory](./AbstractFactory.java) - Provides an interface for creating families of related or dependent objects without specifying their concrete classes. ([Unit Test](./AbstractFactoryTest.java))
4. [Builder](./Builder.java) - Separates the construction of a complex object from its representation, allowing the same construction process to create various representations. ([Unit Test](./BuilderTest.java))
5. [Prototype](./Prototype.java) - Specifies the kind of objects to create using a prototypical instance and creates new objects by cloning this prototype. ([Unit Test](./PrototypeTest.java))

## Structural Patterns

Structural design patterns are concerned with class and object composition. They use inheritance and composition to form larger structures.

1. [Adapter](./Adapter.java) - Allows classes with incompatible interfaces to work together by wrapping its own interface around that of an already existing class. ([Unit Test](./AdapterTest.java))
2. [Bridge](./Bridge.java) - Decouples an abstraction from its implementation, enabling both to vary independently. ([Unit Test](./BridgeTest.java))
3. [Composite](./Composite.java) - Composes zero or more similar objects so that they can be manipulated as one object. ([Unit Test](./CompositeTest.java))
4. [Decorator](./Decorator.java) - Attaches additional responsibilities to an object dynamically. ([Unit Test](./DecoratorTest.java))
5. [Facade](./Facade.java) - Provides a simplified interface to a larger body of code. ([Unit Test](./FacadeTest.java))
6. [Flyweight](./Flyweight.java) - Reduces the number of objects created and minimizes memory usage by sharing as much data as possible with other similar objects. ([Unit Test](./FlyweightTest.java))
7. [Proxy](./Proxy.java) - Provides a surrogate or placeholder for another object to control access to it. ([Unit Test](./ProxyTest.java))

## Behavioral Patterns

Behavioral design patterns define the communication patterns between objects.

1. [Chain of Responsibility](./ChainOfResponsibility.java) - Passes a request along a chain of handlers until one of them handles the request. ([Unit Test](./ChainOfResponsibilityTest.java))
2. [Command](./Command.java) - Encapsulates a request as an object, allowing you to parameterize clients with different requests, queue or log requests, and support undoable operations. ([Unit Test](./CommandTest.java))
3. [Interpreter](./Interpreter.java) - Defines a representation for a grammar and provides an interpreter to deal with this grammar. ([Unit Test](./InterpreterTest.java))
4. [Iterator](./Iterator.java) - Provides a way to access the elements of an aggregate object sequentially without exposing its underlying representation. ([Unit Test](./IteratorTest.java))
5. [Mediator](./Mediator.java) - Defines an object that encapsulates how a set of objects interact, promoting loose coupling by keeping objects from referring to each other explicitly. (Unit Test)
6. Memento - Captures and externalizes an object's internal state so that the object can be restored to this state later. (Unit Test)

Observer - Defines a one-to-many dependency between objects so that when one object changes state, all its dependents are notified and updated automatically. (Unit Test)
State - Allows an object to alter its behavior when its internal state changes. The object will appear to change its class. (Unit Test)
Strategy - Defines a family of algorithms, encapsulates each one, and makes them interchangeable. Strategy lets the algorithm vary independently from the clients that use it. (Unit Test)
Template Method - Defines the skeleton of an algorithm in an operation, deferring some steps to subclasses. Template Method lets subclasses redefine certain steps of an algorithm without changing the algorithm's structure. (Unit Test)
Visitor - Represents an operation to be performed on the elements of an object structure without changing the classes on which it operates. (Unit Test)
Feel free to explore the examples and unit tests to gain a better understanding of each design pattern. Remember that these tests are basic examples and may not cover all possible scenarios. Adjust and expand them according to your specific requirements and use cases.

Singleton
Singleton.java
SingletonTest.java
Factory Method
FactoryMethod.java
FactoryMethodTest.java
Abstract Factory
AbstractFactory.java
AbstractFactoryTest.java
Builder
Builder.java
BuilderTest.java
Prototype
Prototype.java
PrototypeTest.java
Adapter
Adapter.java
AdapterTest.java
Bridge
Bridge.java
BridgeTest.java
Composite
Composite.java
CompositeTest.java
Decorator
Decorator.java
DecoratorTest.java
Facade
Facade.java
FacadeTest.java
Flyweight
Flyweight.java
FlyweightTest.java
Proxy
Proxy.java
ProxyTest.java
Chain of Responsibility
ChainOfResponsibility.java
ChainOfResponsibilityTest.java
Command
Command.java
CommandTest.java
Interpreter
Interpreter.java
InterpreterTest.java
Iterator
Iterator.java
IteratorTest.java
Mediator
Mediator.java
MediatorTest.java
Memento
Memento.java
MementoTest.java
Observer
Observer.java
ObserverTest.java
State
State.java
StateTest.java
Strategy
Strategy.java
StrategyTest.java
Template Method
TemplateMethod.java
TemplateMethodTest.java
Visitor
Visitor.java
VisitorTest.java