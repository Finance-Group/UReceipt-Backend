package com.app.dto;

import lombok.Data;

import java.util.Date;

@Data
public class CarteraDto {
    private Long id;
    private Date descuento;
    private Float plazo;
    private Float taza;
    private Integer peridoCapitalizacion;
    private Float gastoITotal;
    private Float GastoFTotal;
    private Integer numRecibos;
    private Float recibidoTotal;
    private Float tceaTotal;
    private Long personaId;
    private Long monedaId;
    private Long pcId;
    private Long plazoId;
    private Long tasaId;
    private Long diaId;
}
