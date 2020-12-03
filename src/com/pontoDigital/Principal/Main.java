package com.pontoDigital.Principal;

import java.io.IOException;

import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class Main extends Application {

    private double xOffset;
    private double yOffset;
    //private Stage primaryStage;
    //private AnchorPane AplicationOne;
    //private Parent root;

    @Override
    public void start(Stage primaryStage) throws IOException{
    	
//    	this.primaryStage = primaryStage;
//    	this.primaryStage.initStyle(StageStyle.TRANSPARENT);
//    	this.primaryStage.setTitle("Ponto Digital - TDM");
    	
    	String localiUrl = "/com/pontoDigital/gui/AplicationOne.fxml";
    	
    	//Parent loader = FXMLLoader.load(getClass().getResource(localiUrl));
    	
    	//initMainStage(localiUrl);
    	//movitScene(loader, primaryStage);
    
    	
        Parent root = FXMLLoader.load(getClass().getResource(localiUrl));
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
                primaryStage.setX(mouseEvent.getScreenX() - xOffset);
                primaryStage.setY(mouseEvent.getScreenY() - yOffset);
            }
        });
        
        primaryStage.initStyle(StageStyle.TRANSPARENT);
        primaryStage.setTitle("Ponto Digital - TDM");
        Scene scene = new Scene(root);
        scene.setFill(Color.TRANSPARENT);
        scene.getStylesheets().add("/com/pontoDigital/gui/styleGlobal.css");
        primaryStage.setScene(scene);
        primaryStage.show();
        
    }


//    private void movitScene(Parent loader, Stage primaryStage){
//    	
//    	
//    	Parent root = loader;
//    	root.setOnMousePressed(new EventHandler<MouseEvent>() {
//            @Override
//            public void handle(MouseEvent mouseEvent) {
//                xOffset = mouseEvent.getSceneX();
//                yOffset = mouseEvent.getSceneY();
//            }
//        });
//    	root.setOnMouseDragged(new EventHandler<MouseEvent>() {
//            @Override
//            public void handle(MouseEvent mouseEvent) {
//                primaryStage.setX(mouseEvent.getScreenX() - xOffset);
//                primaryStage.setY(mouseEvent.getScreenY() - yOffset);
//            }
//        });
//		
//	}
//	private void initMainStage(String url) {
//    	try {
//    		FXMLLoader loader = new FXMLLoader();
//    		loader.setLocation(Main.class.getResource(url));
//    		this.AplicationOne = (AnchorPane) loader.load();
//    		
//    		Scene scene = new Scene(this.AplicationOne);
//    		scene.setFill(Color.TRANSPARENT);
//    		this.primaryStage.setScene(scene);
//    		this.primaryStage.show();
//    	}catch(IOException ex){
//    		ex.printStackTrace();
//    	}
//	}

	public static void main(String[] args) {
        launch(args);
    }

}
