package com.sintad.emaintenance.domain.service;

import com.sintad.emaintenance.domain.model.DocumentType;
import com.sintad.emaintenance.dto.DocumentTypeDto;
import com.sintad.emaintenance.dto.SaveDocumentTypeDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;


public interface DocumentTypeService {

    DocumentType create(DocumentType documentType);

    DocumentType update(Long id, SaveDocumentTypeDto documentType);


    void delete(Long id);

    DocumentType findById(Long id);

    Page<DocumentType> getAllDocumentTypes(Pageable pageable);

}
