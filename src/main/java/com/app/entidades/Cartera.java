package com.app.entidades;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(
        name = "cartera"
)
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Cartera {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(
            name = "CCartera",
            updatable = false
    )
    private Long id;

    @Column(
            name = "DDescuento",
            nullable = false,
            columnDefinition = "varchar(30)"
    )
    private Double descuento;

    @Column(
            name = "NumPlazo",
            nullable = false,
            columnDefinition = "varchar(30)"
    )
    private Double plazo;

    @Column(
            name = "NumTaza",
            nullable = false,
            columnDefinition = "varchar(30)"
    )
    private Integer taza;

    @Column(
            name = "NumPeriodoCapitalizacion",
            nullable = false,
            columnDefinition = "varchar(30)"
    )
    private Integer peridoCapitalizacion;

    @Column(
            name = "MGastoITotal",
            nullable = false,
            columnDefinition = "varchar(30)"
    )
    private Double gastoITotal;

    @Column(
            name = "MGastoFTotal",
            nullable = false,
            columnDefinition = "varchar(30)"
    )
    private Double GastoFTotal;

    @Column(
            name = "NumRecibos",
            nullable = false,
            columnDefinition = "varchar(30)"
    )
    private Integer numRecibos;

    @Column(
            name = "MRecibidoTotal",
            nullable = false,
            columnDefinition = "varchar(30)"
    )
    private Double recibidoTotal;

    @Column(
            name = "PerTCEATotal",
            nullable = false,
            columnDefinition = "varchar(30)"
    )
    private Double tceaTotal;

    @OneToMany(mappedBy = "cartera", cascade = { CascadeType.PERSIST, CascadeType.REMOVE }, fetch = FetchType.LAZY)
    private List<CarteraGastoInicial> detailGastoInicial;

    @OneToMany(mappedBy = "cartera", cascade = { CascadeType.PERSIST, CascadeType.REMOVE }, fetch = FetchType.LAZY)
    private List<CarteraGastoFinal> detailGastoFinal;

}
