package com.app.entidades;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(
        name = "persona"
)
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Persona {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(
            name = "NumDocumento",
            updatable = false,
            columnDefinition = "varchar(15)"
    )
    private String id;

    @Column(
            name = "NNombres",
            nullable = false,
            columnDefinition = "varchar(30)"
    )
    private String nombres;

    @Column(
            name = "NApellidos",
            nullable = false,
            columnDefinition = "varchar(30)"
    )
    private String apellidos;

    @Column(
            name = "NumRuc",
            nullable = false
    )
    private Long ruc;

    @Column(
            name = "NEmail",
            nullable = false,
            columnDefinition = "varchar(60)"
    )
    private String correo;

    @Column(
            name = "NPassword",
            nullable = false,
            columnDefinition = "varchar(30)"
    )
    private String password;
}
