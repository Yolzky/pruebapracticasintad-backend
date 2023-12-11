package com.sintad.emaintenance.domain.repository;


import com.sintad.emaintenance.domain.model.DocumentType;
import com.sintad.emaintenance.dto.DocumentTypeDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface DocumentTypeRepository extends JpaRepository<DocumentType, Long> {

    Optional<DocumentType> findByCode(String code);


}
