package com.example.demo;

import com.example.service.CreditoService;
import com.exemple.dto.CreditoDTO;
import com.exemple.models.Credito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/creditos")
@Validated
public class CreditoController {

    @Autowired
    private CreditoService creditoService;

    @GetMapping
    public List<Credito> listar() {
        return creditoService.listarTodos();
    }

    @PostMapping
    public Credito criar(@RequestBody CreditoDTO creditoDTO) {
        return creditoService.criarCredito(creditoDTO);
   
}}