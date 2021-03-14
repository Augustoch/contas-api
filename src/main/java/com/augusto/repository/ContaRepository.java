package com.augusto.repository;

import java.util.Collection;
import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.augusto.model.ContaPagar;
import com.augusto.model.dto.ListagemDeContaDTO;
import com.augusto.model.dto.PesquisaContaDTO;
import com.augusto.model.dto.SalvarPagamentoDTO;

@Repository
public interface ContaRepository extends JpaRepository<ContaPagar, Long> {

	@Query("SELECT NEW com.augusto.model.dto.ListagemDeContaDTO(cp.id, cp.descricao, cp.vencimento, cp.comentarios,"
			+ " cp.situacaoConta, cp.comentarioDePagamento, cs.descricao, er.descricao, ep.descricao, cp.dataPagamento) "
			+ " FROM ContaPagar cp "
			+ "	LEFT JOIN cp.contaDeSaida cs"
			+ " LEFT JOIN cp.empresaReponsavelConta er"
			+ "	LEFT JOIN cp.empresaPagamentoDaConta ep"
			+ " WHERE (cp.id = :#{#pesquisa.id} OR :#{#pesquisa.id} IS NULL) "
			+ " AND   (cp.descricao LIKE CONCAT('%', :#{#pesquisa.descricao} , '%') OR :#{#pesquisa.descricao} IS NULL) "
			+ " AND   (cp.vencimento BETWEEN :#{#pesquisa.vencInicial} AND :#{#pesquisa.vencFinal}) "
			//+ "		  OR ( :#{#pesquisa.vencInicial} IS NULL AND :#{#pesquisa.vencFinal} IS NULL ))"
			+ " AND   (cp.dataPagamento BETWEEN :#{#pesquisa.pagInicial} AND :#{#pesquisa.pagFinal}) "
			//+ "		  OR (DATE(:#{#pesquisa.pagInicial}) IS NULL AND DATE(:#{#pesquisa.pagFinal})  IS NULL))"
			+ " AND   (cp.situacaoConta = :#{#pesquisa.situacaoConta} OR  :#{#pesquisa.situacaoConta} IS NULL)"
			+ " AND   (cp.contaDeSaida.id = :#{#pesquisa.idContaSaida} OR  :#{#pesquisa.idContaSaida} IS NULL) "
			+ " ORDER BY cp.vencimento ")
	List<ListagemDeContaDTO> obterContas(@Param("pesquisa") PesquisaContaDTO pesquisa);

	@Transactional
	@Modifying
	@Query(" UPDATE ContaPagar SET situacaoConta = :#{#pago.situacaoConta} ,"
			+ " contaDeSaida.id = :#{#pago.idContaSaida},"
			+ " comentarioDePagamento = :#{#pago.comentarioDePagamento},"
			+ " empresaPagamentoDaConta.id = :#{#pago.idEmpresaPagamento}, "
			+ " dataPagamento = :#{#pago.dataPagamento} "
			+ " WHERE id = :#{#pago.idContaPagar} ")
	void atualizarContaPagar(@Param(value = "pago") SalvarPagamentoDTO pago);

}
