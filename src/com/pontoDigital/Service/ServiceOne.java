package com.pontoDigital.Service;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.time.LocalTime;
import java.util.Date;

import javax.swing.JOptionPane;

import com.pontoDigital.DAO.Interaction;
import com.pontoDigital.DAO.InteractionDateDao;
import com.pontoDigital.DAO.InteractionFuncDao;
import com.pontoDigital.DAO.Exceptions.DAOException;
import com.pontoDigital.Model.Entity.DataYear;
import com.pontoDigital.Model.Entity.Funcionario;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.util.Duration;

public class ServiceOne implements Serializable{

	private static final long serialVersionUID = 1L;

	private ServiceOne() {
	    throw new IllegalStateException("Utility class");
	  }
	
	//Special attribute
	public static Date data = new Date();
	public static SimpleDateFormat sdf1 = new SimpleDateFormat("HH:mm:ss");
	public static SimpleDateFormat sdf2 = new SimpleDateFormat("dd/MM/yyyy");
	public static SimpleDateFormat sdf3 = new SimpleDateFormat("yyyy");
	public static SimpleDateFormat sdf4 = new SimpleDateFormat("MM");
	public static SimpleDateFormat sdf5 = new SimpleDateFormat("dd");
	
	//Part of guide button hours time
	public static void countHours(Label hours) {
		KeyFrame frame = new KeyFrame(Duration.millis(1000), e -> atualizaHoras(hours));
		Timeline timeline = new Timeline(frame);
		timeline.setCycleCount(Timeline.INDEFINITE);
		timeline.play();
	}
	
	//Function used to Thread Time line
	private static void atualizaHoras(Label lblRelogio) {
		Date agora = new Date();
		lblRelogio.setText(sdf1.format(agora)); 
	}
	
	//Find employer for the manager jpa
	public static Funcionario funcToAll(TextField login, TextField senha) {
		Interaction<Funcionario> interFunc = new InteractionFuncDao();
		
		//Changes the process of validation
		Funcionario acessoFunc = null;
		try {			
			for(Funcionario emp : interFunc.listAll()) {
				if(login.getText().equals(emp.getNome()) 
						&& senha.getText().equals(emp.getSenha())) {
					acessoFunc = emp;
				}
			}
		}catch(RuntimeException e) {
			JOptionPane.showMessageDialog(null, "Funcionário não encontrado, verifique se os dados"
					+ ", informados estão corretos.", "Erro ao encontrar funcionário", JOptionPane.ERROR_MESSAGE);
		}			
			return acessoFunc;
		
	}
	
	//Options office hour automatic
	private static void configurationOptionsAuto(DataYear dataYear) {
		
		if((LocalTime.now().isAfter(LocalTime.of(7, 59, 0)) &&
				(LocalTime.now().isBefore(LocalTime.of(9, 59, 0))))) {
			
			System.out.println("Salvando na hora inicio");
			dataYear.setEarlyHours(LocalTime.now());				
		}
		
		if((LocalTime.now().isAfter(LocalTime.of(10, 0, 0)))
				&& (LocalTime.now().isBefore(LocalTime.of(13, 29, 0)))) {
			
			System.out.println("Salvando no horário de almoço");
			dataYear.setEntranceLunch(LocalTime.now());
		}
		
		if((LocalTime.now().isAfter(LocalTime.of(13, 30, 0)))
				&& (LocalTime.now().isBefore(LocalTime.of(14, 30, 0)))) {
			
			System.out.println("Salvando na saída do almoço");
			dataYear.setExitLunch(LocalTime.now());
		}
		
		else {
			
			System.out.println("Salvando na saída");
			dataYear.setFinalHours(LocalTime.now());
		}
	
	}
	
	private static DataYear linkingDate(Funcionario func) {
		DataYear dataYear = new DataYear();
		
		configurationOptionsAuto(dataYear);
		dataYear.setYear(Integer.parseInt(sdf3.format(data)));
		dataYear.setDays(Integer.parseInt(sdf5.format(data)));
		dataYear.setMonth(Integer.parseInt(sdf4.format(data)));
		dataYear.setFuncionarios(func);
		
		return dataYear;
	}
	
	public static void registerPoint(Funcionario func) {
		Interaction<DataYear> interDateDao = new InteractionDateDao();
		linkingDate(func);
		
		try {
			interDateDao.save(linkingDate(func));
			JOptionPane.showMessageDialog(null, "Horário registrado!", "Registrado!", 
			JOptionPane.INFORMATION_MESSAGE);
		} catch (DAOException e) {
			JOptionPane.showMessageDialog(null, e.getMessage(), "Erro ao bater o ponto",
					JOptionPane.ERROR_MESSAGE);
		}
	}
}
