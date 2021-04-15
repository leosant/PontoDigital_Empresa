package com.pontoDigital.Service;

import javax.swing.JOptionPane;

import com.pontoDigital.DAO.InteractionDAO;
import com.pontoDigital.Model.Funcionario;
import com.pontoDigital.Model.Status;
import com.pontoDigital.Model.Tipo;

import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;

public class Utilitario {
	
	private static InteractionDAO interactionDAO = new InteractionDAO();
	
	public static void generateUser(Funcionario func, ToggleGroup rbG, ToggleGroup rbP, TextField nome, TextField cpf, TextField senha) throws Exception {
		Funcionario generateFunc = new Funcionario();
		RadioButton radioGrau = (RadioButton) rbG.getSelectedToggle();
		RadioButton radioPriv = (RadioButton) rbP.getSelectedToggle();
		
		try {
			if(radioGrau.getText().equals("Estagiário")) {
				generateFunc.setId(func.getId());
				generateFunc.setTipo(Tipo.ESTAGIARIO);
				generateFunc.setCpf(func.getCpf());
				generateFunc.setNome(func.getNome());
				generateFunc.setSenha(func.getSenha());
				generateFunc.setStatus(Status.DEFAULT);	
				interactionDAO.save(generateFunc);
				
			}else if(radioGrau.getText().equals("Efetivo")) {
				func = new Funcionario(Tipo.EFETIVO, cpf.getText(), nome.getText(), senha.getText());
				if(radioPriv.getText().equals("Padrão")) {
					func.setStatus(Status.DEFAULT);
					interactionDAO.save(func);
					
				}else {
					func.setStatus(Status.ADMIN);
					interactionDAO.save(func);
				}
				
			}else if(radioGrau.getText().equals(null)){
				JOptionPane.showMessageDialog(null, "Marque a opção Grau do funcionário");
			}
		}catch(Exception e) {			
			throw e;
		}
	}
	
}
