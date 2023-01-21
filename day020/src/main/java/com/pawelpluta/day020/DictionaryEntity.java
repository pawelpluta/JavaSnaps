package com.pawelpluta.day020;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "dictionary")
class DictionaryEntity {
    @Id
    private String key;
    private String value;

    public String key() {
        return key;
    }

    public String value() {
        return value;
    }
}
