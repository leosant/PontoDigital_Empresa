package com.pontoDigital.Controllers;

import com.pontoDigital.Principal.ScreenOne;
import com.pontoDigital.Principal.ScreenThree;
import com.pontoDigital.Principal.ScreenTwo;

import javafx.application.Platform;
import javafx.stage.Stage;

public class ControllerTwo {
		
	public void closedApp() {
		Platform.exit();
	}
	
	public void returnScreenOne() {
		try {
			new ScreenOne().start(new Stage());
			ScreenTwo.getStage().close();
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	public void newUser() {
		try {
			new ScreenThree().start(new Stage());
			ScreenTwo.getStage().close();
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
}
