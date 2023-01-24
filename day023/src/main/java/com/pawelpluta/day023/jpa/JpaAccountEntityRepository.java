package com.pawelpluta.day023.jpa;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
interface JpaAccountEntityRepository extends JpaRepository<AccountEntity, String> {
}
