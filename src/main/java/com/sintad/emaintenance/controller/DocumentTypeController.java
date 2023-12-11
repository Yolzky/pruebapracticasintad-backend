package com.sintad.emaintenance.controller;

import com.sintad.emaintenance.domain.service.DocumentTypeService;

import com.sintad.emaintenance.dto.SaveDocumentTypeDto;
import com.sintad.emaintenance.domain.model.DocumentType;
import com.sintad.emaintenance.dto.DocumentTypeDto;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.web.bind.annotation.*;
import org.modelmapper.ModelMapper;


import jakarta.validation.Valid;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@AllArgsConstructor
@RequestMapping("/document-type")
@CrossOrigin(origins = "*")
public class DocumentTypeController {

    @Autowired
    private DocumentTypeService documentTypeService;

    @Autowired
    private final ModelMapper mapper;

    @PostMapping
    public DocumentTypeDto createDocumentType(@Valid @RequestBody SaveDocumentTypeDto resource) {
        DocumentType documentType = convertToEntity(resource);
        return convertToResource(documentTypeService.create(documentType));
    }

    @GetMapping("/{documentTypeId}")
    public DocumentType getDocumentTypeById(@PathVariable Long documentTypeId) {
        return documentTypeService.findById(documentTypeId);
    }

    @PutMapping("/{documentTypeId}")
    public DocumentType updateDocumentType(@PathVariable Long documentTypeId, @RequestBody SaveDocumentTypeDto documentTypeRequest) {
        return documentTypeService.update(documentTypeId, documentTypeRequest);
    }

    @DeleteMapping("/{documentTypeId}")
    public void deleteDocumentType(@PathVariable Long documentTypeId) {
        documentTypeService.delete(documentTypeId);
    }

    @GetMapping("/all")
    public Page<DocumentType> getAllDocumentTypes(Pageable pageable) {
        pageable = PageRequest.of(
                pageable.getPageNumber(),
                pageable.getPageSize(),
                Sort.by(Sort.Order.asc("id"))
        );
        Page<DocumentType> documentTypePage = documentTypeService.getAllDocumentTypes(pageable);
        List<DocumentType> resources = documentTypePage.getContent()
                .stream()
                .collect(Collectors.toList());

        return new PageImpl<>(resources, pageable, documentTypePage.getTotalElements());
    }

    private DocumentType convertToEntity(SaveDocumentTypeDto resource) {
        return mapper.map(resource, DocumentType.class);
    }
    private DocumentTypeDto convertToResource(DocumentType entity)
    {
        return mapper.map(entity, DocumentTypeDto.class);
    }


}
