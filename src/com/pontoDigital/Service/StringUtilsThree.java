package com.pontoDigital.Service;

import com.pontoDigital.Model.Funcionario;
import com.pontoDigital.Model.Status;
import com.pontoDigital.Model.Tipo;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;

public class StringUtilsThree {
	
	private StringUtilsThree() {
		throw new IllegalStateException("Utility class");
	 }
	
	public static void tbSetup(Funcionario empregado, TextField nome, TextField cpf, 
			TextField senha, ToggleGroup groupGrau, ToggleGroup groupPriv, RadioButton rbEfe,
			RadioButton rbAdmin, RadioButton rbDefault, RadioButton rbEst){
	
		nome.setText(empregado.getNome());
		cpf.setText(empregado.getCpf());
		senha.setText(empregado.getSenha());
		
		if(empregado.getTipo().equals(Tipo.EFETIVO)) {
			groupGrau.selectToggle(rbEfe);
			if(empregado.getStatus().equals(Status.ADMIN)) {
				groupPriv.selectToggle(rbAdmin);
			}else {
				groupPriv.selectToggle(rbDefault);
			}
		}else {
			groupGrau.selectToggle(rbEst);
			groupPriv.selectToggle(rbDefault);
		}
		
	}
	
	public static Funcionario persistUser(Funcionario empregado, TextField nome, TextField cpf, 
			TextField senha, ToggleGroup groupGrau, ToggleGroup groupPriv) {
		
		RadioButton radioGrau = (RadioButton) groupGrau.getSelectedToggle();
		RadioButton radioPriv = (RadioButton) groupPriv.getSelectedToggle();
		
		if(radioGrau.getText().equals("Estagiário")) {
			empregado.setTipo(Tipo.ESTAGIARIO);
			empregado.setCpf(cpf.getText());
			empregado.setNome(nome.getText());
			empregado.setSenha(senha.getText());
			empregado.setStatus(Status.DEFAULT);

		}else if(radioGrau.getText().equals("Efetivo")) {
			empregado.setTipo(Tipo.EFETIVO);
			empregado.setCpf(cpf.getText());
			empregado.setNome(nome.getText());
			empregado.setSenha(senha.getText());

			if(radioPriv.getText().equals("Padrão")) {
				empregado.setStatus(Status.DEFAULT);
			}else {
				empregado.setStatus(Status.ADMIN);
			}
		}
		
		return empregado;
	}
	
}
