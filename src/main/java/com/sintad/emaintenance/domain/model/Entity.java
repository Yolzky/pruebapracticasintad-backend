package com.sintad.emaintenance.domain.model;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@jakarta.persistence.Entity
@Table(name = "tb_entidad")
public class Entity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_entidad")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "id_tipo_documento")
    private DocumentType documentType;

    @Column(name = "nro_documento")
    private String documentNumber;

    @Column(name = "razon_social")
    private String businessName;

    @Column(name = "nombre_comercial")
    private String commercialName;

    @ManyToOne
    @JoinColumn(name = "id_tipo_contribuyente")
    private TaxPayerType taxPayerType;

    @Column(name = "direccion")
    private String address;

    @Column(name = "telefono")
    private String phone;

    @Column(name = "estado")
    private Boolean status;

}
