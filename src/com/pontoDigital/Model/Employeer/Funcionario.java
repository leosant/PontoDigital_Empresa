package com.pontoDigital.Model.Employeer;

import javax.persistence.*;

@Entity
public class Funcionario {
	
	@Id
	@GeneratedValue
	private int id;
	private String cpf;
	private String Nome;
	private String privilegios;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	
	public String getCpf() {
		return cpf;
	}

	public void setCpf(String i) {
		this.cpf = i;
	}
	public String getNome() {
		return Nome;
	}
	public void setNome(String nome) {
		Nome = nome;
	}
	
	public String getPrivilegios() {
		return privilegios;
	}
	public void setPrivilegios(String privilegios) {
		this.privilegios = privilegios;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((Nome == null) ? 0 : Nome.hashCode());
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
		if (Nome == null) {
			if (other.Nome != null)
				return false;
		} else if (!Nome.equals(other.Nome))
			return false;
		return true;
	}

}
