package com.pawelpluta.day006;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static com.pawelpluta.day006.PersonFixture.personWithLastName;
import static com.pawelpluta.day006.PersonFixture.randomLastName;
import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
class MongoDBTests {

    @Autowired
    PersonRepository personRepository;

    @Test
    void personCouldBeFoundByItsLastName() {
        // given - person to find
        Person knownPerson = personWithLastName(randomLastName());
        saved(knownPerson);
        // given - additional irrelevant data
        saved(personWithLastName(randomLastName()));
        saved(personWithLastName(randomLastName()));

        // when
        List<Person> foundPersons = personRepository.findByLastName(knownPerson.getLastName());

        // then
        assertEquals(1, foundPersons.size());
        assertEquals(knownPerson.getFirstName(), foundPersons.get(0).getFirstName());
        assertEquals(knownPerson.getLastName(), foundPersons.get(0).getLastName());
    }

    private void saved(Person personData) {
        personRepository.save(personData);
    }

}