package com.fsc.postegresql.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "humano")
public class Pessoa {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "nome")
	private String nome;
	
	@Column(name = "idade")
	private Integer idade;
	
	@Column(name = "salario")
	private Double salario;
	
	@Column(name = "critao")
	private Boolean cristao;

	public Pessoa() {
		super();
	}

	public Pessoa(String nome, Integer idade, Double salario, Boolean cristao) {
		super();
		this.nome = nome;
		this.idade = idade;
		this.salario = salario;
		this.cristao = cristao;
	}

	public Pessoa(Long id, String nome, Integer idade, Double salario, Boolean cristao) {
		super();
		this.id = id;
		this.nome = nome;
		this.idade = idade;
		this.salario = salario;
		this.cristao = cristao;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Integer getIdade() {
		return idade;
	}

	public void setIdade(Integer idade) {
		this.idade = idade;
	}

	public Double getSalario() {
		return salario;
	}

	public void setSalario(Double salario) {
		this.salario = salario;
	}

	public Boolean getCristao() {
		return cristao;
	}

	public void setCristao(Boolean cristao) {
		this.cristao = cristao;
	}


	
	
	 @Override
	public String toString() {
		
		return "Olá, meu nome é " + nome + " a minha idade atual é " + idade +
				". Hoje o meu salário atual é de R$" + salario + 
				", e para finalizar essa entrevista  a minha respota se sou cristão é " + cristao + ".";
	}
	
	
}

