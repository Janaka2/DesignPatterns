package org.example.fluent_api_pattern;

/**
 * Demonstrates a small fluent API where each mutator returns {@code this}
 * so calls can be chained in a readable, DSL-like style.
 */
public class Person {
    private String firstName;
    private String lastName;
    private int age;

    public static Person builder() {
        return new Person();
    }

    public Person setFirstName(String firstName) {
        return withFirstName(firstName);
    }

    public Person setLastName(String lastName) {
        return withLastName(lastName);
    }

    public Person setAge(int age) {
        return withAge(age);
    }

    public Person withFirstName(String firstName) {
        this.firstName = firstName;
        return this;
    }

    public Person withLastName(String lastName) {
        this.lastName = lastName;
        return this;
    }

    public Person withAge(int age) {
        if (age < 0) {
            throw new IllegalArgumentException("age must be >= 0");
        }
        this.age = age;
        return this;
    }

    public String fullName() {
        return (firstName == null ? "" : firstName) + " " + (lastName == null ? "" : lastName);
    }

    public boolean isAdult() {
        return age >= 18;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public int getAge() {
        return age;
    }
}
