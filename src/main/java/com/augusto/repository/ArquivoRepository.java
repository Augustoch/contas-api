package com.augusto.repository;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.augusto.model.Arquivo;

@Repository
public interface ArquivoRepository extends JpaRepository<Arquivo, Long> {
	
	@Transactional
	@Query(" select a.data from Arquivo a where a.id = ?1 ")
	byte[] getData(Long id);
	
	@Query(" select new com.augusto.model.Arquivo(a.id, a.nome, a.tipoArquivo) FROM Arquivo a JOIN a.contaPagar cp where cp.id = ?1 ")
	Collection<Arquivo> getArquivos(Long idConta);
	
	@Transactional
	@Modifying
	@Query(" UPDATE Arquivo SET nome = ?2, data = ?3 WHERE id = ?1 ")
	void atualizar(Long idArquivo, String nomeArquivo, byte[] bytes);

}
