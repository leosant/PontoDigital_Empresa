package com.pontoDigital.Controllers;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.swing.JOptionPane;

import com.pontoDigital.DAO.InteractionDAO;
import com.pontoDigital.Model.Data.DataDays;
import com.pontoDigital.Model.Data.DataMonth;
import com.pontoDigital.Model.Data.DataYear;
import com.pontoDigital.Model.Employer.Funcionario;
import com.pontoDigital.Principal.ScreenOne;
import com.pontoDigital.Principal.ScreenTwo;
import com.pontoDigital.Service.StringUtilsOne;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.util.Duration;

public class Controller{
	//Special attribute
	SimpleDateFormat sfd1 = new SimpleDateFormat("HH:mm:ss");
	SimpleDateFormat sfd2 = new SimpleDateFormat("dd/MM/yyyy");
	SimpleDateFormat sfd3 = new SimpleDateFormat("yyyy");
	SimpleDateFormat sfd4 = new SimpleDateFormat("MM");
	SimpleDateFormat sfd5 = new SimpleDateFormat("dd");
	Date data = new Date();
	
	//Labels
	@FXML private Label lblUser;
	@FXML private Label lblRelogio;
	@FXML private Label lblSeconds;
	@FXML private Label lblData;
	@FXML private Label lblDataCompleta;
	
	//TextFields
	@FXML private TextField txtLoginUser;
	@FXML private TextField txtSenhaUser;
	@FXML private TextField txtLogin;
	@FXML private TextField txtSenha;

	//Buttons
	@FXML private Button btPonto;

	//ImageView
	@FXML private ImageView setaPonto;
	@FXML private ImageView setaRelatorio;
	@FXML private ImageView setaSair;

	//AnchorPanel
	@FXML private AnchorPane relogioPainel;
	@FXML private AnchorPane relatorioPainel;
	
	//Interaction with the layer database and Employer
	private InteractionDAO interactionDAO;
	
	//Teste
	EntityManager getEntityManager() {
		EntityManagerFactory factory = Persistence.createEntityManagerFactory("pontodigital");
		return factory.createEntityManager();
	}
	
	private DataYear dataYear;
	private DataMonth dataMonth;
	private DataDays dataDays;
	private Funcionario funcTest;

	//Part of guide button hours time
	public void botaoHoraRelogio() {
		interactionDAO = new InteractionDAO();
		dataYear = new DataYear();
		dataMonth = new DataMonth();
		dataDays = new DataDays();
		funcTest = StringUtilsOne.funcToAll(txtLoginUser, txtSenhaUser, interactionDAO);
		EntityManager em = getEntityManager();
		labelsInstanciaBtr();
			
		lblUser.setText(funcTest.getNome());
		dataYear.setYear(Integer.parseInt(sfd3.format(data)));
		dataMonth.setMonth(Integer.parseInt(sfd4.format(data)));
		dataDays.setDay(Integer.parseInt(sfd5.format(data)));
		
		dataYear.setFuncionarios(funcTest);
		dataMonth.setDataYear(dataYear);
		dataDays.setDataMonth(dataMonth);
	
		
		System.out.println("data resultado ano: " +dataYear.getYear());
		System.out.println("data resultado mês: " +dataMonth.getMonth());
		System.out.println("data resultado dia: " +dataDays.getDay());
		
		em.getTransaction().begin();
		em.merge(funcTest);
		em.merge(dataMonth);
		em.merge(dataDays);
		em.getTransaction().commit();
		em.close();
		
		KeyFrame frame = new KeyFrame(Duration.millis(1000), e -> atualizaHoras());
		Timeline timeline = new Timeline(frame);
		timeline.setCycleCount(Timeline.INDEFINITE);
		timeline.play();
	}

	// Part of guide button hours time. Here is the button to send data to the
	// database
	public void registrarPonto() {
		//TODO build call to my database (button)
		interactionDAO = new InteractionDAO();
		Funcionario testFunc = StringUtilsOne.funcToAll(txtLoginUser, txtSenhaUser, interactionDAO);
		
		JOptionPane.showMessageDialog(null, testFunc.getNome() + "\n" + lblRelogio.getText() + "\n"
				+ lblDataCompleta.getText() + "\nSalving my database");
	}

	//Part of guide button reports
	public void botaoRelatorioPonto() {
		//True
		setaRelatorio.setVisible(true);
		relatorioPainel.setVisible(true);
		//False
		setaSair.setVisible(false);
		setaPonto.setVisible(false);
		relogioPainel.setVisible(false);
	}

	//Part of guide reports
	public void eventAcessarBD () {
		interactionDAO = new InteractionDAO();
		Funcionario acessoFunc = null;
		
		acessoFunc = StringUtilsOne.funcToAll(txtLogin, txtSenha, interactionDAO);
		
		if((acessoFunc != null) || 
				txtLogin.getText().equals("admin") 
				&& txtSenha.getText().equals("admin")) {
			//Change for screen two
			try {
				new ScreenTwo().start(new Stage());
				ScreenOne.getStage().close();
			}catch(Exception e) {
				e.printStackTrace();
			}
			
		}else {
			JOptionPane.showMessageDialog(null, "Login e/ou Senha invalida!", "Error", JOptionPane.ERROR_MESSAGE);
		}
		
	}

	//Part of guide button of exit
	public void botaoSair() {
		setaSair.setVisible(true);
		Platform.exit();
	}

	//Function used to Thread Time line
	private void atualizaHoras() {
		Date agora = new Date();
		lblRelogio.setText(sfd1.format(agora)); 
	}

	//This function arranges the event button time labels
	private void labelsInstanciaBtr() {
		//True
		setaPonto.setVisible(true);
		relogioPainel.setVisible(true);
		//Label�s
		lblRelogio.setVisible(true);
		lblData.setVisible(true);
		lblDataCompleta.setVisible(true);
		//False
		setaSair.setVisible(false);
		setaRelatorio.setVisible(false);
		relatorioPainel.setVisible(false);
		//Hours Time
		lblRelogio.setText(sfd1.format(data));
		lblRelogio.getText();
		//Date part of up
		lblDataCompleta.setText(sfd2.format(data));
	}
	
}
