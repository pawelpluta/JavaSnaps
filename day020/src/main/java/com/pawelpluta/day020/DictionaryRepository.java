package com.pawelpluta.day020;

import org.springframework.data.jpa.repository.JpaRepository;

interface DictionaryRepository extends JpaRepository<DictionaryEntity, String> {
}
