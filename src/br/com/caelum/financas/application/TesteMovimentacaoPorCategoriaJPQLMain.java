package br.com.caelum.financas.application;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import br.com.caelum.financas.model.Categoria;
import br.com.caelum.financas.model.Movimentacao;
import br.com.caelum.financas.util.ConnectionJPA;

public class TesteMovimentacaoPorCategoriaJPQLMain {

	public static void main(String[] args) {
		
		EntityManager em = new ConnectionJPA().getEntityManager();
		em.getTransaction().begin();
		
		Categoria categoria = new Categoria();
		categoria.setId(2);
		
		String jpql = "select m from Movimentacao m join m.categoria c where c = :pCategoria";
		
		Query query = em.createQuery(jpql);
		query.setParameter("pCategoria", categoria);
		
		List<Movimentacao> resultados = query.getResultList();
		
		for (Movimentacao movimentacao : resultados) {
			System.out.println("Descrição: " + movimentacao.getDescricao());
			System.out.println("Conta ID: " + movimentacao.getConta().getId());
			System.out.println();
		}
		
		em.getTransaction().commit();
		em.close();

	}

}
