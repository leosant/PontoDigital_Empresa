package com.pontoDigital.Service;

import java.io.Serializable;
import java.util.List;

import javax.swing.JOptionPane;

import com.pontoDigital.DAO.Interaction;
import com.pontoDigital.DAO.InteractionFuncDao;
import com.pontoDigital.Model.Entity.Funcionario;
import com.pontoDigital.Model.Enums.Status;
import com.pontoDigital.Model.Enums.Tipo;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.cell.PropertyValueFactory;

public class ServiceThree implements Serializable{
	
	private static final long serialVersionUID = 1L;

	private ServiceThree() {
		 throw new IllegalStateException("Utility class");
	}
	
	//List
	private static ObservableList<Funcionario> listObs= FXCollections.observableArrayList();
	private static Interaction<Funcionario> interFunc;
	
	public static void tbBuildEdit(TableView<Funcionario> tbFind, TableColumn<Funcionario, String> tcName) {
		tcName.setCellValueFactory(new PropertyValueFactory<Funcionario, String>("nome"));
		tbFind.setItems(updateTable(tbFind));
	}
	
	public static void tbBuildDelete(TableView<Funcionario> tbFind, TableColumn<Funcionario, String> tcName,
			TableColumn<Funcionario, String>tcCpf) {
		tcName.setCellValueFactory(new PropertyValueFactory<Funcionario, String>("nome"));
		tcCpf.setCellValueFactory(new PropertyValueFactory<Funcionario, String>("cpf"));
		tbFind.setItems(ServiceThree.updateTable(tbFind));
	}
	
	public static void tbSetItems(TableView<Funcionario> tbFind, TextField nome, TextField cpf, 
			TextField senha, ToggleGroup groupGrau, ToggleGroup groupPriv, RadioButton rbEfe,
			RadioButton rbAdmin, RadioButton rbDefault, RadioButton rbEst){
	
		Funcionario employer = tbFind.getSelectionModel().getSelectedItem();
		
		nome.setText(employer.getNome());
		cpf.setText(employer.getCpf());
		senha.setText(employer.getSenha());
		
		if(employer.getTipo().equals(Tipo.EFETIVO)) {
			groupGrau.selectToggle(rbEfe);
			if(employer.getStatus().equals(Status.ADMIN)) {
				groupPriv.selectToggle(rbAdmin);
			}else {
				groupPriv.selectToggle(rbDefault);
			}
		}else {
			groupGrau.selectToggle(rbEst);
			groupPriv.selectToggle(rbDefault);
		}
		
		tbFind.setVisible(false);
	}
	
	//AnchorPane edit and User - add and update
	public static void persistUser(TableView<Funcionario>tbFind, TextField nome, TextField cpf, TextField senha, ToggleGroup groupGrau,
			ToggleGroup groupPriv) {
	
		interFunc = new InteractionFuncDao();
		Funcionario func = tbFind.getSelectionModel().getSelectedItem();
		
		if(func == null) {
			func = new Funcionario();
		}
		
		RadioButton radioGrau = (RadioButton) groupGrau.getSelectedToggle();
		RadioButton radioPriv = (RadioButton) groupPriv.getSelectedToggle();
		
		radioGrau.setText(ServiceGlobal.removerAcentos(radioGrau.getText()));
		
		if(radioGrau.getText().equals("Estagiario")) {
			func.setTipo(Tipo.ESTAGIARIO);
			func.setCpf(cpf.getText());
			func.setNome(nome.getText());
			func.setSenha(senha.getText());
			func.setStatus(Status.DEFAULT);

		}
		
		radioPriv.setText(ServiceGlobal.removerAcentos(radioPriv.getText()));
		
		if(radioGrau.getText().equals("Efetivo")) {
			func.setTipo(Tipo.EFETIVO);
			func.setCpf(cpf.getText());
			func.setNome(nome.getText());
			func.setSenha(senha.getText());

			if(radioPriv.getText().equals("Padrao")) {
				func.setStatus(Status.DEFAULT);
			}else {
				func.setStatus(Status.ADMIN);
			}
		}
		try {
			interFunc.save(func);
			JOptionPane.showMessageDialog(null, "Funcionário salvo com sucesso!", "Concluído"
					, JOptionPane.INFORMATION_MESSAGE);
		}catch(Exception e) {
			JOptionPane.showMessageDialog(null, "Verifique a conexão com o banco de dados", "Erro no servidor"
					, JOptionPane.ERROR_MESSAGE);
		}
	}

	//AnchorPane edit - TableView - TextField
	public static ObservableList<Funcionario> findAutoComplete(TextField busca) {

		ObservableList<Funcionario> listSenFun = FXCollections.observableArrayList();
		
		for(int i = 0; i < listObs.size(); i++) {
			if(listObs.get(i).getNome().toLowerCase().contains
					(busca.getText().toLowerCase())) {
				listSenFun.add(listObs.get(i));
			}
		}
		
		return listSenFun;
		
	}
	
	//AnchorPane edit and remove - TableView (Adjusted)
	public static ObservableList<Funcionario> updateTable(TableView<Funcionario> tbFind) {
		tbFind.refresh();
		interFunc = new InteractionFuncDao();
		
		List<Funcionario> listFunc = interFunc.listAll();
		listObs= FXCollections.observableArrayList(listFunc);
		
		return listObs;
	}
	
	public static void visibleTable(TableView<Funcionario> table) {
		if(!(table.isVisible())) {
			table.setVisible(true);
		}	
	}
	
	public static Funcionario tbSelected(TableView<Funcionario>tb) {
		tb.refresh();
		tb.setVisible(false);
		return tb.getSelectionModel().getSelectedItem();
	}
	
	public static void removedEmployer(TableView<Funcionario> tb) {
		interFunc = new InteractionFuncDao();

		try {					
			interFunc.delete(tb.getSelectionModel().getSelectedItem().getId());
		}catch(Exception e) {
			JOptionPane.showMessageDialog(null, "Error no servido, verique a sua conexão ao banco de "
					+ "dados");
		}
	}

}
