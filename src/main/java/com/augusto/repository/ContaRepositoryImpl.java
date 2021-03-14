package com.augusto.repository;

import java.util.Collection;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.augusto.model.ContaPagar;
import com.augusto.model.dto.ListagemDeContaDTO;
import com.augusto.model.dto.PesquisaContaDTO;
import com.augusto.model.enums.SituacaoConta;

@Repository
public class ContaRepositoryImpl {

	@Autowired
	private EntityManager entityManager;

	public Collection<ListagemDeContaDTO> obterContas(PesquisaContaDTO pesquisa) {

		StringBuilder hql = new StringBuilder();

		hql.append(" SELECT NEW com.augusto.model.dto.ListagemDeContaDTO(cp.id, cp.descricao, cp.vencimento, cp.comentarios,");
		hql.append(
				" cp.situacaoConta, cp.comentarioDePagamento, cs.descricao, er.descricao, ep.descricao, cp.dataPagamento) ");
		hql.append(" FROM ContaPagar cp ");
		hql.append("	LEFT JOIN cp.contaDeSaida cs");
		hql.append(" LEFT JOIN cp.empresaReponsavelConta er");
		hql.append("	LEFT JOIN cp.empresaPagamentoDaConta ep");
		hql.append(" WHERE (cp.id = :id OR :id IS NULL) ");
		hql.append(" AND   (cp.descricao LIKE CONCAT('%', :descricao , '%') OR :descricao IS NULL) ");
		hql.append(" AND   (cp.vencimento BETWEEN :vencInicial AND :vencFinal) ");
		if (pesquisa.temDataPagamento())
			hql.append(" AND   (cp.dataPagamento BETWEEN :pagInicial AND :pagFinal) ");
		hql.append(" AND   (cp.situacaoConta = :situacaoConta OR  :situacaoConta IS NULL)");
		hql.append(" AND   (cp.contaDeSaida.id = :idContaSaida OR  :idContaSaida IS NULL) ");
		hql.append(" ORDER BY cp.vencimento ");

		Query query = this.entityManager.createQuery(hql.toString());

		query.setParameter("id", pesquisa.getId());
		query.setParameter("descricao", pesquisa.getDescricao());
		query.setParameter("vencInicial", pesquisa.getVencInicial());
		query.setParameter("vencFinal", pesquisa.getVencFinal());
		query.setParameter("vencFinal", pesquisa.getVencFinal());
		if (pesquisa.temDataPagamento()) {
			query.setParameter("pagInicial", pesquisa.getPagInicial());
			query.setParameter("pagFinal", pesquisa.getPagFinal());
		}
		query.setParameter("situacaoConta", pesquisa.getSituacaoConta());
		query.setParameter("idContaSaida", pesquisa.getIdContaSaida());
		
		return query.getResultList();

	}
}
