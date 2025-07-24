# Java `HashMap` Key Behavior: Why Override `equals()` and `hashCode()`?

## Reference Table

- [Abstraction in Java](#abstraction-in-java)
- [Default Behavior of Object Comparison in HashMap](#default-behavior-of-object-comparison-in-hashmap)
- [When Is `equals()` Called in a HashMap?](#when-is-equals-called-in-a-hashmap)
- [Why Override `equals()` and `hashCode()`?](#why-override-equals-and-hashcode)
- [Example 1: HashMap with Default Methods](#example-1-hashmap-with-default-methods)
- [Example 2: HashMap with Overridden Methods](#example-2-hashmap-with-overridden-methods)
- [Random topics](#random)
- [Key Takeaways](#key-takeaways)

---

## Abstraction in Java

**Abstraction** is the process of hiding implementation details and showing only the essential features to the user.  
This concept helps focus on what an object does instead of how it does it. also The abstract keyword is a non-access modifier, used for classes and methods

---

## Default Behavior of Object Comparison in HashMap

By default, a `HashMap` in Java compares two objects using their memory address (reference comparison) via the `equals()` method inherited from `Object`.  
This checks if two objects are the *exact same instance* in memory, not if their contents are equal.

### Behavior

- **hashCode()**: Returns a value based on the object's memory address.
- **equals()**: Compares memory addresses (reference equality), not object content.

**Result:**  
Two objects with the same content but different instances are *not* considered equal and will have different hash codes.  
Thus, they will be treated as different keys in a `HashMap`.

---

## When Is `equals()` Called in a HashMap?

The `equals()` method is called when:

- Checking if a key exists: `map.containsKey(key)`
- Inserting a new key-value pair: `map.put(key, value)` (checks if the key already exists)
- Removing an entry by key: `map.remove(key)`

---

## Why Override `equals()` and `hashCode()`?

Overriding these methods ensures consistent behavior in hash-based collections like `HashMap`.

### Reasons

- **Consistency:** Logically equal objects are treated as the same key.
- **Default Behavior Issue:** Without overrides, keys are compared by memory address. Two objects with the same content are considered different keys.
- **Avoiding Errors:**
    - *Incorrect Key Matching*: Two different instances with the same content are different keys.
    - *Collisions*: Even when two keys hash to the same bucket, correct equality checks are required for key matching.
- **Collision Handling:** If two keys land in the same hash bucket, overriding `equals()` ensures proper comparison within that bucket.
- **Best Practice:** Always override both methods when using custom objects as keys.

---

## Example 1: HashMap with Default Methods

```java
import java.util.HashMap;

class Product {
    private String name;
    private double price;

    public Product(String name, double price) {
        this.name = name;
        this.price = price;
    }
    // Default hashCode() and equals() from Object
}

public class HashMapTest {
    public static void main(String[] args) {
        Product p1 = new Product("Laptop", 999.99);
        Product p2 = new Product("Laptop", 999.99);

        HashMap<Product, Integer> map = new HashMap<>();
        map.put(p1, 1);
        map.put(p2, 2);

        System.out.println("Map size: " + map.size()); // Output: 2
        System.out.println("p1 == p2 value: " + (map.get(p1) == map.get(p2))); // Output: false
    }
}
```

**Explanation:**
- `p1` and `p2` have identical content but are different instances.
- Both are treated as separate keys.
- Map size is 2, and retrieval with each key gives different results.

---

## Example 2: HashMap with Overridden Methods

```java
import java.util.HashMap;
import java.util.Objects;

class Product {
    private String name;
    private double price;

    public Product(String name, double price) {
        this.name = name;
        this.price = price;
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, price); // hash based on content
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Product other = (Product) obj;
        return Double.compare(other.price, price) == 0 &&
               Objects.equals(name, other.name);
    }
}

public class HashMapTest {
    public static void main(String[] args) {
        Product p1 = new Product("Laptop", 999.99);
        Product p2 = new Product("Laptop", 999.99);

        HashMap<Product, Integer> map = new HashMap<>();
        map.put(p1, 1);
        map.put(p2, 2); // Overwrites value for key 'Laptop', 999.99

        System.out.println("Map size: " + map.size()); // Output: 1
        System.out.println("Value for p1: " + map.get(p1)); // Output: 2
        System.out.println("Value for p2: " + map.get(p2)); // Output: 2
    }
}
```

**Explanation:**
- Overridden `hashCode()` and `equals()` use content (name, price).
- `p1` and `p2` are considered equal.
- `map.put(p2, 2)` overwrites the value for the existing key.
- Map size is 1, and both keys retrieve the same value.

---

## Random Questions
- What is the purpose of the @Override annotation in Java?
    - it's used to tell the compiler that this method is meant to override a method in superclass or interface
- What is method hiding
    - Method hiding happens when a subclass defines a static method with the same signature as a static method in its superclass.
- Can you override a static method?
    - no, static isn't polymorphic, they are resolved in compile time not runtime
- Can you override a private method?
    - No. Private methods are not visible to subclasses, so they can't be overridden.
- Does @Override have any effect at runtime?
    - No. It's a compile-time annotation. It has no effect on how the program behaves at runtime.
- What is Map in java?
    - it's a collection of key-value pairs
- Why Map is an interface
    - so the implementation can be swapped later easily (HashMap, TreeMap)
- What is `instanceof` in Java?
    
    - The `instanceof` operator is a runtime operator used to check whether a reference points to an object of a specific type (class, subclass, or interface).
      - s is a reference variable (the thing you have on the left side of an assignment, or just a variable that holds a reference to an object).
      - t is a type (a class, interface, or an array type).
    - It returns `true` **only if**:
        - The reference is **not null**, and
        - The object can be safely cast to the specified type without causing a `ClassCastException`.
    
    **Syntax Example:**
    ```java
    Object obj = "Hello, world!";
    System.out.println(obj instanceof String); // ✅ true
    String s = (String) obj;                   // ✅ this works fine
    ```
    
    ### Type Compatibility Rules for `instanceof`
    
    | Scenario                       | Condition for `instanceof` to be `true`            |
    |--------------------------------|----------------------------------------------------|
    | `s` is a class, `t` is a class | `s` must be a subclass of `t` (or the same class)  |
    | `s` is an interface, `t` is a class | `t` must be an object that implements interface `s`      |
    | `s` is a class, `t` is an interface | `s` must implement interface `t`                     |
    | `s` is an interface, `t` is an interface | `t` must be a superinterface of `s`                     |
    
    **Notes:**
    - If the reference is `null`, `instanceof` always returns `false`.
    - `instanceof` helps prevent `ClassCastException` by allowing you to check type compatibility before casting.
    
    **Example with interfaces and classes:**
    ```java
    interface Vehicle {}
    class Car implements Vehicle {}
    
    Vehicle v = new Car();
    System.out.println(v instanceof Car);      // true
    System.out.println(v instanceof Vehicle);  // true
    System.out.println(v instanceof Object);   // true
    System.out.println(v instanceof String);   // false
    ```
- What happens internally when using instanceof?
    - At runtime, the JVM uses the object’s actual class and compares it to the class/interface specified using the internal type hierarchy
- What is the difference between data hiding and encapsulation?
    - Data hiding refers to restricting access to internal object details using access modifiers like private, protected, and public. The goal is to prevent             external interference and protect the integrity of the object.
      Encapsulation is the broader OOP (Object-Oriented Programming) principle of bundling data (fields) and methods that operate on that data into a single unit        usually a class. It enables modularity and enforces data hiding.

---
