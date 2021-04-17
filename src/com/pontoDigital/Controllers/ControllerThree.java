package com.pontoDigital.Controllers;
import java.util.List;
import javax.swing.JOptionPane;
import com.pontoDigital.DAO.InteractionDAO;
import com.pontoDigital.Model.Funcionario;
import com.pontoDigital.Principal.ScreenThree;
import com.pontoDigital.Principal.ScreenTwo;
import com.pontoDigital.Service.StringUtils;
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
	private InteractionDAO interactioDAO;
	
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
	
	//AnchorPane user - add (Adjusted)
	public void adicionarUsario(){	
		//Create at controller	
		empregado = new Funcionario();
		interactioDAO = new InteractionDAO();
		
		try {
			interactioDAO.save(StringUtils.persistUser(empregado, nomeFunc, cpfFunc, txtSenha,
					groupGrau, groupPriv));
			
		}catch(Exception e) {
			JOptionPane.showMessageDialog(null, "Erro no servidor, verifique a conexão com o banco de"
					+ "dados");
		}
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
		interactioDAO = new InteractionDAO();
		List<Funcionario> listFunc = interactioDAO.listAll();
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

	//AnchorPane edit - TableView - TextField
	public void findDirect() {
		tbFindEdit.setItems(find());
	}
	
	//AnchorPane edit - TableView - Selected (Adjusted)
	public void EditSelectedTU() {
		tbFindEdit.refresh();			
		empregado = tbFindEdit.getSelectionModel().getSelectedItem();
		
		StringUtils.tbSetup(empregado, nomeFuncEdit, cpfFuncEdit, senhaFuncEdit, groupGrau, 
				groupPriv, rbEfeEdit, rbAdminEdit, rbDefaultEdit, rbEstEdit);
		
		tbFindEdit.setVisible(false);
	}
	
	//AnchorPane edit - button update
	public void updateUser(){
		interactioDAO = new InteractionDAO();
		
		empregado = tbFindEdit.getSelectionModel().getSelectedItem();
		
		lblFuncRemove.setText(empregado.getNome());
		
		try {
			int confirmUpdate = JOptionPane.showConfirmDialog(null, "Deseja realmente alterar esse usuário?",
					"Alterar Usuário ?", JOptionPane.YES_NO_OPTION);
			
			if(confirmUpdate == 0) {
				interactioDAO.save(StringUtils.persistUser(empregado, nomeFuncEdit, cpfFuncEdit, senhaFuncEdit, 
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
		paneVisibleIs(3);
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
		interactioDAO = new InteractionDAO();
		empregado = tbFindDelete.getSelectionModel().getSelectedItem();
		
		try {
			int confirmDelete = JOptionPane.showConfirmDialog(null, "Atenção todos os dados desse funcionário será removidos,"
					+ "\n sem a possibilidade de recuperação!", "Deseja remover o usuário?", JOptionPane.YES_NO_OPTION,
					JOptionPane.INFORMATION_MESSAGE);
			
			if(confirmDelete == 0) {				
				interactioDAO.delete(empregado.getId());
			}
		}catch(Exception e) {
			JOptionPane.showMessageDialog(null, "Error no servido, verique a sua conexão ao banco de "
					+ "dados");
		}
		
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
