package com.pontoDigital.Model;

public enum Status {
	
	ADMIN("Admin"),
	DEFAULT("Padrao");
	
	private String descricao;
	
	Status(String descricao) {
		this.descricao = descricao;
	}
	
	public String getDescricao() {
		return descricao;
	}
}
