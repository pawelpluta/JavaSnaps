package com.pawelpluta.day020;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class DictionaryRepositoryIT implements PostgresqlSupport {

    @Autowired
    DictionaryRepository repository;

    @Test
    void shouldBeAbleToFetchMigratedData() {
        // given
        String dictionaryKeyFromFlywayMigration = "some_dictionary_key";

        // when
        DictionaryEntity dictionary = repository.findById(dictionaryKeyFromFlywayMigration).get();

        // then
        assertEquals("some_dictionary_value", dictionary.value());
    }
}