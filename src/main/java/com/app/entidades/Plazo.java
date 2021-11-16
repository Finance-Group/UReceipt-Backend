package com.app.entidades;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(
        name = "plazo"
)
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Plazo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(
            name = "CPlazo",
            updatable = false
    )
    private Long id;

    @Column(
            name = "NPlazo",
            nullable = false,
            columnDefinition = "varchar(30)"
    )
    private String nombre;

    @OneToMany(mappedBy = "plazo", cascade = { CascadeType.PERSIST, CascadeType.REMOVE }, fetch = FetchType.LAZY)
    private List<Cartera> carteras;
}
