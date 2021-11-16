package com.app.entidades;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(
        name = "moneda"
)
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Moneda {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(
            name = "CMoneda",
            updatable = false
    )
    private Long id;

    @Column(
            name = "NMoneda",
            nullable = false,
            columnDefinition = "varchar(30)"
    )
    private String nombre;

    @Column(
            name = "SMoneda",
            nullable = false,
            columnDefinition = "varchar(3)"
    )
    private String simbolo;

    @OneToMany(mappedBy = "moneda", cascade = { CascadeType.PERSIST, CascadeType.REMOVE }, fetch = FetchType.LAZY)
    private List<Cartera> carteras;
}
