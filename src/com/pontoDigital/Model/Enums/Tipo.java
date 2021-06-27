package com.pontoDigital.Model.Enums;

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
