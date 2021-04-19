package com.pontoDigital.Model.Employer;
import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.Data;

@Entity
@Data
public class Funcionario  implements Serializable{
	
	private static final long serialVersionUID = -8364936441130394066L;
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Basic(fetch = FetchType.EAGER)
	private Integer id;
	
	@Enumerated(EnumType.STRING)
	@Basic(fetch = FetchType.LAZY)
	private Tipo tipo;
	
	@Basic(fetch = FetchType.EAGER)
	private String cpf;
	
	@Basic(fetch = FetchType.EAGER)
	private String nome;
	
	@Basic(fetch = FetchType.LAZY)
	private String senha;
	
	//Access to administration or default
	@Enumerated(EnumType.STRING)
	@Basic(fetch = FetchType.LAZY)
	private Status status;
	
	public Funcionario() {
		
	}
		
	public Funcionario(Tipo tipo, String cpf, String nome, String senha) {
		this.tipo = tipo;
		this.cpf = cpf;
		this.nome = nome;
		this.senha = senha;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
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
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
	

}
