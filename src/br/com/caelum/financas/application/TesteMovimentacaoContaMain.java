package br.com.caelum.financas.application;

import javax.persistence.EntityManager;

import br.com.caelum.financas.model.Conta;
import br.com.caelum.financas.model.Movimentacao;
import br.com.caelum.financas.util.ConnectionJPA;

public class TesteMovimentacaoContaMain {

	public static void main(String[] args) {
		
		EntityManager em = new ConnectionJPA().getEntityManager();
		em.getTransaction().begin();
		
		Movimentacao movimentacao = em.find(Movimentacao.class, 3);
		Conta conta = movimentacao.getConta();
		
		System.out.println("Titular: " + conta.getTitular());
		System.out.println("Quantidade de Movimentações: " + conta.getMovimentacoes().size());
		
		em.getTransaction().commit();
		em.close();

	}

}
