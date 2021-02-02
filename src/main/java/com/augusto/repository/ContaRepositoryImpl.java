package com.augusto.repository;

import java.util.Collection;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;

import com.augusto.model.dto.ListagemDeContaDTO;
import com.augusto.model.dto.PesquisaContaDTO;

@Repository
public class ContaRepositoryImpl {

	@PersistenceContext
	private EntityManager entityManager;
	
	public Collection<ListagemDeContaDTO> obterContas(PesquisaContaDTO pesquisaContaDTO){
		
		StringBuilder sql = new StringBuilder();
		
		sql.append(" SELECT new com.augusto.model.dto.ListagemDeContaDTO(cp.id, cp.descricao, cp.vencimento, cp.comentarios,");
		sql.append(" cp.situacaoConta, cp.comentarioDePagamento, cs.descricao) ");
		sql.append(" FROM ContaPagar cp LEFT JOIN cp.contaDeSaida cs");
		sql.append(" WHERE (cp.id = :pesquisaContaDTO.id OR :pesquisaContaDTO.id IS NULL) ");
		sql.append(" AND   (cp.descricao LIKE CONCAT('%', :pesquisaContaDTO.descricao , '%') OR :pesquisaContaDTO.descricao IS NULL) ");
		sql.append(" AND   ((cp.vencimento BETWEEN :pesquisaContaDTO.vencInicialDate AND :pesquisaContaDTO.vencFinalDate) ");
		sql.append(" 	  OR (:pesquisaContaDTO.vencInicial IS NULL AND :pesquisaContaDTO.vencFinal IS NULL))");
		sql.append(" AND   (cp.situacaoConta = :pesquisaContaDTO.situacaoConta OR  :pesquisaContaDTO.situacaoConta IS NULL)");
		sql.append(" AND   (cp.contaDeSaida.id = :pesquisaContaDTO.idContaSaida OR  :pesquisaContaDTO.idContaSaida IS NULL) ");
		sql.append(" ORDER BY cp.vencimento ");
		
		Query query = entityManager.createQuery(sql.toString());
		
		query.setParameter("id", pesquisaContaDTO.getId());
		query.setParameter("pesquisaContaDTO.descricao", pesquisaContaDTO.getDescricao());
		query.setParameter("pesquisaContaDTO.vencInicialDate", pesquisaContaDTO.getVencInicialDate());
		query.setParameter("pesquisaContaDTO.vencInicial", pesquisaContaDTO.getVencInicial());
		query.setParameter("pesquisaContaDTO.vencFinalDate", pesquisaContaDTO.getVencFinalDate());
		query.setParameter("pesquisaContaDTO.vencFinal", pesquisaContaDTO.getVencFinal());
		query.setParameter("pesquisaContaDTO.situacaoConta", pesquisaContaDTO.getSituacaoConta());
		query.setParameter("pesquisaContaDTO.idContaSaida", pesquisaContaDTO.getIdContaSaida());
		
		query.setMaxResults(50);
		
		return query.getResultList();
	}
	
}
