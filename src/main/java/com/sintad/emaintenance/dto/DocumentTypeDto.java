package com.sintad.emaintenance.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DocumentTypeDto {

    private String code;

    private String name;

    private String description;

    private Boolean status;
}
