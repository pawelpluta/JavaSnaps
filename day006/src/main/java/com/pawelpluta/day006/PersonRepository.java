package com.pawelpluta.day006;

import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

interface PersonRepository extends MongoRepository<Person, String> {
    List<Person> findByLastName(String lastName);
}
