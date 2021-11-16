package com.app.entidades;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(
        name = "gastoInicial"
)
@Data
@AllArgsConstructor
@NoArgsConstructor
public class GastoInicial {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(
            name = "CGastoInicial",
            updatable = false
    )
    private Long id;

    @Column(
            name = "NGastoInicial",
            nullable = false,
            columnDefinition = "varchar(200)"
    )
    private String NombreGasto;

    @OneToMany(mappedBy = "gastoinicial", cascade = { CascadeType.PERSIST, CascadeType.REMOVE }, fetch = FetchType.LAZY)
    private List<CarteraGastoInicial> carteraGastoInicial = new ArrayList<>();
}
