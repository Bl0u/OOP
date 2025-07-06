# OOP Studying - Object-Oriented Programming Concepts

A comprehensive study guide covering fundamental Object-Oriented Programming concepts, focusing on Java implementations with comparisons to other languages.

## Table of Contents
- [Method Overriding](#method-overriding)
- [Static Methods and Overriding](#static-methods-and-overriding)
- [Interfaces vs Abstract Classes](#interfaces-vs-abstract-classes)
- [Abstract Methods Implementation](#abstract-methods-implementation)
- [Access Modifiers and Overriding](#access-modifiers-and-overriding)
- [Polymorphism and Type Casting](#polymorphism-and-type-casting)
- [Interview Tips](#interview-tips)
- [References](#references)

## Method Overriding

### Core Concepts
Method overriding allows a subclass to provide a specific implementation of a method that is already defined in its parent class.

### Rules for Overriding
- **Signature Match**: Name, parameters, and return type must match the parent method
- **Dynamic Method Resolution**: Java picks which method to run based on the actual object type, not the variable type
- **Static Limitation**: Static methods cannot be overridden
- **Annotation Safety**: The `@Override` annotation catches mistakes like typos in method names

### Example
```java
Person person = new Customer(); 
person.whoAmI(); // Calls Customer's whoAmI() method
```

**Important Note**: The parent class (Person) must already implement the called function, otherwise compilation will fail.

## Static Methods and Overriding

### Why Static Methods Can't Be Overridden
- **Virtual Table Mechanism**: Overriding works through the virtual table constructed for virtual functions
- **Class Ownership**: Static methods belong to the class itself, not to any instances from that class
- **Resolution Time**: Static methods are resolved at compile-time, not runtime

## Interfaces vs Abstract Classes

### Key Differences

| Aspect | Interface | Abstract Class |
|--------|-----------|----------------|
| **Implementation** | Cannot contain implementation (except default methods) | Can contain both abstract and concrete methods |
| **Multiple Inheritance** | A class can implement multiple interfaces | A class can extend only one class |
| **Access Modifiers** | Methods are implicitly public | Can have various access modifiers (public, protected, private) |
| **Fields** | Only constants (static final public fields) | Can contain instance variables, static variables, constants |
| **Purpose** | Defines a contract for implementing classes | Provides base implementation for subclasses |

### Interface Features
- **Constants Only**: Any variables in an interface must be `public static final`
- **Default Methods**: Java 8+ allows default implementations in interfaces
- **Static Methods**: Interfaces can contain static methods
- **Evolution Support**: Default methods enable interface evolution without breaking existing implementations

### Abstract Class Capabilities
- **Mixed Implementation**: Can have both abstract methods (no implementation) and concrete methods (with implementation)
- **Constructors**: Can have constructors (called when subclass is instantiated)
- **Instance Variables**: Can contain non-static fields
- **Access Control**: Full range of access modifiers available

## Abstract Methods Implementation

### Implementation Requirements
**Abstract methods are NOT optional for concrete child classes.**

#### Rules:
1. **Concrete Classes**: Must implement ALL abstract methods from parent
2. **Abstract Child Classes**: Can choose not to implement abstract methods
3. **Compilation**: Failure to implement abstract methods results in compiler error

#### Implementation vs Overriding
- **Implementation**: Providing a body for a method that had none
- **Overriding**: Replacing an existing implementation with a new one

## Access Modifiers and Overriding

### Private Methods
```java
// In Person class
private void test() // Cannot be overridden in Person's children

// In Person class  
void test() // Can be overridden, this format means it's accessable only within any caller from same package
```

**Rule**: Private methods cannot be overridden because they are not accessible to subclasses.

## Polymorphism and Type Casting

### Reference Types and Object Types
```java
Interface interface = new Class();
Class class = new Class();
```

Both variables store objects that implement all methods from both `Interface` and `Class`.

### Method Availability
```java
Person person = new Customer();
person.display(); // Compiler error if display() not in Person even if it does exist in Customer class
```

**Key Point**: The compiler checks method availability based on the reference type (left side), not the object type (right side).

## Language Differences

### Java vs C++
- **Operator Overloading**: Java doesn't support operator overloading like C++
- **Multiple Inheritance**: Java supports multiple interface implementation but not multiple class inheritance
- **Virtual Functions**: Java uses dynamic dispatch by default for instance methods

## Interview Tips

### Common Questions
1. **When to use Abstract Class vs Interface?**
    - Abstract class: Share code among closely related classes
    - Interface: Define contracts for unrelated classes

2. **Can abstract classes implement interfaces?**
    - Yes, and they don't need to implement interface methods immediately

3. **Can abstract classes have constructors?**
    - Yes, constructors are called when subclasses are instantiated

4. **Interface evolution in Java 8+**
    - Default methods enable backward compatibility
    - Static methods provide utility functions
5. **Why Java isn't 100% object-oriented?**
   - Because it has primitive data types 'discbblf':
      - double
      - int
      - string
      - boolean
      - byte
      - long
      - float
6. **Why pointers aren't used in Java?**
   - It raises the complexity of the program which directly contradicts Java's intention of maintaining simple code at all times
   - Also JDK is responsible for all memory allocation, so allowing the user to use pointers violates this responsibility
7. **Can you override static methods?**
   - No, static methods cannot be overridden, only hidden
   - When a child class defines a static method with the same signature as a static method in its parent class, it's called "hiding" not "overriding"
   - For instance methods, this would be "overriding" and would use the child's implementation
   - Example: When `Person person = new Customer();` calls `person.displayName()`, the compiler sees a Person reference
   - Since static methods are resolved based on the reference type (Person), it calls Person's displayName method
   - The actual object type (Customer) is irrelevant for static method resolution
   - For detailed explanation, refer to the "Method Hiding" section above

### Key Points to Remember
- Interface variables are `public static final` by default
- Default methods allow interface evolution without breaking existing code
- Abstract classes can have instance variables and constructors
- Static methods cannot be overridden due to compile-time resolution

## References
- [GeeksforGeeks - Java OOP Concepts](https://www.geeksforgeeks.org/java/object-oriented-programming-oops-concept-in-java/)
- [GeeksforGeeks - OOP Questions](https://www.geeksforgeeks.org/oops-interview-questions/)
- [InterviewBit - OOP Interview Questions](https://www.interviewbit.com/oops-interview-questions/)
- [Java Questions](https://www.youtube.com/watch?v=PwiuAebCruY&list=PLyHJZXNdCXscoyL5XEZoHHZ86_6h3GWE1&index=1)
- 