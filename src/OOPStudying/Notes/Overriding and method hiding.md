# Java Static Methods and Inheritance: Method Hiding vs. Overriding

## Table of Contents
- [Introduction](#introduction)
- [Static Method Resolution](#static-method-resolution)
- [Method Hiding vs. Overriding](#method-hiding-vs-overriding)
- [Code Example and Analysis](#code-example-and-analysis)
- [How to Call Child Static Methods](#how-to-call-child-static-methods)
- [Interview Tips on Static Methods](#interview-tips-on-static-methods)
- [Summary](#summary)

---

## Introduction

This document explains the behavior of static methods in Java in the context of inheritance. It provides a clear distinction between **method hiding** (for static methods) and **overriding** (for instance methods), and illustrates the concept with practical code examples.

---

## Static Method Resolution

- **Static methods belong to the class, not to instances.**
- They are resolved at **compile time** based on the **reference type**, not the actual object type.
- This is known as **static binding** or **early binding**.

---

## Method Hiding vs. Overriding

- **Static methods with the same signature in a subclass "hide" the parent method.**
    - This is called **method hiding**, not overriding.
- **Instance methods** with the same signature in a subclass **override** the parent method.
    - This is called **overriding** and is resolved at runtime (dynamic binding).

---

## Code Example and Analysis

```java
class Person {
    public static void displayName(String first, String last) {
        System.out.println("From Person");
    }
}

class Customer extends Person {
    public static void displayName(String first, String last) {
        System.out.println("From Customer");
    }
}

public class TestStatic {
    public static void testStaticOverriding() {
        Person person = new Customer();
        person.displayName("Peter", "Emil"); // Output: "From Person"
    }
}
```

### Why "From Person" is Displayed

- `Person person = new Customer();`
    - The reference type is `Person`, but the object is actually a `Customer`.
- `person.displayName("Peter", "Emil");`
    - The compiler checks the **reference type** (`Person`) for static methods.
    - Therefore, `Person.displayName` is called, not `Customer.displayName`.
    - The output is `"From Person"`.

---

## How to Call Child Static Methods

To call the child (`Customer`) version of the static method, use:

```java
Customer customer = new Customer();
customer.displayName("Peter", "Emil"); // Output: "From Customer"

// Or call directly on the class:
Customer.displayName("Peter", "Emil"); // Output: "From Customer"
```

---

## Interview Tips on Static Methods

- **Static methods belong to the class, not objects**
- **Static method calls are resolved using the reference type (static binding)**
- **"Hiding"** is the term for static methods; **"overriding"** is for instance methods
- This behavior is a common source of confusion and an excellent topic in technical interviews
- Be ready to explain the difference between static and dynamic binding in Java

---

## Summary

- **Static methods cannot be overridden, only hidden.**
- **Static methods are resolved at compile time using the reference type.**
- **Instance methods are overridden and resolved at runtime using the object type.**
- Always call static methods using the class name for clarity.

---
