package sample;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.control.Label;
import javafx.util.Duration;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Relogio{

    // criando o Label que vai informar as horas
    private Label lblRelogio = new Label();

    // SimpleDateFormat é a classe do Java que transforma datas para Strings
    // usando o formato passado
    private SimpleDateFormat formatador = new SimpleDateFormat("hh:mm:ss");

        // agora ligamos um loop infinito que roda a cada segundo e atualiza nosso label chamando atualizaHoras.
        KeyFrame frame = new KeyFrame(Duration.millis(1000), e -> atualizaHoras());
        Timeline timeline = new Timeline(frame);


    private void atualizaHoras() {
        Date agora = new Date();
        lblRelogio.setText(formatador.format(agora));
    }

}
