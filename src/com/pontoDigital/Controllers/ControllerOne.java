package com.pontoDigital.Controllers;

import javax.swing.JOptionPane;

import com.pontoDigital.Model.Entity.Funcionario;
import com.pontoDigital.Principal.ScreenOne;
import com.pontoDigital.Principal.ScreenTwo;
import com.pontoDigital.Service.ServiceGlobal;
import com.pontoDigital.Service.ServiceOne;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class ControllerOne{	
	//Labels
	@FXML private Label lblUser;
	@FXML private Label lblRelogio;
	@FXML private Label lblDataCompleta;
	
	//TextFields
	@FXML private TextField txtLoginUser;
	@FXML private TextField txtSenhaUser;
	@FXML private TextField txtLogin;
	@FXML private TextField txtSenha;

	//AnchorPanel
	@FXML private AnchorPane paneDeafult;
	@FXML private AnchorPane paneReports;
	@FXML private AnchorPane paneHours;
	@FXML private AnchorPane paneConfig;
	
	//Part of guide pane hours
	public void buttonHours() {
		ServiceGlobal.paneVisibleIs(1, paneHours, paneReports, paneDeafult);
		ServiceOne.countHours(lblRelogio);
		//Date part of up
		lblDataCompleta.setText(ServiceOne.sdf2.format(ServiceOne.data));
	}
	
	//Part of guide pane hours - configuration manual of options button
	public void configurationManual() {
		paneConfig.setVisible(true);
	}
	
	public void configurationManualExit() {
		paneConfig.setVisible(false);
	}
	
	//Part of guide pane hours - Here is the button to send data to the database.
	public void buttonPoint(){	
		Funcionario managerFunc = ServiceOne.funcToAll(txtLoginUser, txtSenhaUser);
		lblUser.setText(managerFunc.getNome());
		ServiceOne.registerPoint(managerFunc);
	}

	//Part of guide button reports
	public void buttonReports() {
		ServiceGlobal.paneVisibleIs(2, paneHours, paneReports, paneDeafult);
	}

	//Part of guide reports - for find employer in database and changes for screen two
	public void eventAcessarBD () {
		Funcionario acessoFunc = null;
		acessoFunc = ServiceOne.funcToAll(txtLogin, txtSenha);
		
		try {
			if((acessoFunc != null) || 
					txtLogin.getText().equals("admin") 
					&& txtSenha.getText().equals("admin")) {
				//Change for screen two
				new ScreenTwo().start(new Stage());
				ScreenOne.getStage().close();
			}
		}catch(Exception e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, "Login e/ou Senha invalida!", "Error autenticação",
			JOptionPane.ERROR_MESSAGE);
		}
	}

	public void buttonExit() {
		Platform.exit();
	}
}
