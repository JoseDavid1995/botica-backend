package com.example.botica.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.botica.entity.Medicamento;

import java.util.Optional;

public interface MedicamentoRepository extends JpaRepository<Medicamento, Long> {
    Optional<Medicamento> findByCodigoMedicamento(String codigo);
}