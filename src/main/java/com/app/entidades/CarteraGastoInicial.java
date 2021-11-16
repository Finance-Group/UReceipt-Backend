package com.app.entidades;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(
        name = "cartera_gastoInicial"
)
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CarteraGastoInicial {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(
            name = "CCarteraGastoInicial",
            updatable = false
    )
    private Long id;

    @ManyToOne
    @JoinColumn(name = "cartera_id", updatable = false, nullable = false, referencedColumnName = "CCartera", foreignKey = @ForeignKey(name = "gastoInicial_carteras_fk"))
    private Cartera cartera;

    @ManyToOne
    @JoinColumn(name = "gastoInicial_id", updatable = false, nullable = false, referencedColumnName = "CGastoInicial", foreignKey = @ForeignKey(name = "carteras_gastoInicial_fk"))
    private GastoInicial gastoinicial;

    @Column(
            name = "MGastoFinal",
            nullable = false
    )
    private Double MGastoFinal;

}
