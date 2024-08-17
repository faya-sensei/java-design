
## Creational Patterns

These design patterns provide a way to hide the creation logic of objects while
creating them, rather than directly instantiating objects using the `new`
operator. This makes the program more flexible when determining which objects
need to be created for a given instance.

### 1. Singleton Pattern

The Singleton Pattern is one of the simplest design patterns in Java. This type
of design pattern falls under creational patterns and ensures that a class has
only one instance, while providing a global access point to that instance.

1. **Lazy Singleton Pattern**:
   * Implement the Singleton pattern using lazy initialization: The instance
     should be created only when it is first requested, ensuring efficient
     memory usage.

2. **Double-checked locking**:
   * Declare the instance variable as `volatile` to ensure visibility across
     threads.
   * Modified the `getInstance` to implement double-checked locking: Ensure
     that the instance is only synchronized during the initial request, making
     subsequent calls non-blocking and improving performance.

### 2. (Abstract) Factory Pattern

The Factory Pattern is one of the most commonly used design patterns in Java.
It provides a way to create objects, separating the process of object creation
from the process of using the objects.

In language localization, we often encounter formats like `en-US` for American
English or `en-GB` for British English. Here, we will implement a language
loader that correctly loads either the American English or British English
instance based on the given input.

1. **Factory and product**:
   * Implement the **abstract product** `ILanguage` interface with classes like
     `AmericanEnglish` and `BritishEnglish` as **concrete product**.

   * Implement the **concrete factory** inherit `ILanguageFamily` interface to
     generate Language objects using country codes like `US` or `GB`.

2. **Factory and abstract factory**:
   * Leverage the **abstract factory** `ILanguageFamilyFactory` to select
     languages based on language codes like `en` or `fr`.
   * Implement a provider to streamline and enhance the object creation process.

### 3. Builder Pattern

The Builder pattern is a creational design pattern that facilitates the
separation of the construction process of a complex object from its final
representation.

This pattern is especially useful when modules have tightly coupled attributes
or when simplifying JNI interactions. In such cases, the Builder serves as a
wrapper to manage JNI method construction while encapsulating complexity. To
illustrate this further, let us assume that `IShader` represents an external API
call. In this context, `IMaterial` serve as the **products**, `IMaterialBuilder`
serve as the **Builder**.

### 4. Prototype Pattern

The Prototype Pattern is a creational design pattern that optimizes object
creation by allowing objects to be cloned instead of created from scratch. It is
particularly useful when direct object creation is costly, as it provides an
efficient way to generate multiple instances of an object by cloning a prototype.

Loading mesh or texture resources often involves expensive I/O operations. To
improve performance, these resources are typically preloaded into a cache. Minor
modifications, such as generating runtime UUIDs or adjusting positions, can then
be applied without the need for extensive reloading.

1. Implement `MeshResource` and `TextureResource` based on `IResource` interface.

2. Develop a standard resource creator based on `IStandardResource` abstract and
   preload all standard resources in the `loadStandardResource` function with
   unique id.

## Structural Patterns

These patterns focus on the composition and relationships between objects and
aim to solve how to build flexible and reusable structures of classes and
objects.

## Behavioral Patterns

These patterns focus on communication and interaction between objects and aim to
solve the distribution of responsibilities and the encapsulation of algorithms
between objects.