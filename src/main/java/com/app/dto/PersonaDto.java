package com.app.dto;

import lombok.Data;

@Data
public class PersonaDto {
    private Long id;
    private String nombre;
    private Long ruc;
    private String correo;
    private String password;
    private Long documentoId;

}
