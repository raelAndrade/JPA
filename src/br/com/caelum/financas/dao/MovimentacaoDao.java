package br.com.caelum.financas.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import br.com.caelum.financas.model.Conta;
import br.com.caelum.financas.model.TipoMovimentacao;

public class MovimentacaoDao {

	private EntityManager em;
	
	public MovimentacaoDao(EntityManager em) {
		this.em = em;
	}

	public List<Double> getMediasPorDiaETipo(TipoMovimentacao saida, Conta conta) {
		// Movimentação estamos referenciando a entidade Movimentacao e não a tabela movimentacao 
		String mediaPorGrupo = "select distinct avg(m.valor) from Movimentacao m where m.conta = :pConta"
				+ " and m.tipo = :pTipo"
				+ " group by m.data";
		
		TypedQuery<Double> query = em.createQuery(mediaPorGrupo, Double.class);
		query.setParameter("pConta", conta);
		query.setParameter("pTipo", TipoMovimentacao.SAIDA);
		
		return query.getResultList();
	}
	
}
