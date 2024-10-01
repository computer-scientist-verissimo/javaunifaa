package com.exemple.models;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
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

    public void calcularDiferenca() {
        this.valorDiferenca = valorDebito - valorPago;
    }
}
