# Java `HashMap` Key Behavior: Why Override `equals()` and `hashCode()`?

## Reference Table

- [Abstraction in Java](#abstraction-in-java)
- [Default Behavior of Object Comparison in HashMap](#default-behavior-of-object-comparison-in-hashmap)
- [When Is `equals()` Called in a HashMap?](#when-is-equals-called-in-a-hashmap)
- [Why Override `equals()` and `hashCode()`?](#why-override-equals-and-hashcode)
- [Example 1: HashMap with Default Methods](#example-1-hashmap-with-default-methods)
- [Example 2: HashMap with Overridden Methods](#example-2-hashmap-with-overridden-methods)
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

## Key Takeaways

- **Without Overriding:** HashMap compares keys by memory address (reference), not content.
- **With Overriding:** HashMap compares keys by logical equality (content), which is usually the desired behavior for custom objects.
- **Always override both `equals()` and `hashCode()`** when using objects as keys in hash-based collections.

---