package com.augusto.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.augusto.model.Perfil;

@Repository
public interface PerfilRepository extends JpaRepository<Perfil, Long> {

}
