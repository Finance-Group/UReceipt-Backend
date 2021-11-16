package com.app.dto;

import lombok.Data;

@Data
public class CarteraDto {
    private Long id;
    private Double descuento;
    private Double plazo;
    private Integer taza;
    private Integer peridoCapitalizacion;
    private Double gastoITotal;
    private Double GastoFTotal;
    private Integer numRecibos;
    private Double recibidoTotal;
    private Double tceaTotal;

}
