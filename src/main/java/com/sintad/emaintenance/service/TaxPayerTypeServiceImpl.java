package com.sintad.emaintenance.service;

import com.sintad.emaintenance.domain.model.TaxPayerType;
import com.sintad.emaintenance.domain.repository.TaxPayerTypeRepository;
import com.sintad.emaintenance.domain.service.TaxPayerTypeService;
import com.sintad.emaintenance.dto.SaveTaxPayerTypeDto;
import com.sintad.emaintenance.exception.TaxPayerTypeNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

@Service
public class TaxPayerTypeServiceImpl implements TaxPayerTypeService {

    @Autowired
    private TaxPayerTypeRepository taxPayerTypeRepository;

    @Override
    public TaxPayerType create(TaxPayerType taxPayerType) {
        Optional<TaxPayerType> existingTaxPayerType = taxPayerTypeRepository.findByName(taxPayerType.getName());

        if (existingTaxPayerType.isPresent()) {
            throw new RuntimeException("Ya existe un tipo de contribuyente con el nombre: " + taxPayerType.getName());
        }

        return taxPayerTypeRepository.save(taxPayerType);
    }

    @Override
    public TaxPayerType update(Long id, SaveTaxPayerTypeDto taxPayerType) {

        TaxPayerType taxPayerTypeToUpdate = taxPayerTypeRepository.findById(id)
                .orElseThrow(() -> new TaxPayerTypeNotFoundException("No se encontró un contribuyente con ID: " + id));
        taxPayerTypeToUpdate.setName(taxPayerType.getName());
        taxPayerTypeToUpdate.setStatus(taxPayerType.getStatus());
        return taxPayerTypeRepository.save(taxPayerTypeToUpdate);
    }

    @Override
    public void delete(Long id) {

        TaxPayerType taxPayerType = taxPayerTypeRepository.findById(id)
                .orElseThrow(() -> new TaxPayerTypeNotFoundException("No se encontró un contribuyente con ID: " + id));

        taxPayerTypeRepository.delete(taxPayerType);
    }

    @Override
    public TaxPayerType findById(Long id) {

        return taxPayerTypeRepository.findById(id)
                .orElseThrow(() -> new TaxPayerTypeNotFoundException("No se encontró un contribuyente con ID: " + id));

    }

    @Override
    public Page<TaxPayerType> getAllTaxPayerTypes(Pageable pageable) {
        return taxPayerTypeRepository.findAll(pageable);

    }
}
