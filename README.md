<div align="center">

# Design Pattern

Introduction to the subject of OO Programming

</div>

## Creational Patterns

Creational design patterns provide a method to abstract the instantiation logic
of objects, promoting flexibility in object creation rather than using direct
instantiation with the `new` operator. This makes the program more flexible when
determining which objects need to be created for a given instance.

### 1. Singleton Pattern

The Singleton Pattern is one of the simplest and most well-known design patterns
in Java. It falls under the category of creational patterns and ensures that a
class has only one instance, while providing a global point of access to that
instance.

1. **Lazy Singleton Pattern**:
   * Implement the Singleton pattern using lazy initialization. The instance
     should be created only when it is first requested, optimizing memory usage.

2. **Double-checked locking**:
   * Declare the instance variable as `volatile` to ensure visibility across
     threads.
   * Modify the `getInstance` method to implement double-checked locking, ensure
     that the instance is only synchronized during the initial request,
     improving performance by avoiding synchronization overhead in subsequent
     calls.

### 2. Factory Pattern

The Factory Pattern is one of the most commonly used design patterns in Java.
It provides an approach to object creation that separates the creation process
from the use of the objects.

In the context of language localization, formats like `en-US` for American
English or `en-GB` for British English are common. We can implement a language
loader that loads either the American English or British English instance based
on the input.

1. **Factory and product**:
   * Implement the **abstract product** `ILanguage` interface with classes like
     `AmericanEnglish` and `BritishEnglish` as **concrete product**.

   * Implement a **concrete factory** inherit `ILanguageFamily` interface to
     generate Language objects using country codes like `US` or `GB`.

2. **Factory and abstract factory**:
   * Leverage the **abstract factory** `ILanguageFamilyFactory` to select
     languages based on language codes like `en` or `fr`.
   * Implement a provider to streamline and enhance the object creation process.

### 3. Builder Pattern

The Builder pattern is a creational design pattern that facilitates the
separation of the construction process of a complex object from its final
representation.

This pattern is particularly beneficial when dealing with modules have tightly
coupled attributes or when simplifying JNI interactions. To illustrate this
further, assume `IShader` as an external API call, while `IMaterial` require a
fully constructed shader with initialized content. The `IMaterialBuilder`
simplifies the process by managing both the I/O loading and initial binding
within the builder.

1. Create a mock implementation that inherits from IShader for simulation
   purposes.

2. Implement the **products** `IMaterial` interface and the **Builder**
   `IMaterialBuilder`.

### 4. Prototype Pattern

The Prototype Pattern is a creational design pattern that optimizes object
creation by enabling objects to be cloned instead of created from scratch. It is
particularly useful when direct object creation is expensive. It provides an
efficient way to generate multiple instances of an object by cloning a prototype.

For example, loading mesh or texture resources often involves expensive I/O
operations. To enhance performance, these resources can be preloaded into a
cache. Minor modifications, such as generating runtime UUIDs or adjusting
positions, can then be applied without the need for reloading.

1. Implement `MeshResource` and `TextureResource` based on `IResource` interface.

2. Develop a standard resource creator based on `IStandardResource` abstract and
   preload all standard resources in the `loadStandardResource` function with
   unique id.

## Structural Patterns

These patterns focus on the composition and relationships between objects and
aim to solve how to build flexible and reusable structures of classes and
objects.

### 1. Adapter Pattern

### 2. Bridge Pattern

### 3. Composite Pattern

### 4. Criteria Pattern

### 5. Decorator Pattern

### 6. Facade Pattern

### 7. Flyweight Pattern

### 8. Proxy Pattern

## Behavioral Patterns

These patterns focus on communication and interaction between objects and aim to
solve the distribution of responsibilities and the encapsulation of algorithms
between objects.

### 1. Chain of Responsibility Pattern

The Chain of Responsibility Pattern creates a chain of receiver objects for a
request. This pattern decouples the sender and receiver of the request based on
the type of the request. It is classified as a behavioral design pattern.

### 2. Command Pattern

The Command Pattern is a data-driven design pattern categorized under behavioral
patterns. It primarily addresses the challenge of tight coupling between the
requestor and the executor within software systems, particularly in scenarios
that require behavior to be recorded, undone/redone, or managed through
transactional processing.

### 3. Interpreter Pattern

The Interpreter Pattern provides a mechanism for evaluating a language's grammar
or expressions and is classified as a behavioral design pattern. It is used to
construct an interpreter capable of analyzing and processing sentences based on
a specific language or grammar.

### 4. Iterator Pattern

### 5. Mediator Pattern

### 6. Memento Pattern

### 7. Observer Pattern

### 8. State Pattern

### 9. Null Object Pattern

### 10. Strategy Pattern

### 11. Template Pattern

### 12. Visitor Pattern