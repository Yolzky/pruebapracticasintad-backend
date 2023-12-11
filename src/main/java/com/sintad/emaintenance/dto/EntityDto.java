package com.sintad.emaintenance.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EntityDto {

    private DocumentTypeDto documentType;

    private String documentNumber;

    private String businessName;

    private String commercialName;

    private TaxPayerTypeDto taxPayerType;

    private String address;

    private String phone;

    private Boolean status;

}
