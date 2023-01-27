package com.pawelpluta.day026.jpa;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
interface JpaBankAccountEntityRepository extends JpaRepository<BankAccountEntity, String> {
}
