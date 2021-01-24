package com.pontoDigital.Controllers;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.swing.JOptionPane;

import com.pontoDigital.Model.Employeer.Efetivo;
import com.pontoDigital.Model.Employeer.Estagiario;
import com.pontoDigital.Model.Employeer.Funcionario;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class ControllerThree{
	
	@FXML
	TextField tipoFunc;
	@FXML
	TextField nomeFunc;
	@FXML
	TextField cpfFunc;
	@FXML
	TextField priviFunc;
	
	@FXML
	Label condicao;
	
	private static EntityManagerFactory emf;
	
	private static EntityManager getEntityManager() {
		
		if(emf == null) {
			emf = Persistence.createEntityManagerFactory("pontodigital");
		}
		
		return emf.createEntityManager();
	}
	
	Funcionario empregado;
	
	public void gravarBD() {
		
		try {
			if(tipoFunc.getText().equals("Estagiario")) {
				empregado = new Estagiario();
				empregado.setNome(nomeFunc.getText());
				empregado.setCpf(Integer.parseInt(cpfFunc.getText()));
				condicao = new Label();
				condicao.setText("Gravado com sucesso");
			}else if(tipoFunc.getText().equals("Efetivo")) {
				empregado = new Efetivo();
				empregado.setNome(nomeFunc.getText());
				empregado.setCpf(Integer.parseInt(cpfFunc.getText()));
				empregado.setPrivilegios(priviFunc.getText());
				condicao.setText("Gravado com sucesso");
			}else {
				condicao.setText("Tipo inválido");
			}
		}catch(Exception e) {
			JOptionPane.showConfirmDialog(null,"Infelizmente ocorreu um erro de gravação, "
					+ "verifique se o Banco de dados está disponível");
			e.printStackTrace();
		}
		
		EntityManager em = getEntityManager();
		em.getTransaction().begin();
		em.persist(empregado);
		em.getTransaction().commit();
		
		em.close();
		emf.close();
	}
}
