package com.sintad.emaintenance.domain.repository;

import com.sintad.emaintenance.domain.model.Entity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface EntityRepository extends JpaRepository<Entity,Long> {

    Optional<Entity> findByDocumentNumber(String documentNumber);

}
