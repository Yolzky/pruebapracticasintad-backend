package com.sintad.emaintenance.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SaveEntityDto {

    private Long documentTypeId;

    private String documentNumber;

    private String businessName;

    private String commercialName;

    private Long taxPayerTypeId;

    private String address;

    private String phone;

    private Boolean status;
}
