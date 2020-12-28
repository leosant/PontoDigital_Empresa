package com.pontoDigital.Controllers;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.JOptionPane;

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
import javafx.util.Duration;

public class Controller{
    //Atributos especiais
    SimpleDateFormat sfd1 = new SimpleDateFormat("HH:mm:ss");
    SimpleDateFormat sfd2 = new SimpleDateFormat("dd/MM/yyyy");
    Date data = new Date();
    
    //Buttons and label´s
    @FXML
    private Button btPonto;
    @FXML
    private TextField txtLogin;
    
    @FXML
    private Label lblRelogio;
    @FXML
    private Label lblSeconds;
    @FXML
    private Label lblData;
    @FXML
    private Label lblDataCompleta;
    
    @FXML
    private ImageView setaPonto;
    @FXML
    private ImageView setaRelatorio;
    @FXML
    private ImageView setaSair;
    
    //AnchorPanel
    @FXML
    private AnchorPane relogioPainel;
    @FXML
    private AnchorPane relatorioPainel;

    public void botaoSair(MouseEvent event) {
        setaSair.setVisible(true);
        Platform.exit();
        System.exit(0);
    }

    public void botaoHoraRelogio() {
    	
    	labelsInstanciaBtr();

        KeyFrame frame = new KeyFrame(Duration.millis(1000), e -> atualizaHoras());
		Timeline timeline = new Timeline(frame);
		timeline.setCycleCount(Timeline.INDEFINITE);
		timeline.play();
    }
    
    public void registrarPonto() {
    	JOptionPane.showMessageDialog(null, "Construindo o registro");
    }

    public void botaoRelatorioPonto() {
        //True
        setaRelatorio.setVisible(true);
        relatorioPainel.setVisible(true);
        //False
        setaSair.setVisible(false);
        setaPonto.setVisible(false);
        relogioPainel.setVisible(false);
    }
    
    public void eventAcessarBD () {
    	JOptionPane.showMessageDialog(null, "Construindo o acesso do banco de dados");
    }
    
    private void atualizaHoras() {
		Date agora = new Date();
		lblRelogio.setText(sfd1.format(agora)); 
	}
    
    //Este metodo organiza as labels do evento Botao hora relogio
    private void labelsInstanciaBtr() {
    	 //True
        setaPonto.setVisible(true);
        relogioPainel.setVisible(true);
        //Label´s
        lblRelogio.setVisible(true);
        lblData.setVisible(true);
        lblDataCompleta.setVisible(true);
        //False
        setaSair.setVisible(false);
        setaRelatorio.setVisible(false);
        relatorioPainel.setVisible(false);
        //Interações
        //Relógio
        lblRelogio.setText(sfd1.format(data));
        lblRelogio.getText();
        //Data a parte Superior
        lblDataCompleta.setText(sfd2.format(data));
        lblDataCompleta.getText();
    }
}
