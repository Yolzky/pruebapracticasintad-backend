package com.sintad.emaintenance.domain.repository;


import com.sintad.emaintenance.domain.model.TaxPayerType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TaxPayerTypeRepository extends JpaRepository<TaxPayerType, Long> {

    Optional<TaxPayerType> findByName(String name);

}
