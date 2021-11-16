package com.app.entidades;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(
        name = "periodocapitalizacion"
)
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PeriodoCapitalizacion {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(
            name = "CPeriodoCapitalizacion",
            updatable = false
    )
    private Long id;

    @Column(
            name = "NPeriodoCapitalizacion",
            nullable = false,
            columnDefinition = "varchar(30)"
    )
    private String nombre;
}
