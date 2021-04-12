package com.pontoDigital.Controllers;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.swing.JOptionPane;

import com.mysql.cj.util.Util;
import com.pontoDigital.DAO.InteractionDAO;
import com.pontoDigital.Model.Funcionario;
import com.pontoDigital.Model.Status;
import com.pontoDigital.Model.Tipo;
import com.pontoDigital.Principal.ScreenThree;
import com.pontoDigital.Principal.ScreenTwo;
import com.pontoDigital.Service.Utilitario;

import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class ControllerThree{
	
	//TableView - edit
    @FXML private TableView<Funcionario> tbFindEdit;
    @FXML private TableColumn<Funcionario, String> clmNameEdit;
    
    //TableView - Delete
    @FXML private TableView<Funcionario> tbFindDelete;
    @FXML private TableColumn<Funcionario, String> clmNameDelete;
    @FXML private TableColumn<Funcionario, String> clmCPFDelete;
	
	//TextField - User
	@FXML private TextField nomeFunc;
	@FXML private TextField cpfFunc;
	@FXML private TextField txtSenha;
	@FXML private TextField txtFind;
	
	//TextField - Edit
	@FXML private TextField nomeFuncEdit;
	@FXML private TextField cpfFuncEdit;
	@FXML private TextField senhaFuncEdit;
	
	//Radio button - Edit
	@FXML private RadioButton rbEfeEdit;
	@FXML private RadioButton rbEstEdit;
	@FXML private RadioButton rbDefaultEdit;
	@FXML private RadioButton rbAdminEdit;
	
	//Label of test
	@FXML private Label condicao;
	
	//ToggleGroup of radio Button
	@FXML private ToggleGroup groupGrau;
	@FXML private ToggleGroup groupPriv;
	
	//AnchorPane Visible
	@FXML private AnchorPane panePrivEdit;
	@FXML private AnchorPane panePrivUser;
	
	//AnchorPane
	@FXML private AnchorPane paneAddUser;
	@FXML private AnchorPane paneEdit;
	@FXML private AnchorPane paneRemove;
	
	//Class Orient Objects
	private Funcionario empregado;
	private ObservableList<Funcionario> listObsFunc = FXCollections.observableArrayList();
	
	//Layer DAO
	InteractionDAO interactioDAO = new InteractionDAO();
	
	//Layer of test
	@FXML private Label teste;
	
	//AnchorPane add
	public void clickAddUser() {
		paneVisibleIs(1);
		if(tbFindEdit.isVisible()) {
			tbFindEdit.setVisible(false);
		}
	}
	
	//AnchorPane user - Radio button
	public void visiblePaneUserPrivFalse() {
		if(panePrivUser.isVisible()) {
			panePrivUser.setVisible(false);
		}
		
	}
	//AnchorPane user - Radio button
	public void visiblePaneUserPrivTrue() {
		if(!(panePrivUser.isVisible())) {
			panePrivUser.setVisible(true);
		}
	}
	
	//AnchorPane user - add
	public void adicionarUsario() throws Exception {	
		//Create at controller	
		//Utilitario.generateUser(EditSelectedTU(), groupGrau, groupPriv, nomeFunc, cpfFunc, txtSenha);
	}
	
	//AnchorPane edit
	public void clickEdit() {
		paneVisibleIs(2);
	}
	//AnchorPane edit - Radio button
	public void visiblePaneEditPrivFalse() {
		if(panePrivEdit.isVisible()) {
			panePrivEdit.setVisible(false);
		}
	}
	//AnchorPane edit - Radio button
	public void visiblePaneEditPrivtrue() {
		if(!(panePrivEdit.isVisible())) {
			panePrivEdit.setVisible(true);
		}
	}
	//AnchorPane edit and delete - TableView
	public void initTable() {
		if(paneEdit.isVisible()) {
			clmNameEdit.setCellValueFactory(new PropertyValueFactory<Funcionario, String>("nome"));
			tbFindEdit.setItems(updateTable(interactioDAO.getEntityManager()));
		}if(paneRemove.isVisible()) {
			clmNameDelete.setCellValueFactory(new PropertyValueFactory<Funcionario, String>("nome"));
			clmCPFDelete.setCellValueFactory(new PropertyValueFactory<Funcionario, String>("cpf"));
			tbFindDelete.setItems(updateTable(interactioDAO.getEntityManager()));
		}
		
	}
	//AnchorPane edit and remove - TableView
	public ObservableList<Funcionario> updateTable(EntityManager em) {
		String consFind = "select f from Funcionario f";
		TypedQuery<Funcionario> sqlFind = em.createQuery(consFind, Funcionario.class);
		List<Funcionario> listFunc = sqlFind.getResultList();
		listObsFunc = FXCollections.observableArrayList(listFunc);
		return listObsFunc;
	}
	//AnchorPane edit - TableView - TextField
	public ObservableList<Funcionario> find() {
		ObservableList<Funcionario> listSenFun = FXCollections.observableArrayList();
		for(int i = 0; i < listObsFunc.size(); i++) {
			if(listObsFunc.get(i).getNome().toLowerCase().contains
					(txtFind.getText().toLowerCase())) {
				listSenFun.add(listObsFunc.get(i));
			}
		}
		return listSenFun;
	}
	//AnchorPane edit - TableView - Button - Verificar utilizacao
	public void clickFindEdit() {
 	}
	//AnchorPane edit - TableView - TextField
	public void findDirect() {
		tbFindEdit.setItems(find());
	}
	
	//AnchorPane edit - TableView - Selected
	public Funcionario EditSelectedTU() {
		Funcionario auxEmpregado;
		empregado = tbFindEdit.getSelectionModel().getSelectedItem();

		nomeFuncEdit.setText(empregado.getNome());
		cpfFuncEdit.setText(empregado.getCpf());
		senhaFuncEdit.setText(empregado.getSenha());
		
		if(empregado.getTipo().equals(Tipo.EFETIVO)) {
			groupGrau.selectToggle(rbEfeEdit);
			if(empregado.getStatus().equals(Status.ADMIN)) {
				groupPriv.selectToggle(rbAdminEdit);
			}else {
				groupPriv.selectToggle(rbDefaultEdit);
			}
		}else {
			groupGrau.selectToggle(rbEstEdit);
		}
		
		auxEmpregado = empregado;
		
		return auxEmpregado;
	}
	
	//AnchorPane edit - button update
	public void updateUser() throws Exception {
		Utilitario.generateUser(EditSelectedTU(), groupGrau, groupPriv, nomeFuncEdit, cpfFuncEdit, senhaFuncEdit);
	}
	
	//AnchorPane edit and remove - TableView - TextField
	public void startTable() {
		initTable();
	
		if(paneEdit.isVisible()) {
			if(!(tbFindEdit.isVisible())) {
				tbFindEdit.setVisible(true);
			}
		}
		if(paneRemove.isVisible()) {
			if(!(tbFindDelete.isVisible())) {
				tbFindDelete.setVisible(true);
			}
		}
	}
	//AnchorPane remove
	public void clickRemove() {
		paneVisibleIs(3);
	}
	
	//AnchorPane remove - tableView
	public void TstSelecionadoTable() {
		/*
		 * funcTeste = tbFindDelete.getSelectionModel().getSelectedItem();
		 * teste.setText(funcTeste.getNome());
		 */
	}
	
	
	//Button closed
	public void closedApp() {
		Platform.exit();
	}
	//Button return in other screen
	public void returnScreenTwo() {
		try {		
			new ScreenTwo().start(new Stage());
			ScreenThree.getStage().close();
		}catch(Exception e){
			e.printStackTrace();
		}	
	}
	//Switch to AnchorPane
	public void paneVisibleIs(Integer flag) {
		
		if(paneAddUser.isVisible() || paneEdit.isVisible() || paneRemove.isVisible()) {
			paneAddUser.setVisible(false);
			paneEdit.setVisible(false);
			paneRemove.setVisible(false);
		}
			switch (flag){
			case 1: 
				paneAddUser.setVisible(true);
				break;
			case 2:
				paneEdit.setVisible(true);
				break;
			case 3:
				paneRemove.setVisible(true);
				break;
			}	
	}
	
}
