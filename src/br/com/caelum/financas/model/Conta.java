package br.com.caelum.financas.model;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Conta implements Serializable{

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	private String titular;
	private String numero;
	private String banco;
	private String agencia;
	
	// Os relacionamentos que são lista/conjuntos eles são considerados 'Lazy' (Carregamento preguiçoso), conhecido como N + 1
	// Relacionamento bidirecional, ou seja, as duas partes conhece o relacionamento
	@OneToMany(mappedBy = "conta")
	private List<Movimentacao> movimentacoes;
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getTitular() {
		return titular;
	}
	public void setTitular(String titular) {
		this.titular = titular;
	}
	public String getNumero() {
		return numero;
	}
	public void setNumero(String numero) {
		this.numero = numero;
	}
	public String getBanco() {
		return banco;
	}
	public void setBanco(String banco) {
		this.banco = banco;
	}
	public String getAgencia() {
		return agencia;
	}
	public void setAgencia(String agencia) {
		this.agencia = agencia;
	}
	
	public List<Movimentacao> getMovimentacoes(){
		return movimentacoes;
	}
	
}
