package com.pontoDigital.Service;

import java.time.LocalTime;

import com.pontoDigital.DAO.InteractionDAO;
import com.pontoDigital.Model.Data.DataYear;
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
	
	public static void configurationOptionsAuto(DataYear dataYear) {
		
		if((LocalTime.now().isAfter(LocalTime.of(7, 59, 0)) &&
				(LocalTime.now().isBefore(LocalTime.of(9, 59, 0))))) {
			
			System.out.println("Salvando na hora inicio");
			dataYear.setEarlyHours(LocalTime.now());				
		}
		
		if((LocalTime.now().isAfter(LocalTime.of(10, 0, 0)))
				&& (LocalTime.now().isBefore(LocalTime.of(13, 29, 0)))) {
			
			System.out.println("Salvando no horário de almoço");
			dataYear.setEntranceLunch(LocalTime.now());
		}
		
		if((LocalTime.now().isAfter(LocalTime.of(13, 30, 0)))
				&& (LocalTime.now().isBefore(LocalTime.of(14, 30, 0)))) {
			
			System.out.println("Salvando na saída do almoço");
			dataYear.setExitLunch(LocalTime.now());
		}
		
		else {
			
			System.out.println("Salvando na saída");
			dataYear.setFinalHours(LocalTime.now());
		}
	}
	
}
