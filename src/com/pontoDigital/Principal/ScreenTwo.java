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

public class ScreenTwo extends Application{
	private Double xOffset;
	private Double yOffset;
	private static Stage stage;
	
	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage secondStage) throws IOException {
		
		String localiUrl = "/com/pontoDigital/gui/AplicationTwo.fxml";
		Parent root = FXMLLoader.load(getClass().getResource(localiUrl));
		
		sceneMovit(root);
		
		secondStage.initStyle(StageStyle.TRANSPARENT);
		secondStage.setTitle("Ponto Digital - TDM");
		
		Scene scene = new Scene(root);
		scene.setFill(Color.TRANSPARENT);
		
		secondStage.setScene(scene);
		secondStage.show();
		
		ScreenTwo.stage = secondStage;
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
