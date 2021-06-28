package com.pontoDigital.Service;

import java.io.Serializable;
import java.text.Normalizer;

import javax.swing.JOptionPane;

import javafx.scene.layout.AnchorPane;

public class ServiceGlobal implements Serializable{
	
	private static final long serialVersionUID = 1L;

	private ServiceGlobal() {
	    throw new IllegalStateException("Utility class");
	  }
	
	//Switch to AnchorPane
	public static void paneVisibleIs(Integer flag, AnchorPane p1, AnchorPane p2, AnchorPane p3) {
		
		if(p1.isVisible() || p2.isVisible() || p3.isVisible()) {
			p1.setVisible(false);
			p2.setVisible(false);
			p3.setVisible(false);
		}
		
		switch(flag) {
		case 1:
			p1.setVisible(true);
			break;
		case 2:
			p2.setVisible(true);
			break;
		case 3:
			p3.setVisible(true);
			break;
		default:
			JOptionPane.showMessageDialog(null, "Desculpe ocorreu um erro distinto, retorne a "
					+ "página anterior.", "Error!", JOptionPane.ERROR_MESSAGE);
			break;
		}
	}
	
	public static String removerAcentos(String str) {
	    return Normalizer.normalize(str, Normalizer.Form.NFD).replaceAll("[^\\p{ASCII}]", "");
	}
}
