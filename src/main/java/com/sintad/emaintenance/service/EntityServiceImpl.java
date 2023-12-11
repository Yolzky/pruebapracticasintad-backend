package com.sintad.emaintenance.service;

import com.sintad.emaintenance.domain.model.Entity;
import com.sintad.emaintenance.domain.repository.EntityRepository;
import com.sintad.emaintenance.domain.service.EntityService;
import com.sintad.emaintenance.dto.SaveEntityDto;
import com.sintad.emaintenance.exception.EntityAlreadyExistsException;
import com.sintad.emaintenance.exception.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class EntityServiceImpl implements EntityService {

    @Autowired
    private EntityRepository entityRepository;

    @Override
    public Entity create(Entity entity) {
        //verificar si ya existe una entidad con el mismo numero de documento
        Optional<Entity> existingEntity = entityRepository.findByDocumentNumber(entity.getDocumentNumber());
        if (existingEntity.isPresent()) {
            throw new EntityAlreadyExistsException("Ya existe una entidad con el numero de documento: " + entity.getDocumentNumber());
        }
        return entityRepository.save(entity);

    }

    @Override
    public Entity update(Long id, SaveEntityDto entity) {

        Entity entityToUpdate = entityRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Entidad no encontrada con ID: " + id));
        entityToUpdate.setDocumentNumber(entity.getDocumentNumber());
        entityToUpdate.setBusinessName(entity.getBusinessName());
        entityToUpdate.setCommercialName(entity.getCommercialName());
        entityToUpdate.setAddress(entity.getAddress());
        entityToUpdate.setPhone(entity.getPhone());
        entityToUpdate.setStatus(entity.getStatus());
        return entityRepository.save(entityToUpdate);

    }
    @Override
    public void delete(Long id) {

        Entity entity = entityRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Entidad no encontrada con ID: " + id));
        entityRepository.delete(entity);

    }

    @Override
    public Entity findById(Long id) {

        return entityRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Entidad no encontrada con ID: " + id));
    }

    @Override
    public Page<Entity> getAllEntities(Pageable pageable) {
        return entityRepository.findAll(pageable);
    }

}
