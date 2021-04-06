package com.pontoDigital.Controllers;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.swing.JOptionPane;

import com.pontoDigital.DAO.InteractionDAO;
import com.pontoDigital.Model.Funcionario;
import com.pontoDigital.Model.Status;
import com.pontoDigital.Model.Tipo;
import com.pontoDigital.Principal.ScreenThree;
import com.pontoDigital.Principal.ScreenTwo;

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
	private Funcionario empregadoUpdated;
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
	public void adicionarUsario() {	
		//Create at controller		
		RadioButton radioGrau = (RadioButton) groupGrau.getSelectedToggle();
		RadioButton radioPriv = (RadioButton) groupPriv.getSelectedToggle();
		try {
			if(radioGrau.getText().equals("Estagiário")) {
				empregado = new Funcionario(Tipo.ESTAGIARIO, cpfFunc.getText(), nomeFunc.getText(), txtSenha.getText());
				empregado.setStatus(Status.DEFAULT);
				//remove before of test
				condicao.setText("Gravado com sucesso");
				interactioDAO.save(empregado);
			}else if(radioGrau.getText().equals("Efetivo")) {
				empregado = new Funcionario(Tipo.EFETIVO, cpfFunc.getText(), nomeFunc.getText(), txtSenha.getText());
				if(radioPriv.getText().equals("Padrao")) {
					empregado.setStatus(Status.DEFAULT);
				}else {
					empregado.setStatus(Status.ADMIN);
				}
				//remove before of test
				condicao.setText("Gravado com sucesso");
				interactioDAO.save(empregado);
			}else if(radioGrau.getText().equals(null)){
				JOptionPane.showMessageDialog(null, "Marque a opção Grau do funcionário");
			}
		}catch(IllegalStateException e) {
			JOptionPane.showMessageDialog(null, "Ocorreu um erro no banco de dados!"
					+ "\nfeche aplicação e abra novamente, que tudo ocorrerá bem."
					+ "\nDesculpe o transtorno !");
			Platform.exit();
			e.printStackTrace();
		}catch(Exception e) {
			JOptionPane.showMessageDialog(null, "Infelizmente ocorreu um erro de gravação, "
					+ "verifique se o Banco de dados está disponível");
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
	public void EditSelectedTU() {
		empregado = tbFindEdit.getSelectionModel().getSelectedItem();

		nomeFuncEdit.setText(empregado.getNome());
		cpfFuncEdit.setText(empregado.getCpf());
		senhaFuncEdit.setText(empregado.getSenha());
		//rbEfeEdit.set
		
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
