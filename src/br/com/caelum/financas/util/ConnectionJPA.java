package br.com.caelum.financas.util;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class ConnectionJPA {

	private static EntityManagerFactory emf = Persistence.createEntityManagerFactory("contas-mysql");
	
	public EntityManager getEntityManager() {
		return emf.createEntityManager();
	}
}
