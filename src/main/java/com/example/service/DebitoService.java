package com.example.service;

import com.exemple.dto.DebitoDTO;
import com.exemple.models.Debito;
import com.exemple.repositories.DebitoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DebitoService {

    @Autowired
    private DebitoRepository debitoRepository;

    public List<Debito> listarTodos() {
        return debitoRepository.findAll();
    }

    public Debito criarDebito(DebitoDTO debitoDTO) {
        Debito debito = new Debito();
        debito.setDescricao(debitoDTO.getDescricao());
        debito.setDataReferencia(debitoDTO.getDataReferencia());
        debito.setDataPagamento(debitoDTO.getDataPagamento());
        debito.setValorDebito(debitoDTO.getValorDebito());
        debito.setValorPago(debitoDTO.getValorPago());
        debito.setTipoDespesa(debitoDTO.getTipoDespesa());
        debito.calcularDiferenca();
        return debitoRepository.save(debito);
    }

    public ResponseEntity<Debito> atualizarDebito(Long id, DebitoDTO debitoDTO) {
        return debitoRepository.findById(id)
                .map(existingDebito -> {
                    existingDebito.setDescricao(debitoDTO.getDescricao());
                    existingDebito.setDataReferencia(debitoDTO.getDataReferencia());
                    existingDebito.setDataPagamento(debitoDTO.getDataPagamento());
                    existingDebito.setValorDebito(debitoDTO.getValorDebito());
                    existingDebito.setValorPago(debitoDTO.getValorPago());
                    existingDebito.setTipoDespesa(debitoDTO.getTipoDespesa());
                    existingDebito.calcularDiferenca();
                    return ResponseEntity.ok(debitoRepository.save(existingDebito));
                })
                .orElse(ResponseEntity.notFound().build());
    }

    public ResponseEntity<Object> deletarDebito(Long id) {
        return debitoRepository.findById(id)
                .map(debito -> {
                    debitoRepository.delete(debito);
                    return ResponseEntity.noContent().build();
                })
                .orElse(ResponseEntity.notFound().build());
    }

    public Double totalAPagar() {
        return debitoRepository.findAll().stream()
                .mapToDouble(Debito::getValorDiferenca)
                .sum();
    }
}

