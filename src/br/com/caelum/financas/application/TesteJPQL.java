package br.com.caelum.financas.application;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import br.com.caelum.financas.model.Conta;
import br.com.caelum.financas.model.TipoMovimentacao;
import br.com.caelum.financas.util.ConnectionJPA;

public class TesteJPQL {

	public static void main(String[] args) {
		
		EntityManager em = new ConnectionJPA().getEntityManager();
		em.getTransaction().begin();
		
		Conta conta = new Conta();
		conta.setId(2);
		
		// NamedParameter :pConta, permiti pegar os dados da classe
		// Movimentação estamos referenciando a entidade Movimentacao e não a tabela movimentacao 
		String somaJpql = "select sum(m.valor) from Movimentacao m where m.conta = :pConta"
				+ " and m.tipo = :pTipo"
				+ " order by m.valor desc";
		
		String mediaJpql = "select avg(m.valor) from Movimentacao m where m.conta = :pConta"
				+ " and m.tipo = :pTipo"
				+ " order by m.valor desc";
		
		String mediaPorGrupo = "select distinct avg(m.valor) from Movimentacao m where m.conta = :pConta"
				+ " and m.tipo = :pTipo"
				+ " group by day(m.data), month(m.data), year(m.data)";
		
		Query queryCount = em.createQuery("select count(m) from Movimentacao m where m.conta = :pConta");
		queryCount.setParameter("pConta", conta);
		
		//Query query = em.createQuery(mediaPorGrupo);
		TypedQuery<Double> query = em.createQuery(mediaPorGrupo, Double.class);
		query.setParameter("pConta", conta);
		query.setParameter("pTipo", TipoMovimentacao.SAIDA);
		
		//BigDecimal soma = (BigDecimal) query.getSingleResult();
		//System.out.println("A soma é: " +  soma);
		
		//Double media = (Double) query.getSingleResult();
		//System.out.println("A média é: " + media);
		
		//Long quantidade =  (Long) queryCount.getSingleResult();
		//System.out.println("Total de movimentações: " + quantidade);
		
		List<Double> medias = (List<Double>) query.getResultList();
		System.out.println("A média do dia 26 é: " + medias.get(0));
		System.out.println("A média do dia 27 é: " + medias.get(1));
		
		
		em.getTransaction().commit();
		em.close();
	}

}
