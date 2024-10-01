package com.exemple.models;

import lombok.Data;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
@Data
public class Credito {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String descricao;
    private String dataReferencia;
    private String dataRecebimento;
    private Double valorCredito;
    private Double valorRecebido;
    private Double valorDiferenca;
    private String tipoRecebimento;
}
