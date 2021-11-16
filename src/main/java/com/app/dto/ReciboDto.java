package com.app.dto;

import lombok.Data;

import java.util.Date;

@Data
public class ReciboDto {
    private Long id;
    private Date fecha_emision;
    private Date fecha_pago;
    private Float monto;
    private Float retencion;
    private Integer dias;
    private Float tasa_e;
    private Float tasa_descuento;
    private Float monto_descuento;
    private Float neto;
    private Float recibido;
    private Float entregado;
    private Float tcea;

}
