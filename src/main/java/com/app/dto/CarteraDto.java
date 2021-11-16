package com.app.dto;

import lombok.Data;

import java.util.Date;

@Data
public class CarteraDto {
    private Long id;
    private Date descuento;
    private Double plazo;
    private Integer taza;
    private Integer peridoCapitalizacion;
    private Double gastoITotal;
    private Double GastoFTotal;
    private Integer numRecibos;
    private Double recibidoTotal;
    private Double tceaTotal;

}
