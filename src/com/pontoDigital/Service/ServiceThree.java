package com.pontoDigital.Service;

import java.io.Serializable;

import com.pontoDigital.Model.Entity.Funcionario;
import com.pontoDigital.Model.Enums.Status;
import com.pontoDigital.Model.Enums.Tipo;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;

public class ServiceThree implements Serializable{
	
	private static final long serialVersionUID = 1L;

	private ServiceThree() {
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
	
	//AnchorPane edit and User - add and update
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

	//AnchorPane edit - TableView - TextField
	public static ObservableList<Funcionario> findAutoComplete(TextField busca, ObservableList<Funcionario> listObsFunc) {

		ObservableList<Funcionario> listSenFun = FXCollections.observableArrayList();
		
		for(int i = 0; i < listObsFunc.size(); i++) {
			if(listObsFunc.get(i).getNome().toLowerCase().contains
					(busca.getText().toLowerCase())) {
				listSenFun.add(listObsFunc.get(i));
			}
		}
		
		return listSenFun;
		
	}

}
