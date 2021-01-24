package com.pontoDigital.Model.Employeer;

import javax.persistence.*;
@Entity
public class Funcionario {
	
	@Id
	private int cpf;
	private String Nome;
	private String privilegios;
	/*Mudar para enum*/
	//private Boolean sexo;
	
	public int getCpf() {
		return cpf;
	}
	public void setCpf(int i) {
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
	
//	public Boolean getSexo() {
//		return sexo;
//	}
//	public void setSexo(Boolean sexo) {
//		this.sexo = sexo;
//	}
	
	
}
