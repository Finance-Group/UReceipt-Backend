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
    private Double nPlazo;

    @Column(
            name = "NumTaza",
            nullable = false,
            columnDefinition = "varchar(30)"
    )
    private Integer nTaza;

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

    @OneToMany(mappedBy = "cartera", cascade = { CascadeType.PERSIST, CascadeType.REMOVE }, fetch = FetchType.LAZY)
    private List<Recibo> recibos;

    @ManyToOne
    @JoinColumn(name = "persona_id", updatable = false, nullable = false, referencedColumnName = "NumDocumento", foreignKey = @ForeignKey(name = "persona_cartera_fk"))
    private Persona persona;

    @ManyToOne
    @JoinColumn(name = "moneda_id", updatable = false, nullable = false, referencedColumnName = "CMoneda", foreignKey = @ForeignKey(name = "moneda_cartera_fk"))
    private Moneda moneda;

    @ManyToOne
    @JoinColumn(name = "periodocapitalizacion_id", updatable = false, nullable = false, referencedColumnName = "CPeriodoCapitalizacion", foreignKey = @ForeignKey(name = "periodocapitalizacion_cartera_fk"))
    private PeriodoCapitalizacion periodocapitalizacion;

    @ManyToOne
    @JoinColumn(name = "plazo_id", updatable = false, nullable = false, referencedColumnName = "CPlazo", foreignKey = @ForeignKey(name = "plazo_cartera_fk"))
    private Plazo plazo;

    @ManyToOne
    @JoinColumn(name = "tasa_id", updatable = false, nullable = false, referencedColumnName = "CTasa", foreignKey = @ForeignKey(name = "tasa_cartera_fk"))
    private Tasa tasa;

    @ManyToOne
    @JoinColumn(name = "dia_id", updatable = false, nullable = false, referencedColumnName = "CDia", foreignKey = @ForeignKey(name = "dia_cartera_fk"))
    private Dia dia;

    @ManyToOne
    @JoinColumn(name = "formato_id", updatable = false, nullable = false, referencedColumnName = "CFormato", foreignKey = @ForeignKey(name = "formato_cartera_fk"))
    private Formato formato;
}
