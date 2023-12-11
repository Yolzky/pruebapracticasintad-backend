package com.sintad.emaintenance.domain.model;


import jakarta.persistence.*;
import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "tb_tipo_contribuyente")
public class TaxPayerType {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_tipo_contribuyente")
    private Long id;

    @Column(name = "nombre")
    private String name;

    @Column(name = "estado")
    private Boolean status;

}
