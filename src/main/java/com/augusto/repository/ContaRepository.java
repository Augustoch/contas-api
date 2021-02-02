package com.augusto.repository;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.augusto.model.ContaPagar;
import com.augusto.model.dto.ContaDTO;
import com.augusto.model.dto.ListagemDeContaDTO;
import com.augusto.model.dto.PesquisaContaDTO;
import com.augusto.model.enums.SituacaoConta;

@Repository
public interface ContaRepository extends JpaRepository<ContaPagar, Long> {
	
	@Query("SELECT new com.augusto.model.dto.ListagemDeContaDTO(cp.id, cp.descricao, cp.vencimento, cp.comentarios,"
			+ " cp.situacaoConta, cp.comentarioDePagamento, cs.descricao) "
			+ " FROM ContaPagar cp LEFT JOIN cp.contaDeSaida cs"
			+ " WHERE (cp.id = :#{#pesquisaContaDTO.id} OR :#{#pesquisaContaDTO.id} IS NULL) "
			+ " AND   (cp.descricao LIKE CONCAT('%', :#{#pesquisaContaDTO.descricao} , '%') OR :#{#pesquisaContaDTO.descricao} IS NULL) "
			+ " AND   ((cp.vencimento BETWEEN :#{#pesquisaContaDTO.vencInicialDate} AND :#{#pesquisaContaDTO.vencFinalDate}) "
			+ "		  OR (:#{#pesquisaContaDTO.vencInicial} IS NULL AND :#{#pesquisaContaDTO.vencFinal} IS NULL))"
			+ " AND   (cp.situacaoConta = :#{#pesquisaContaDTO.situacaoConta} OR  :#{#pesquisaContaDTO.situacaoConta} IS NULL)"
			+ " AND   (cp.contaDeSaida.id = :#{#pesquisaContaDTO.idContaSaida} OR  :#{#pesquisaContaDTO.idContaSaida} IS NULL) "
			+ " ORDER BY cp.vencimento ")
	Collection<ListagemDeContaDTO> obterContas(@Param("pesquisaContaDTO") PesquisaContaDTO pesquisaContaDTO);
	
	@Transactional
	@Modifying
	@Query(" update ContaPagar set situacaoConta = ?1, contaDeSaida.id = ?2, comentarioDePagamento = ?3 where id = ?4 ")
	void atualizarContaPagar(SituacaoConta pago, Long idContaSaida, String comentarioDePagamento, Long ContaPagar);

}
