package com.app.entidades;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(
        name = "gastoFinal"
)
@Data
@AllArgsConstructor
@NoArgsConstructor
public class GastoFinal {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(
            name = "CGastoFinal",
            updatable = false
    )
    private Long id;

    @Column(
            name = "NGastoFinal",
            nullable = false,
            columnDefinition = "varchar(200)"
    )
    private String nombre;

    @OneToMany(mappedBy = "gastofinal", cascade = { CascadeType.PERSIST, CascadeType.REMOVE }, fetch = FetchType.LAZY)
    private List<CarteraGastoFinal> carteraGastoFinal = new ArrayList<>();
}
