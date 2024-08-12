# Singleton Pattern

The Singleton Pattern is one of the simplest design patterns in Java. This type
of design pattern falls under creational patterns and ensures that a class has
only one instance, while providing a global access point to that instance.

## Todo

1. **Lazy Singleton Pattern**:

   * Implement the Singleton pattern using **lazy initialization**: The instance
     should be created only when it is first requested, ensuring efficient
     memory usage.

2. **Double-checked locking**:

   * Declare the instance variable as `volatile` to ensure visibility across
     threads.
   * Modified the `getInstance` to implement **double-checked locking**: Ensure
     that the instance is only synchronized during the initial request, making
     subsequent calls non-blocking and improving performance.