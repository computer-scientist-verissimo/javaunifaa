package com.example.demo;

import com.exemple.models.Credito;
import com.exemple.repositories.CreditoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/creditos")
public class CreditoController {

    @Autowired
    private CreditoRepository creditoRepository;

    @GetMapping
    public List<Credito> listar() {
        return creditoRepository.findAll();
    }

    @PostMapping
    public Credito criar(@RequestBody Credito credito) {
        credito.setValorDiferenca(credito.getValorCredito() - credito.getValorRecebido());
        return creditoRepository.save(credito);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Credito> atualizar(@PathVariable Long id, @RequestBody Credito credito) {
        return creditoRepository.findById(id)
                .map(existingCredito -> {
                    existingCredito.setDescricao(credito.getDescricao());
                    existingCredito.setDataReferencia(credito.getDataReferencia());
                    existingCredito.setDataRecebimento(credito.getDataRecebimento());
                    existingCredito.setValorCredito(credito.getValorCredito());
                    existingCredito.setValorRecebido(credito.getValorRecebido());
                    existingCredito.setTipoRecebimento(credito.getTipoRecebimento());
                    existingCredito.setValorDiferenca(existingCredito.getValorCredito() - existingCredito.getValorRecebido());
                    return ResponseEntity.ok(creditoRepository.save(existingCredito));
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deletar(@PathVariable Long id) {
        return creditoRepository.findById(id)
                .map(credito -> {
                    creditoRepository.delete(credito);
                    return ResponseEntity.noContent().build();
                })
                .orElse(ResponseEntity.notFound().build());
    }
}

