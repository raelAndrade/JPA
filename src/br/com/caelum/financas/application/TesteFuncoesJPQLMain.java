package br.com.caelum.financas.application;

import java.util.List;

import javax.persistence.EntityManager;

import br.com.caelum.financas.dao.MovimentacaoDao;
import br.com.caelum.financas.model.Conta;
import br.com.caelum.financas.model.TipoMovimentacao;
import br.com.caelum.financas.util.ConnectionJPA;

public class TesteFuncoesJPQLMain {

	public static void main(String[] args) {
		
		EntityManager em = new ConnectionJPA().getEntityManager();
		em.getTransaction().begin();
		
		Conta conta = new Conta();
		conta.setId(2);
		
		MovimentacaoDao dao = new MovimentacaoDao(em);
		
//		TypedQuery<Double> typedQuery = em.createNamedQuery("MediasPorDiaETipo", Double.class);
//		typedQuery.setParameter("pConta", conta);
//		typedQuery.setParameter("pTipo", TipoMovimentacao.SAIDA);
//		List<Double> medias = typedQuery.getResultList();
		
		List<Double> medias = dao.getMediasPorDiaETipo(TipoMovimentacao.SAIDA, conta); 
		for (Double media : medias) {
			System.out.println("A média é: " + media);
		}
		
		em.getTransaction().commit();
		em.close();
	}

}
