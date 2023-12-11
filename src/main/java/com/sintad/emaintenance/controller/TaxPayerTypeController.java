package com.sintad.emaintenance.controller;

import com.sintad.emaintenance.domain.model.TaxPayerType;
import com.sintad.emaintenance.domain.service.TaxPayerTypeService;
import com.sintad.emaintenance.dto.SaveTaxPayerTypeDto;
import com.sintad.emaintenance.dto.TaxPayerTypeDto;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@AllArgsConstructor
@RequestMapping("/tax-payer-type")
@CrossOrigin(origins = "*")
public class TaxPayerTypeController {
    
    @Autowired
    private TaxPayerTypeService taxPayerTypeService;
    
    @Autowired
    private final ModelMapper mapper;

    @PostMapping
    public TaxPayerTypeDto createTaxPayerType(SaveTaxPayerTypeDto resource) {
        TaxPayerType taxPayerType = convertToEntity(resource);
        return convertToResource(taxPayerTypeService.create(taxPayerType));
    }

    @GetMapping("/{taxPayerTypeId}")
    public TaxPayerType getTaxPayerTypeById(@PathVariable Long taxPayerTypeId) {
        return taxPayerTypeService.findById(taxPayerTypeId);
    }

    @PutMapping("/{taxPayerTypeId}")
    public TaxPayerType updateTaxPayerType(@PathVariable Long taxPayerTypeId, @RequestBody SaveTaxPayerTypeDto taxPayerTypeRequest) {
        return taxPayerTypeService.update(taxPayerTypeId, taxPayerTypeRequest);
    }

    @DeleteMapping("/{taxPayerTypeId}")
    public void deleteTaxPayerType(@PathVariable Long taxPayerTypeId) {
        taxPayerTypeService.delete(taxPayerTypeId);
    }


    @GetMapping("/all")
    public Iterable<TaxPayerType> getAllTaxPayerTypes(Pageable pageable) {
        pageable = PageRequest.of(
                pageable.getPageNumber(),
                pageable.getPageSize(),
                Sort.by(Sort.Order.asc("id"))
        );
        Page<TaxPayerType> taxPayerTypePage = taxPayerTypeService.getAllTaxPayerTypes(pageable);
        List<TaxPayerType> resources = taxPayerTypePage.getContent()
                .stream()
                .collect(Collectors.toList());
        return new PageImpl<>(resources, pageable, taxPayerTypePage.getTotalElements());
    }

    private TaxPayerType convertToEntity(SaveTaxPayerTypeDto resource) {
        return mapper.map(resource, TaxPayerType.class);
    }
    private TaxPayerTypeDto convertToResource(TaxPayerType entity)
    {
        return mapper.map(entity, TaxPayerTypeDto.class);
    }

}
