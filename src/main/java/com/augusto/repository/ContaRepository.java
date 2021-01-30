package com.augusto.repository;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.augusto.model.ContaPagar;
import com.augusto.model.dto.ContaDTO;
import com.augusto.model.dto.ListagemDeContaDTO;
import com.augusto.model.enums.SituacaoConta;

@Repository
public interface ContaRepository extends JpaRepository<ContaPagar, Long> {
	
	@Query("SELECT new com.augusto.model.dto.ListagemDeContaDTO(cp.id, cp.descricao, cp.vencimento, cp.comentarios,"
			+ " cp.situacaoConta, cp.comentarioDePagamento) "
			+ " FROM ContaPagar cp")
	Collection<ListagemDeContaDTO> obterContas(ContaDTO contaDTO);
	
	@Transactional
	@Modifying
	@Query(" update ContaPagar set situacaoConta = ?1, contaDeSaida.id = ?2, comentarioDePagamento = ?3 where id = ?4 ")
	void atualizarContaPagar(SituacaoConta pago, Long idContaSaida, String comentarioDePagamento, Long ContaPagar);

}
