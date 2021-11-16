package com.app.entidades;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

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
    private String numero;

    @OneToMany(mappedBy = "documento", cascade = { CascadeType.PERSIST, CascadeType.REMOVE }, fetch = FetchType.LAZY)
    private List<Persona> personas;
}
