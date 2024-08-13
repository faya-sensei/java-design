# Prototype Pattern

The Prototype Pattern is a creational design pattern that optimizes object
creation by allowing objects to be cloned instead of created from scratch. It is
particularly useful when direct object creation is costly, as it provides an
efficient way to generate multiple instances of an object by cloning a prototype.

## Introduction

Loading mesh or texture resources often involves expensive I/O operations. To
improve performance, these resources are typically preloaded into a cache. Minor
modifications, such as generating runtime UUIDs or adjusting positions, can then
be applied without the need for extensive reloading.

## Todo

1. Implement `MeshResource` and `TextureResource` based on `IResource` interface.

2. Develop a standard resource creator based on `IStandardResource` abstract and
   preload all standard resources in the `loadStandardResource` function with
   unique id.