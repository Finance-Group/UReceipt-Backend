package com.app.entidades;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(
        name = "tasa"
)
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Tasa {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(
            name = "CTasa",
            updatable = false
    )
    private Long id;

    @Column(
            name = "NTasa",
            nullable = false,
            columnDefinition = "varchar(30)"
    )
    private String nombre;

    @OneToMany(mappedBy = "tasa", cascade = { CascadeType.PERSIST, CascadeType.REMOVE }, fetch = FetchType.LAZY)
    private List<Cartera> carteras;
}
