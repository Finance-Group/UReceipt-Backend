package com.app.entidades;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(
        name = "documento"
)
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Documento {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(
            name = "CDocumento",
            updatable = false
    )
    private Long id;

    @Column(
            name = "NDocumento",
            nullable = false,
            columnDefinition = "varchar(30)"
    )
    private Integer numero;
}
