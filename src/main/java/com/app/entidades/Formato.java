package com.app.entidades;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(
        name = "formato"
)
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Formato {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(
            name = "CFormato",
            updatable = false
    )
    private Long id;

    @Column(
            name = "NFormato",
            nullable = false,
            columnDefinition = "varchar(100)"
    )
    private String nombre;

    @OneToMany(mappedBy = "formato", cascade = { CascadeType.PERSIST, CascadeType.REMOVE }, fetch = FetchType.LAZY)
    private List<CarteraGastoInicial> carteraGastoInicialList;

    @OneToMany(mappedBy = "formato", cascade = { CascadeType.PERSIST, CascadeType.REMOVE }, fetch = FetchType.LAZY)
    private List<CarteraGastoFinal> carteraGastoFinalList;
}
