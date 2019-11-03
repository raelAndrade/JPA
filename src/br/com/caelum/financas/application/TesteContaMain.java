package br.com.caelum.financas.application;

import javax.persistence.EntityManager;

import br.com.caelum.financas.model.Conta;
import br.com.caelum.financas.util.ConnectionJPA;

public class TesteContaMain {

	public static void main(String[] args) {
		
		// Estado Transient
		Conta conta1 = new Conta();
		conta1.setId(1);
		conta1.setNumero("Teste");
		conta1.setNumero("123456");
		conta1.setAgencia("123");
		conta1.setBanco("Teste");

		EntityManager em = new ConnectionJPA().getEntityManager();
		em.getTransaction().begin();

		// O método 'persist' transforma o estado para Managed
		// em.persist(conta1);

		// Nesse processo o hibernate busca a conta e muda o estado para Managed
		Conta conta = em.find(Conta.class, 3);
		
		// Alterar nome do titular da conta '3'
		conta.setTitular("Bianca");
		System.out.println(conta.getTitular());
		
		em.getTransaction().commit();
		em.close();
		
		EntityManager em2 = new ConnectionJPA().getEntityManager();
		em2.getTransaction().begin();
		
		conta1 = em2.find(Conta.class, 1);
		
		// Estado Detached, ou seja, essa conta não é mais gerenciada pela JPA.
		// conta1.setTitular("Zé da Feira");
		// em2.persist(conta);
		
		// Passa para o estado Managed pela JPA
		em2.merge(conta1);
		
		em2.remove(conta1);
		
		em2.getTransaction().commit();
		em2.close();
	}

}
