package com.exemple.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.exemple.models.Credito;

public interface CreditoRepository extends JpaRepository<Credito, Long> {
}
