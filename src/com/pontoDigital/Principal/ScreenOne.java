package com.pontoDigital.Principal;

import java.io.IOException;

import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class ScreenOne extends Application {

    private double xOffset;
    private double yOffset;
    private static Stage stage;

	public static void main(String[] args) {
        launch(args);
    }
    
    @Override
    public void start(Stage primaryStage) throws IOException{
    	
    	String localiUrl = "/com/pontoDigital/gui/AplicationOne.fxml";
        Parent root = FXMLLoader.load(getClass().getResource(localiUrl));

        sceneMovit(root);
        
        primaryStage.initStyle(StageStyle.TRANSPARENT);
        primaryStage.setTitle("Ponto Digital - TDM");
        
        Scene scene = new Scene(root);
        scene.setFill(Color.TRANSPARENT);
        scene.getStylesheets().add("/com/pontoDigital/gui/styleGlobal.css");
        
        primaryStage.setScene(scene);
        primaryStage.show();
        
        ScreenOne.stage = primaryStage;   
    }
    
    public void sceneMovit(Parent root) {
    	//Methods to move application
        root.setOnMousePressed(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                xOffset = mouseEvent.getSceneX();
                yOffset = mouseEvent.getSceneY();
            }
        });
        root.setOnMouseDragged(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                getStage().setX(mouseEvent.getScreenX() - xOffset);
                getStage().setY(mouseEvent.getScreenY() - yOffset);
            }
        });
    }

	public static Stage getStage() {
		return stage;
	}
    
    
}
