package com.example.service;

import com.exemple.dto.CreditoDTO;
import com.exemple.models.Credito;
import com.exemple.repositories.CreditoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CreditoService {

    @Autowired
    private CreditoRepository creditoRepository;

    public List<Credito> listarTodos() {
        return creditoRepository.findAll();
    }

    public Credito criarCredito(CreditoDTO creditoDTO) {
        Credito credito = new Credito();
        credito.setDescricao(creditoDTO.getDescricao());
        credito.setDataReferencia(creditoDTO.getDataReferencia());
        credito.setDataRecebimento(creditoDTO.getDataRecebimento());
        credito.setValorCredito(creditoDTO.getValorCredito());
        credito.setValorRecebido(creditoDTO.getValorRecebido());
        credito.setTipoRecebimento(creditoDTO.getTipoRecebimento());
        credito.calcularDiferenca();
        return creditoRepository.save(credito);
    }

    public ResponseEntity<Credito> atualizarCredito(Long id, CreditoDTO creditoDTO) {
        return creditoRepository.findById(id)
                .map(existingCredito -> {
                    existingCredito.setDescricao(creditoDTO.getDescricao());
                    existingCredito.setDataReferencia(creditoDTO.getDataReferencia());
                    existingCredito.setDataRecebimento(creditoDTO.getDataRecebimento());
                    existingCredito.setValorCredito(creditoDTO.getValorCredito());
                    existingCredito.setValorRecebido(creditoDTO.getValorRecebido());
                    existingCredito.setTipoRecebimento(creditoDTO.getTipoRecebimento());
                    existingCredito.calcularDiferenca();
                    return ResponseEntity.ok(creditoRepository.save(existingCredito));
                })
                .orElse(ResponseEntity.notFound().build());
    }

    public ResponseEntity<Object> deletarCredito(Long id) {
        return creditoRepository.findById(id)
                .map(credito -> {
                    creditoRepository.delete(credito);
                    return ResponseEntity.noContent().build();
                })
                .orElse(ResponseEntity.notFound().build());
    }

    public Double totalAReceber() {
        return creditoRepository.findAll().stream()
                .mapToDouble(Credito::getValorDiferenca)
                .sum();
    }
}
