package com.pawelpluta.day026.jpa;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
interface JpaEventEntityRepository extends JpaRepository<EventEntity, String> {

    List<EventEntity> findAllBySentIsFalse();

}
