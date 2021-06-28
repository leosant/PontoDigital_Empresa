package com.pontoDigital.Controllers;

import javax.swing.JOptionPane;

import com.pontoDigital.Model.Entity.Funcionario;
import com.pontoDigital.Principal.ScreenThree;
import com.pontoDigital.Principal.ScreenTwo;
import com.pontoDigital.Service.ServiceGlobal;
import com.pontoDigital.Service.ServiceThree;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
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
	
	//TextField - Edit
	@FXML private TextField nomeFuncEdit;
	@FXML private TextField cpfFuncEdit;
	@FXML private TextField senhaFuncEdit;
	@FXML private TextField txtFind;
	
	//TextField - Delete
	@FXML private TextField txtFindDelete; 
	
	//Label - Remove
	@FXML private Label lblFuncRemove;
	
	//Radio button - Edit
	@FXML private RadioButton rbEfeEdit;
	@FXML private RadioButton rbEstEdit;
	@FXML private RadioButton rbDefaultEdit;
	@FXML private RadioButton rbAdminEdit;
		
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
		
	//AnchorPane add
	public void clickAddUser() {
		ServiceGlobal.paneVisibleIs(1, paneAddUser, paneEdit, paneRemove);
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
	
	//AnchorPane user - add (Adjusted)
	public void addEmployer(){			
		ServiceThree.persistUser(tbFindEdit, nomeFunc, cpfFunc, txtSenha, groupGrau, groupPriv);
	}
	
	//AnchorPane edit
	public void clickEdit() {
		ServiceGlobal.paneVisibleIs(2, paneAddUser, paneEdit, paneRemove);
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
	
	//AnchorPane edit and delete - TableView (Adjusted)
	public void initTable() {
		if(paneEdit.isVisible()) { 
			ServiceThree.tbBuildEdit(tbFindEdit, clmNameEdit);
		}

		if(paneRemove.isVisible()) {
			ServiceThree.tbBuildDelete(tbFindDelete, clmNameDelete, clmCPFDelete);
		}
		
	}

	//AnchorPane edit - TableView - TextField
	public void findDirect() {
		tbFindEdit.setItems(ServiceThree.findAutoComplete(txtFind));
		tbFindDelete.setItems(ServiceThree.findAutoComplete(txtFindDelete));
	}
	
	//AnchorPane edit - TableView - Selected (Adjusted)
	public void EditSelectedTU() {
		ServiceThree.tbSetItems(tbFindEdit, nomeFuncEdit, cpfFuncEdit, senhaFuncEdit, groupGrau, 
				groupPriv, rbEfeEdit, rbAdminEdit, rbDefaultEdit, rbEstEdit);
	}
	
	//AnchorPane edit - button update
	public void updateUser(){
			int confirmUpdate = JOptionPane.showConfirmDialog(null, "Deseja realmente alterar esse usuário?",
					"Alterar Usuário ?", JOptionPane.YES_NO_OPTION);
			
			if(confirmUpdate == 0) {
				ServiceThree.persistUser(tbFindEdit, nomeFuncEdit, cpfFuncEdit, senhaFuncEdit, 
						groupGrau, groupPriv);
			}
	}
	
	//AnchorPane edit and remove - TableView - TextField
	public void startTable(){
		initTable();
	
		if(paneEdit.isVisible()) {
			ServiceThree.visibleTable(tbFindEdit);
		}
		if(paneRemove.isVisible()) {
			ServiceThree.visibleTable(tbFindDelete);
		}
	}
	
	//AnchorPane remove
	public void clickRemove() {
		ServiceGlobal.paneVisibleIs(3, paneAddUser, paneEdit, paneRemove);
	}
	
	//AnchorPane remove - tableView - Selected (Adjusted)
	public void RemoveSelectedTU() {
		lblFuncRemove.setText(ServiceThree.tbSelected(tbFindDelete).getNome()); 
	}
	
	//AnchorPane remove - button remove
	public void removeUser() {
		int confirmDelete = JOptionPane.showConfirmDialog(null, "Atenção todos os dados desse funcionário será removidos,"
				+ "\n sem a possibilidade de recuperação!", "Deseja remover o usuário?", JOptionPane.YES_NO_OPTION,
				JOptionPane.INFORMATION_MESSAGE);
		if(confirmDelete == 0) {			
			ServiceThree.removedEmployer(tbFindDelete);
		}
		
	}
	
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
}