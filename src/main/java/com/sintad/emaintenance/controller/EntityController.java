package com.sintad.emaintenance.controller;


import com.sintad.emaintenance.domain.model.Entity;
import com.sintad.emaintenance.domain.service.EntityService;
import com.sintad.emaintenance.dto.EntityDto;
import com.sintad.emaintenance.dto.SaveEntityDto;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.web.bind.annotation.*;
import jakarta.validation.Valid;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@AllArgsConstructor
@RequestMapping("/entity")
@CrossOrigin(origins = "*")
public class EntityController {

    @Autowired
    private EntityService entityService;

    @Autowired
    private final ModelMapper mapper;

    @PostMapping
    public EntityDto createEntity(@Valid @RequestBody SaveEntityDto resource) {
        Entity entity = convertToEntity(resource);
        return convertToResource(entityService.create(entity));
    }

    @GetMapping("/{entityId}")
    public Entity getEntityById(@PathVariable Long entityId) {
        return entityService.findById(entityId);
    }

    @PutMapping("/{entityId}")
    public Entity updateEntity(@PathVariable Long entityId, @RequestBody SaveEntityDto entityRequest) {
        return entityService.update(entityId, entityRequest);
    }

    @DeleteMapping("/{entityId}")
    public void deleteEntity(@PathVariable Long entityId) {
        entityService.delete(entityId);
    }

    @GetMapping("/all")
    public Page<Entity> getAllEntities(Pageable pageable) {

        pageable = PageRequest.of(
                pageable.getPageNumber(),
                pageable.getPageSize(),
                Sort.by(Sort.Order.asc("id"))
        );
        Page<Entity> entityPage = entityService.getAllEntities(pageable);
        List<Entity> resources = entityPage.getContent()
                .stream()
                .collect(Collectors.toList());
        return new PageImpl<>(resources, pageable, entityPage.getTotalElements());

    }

    private Entity convertToEntity(SaveEntityDto resource) {
        return mapper.map(resource, Entity.class);
    }
    private EntityDto convertToResource(Entity entity)
    {
        return mapper.map(entity, EntityDto.class);
    }

}
