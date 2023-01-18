package com.pawelpluta.day017;

import jakarta.inject.Singleton;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Singleton
class InMemoryPersonRepository {
    private final Map<String, Person> personsByPesel = new HashMap<>();

    void save(Person person) {
        personsByPesel.put(person.pesel(), person);
    }

    Optional<Person> find(String pesel) {
        return Optional.ofNullable(personsByPesel.get(pesel));
    }

}
