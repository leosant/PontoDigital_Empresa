package com.pontoDigital.Controllers;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.prefs.PreferenceChangeEvent;
import java.util.prefs.Preferences;
import java.util.prefs.PreferencesFactory;

import javax.swing.JOptionPane;

import com.pontoDigital.DAO.InteractionDAO;
import com.pontoDigital.Model.Funcionario;
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
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.util.Duration;

public class Controller{
	//Special attribute
	SimpleDateFormat sfd1 = new SimpleDateFormat("HH:mm:ss");
	SimpleDateFormat sfd2 = new SimpleDateFormat("dd/MM/yyyy");
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

	//Part of guide button hours time
	public void botaoHoraRelogio() {
		interactionDAO = new InteractionDAO();
		labelsInstanciaBtr();
			
		lblUser.setText(StringUtilsOne.funcToAll(txtLoginUser, txtSenhaUser, interactionDAO).getNome());
		
		KeyFrame frame = new KeyFrame(Duration.millis(1000), e -> atualizaHoras());
		Timeline timeline = new Timeline(frame);
		timeline.setCycleCount(Timeline.INDEFINITE);
		timeline.play();
	}

	// Part of guide button hours time. Here is the button to send data to the
	// database
	public void registrarPonto() {
		//TODO build call to my database (button)
		JOptionPane.showMessageDialog(null, lblUser.getText() + "\n" + lblRelogio.getText() + "\n"
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
		lblDataCompleta.getText();
	}
	
}
