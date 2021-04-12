package com.pontoDigital.Service;

import javax.swing.JOptionPane;

import com.pontoDigital.DAO.InteractionDAO;
import com.pontoDigital.Model.Funcionario;
import com.pontoDigital.Model.Status;
import com.pontoDigital.Model.Tipo;

import javafx.application.Platform;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;

public class Utilitario {
	
	private static InteractionDAO interactioDAO = new InteractionDAO();
	
	public static void generateUser(Funcionario func, ToggleGroup rbG, ToggleGroup rbP, TextField nome, TextField cpf, TextField senha) throws Exception {
		//Funcionario generateFunc = func;
		RadioButton radioGrau = (RadioButton) rbG.getSelectedToggle();
		RadioButton radioPriv = (RadioButton) rbP.getSelectedToggle();
		//System.out.println("Generated"+generateFunc.getId());
		System.out.println("Func"+func.getId());
		try {
			if(radioGrau.getText().equals("Estagiário")) {
				func = new Funcionario(Tipo.ESTAGIARIO, cpf.getText(), nome.getText(), senha.getText());
				func.setStatus(Status.DEFAULT);
				
				
			}else if(radioGrau.getText().equals("Efetivo")) {
				func = new Funcionario(Tipo.EFETIVO, cpf.getText(), nome.getText(), senha.getText());
				if(radioPriv.getText().equals("Padrão")) {
					func.setStatus(Status.DEFAULT);
					
				}else {
					func.setStatus(Status.ADMIN);
				}
				
			}else if(radioGrau.getText().equals(null)){
				JOptionPane.showMessageDialog(null, "Marque a opção Grau do funcionário");
			}
		}finally {
			System.out.println("Operação foi um sucesso");
			if(!(func == null)) {
				interactioDAO.save(func);
			}
		}
	}
}
