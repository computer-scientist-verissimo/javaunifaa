package com.exemple.dto;

import lombok.Data;

@Data
public class DebitoDTO {
    private String descricao;
    private String dataReferencia;
    private String dataPagamento;
    private Double valorDebito;
    private Double valorPago;
    private String tipoDespesa;
}
