package com.pontoDigital.Controllers;
import java.util.List;

import javax.swing.JOptionPane;

import com.pontoDigital.DAO.Interaction;
import com.pontoDigital.DAO.InteractionFuncDao;
import com.pontoDigital.Model.Entity.Funcionario;
import com.pontoDigital.Principal.ScreenThree;
import com.pontoDigital.Principal.ScreenTwo;
import com.pontoDigital.Service.ServiceGlobal;
import com.pontoDigital.Service.ServiceThree;

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
	
	//Class Orient Objects
	private Funcionario empregado;
	private ObservableList<Funcionario> listObsFunc = FXCollections.observableArrayList();
	
	//Layer DAO
	private Interaction<Funcionario> interactionDAO;
	
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
	public void adicionarUsario(){	
		//Create at controller	
		empregado = new Funcionario();
		interactionDAO = new InteractionFuncDao();
		
		try {
			interactionDAO.save(ServiceThree.persistUser(empregado, nomeFunc, cpfFunc, txtSenha,
					groupGrau, groupPriv));
			
		}catch(Exception e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, "Erro no servidor, verifique a conexão com o banco de dados");
		}
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
		//Edit
		if(paneEdit.isVisible()) { 
			clmNameEdit.setCellValueFactory(new PropertyValueFactory<Funcionario, String>("nome"));
			tbFindEdit.setItems(updateTable());	
		}
		//Remove
		if(paneRemove.isVisible()) {
			clmNameDelete.setCellValueFactory(new PropertyValueFactory<Funcionario, String>("nome"));
			clmCPFDelete.setCellValueFactory(new PropertyValueFactory<Funcionario, String>("cpf"));
			tbFindDelete.setItems(updateTable());
		}
		
	}
	
	//AnchorPane edit and remove - TableView (Adjusted)
	public ObservableList<Funcionario> updateTable() {
		tbFindEdit.refresh();
		interactionDAO = new InteractionFuncDao();
		List<Funcionario> listFunc = interactionDAO.listAll();
		listObsFunc = FXCollections.observableArrayList(listFunc);
		
		return listObsFunc;
	}
	
	//AnchorPane edit - TableView - TextField
	public void findDirect() {
		tbFindEdit.setItems(ServiceThree.findAutoComplete(txtFind, listObsFunc));
		tbFindDelete.setItems(ServiceThree.findAutoComplete(txtFindDelete, listObsFunc));
	}
	
	//AnchorPane edit - TableView - Selected (Adjusted)
	public void EditSelectedTU() {
		tbFindEdit.refresh();			
		empregado = tbFindEdit.getSelectionModel().getSelectedItem();
		
		ServiceThree.tbSetup(empregado, nomeFuncEdit, cpfFuncEdit, senhaFuncEdit, groupGrau, 
				groupPriv, rbEfeEdit, rbAdminEdit, rbDefaultEdit, rbEstEdit);
		
		tbFindEdit.setVisible(false);
	}
	
	//AnchorPane edit - button update
	public void updateUser(){
		interactionDAO = new InteractionFuncDao();
		
		empregado = tbFindEdit.getSelectionModel().getSelectedItem();
		
		lblFuncRemove.setText(empregado.getNome());
		
		try {
			int confirmUpdate = JOptionPane.showConfirmDialog(null, "Deseja realmente alterar esse usuário?",
					"Alterar Usuário ?", JOptionPane.YES_NO_OPTION);
			
			if(confirmUpdate == 0) {
				interactionDAO.save(ServiceThree.persistUser(empregado, nomeFuncEdit, cpfFuncEdit, senhaFuncEdit, 
						groupGrau, groupPriv));
			}
		}catch(Exception e) {
			JOptionPane.showMessageDialog(null, "Error no servido, verique a sua conexão ao banco de "
					+ "dados");
		}

	}
	
	//AnchorPane edit and remove - TableView - TextField
	public void startTable(){
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
		ServiceGlobal.paneVisibleIs(3, paneAddUser, paneEdit, paneRemove);
	}
	
	//AnchorPane remove - tableView -- VErificar a utilidade
	public void RemoveSelectedTU() {
		tbFindDelete.refresh();
		
		empregado = tbFindDelete.getSelectionModel().getSelectedItem();
		
		lblFuncRemove.setText(empregado.getNome()); 
		
		tbFindDelete.setVisible(false);
	}
	
	//AnchorPane remove - button remove
	public void removeUser() {
		interactionDAO = new InteractionFuncDao();
		empregado = tbFindDelete.getSelectionModel().getSelectedItem();
		
		try {
			int confirmDelete = JOptionPane.showConfirmDialog(null, "Atenção todos os dados desse funcionário será removidos,"
					+ "\n sem a possibilidade de recuperação!", "Deseja remover o usuário?", JOptionPane.YES_NO_OPTION,
					JOptionPane.INFORMATION_MESSAGE);
			
			if(confirmDelete == 0) {				
				interactionDAO.delete(empregado.getId());
			}
		}catch(Exception e) {
			JOptionPane.showMessageDialog(null, "Error no servido, verique a sua conexão ao banco de "
					+ "dados");
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