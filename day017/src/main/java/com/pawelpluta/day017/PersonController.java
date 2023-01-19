package com.pawelpluta.day017;

import io.micronaut.http.MediaType;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Get;
import io.micronaut.http.annotation.Produces;

@Controller("/persons")
class PersonController {

    private final InMemoryPersonRepository personRepository;

    PersonController(InMemoryPersonRepository personRepository) {
        this.personRepository = personRepository;
    }

    @Get("/{pesel}")
    @Produces(MediaType.APPLICATION_JSON)
    Person getByPesel(String pesel) {
        return personRepository.find(pesel)
                .map(found -> new Person(found.pesel(), found.firstName(), found.lastName()))
                .orElse(null);
    }
}
