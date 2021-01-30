package com.augusto.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.augusto.model.DadoContaBancaria;

@Repository
public interface DadoContaBancariaRepository extends JpaRepository<DadoContaBancaria, Long> {
	
}
