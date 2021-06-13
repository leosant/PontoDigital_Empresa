package com.pontoDigital.Service;

import com.pontoDigital.DAO.InteractionDAO;
import com.pontoDigital.Model.Employer.Funcionario;

import javafx.scene.control.TextField;

public class StringUtilsOne {
	
	public static Funcionario funcToAll(TextField login, TextField senha, InteractionDAO interactionDAO) {
		
		Funcionario acessoFunc = null;
		
		for(Funcionario emp : interactionDAO.listAll()) {
			if(login.getText().equals(emp.getNome()) 
					&& senha.getText().equals(emp.getSenha())) {
				acessoFunc = emp;
			}
		}
		
		return acessoFunc;
	}
	
}
