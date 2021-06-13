package com.pontoDigital.Controllers;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Date;

import javax.swing.JOptionPane;

import com.pontoDigital.DAO.InteractionDAO;
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
	Date data = new Date();
	SimpleDateFormat sfd1 = new SimpleDateFormat("HH:mm:ss");
	SimpleDateFormat sfd2 = new SimpleDateFormat("dd/MM/yyyy");
	SimpleDateFormat sfd3 = new SimpleDateFormat("yyyy");
	SimpleDateFormat sfd4 = new SimpleDateFormat("MM");
	SimpleDateFormat sfd5 = new SimpleDateFormat("dd");
	
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
	@FXML private Button btHelpMe;

	//ImageView
	@FXML private ImageView setaPonto;
	@FXML private ImageView setaRelatorio;
	@FXML private ImageView setaSair;

	//AnchorPanel
	@FXML private AnchorPane relogioPainel;
	@FXML private AnchorPane relatorioPainel;
	@FXML private AnchorPane paneConfig;
	
	//Interaction with the layer database and Employer
	private InteractionDAO interactionDAO;
	
	//Part of guide button hours time
	public void botaoHoraRelogio() {
		
		labelsInstanciaBtr();
		
		KeyFrame frame = new KeyFrame(Duration.millis(1000), e -> atualizaHoras());
		Timeline timeline = new Timeline(frame);
		timeline.setCycleCount(Timeline.INDEFINITE);
		timeline.play();
	}
	
	//Part of configuration manual of options button
	public void configurationManual() {
		paneConfig.setVisible(true);
	}
	
	public void configurationManualExit() {
		paneConfig.setVisible(false);
	}
	
	// Part of guide button hours time. Here is the button to send data to the database
	public void registrarPonto() throws Exception {
		
		interactionDAO = new InteractionDAO();
		DataYear dataYear = new DataYear();
		Funcionario managerFunc = StringUtilsOne.funcToAll(txtLoginUser, txtSenhaUser, interactionDAO);
		
		lblUser.setText(((Funcionario) managerFunc).getNome());
		//Verifica possibilidade de mudar para o LocalDate
		dataYear.setYear(Integer.parseInt(sfd3.format(data)));
		dataYear.setDays(Integer.parseInt(sfd5.format(data)));
		dataYear.setMonth(Integer.parseInt(sfd4.format(data)));
		dataYear.setFuncionarios(managerFunc);
		
		//Options office hour automatic
		StringUtilsOne.configurationOptionsAuto(dataYear);
		
		interactionDAO.saveDate(dataYear);
		
		JOptionPane.showMessageDialog(null, managerFunc.getNome() + "\n" + lblRelogio.getText() + "\n"
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
