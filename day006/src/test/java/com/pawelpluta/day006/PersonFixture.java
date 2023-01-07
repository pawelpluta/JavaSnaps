package com.pawelpluta.day006;

import com.pawelpluta.day006.Person;

import static org.apache.commons.lang3.RandomStringUtils.randomAlphabetic;

class PersonFixture {

    static String randomLastName() {
        return "lastName" + randomAlphabetic(10);
    }

    static Person personWithLastName(String lastName) {
        return new Person("firstName", lastName);
    }
}
