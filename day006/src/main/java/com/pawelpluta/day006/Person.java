package com.pawelpluta.day006;

import org.springframework.data.annotation.Id;

class Person {
    @Id
    private String id;
    private String firstName;
    private String lastName;

    Person(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
    }

    String getFirstName() {
        return firstName;
    }

    String getLastName() {
        return lastName;
    }
}
