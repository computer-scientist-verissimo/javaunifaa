package com.exemple.dto;

import lombok.Data;

@Data
public class CreditoDTO {
    private String descricao;
    private String dataReferencia;
    private String dataRecebimento;
    private Double valorCredito;
    private Double valorRecebido;
    private String tipoRecebimento;
}

