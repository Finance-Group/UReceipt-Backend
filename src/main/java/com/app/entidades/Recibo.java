package com.app.entidades;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(
        name = "recibo"
)
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Recibo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(
            name = "CRecibo",
            updatable = false
    )
    private Long id;

    @Column(
            name = "DEmision",
            nullable = false,
            columnDefinition = "TIMESTAMP WITHOUT TIME ZONE"
    )
    private Date fecha_emision;

    @Column(
            name = "DPago",
            nullable = false,
            columnDefinition = "TIMESTAMP WITHOUT TIME ZONE"
    )
    private Date fecha_pago;

    @Column(
            name = "MTotal",
            nullable = false,
            columnDefinition = "float"
    )
    private Float monto;

    @Column(
            name = "MRetencion",
            nullable = false,
            columnDefinition = "float"
    )
    private Float retencion;

    @Column(
            name = "NumDias",
            columnDefinition = "int"
    )
    private Integer dias;

    @Column(
            name = "PerTE",
            columnDefinition = "float"
    )
    private Float tasa_e;

    @Column(
            name = "PerDescuento",
            columnDefinition = "float"
    )
    private Float tasa_descuento;

    @Column(
            name = "MDescuento",
            columnDefinition = "float"
    )
    private Float monto_descuento;

    @Column(
            name = "MNeto",
            columnDefinition = "float"
    )
    private Float neto;

    @Column(
            name = "MRecibido",
            columnDefinition = "float"
    )
    private Float recibido;

    @Column(
            name = "MEntregado",
            columnDefinition = "float"
    )
    private Float entregado;

    @Column(
            name = "PerTCEA",
            columnDefinition = "float"
    )
    private Float tcea;

    @ManyToOne
    @JoinColumn(name = "cartera_id", updatable = false, nullable = false, referencedColumnName = "CCartera", foreignKey = @ForeignKey(name = "carteras_recibo_fk"))
    private Cartera cartera;
}
