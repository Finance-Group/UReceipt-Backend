package com.app.entidades;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.Email;
import java.util.List;

@Entity
@Table(
        name = "persona"
)
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Persona {

    @Id
    @Column(
            name = "NumDocumento",
            nullable = false
    )
    private Long id;

    @Column(
            name = "NNombre",
            nullable = false,
            columnDefinition = "varchar(30)"
    )
    private String nombre;

    @Column(
            name = "NumRuc",
            nullable = false,
            unique = true
    )
    private Long ruc;

    @Column(
            name = "NEmail",
            nullable = false,
            columnDefinition = "varchar(60)",
            unique = true
    )
    @Email
    private String correo;

    @Column(
            name = "NPassword",
            nullable = false,
            columnDefinition = "varchar(30)"
    )
    private String password;

    @ManyToOne
    @JoinColumn(name = "documento_id", updatable = false, nullable = false, referencedColumnName = "CDocumento", foreignKey = @ForeignKey(name = "documentos_persona_fk"))
    private Documento documento;

    @OneToMany(mappedBy = "persona", cascade = { CascadeType.PERSIST, CascadeType.REMOVE }, fetch = FetchType.LAZY)
    private List<Cartera> carteras;
}
