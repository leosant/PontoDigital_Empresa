package com.pontoDigital.Controllers;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.swing.JOptionPane;

import com.pontoDigital.Model.Employeer.Efetivo;
import com.pontoDigital.Model.Employeer.Estagiario;
import com.pontoDigital.Model.Employeer.Funcionario;
import com.pontoDigital.Principal.ScreenThree;
import com.pontoDigital.Principal.ScreenTwo;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class ControllerThree{
	
	//TextField
	@FXML
	TextField nomeFunc;
	@FXML
	TextField cpfFunc;
	@FXML
	TextField priviFunc;
	
	//Label of test
	@FXML
	Label condicao;
	
	//RadioButton
	@FXML
	ToggleGroup groupGrau;
	@FXML
	ToggleGroup groupPriv;
	
	//AnchorPane
	@FXML
	AnchorPane paneAddUser;
	@FXML
	AnchorPane paneEdit;
	@FXML
	AnchorPane paneRemove;
	
	//Class Orient Objects
	Funcionario empregado;
	
	//JPA
	protected static EntityManager em;
	
	private static EntityManagerFactory emf;
	
	private static EntityManager getEntityManager() {
		
		if(emf == null) {
			emf = Persistence.createEntityManagerFactory("pontodigital");
		}
		
		return emf.createEntityManager();
	}
	
	public void clickAddUser() {
		paneVisibleIs(1);
	}
	
	public void clickEdit() {
		paneVisibleIs(2);
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
	
	public void adicionarUsario() {
		//Create at controller		
		RadioButton radioGrau = (RadioButton) groupGrau.getSelectedToggle();
		RadioButton radioPriv = (RadioButton) groupPriv.getSelectedToggle();
		System.out.println("Test value grupoGrau: "+groupGrau.getSelectedToggle());
		try {
			if(radioGrau.getText().equals("Estagiário")) {
				empregado = new Estagiario();
				empregado.setNome(nomeFunc.getText());
				empregado.setCpf(cpfFunc.getText());
				
				empregado.setPrivilegios("padrao");
				//remove before of test
				condicao.setText("Gravado com sucesso");	
			}else if(radioGrau.getText().equals("Efetivo")) {
				empregado = new Efetivo();
				empregado.setNome(nomeFunc.getText());
				empregado.setCpf(cpfFunc.getText());
				if(radioPriv.getText().equals("Padrão")) {
					empregado.setPrivilegios("padrao");
				}else {
					empregado.setPrivilegios("admin");
				}
				//remove before of test
				condicao.setText("Gravado com sucesso");
			}else if(radioGrau.getText().equals(null)){
				JOptionPane.showMessageDialog(null, "Marque a opção Grau do funcionário");
			}
			em = getEntityManager();
			em.getTransaction().begin();
			em.persist(empregado);
			em.getTransaction().commit();
		}catch(IllegalStateException e) {
			JOptionPane.showMessageDialog(null, "Ocorreu um erro no banco de dados !"
					+ "\nfeche aplicação e abra novamente, que tudo ocorrerá bem."
					+ "\nDesculpe o transtorno !");
			Platform.exit();
			e.printStackTrace();
		}catch(Exception e) {
			JOptionPane.showMessageDialog(null, "Infelizmente ocorreu um erro de gravação, "
					+ "verifique se o Banco de dados está disponível");
		}
	
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
