package com.pontoDigital.Model;

public enum Tipo {
	
	EFETIVO("Efetivo"), 
	ESTAGIARIO("Estagiario");
	
	private String descricao;
	
	Tipo(String descricao) {
		this.descricao = descricao;
	}

	public String getDescricao() {
		return descricao;
	}
}
