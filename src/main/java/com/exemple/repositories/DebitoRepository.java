package com.exemple.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.exemple.models.Debito;

public interface DebitoRepository extends JpaRepository<Debito, Long> {
}
