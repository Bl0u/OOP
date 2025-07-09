# Java HashMap Key Behavior: Why Override `equals()` and `hashCode()`?

## Reference Table
- [Abstraction in Java](#abstraction-in-java)
- [How HashMap Compares Keys](#how-hashmap-compares-keys)
- [Default Behavior Without Overriding](#default-behavior-without-overriding)
- [Why Override `equals()` and `hashCode()`?](#why-override-equals-and-hashcode)
- [Example: HashMap with Default Methods](#example-hashmap-with-default-methods)
- [Example: HashMap with Overridden Methods](#example-hashmap-with-overridden-methods)
- [Summary](#summary)

---

## Abstraction in Java
**Abstraction** is the process of hiding implementation details and showing only the essential features to the user.

---

## How HashMap Compares Keys

When using objects as keys in a `HashMap`, Java internally uses the `hashCode()` and `equals()` methods to determine key uniqueness and equality:

- **`hashCode()`**: Determines which bucket the key will be placed in.
- **`equals()`**: Used to compare keys within the same bucket to check if they are logically equal.

---

## Default Behavior Without Overriding

By default, if you don't override `equals()` and `hashCode()` in your key objects:

- **`hashCode()`**: Returns a value based on the object's memory address.
- **`equals()`**: Compares memory addresses (reference equality), not object content.

This means two objects with the same content, but different instances, will **not** be considered equal and will have different hash codes.

### What Happens in HashMap?

- When you add a key-value pair, `HashMap` uses `hashCode()` to find the bucket.
- If two keys have the same hash code, `equals()` determines if they are the same key.
- Without overrides, only the exact same instance will match.

---

## Why Override `equals()` and `hashCode()`?

**Consistency**: To ensure logically equal objects are treated as the same key.

**Avoiding Errors**: Without proper overrides:
- **Duplicate Keys**: Two objects with identical content will be treated as different keys.
- **Incorrect Retrieval**: You might not be able to retrieve a value with a logically equal key.
- **Collisions**: Even if two objects have the same hash code, if `equals()` is not overridden, the map won’t treat them as equal.

**Best Practice**: Always override both methods if you intend to use instances as keys and want logical equality.

---

## Example: HashMap with Default Methods

```java
import java.util.HashMap;

class Product {
    private String name;
    private double price;

    public Product(String name, double price) {
        this.name = name;
        this.price = price;
    }
    // hashCode() and equals() are inherited from Object
}

public class HashMapTest {
    public static void main(String[] args) {
        Product p1 = new Product("Laptop", 999.99);
        Product p2 = new Product("Laptop", 999.99);

        HashMap<Product, Integer> map = new HashMap<>();
        map.put(p1, 1);  // Add p1
        map.put(p2, 2);  // Add p2

        System.out.println("Map size: " + map.size()); // Output: 2
        System.out.println("p1 == p2: " + (map.get(p1) == map.get(p2))); // Output: false
    }
}
```
**Explanation:**
- Even though `p1` and `p2` have the same content, they are different instances.
- Both are treated as separate keys; both remain in the map.
- Retrieving with `p1` and `p2` gives different results.

---

## Example: HashMap with Overridden Methods

```java
import java.util.HashMap;
import java.util.Objects;

class Product {
    private String name;
    private double price;

    public Product
