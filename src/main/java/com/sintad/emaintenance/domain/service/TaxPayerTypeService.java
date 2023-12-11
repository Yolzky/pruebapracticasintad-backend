package com.sintad.emaintenance.domain.service;

import com.sintad.emaintenance.domain.model.TaxPayerType;
import com.sintad.emaintenance.dto.SaveTaxPayerTypeDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface TaxPayerTypeService {

    TaxPayerType create(TaxPayerType taxPayerType);

    TaxPayerType update(Long id, SaveTaxPayerTypeDto taxPayerType);

    void delete(Long id);

    TaxPayerType findById(Long id);

    Page<TaxPayerType> getAllTaxPayerTypes(Pageable pageable);

}
