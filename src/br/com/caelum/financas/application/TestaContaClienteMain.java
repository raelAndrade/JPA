package br.com.caelum.financas.application;

import javax.persistence.EntityManager;

import br.com.caelum.financas.model.Cliente;
import br.com.caelum.financas.model.Conta;
import br.com.caelum.financas.util.ConnectionJPA;

public class TestaContaClienteMain {

	public static void main(String[] args) {
		
		Cliente cliente = new Cliente();
		cliente.setNome("Fulano da Silva");
		cliente.setEndereco("Rua Fulano, 123");
		cliente.setProfissao("Programador");
		
		Conta conta = new Conta();
		conta.setId(2);
		
		cliente.setConta(conta);
		
		EntityManager em = new ConnectionJPA().getEntityManager();
		em.getTransaction().begin();
		
		em.persist(cliente);
		
		em.getTransaction().commit();
		em.close();

	}

}
