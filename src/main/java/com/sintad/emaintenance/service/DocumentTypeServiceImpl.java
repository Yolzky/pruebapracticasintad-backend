package com.sintad.emaintenance.service;

import com.sintad.emaintenance.domain.model.DocumentType;
import com.sintad.emaintenance.domain.repository.DocumentTypeRepository;
import com.sintad.emaintenance.domain.service.DocumentTypeService;
import com.sintad.emaintenance.dto.DocumentTypeDto;
import com.sintad.emaintenance.dto.SaveDocumentTypeDto;
import com.sintad.emaintenance.exception.DocumentTypeAlreadyExistsException;
import com.sintad.emaintenance.exception.DocumentTypeNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class DocumentTypeServiceImpl implements DocumentTypeService {

    @Autowired
    private DocumentTypeRepository documentTypeRepository;

    @Override
    public DocumentType create(DocumentType documentType) {

        //verifica si ya existe un documentType con el mismo codigo
        Optional<DocumentType> existingDocumentType = documentTypeRepository.findByCode(documentType.getCode());
        if (existingDocumentType != null) {
            throw new DocumentTypeAlreadyExistsException("Ya existe un documentType con el codigo: " + documentType.getCode());
        }
        return documentTypeRepository.save(documentType);
    }

    @Override
    public DocumentType update(Long id, SaveDocumentTypeDto documentType) {

        DocumentType documentTypeToUpdate = documentTypeRepository.findById(id)
                .orElseThrow(() -> new DocumentTypeNotFoundException("Tipo de Documento no encontrado con ID: " + id));

        documentTypeToUpdate.setCode(documentType.getCode());
        documentTypeToUpdate.setName(documentType.getName());
        documentTypeToUpdate.setDescription(documentType.getDescription());
        documentTypeToUpdate.setStatus(documentType.getStatus());

        return documentTypeRepository.save(documentTypeToUpdate);

    }

    @Override
    public void delete(Long id) {
        DocumentType documentType = documentTypeRepository.findById(id)
                .orElseThrow(() -> new DocumentTypeNotFoundException("Tipo de Documento no encontrado con ID: " + id));
        documentTypeRepository.delete(documentType);

    }

    @Override
    public DocumentType findById(Long id) {
            return documentTypeRepository.findById(id)
                    .orElseThrow(() -> new DocumentTypeNotFoundException("Tipo de Documento no encontrado con ID: " + id));
    }

    @Override
    public Page<DocumentType> getAllDocumentTypes(Pageable pageable) {
        return documentTypeRepository.findAll(pageable);
    }

}
