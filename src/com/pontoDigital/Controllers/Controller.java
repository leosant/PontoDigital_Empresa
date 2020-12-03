package com.pontoDigital.Controllers;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Controller{
    //Atributos especiais
    SimpleDateFormat sfd1 = new SimpleDateFormat("HH:mm:ss");
    SimpleDateFormat sfd2 = new SimpleDateFormat("dd/MM/yyyy");
    Date data = new Date();
    //Buttons and label´s
    @FXML
    private Button buttonPonto;
    @FXML
    private Label lblRelogio;
    @FXML
    private Label lblSeconds;
    @FXML
    private Label lblData;
    @FXML
    private Label lblDataCompleta;
    //ImageView
    /** Por enquanto não é util
     @FXML
     private ImageView imgPonto;
     @FXML
     private ImageView imgRelatorio;*/
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

    //Ponto digital nessa Parte -Atuando 15/11/2020
    public void botaoHoraRelogio() {
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

        //Teste Thread



        //Final teste Thread
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
}
