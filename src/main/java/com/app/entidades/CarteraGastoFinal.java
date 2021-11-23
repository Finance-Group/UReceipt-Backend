package com.app.entidades;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(
        name = "cartera_gastoFinal"
)
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CarteraGastoFinal {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(
            name = "CCarteraGastoFinal",
            updatable = false
    )
    private Long id;

    @ManyToOne
    @JoinColumn(name = "cartera_id", updatable = false, nullable = false, referencedColumnName = "CCartera", foreignKey = @ForeignKey(name = "gastoFinal_carteras_fk"))
    private Cartera cartera;

    @ManyToOne
    @JoinColumn(name = "gastoFinal_id", updatable = false, nullable = false, referencedColumnName = "CGastoFinal", foreignKey = @ForeignKey(name = "carteras_gastoFinal_fk"))
    private GastoFinal gastofinal;

    @ManyToOne
    @JoinColumn(name = "formato_id", updatable = false, nullable = false, referencedColumnName = "CFormato", foreignKey = @ForeignKey(name = "formato_cartera_gasto_final_fk"))
    private Formato formato;

    @Column(
            name = "MGastoFinall",
            nullable = false
    )
    private Double MGastoFinal;
}
