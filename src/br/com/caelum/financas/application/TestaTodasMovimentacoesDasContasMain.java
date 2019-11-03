package br.com.caelum.financas.application;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import br.com.caelum.financas.model.Conta;
import br.com.caelum.financas.util.ConnectionJPA;

public class TestaTodasMovimentacoesDasContasMain {

	public static void main(String[] args) {
		
		EntityManager em = new ConnectionJPA().getEntityManager();
		em.getTransaction().begin();
		
		String jpql = "select c from Conta c join fetch c.movimentacoes";
		
		Query query = em.createQuery(jpql);
		
		List<Conta> contas = query.getResultList();
		for (Conta conta : contas) {
			System.out.println("Titular: " + conta.getTitular());
			System.out.println("Movimentações: " + conta.getMovimentacoes().size());
		}
		
		em.getTransaction().commit();
		em.close();
		
	}
}
