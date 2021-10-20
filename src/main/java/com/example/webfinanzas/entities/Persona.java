package com.example.webfinanzas.entities;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@Table(name = "persona")
public class Persona {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id" , updatable = false)
    private Long id;

    @Column(name = "nombre", nullable = false, columnDefinition = "TEXT")
    private String nombre;



}
