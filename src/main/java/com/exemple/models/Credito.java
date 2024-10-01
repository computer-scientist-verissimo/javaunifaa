package com.exemple.models;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
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

    public void calcularDiferenca() {
        this.valorDiferenca = valorCredito - valorRecebido;
    }
}
