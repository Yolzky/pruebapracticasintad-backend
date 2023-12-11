package com.sintad.emaintenance.domain.service;

import com.sintad.emaintenance.domain.model.Entity;
import com.sintad.emaintenance.dto.SaveEntityDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface EntityService {

    Entity create(Entity entity);

    Entity update(Long id, SaveEntityDto entity);

    void delete(Long id);

    Entity findById(Long id);

    Page<Entity> getAllEntities(Pageable pageable);








}
