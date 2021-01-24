package com.pontoDigital.Principal;

import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class ScreenThree extends Application{
	
	private Double xOffset;
	private Double yOffset;
	private static Stage stage;
	
	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage threeStage) throws Exception {
		
		String localiUrl = "/com/pontoDigital/gui/AplicationThree.fxml";
		Parent root = FXMLLoader.load(getClass().getResource(localiUrl));
		
		sceneMovit(root);
		
		threeStage.initStyle(StageStyle.TRANSPARENT);
		threeStage.setTitle("Ponto Digital - TDM");
		
		Scene scene = new Scene(root);
		scene.setFill(Color.TRANSPARENT);
		
		threeStage.setScene(scene);
		threeStage.show();
		
		ScreenThree.stage = threeStage;
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
