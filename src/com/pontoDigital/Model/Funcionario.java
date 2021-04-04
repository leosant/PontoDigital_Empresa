package com.pontoDigital.Model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import com.sun.istack.NotNull;

@Entity
public class Funcionario {
	@Id
	@GeneratedValue
	private Long id;
	@Column(name = "tipo", length = 10, nullable = false)
	@Enumerated(EnumType.STRING)
	private Tipo tipo;
	@Column(name = "registro", length = 11, nullable = false, precision = 11)
	private String cpf;
	@Column(name = "nome", length = 150, nullable = false)
	private String nome;
	@Column(name = "senha", length = 4, nullable = false, precision = 4)
	private String senha;
	//Access to administration or default
	@Column(name = "status", length = 7, nullable = false)
	@Enumerated(EnumType.STRING)
	private Status status;
	
	public Funcionario() {
		
	}
		
	public Funcionario(Tipo tipo, String cpf, String nome, String senha) {
		this.tipo = tipo;
		this.cpf = cpf;
		this.nome = nome;
		this.senha = senha;
	}
	
	public Long getId() {
		return id;
	}
	public String getCpf() {
		return cpf;
	}
	public void setCpf(String i) {
		this.cpf = i;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public Status getStatus() {
		return status;
	}
	public void setStatus(Status status) {
		this.status = status;
	}
	public Tipo getTipo() {
		return tipo;
	}
	public void setTipo(Tipo tipo) {
		this.tipo = tipo;
	}
	public String getSenha() {
		return senha;
	}
	public void setSenha(String senha) {
		this.senha = senha;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((nome == null) ? 0 : nome.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Funcionario other = (Funcionario) obj;
		if (nome == null) {
			if (other.nome != null)
				return false;
		} else if (!nome.equals(other.nome))
			return false;
		return true;
	}
	

}
