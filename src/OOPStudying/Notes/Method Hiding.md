# Static Method Behavior in Inheritance

## Understanding Method Hiding vs. Overriding

This document explores how static methods behave in inheritance scenarios and explains the concept of method hiding.

## Example Code

```java
public static void testStaticOverriding() {
    Person person = new Customer();
    person.displayName("Peter", "Emil"); // Outputs: "From Person"
}

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
```

## Why "From Person" is displayed

This behavior occurs because static methods cannot be overridden - they can only be hidden. The key distinction is that static method binding is determined at compile time based on the reference type, not at runtime based on the actual object type.

### Important concepts at play:

#### Static Method Resolution:
- Static methods belong to the class, not to instances
- They are resolved based on the compile-time type of the reference, not the runtime type of the object
- This is called "static binding" or "early binding"

#### Method Hiding vs. Overriding:
- When a child class defines a static method with the same signature as a static method in its parent class, it's called "hiding" not "overriding"
- For instance methods, this would be "overriding" and would use the child's implementation

#### This Specific Example:
- `Person person = new Customer();` - The reference type is Person, but the object is a Customer
- When you call `person.displayName()`, the compiler sees a Person reference
- Since static methods are resolved based on the reference type (Person), it calls Person's displayName method
- The actual object type (Customer) is irrelevant for static method resolution

## How Method Hiding Works

Method hiding occurs when a subclass defines a static method with the same signature as a static method in its parent class.

## Contrast with Instance Method Overriding

To highlight the difference, if displayName were an instance method:

```java
class Person {
    public void displayName(String first, String last) { // Non-static
        System.out.println("From Person");
    }
}

class Customer extends Person {
    @Override
    public void displayName(String first, String last) { // Non-static
        System.out.println("From Customer");
    }
}

// Now when we run:
Person person = new Customer();
person.displayName("Peter", "Emil"); // This would output: "From Customer"
```

With instance methods, the JVM uses dynamic dispatch to determine which method to call based on the actual object type at runtime.

## Why Static Methods Work This Way

Static methods work this way because they belong to the class, not to instances. They're not part of the object's behavior but part of the class's behavior. Since inheritance is about object behavior, static methods don't participate in inheritance the same way instance methods do.