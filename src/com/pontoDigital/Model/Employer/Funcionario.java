package com.pontoDigital.Model.Employer;
import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.pontoDigital.Model.Data.DataYear;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
@Table(name = "funcionarios")
public class Funcionario  implements Serializable{
	
	private static final long serialVersionUID = -8364936441130394066L;
	
	@EqualsAndHashCode.Include
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;
	
	@Enumerated(EnumType.STRING)
	private Tipo tipo;
	
	private String cpf;
	
	@EqualsAndHashCode.Include
	private String nome;
	
	private String senha;
	
	@OneToOne(mappedBy = "funcionarios")
	private DataYear dataYear;
	
	//Access to administration or default
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
		
}
