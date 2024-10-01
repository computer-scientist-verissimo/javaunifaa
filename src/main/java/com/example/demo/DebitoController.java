package com.example.demo;


import com.exemple.models.Debito;
import com.exemple.repositories.DebitoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/debitos")
public class DebitoController {

    @Autowired
    private DebitoRepository debitoRepository;

    @GetMapping
    public List<Debito> listar() {
        return debitoRepository.findAll();
    }

    @PostMapping
    public Debito criar(@RequestBody Debito debito) {
        debito.setValorDiferenca(debito.getValorDebito() - debito.getValorPago());
        return debitoRepository.save(debito);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Debito> atualizar(@PathVariable Long id, @RequestBody Debito debito) {
        return debitoRepository.findById(id)
                .map(existingDebito -> {
                    existingDebito.setDescricao(debito.getDescricao());
                    existingDebito.setDataReferencia(debito.getDataReferencia());
                    existingDebito.setDataPagamento(debito.getDataPagamento());
                    existingDebito.setValorDebito(debito.getValorDebito());
                    existingDebito.setValorPago(debito.getValorPago());
                    existingDebito.setTipoDespesa(debito.getTipoDespesa());
                    existingDebito.setValorDiferenca(existingDebito.getValorDebito() - existingDebito.getValorPago());
                    return ResponseEntity.ok(debitoRepository.save(existingDebito));
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deletar(@PathVariable Long id) {
        return debitoRepository.findById(id)
                .map(debito -> {
                    debitoRepository.delete(debito);
                    return ResponseEntity.noContent().build();
                })
                .orElse(ResponseEntity.notFound().build());
    }
}
