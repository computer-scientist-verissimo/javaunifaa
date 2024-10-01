package com.exemple.models;

import lombok.Data;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
@Data
public class Debito {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String descricao;
    private String dataReferencia;
    private String dataPagamento;
    private Double valorDebito;
    private Double valorPago;
    private Double valorDiferenca;
    private String tipoDespesa;
}
