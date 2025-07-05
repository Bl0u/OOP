You've raised an excellent question about static method behavior in inheritance. Let's analyze what's happening in your code example:

Java
public static void testStaticOverriding(){
Person person = new Customer();
person.displayName("Peter", "Emil");
}
Where both Person and Customer classes have static displayName methods, and the output is "From Person".

Why "From Person" is displayed
This behavior occurs because static methods cannot be overridden - they can only be hidden. The key distinction is that static method binding is determined at compile time based on the reference type, not at runtime based on the actual object type.

Important concepts at play here:
Static Method Resolution:

Static methods belong to the class, not to instances
They are resolved based on the compile-time type of the reference, not the runtime type of the object
This is called "static binding" or "early binding"
Method Hiding vs. Overriding:

When a child class defines a static method with the same signature as a static method in its parent class, it's called "hiding" not "overriding"
For instance methods, this would be "overriding" and would use the child's implementation
Your Specific Example:

Person person = new Customer(); - The reference type is Person, but the object is a Customer
When you call person.displayName(), the compiler sees a Person reference
Since static methods are resolved based on the reference type (Person), it calls Person's displayName method
The actual object type (Customer) is irrelevant for static method resolution
Code breakdown:
Java
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
If you want to call Customer's static method, you must use a Customer reference:

Java
Customer customer = new Customer();
customer.displayName("Peter", "Emil");  // Outputs: "From Customer"
Or call it directly on the class:

Java
Customer.displayName("Peter", "Emil");  // Outputs: "From Customer"
Interview Tips on Static Methods
This is an excellent interview topic because it tests understanding of:

Method binding mechanisms - static vs. dynamic binding
Inheritance behavior differences between static and instance methods
The conceptual difference between class members and instance members
When asked about this in an interview, emphasize:

Static methods belong to the class, not objects
Static methods use static binding based on the reference type
The term "hiding" for static methods vs. "overriding" for instance methods
That this is a common source of confusion for developers