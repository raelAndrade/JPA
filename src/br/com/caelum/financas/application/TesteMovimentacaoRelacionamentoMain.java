package br.com.caelum.financas.application;

import java.math.BigDecimal;
import java.util.Calendar;

import javax.persistence.EntityManager;

import br.com.caelum.financas.model.Conta;
import br.com.caelum.financas.model.Movimentacao;
import br.com.caelum.financas.model.TipoMovimentacao;
import br.com.caelum.financas.util.ConnectionJPA;

public class TesteMovimentacaoRelacionamentoMain {

	public static void main(String[] args) {
		
		Conta conta = new Conta();
		conta.setTitular("Israel");
		conta.setNumero("123456");
		conta.setBanco("Nubank");
		conta.setAgencia("001");
		
		Movimentacao movimentacao = new Movimentacao();
		movimentacao.setData(Calendar.getInstance());
		movimentacao.setDescricao("Churrascaria");
		movimentacao.setTipoMovimentacao(TipoMovimentacao.SAIDA);
		movimentacao.setValor(new BigDecimal("200.0"));
		
		movimentacao.setConta(conta);
		
		EntityManager em = new ConnectionJPA().getEntityManager();
		em.getTransaction().begin();
		
		em.persist(conta);
		em.persist(movimentacao);
		
		em.getTransaction().commit();
		em.close();

	}

}
