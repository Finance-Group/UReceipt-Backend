package com.app.dto;

import lombok.Data;

@Data
public class PersonaDto {
    private String id;
    private String nombres;
    private String apellidos;
    private Long ruc;
    private String correo;
    private String password;

}
