package com.pontoDigital.Controllers;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;
import javax.swing.JOptionPane;

import com.pontoDigital.Model.Efetivo;
import com.pontoDigital.Model.Estagiario;
import com.pontoDigital.Model.Funcionario;
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
	
	//TableView
    @FXML private TableView<Funcionario> tbFindEdit;
    @FXML private TableColumn<Funcionario, String> clmNameEdit;
	
	//TextField
	@FXML private TextField nomeFunc;
	@FXML private TextField cpfFunc;
	@FXML private TextField priviFunc;
	@FXML private TextField txtFind;
	
	//Label of test
	@FXML private Label condicao;
	
	//ToggleGroup of radio Button
	@FXML private ToggleGroup groupGrau;
	@FXML private ToggleGroup groupPriv;
	
	//AnchorPane Visible
	@FXML private AnchorPane panePriv;
	
	//AnchorPane
	@FXML private AnchorPane paneAddUser;
	@FXML private AnchorPane paneEdit;
	@FXML private AnchorPane paneRemove;
	
	//Class Orient Objects
	private Funcionario empregado;
	private ObservableList<Funcionario> listObsFunc = FXCollections.observableArrayList();
	
	//JPA
	private static EntityManager em;
	
	private static EntityManagerFactory emf;
	
	private static EntityManager getEntityManager() {
		
		if(emf == null) {
			emf = Persistence.createEntityManagerFactory("pontodigital");
		}
		
		return emf.createEntityManager();
	}
	//AnchorPane add
	public void clickAddUser() {
		paneVisibleIs(1);
		if(tbFindEdit.isVisible()) {
			tbFindEdit.setVisible(false);
		}
	}
	//AnchorPane add
	public void adicionarUsario() {
		
		//Create at controller		
		RadioButton radioGrau = (RadioButton) groupGrau.getSelectedToggle();
		RadioButton radioPriv = (RadioButton) groupPriv.getSelectedToggle();
		System.out.println("Test value grupoGrau: "+groupGrau.getSelectedToggle());
		try {
			if(radioGrau.getText().equals("Estagi�rio")) {
				//empregado = new Estagiario();
				empregado = new Funcionario();
				empregado.setNome(nomeFunc.getText());
				empregado.setCpf(cpfFunc.getText());
				
				empregado.setPrivilegios("padrao");
				//remove before of test
				condicao.setText("Gravado com sucesso");	
			}else if(radioGrau.getText().equals("Efetivo")) {
				//empregado = new Efetivo();
				empregado = new Funcionario();
				empregado.setNome(nomeFunc.getText());
				empregado.setCpf(cpfFunc.getText());
				if(radioPriv.getText().equals("Padr�o")) {
					empregado.setPrivilegios("padrao");
				}else {
					empregado.setPrivilegios("admin");
				}
				//remove before of test
				condicao.setText("Gravado com sucesso");
			}else if(radioGrau.getText().equals(null)){
				JOptionPane.showMessageDialog(null, "Marque a op��o Grau do funcion�rio");
			}
			em = getEntityManager();
			em.getTransaction().begin();
			em.persist(empregado);
			em.getTransaction().commit();
		}catch(IllegalStateException e) {
			JOptionPane.showMessageDialog(null, "Ocorreu um erro no banco de dados !"
					+ "\nfeche aplica��o e abra novamente, que tudo ocorrer� bem."
					+ "\nDesculpe o transtorno !");
			em.getTransaction().rollback();
			Platform.exit();
			e.printStackTrace();
		}catch(Exception e) {
			JOptionPane.showMessageDialog(null, "Infelizmente ocorreu um erro de grava��o, "
					+ "verifique se o Banco de dados est� dispon�vel");
			em.getTransaction().rollback();
		}
	
	}
	
	//AnchorPane edit
	public void clickEdit() {
		paneVisibleIs(2);
	}
	//AnchorPane edit - Radio button
	public void visiblePanePrivFalse() {
		if(panePriv.isVisible()) {
			panePriv.setVisible(false);
		}
		
	}
	//AnchorPane edit - Radio button
	public void visiblePanePrivtrue() {
		if(!(panePriv.isVisible())) {
			panePriv.setVisible(true);
		}
		
	}
	//AnchorPane edit - Table
	public void initTable() {
		em = getEntityManager();
		clmNameEdit.setCellValueFactory(new PropertyValueFactory<Funcionario, String>("nome"));
		tbFindEdit.setItems(updateTable(em));
	}
	//AnchorPane edit - TableView
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
	//AnchorPane edit - TableView - Button
	public void clickFindEdit() {
		/*Method old 
		tbFindEdit.setItems(find());*/
 	}
	//AnchorPane edit - TableView - TextField
	public void findDirect() {
		tbFindEdit.setItems(find());
	}
	//AnchorPane edit - TableView - TextField
	public void startTable() {
		initTable();
		if(!(tbFindEdit.isVisible())) {
			tbFindEdit.setVisible(true);
		}
		
	}
	
	public void clickRemove() {
		paneVisibleIs(3);
	}
	
	public void closedApp() {
		Platform.exit();
		if(!(em == null)) {
			em.close();
			emf.close();
		}
	}
	
	public void returnScreenTwo() {
		try {		
			new ScreenTwo().start(new Stage());
			ScreenThree.getStage().close();
		}catch(Exception e){
			e.printStackTrace();
		}
//		finally {
//			//Case return screen three. Warning: possible error
//			if(!(em == null)) {
//				em.close();
//				emf.close();
//			}
			
	}
	
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
