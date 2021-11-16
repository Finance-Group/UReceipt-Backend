package com.app.entidades;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(
        name = "dia"
)
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Dia {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(
            name = "CDia",
            updatable = false
    )
    private Long id;

    @Column(
            name = "NDia",
            nullable = false,
            columnDefinition = "varchar(30)"
    )
    private String nombre;

    @Column(
            name = "NumDia",
            nullable = false
    )
    private Long numero;

    @OneToMany(mappedBy = "dia", cascade = { CascadeType.PERSIST, CascadeType.REMOVE }, fetch = FetchType.LAZY)
    private List<Cartera> carteras;
}
