package com.pontoDigital.DAO;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import javax.swing.JOptionPane;

import com.pontoDigital.DAO.Exceptions.DAOException;
import com.pontoDigital.Model.Entity.Funcionario;

public class InteractionFuncDao implements Interaction<Funcionario>{
	
	@Override
	public void save(Funcionario func) throws DAOException {
		EntityManager em = getEntityManager();
		em.getTransaction().begin();
		
		if(func.getId() == null) {
			em.persist(func); //run insert
		}else {
			if(!em.contains(func)) {
				if(em.find(Funcionario.class, func.getId()) == null) {
					throw new DAOException("Verifique se o mesmo está selecionado."
							+ "\n Caso o problema persista, contate o desenvolvedor");
					//Title: Erro na busca do funcionario.
				}
			}
			em.merge(func);
		}				
		em.getTransaction().commit();
		em.close();
		//			//Erro no Banco de dados
		//			throw new DAOException("Verifique se o banco de dados encontra se disponível."
		//					+ "\n Caso o problema persista, contate o desenvolvedor");
	}

	@Override
	public List<Funcionario> listAll() {
		EntityManager em = getEntityManager();	
		CriteriaBuilder criteriaBuilder = em.getCriteriaBuilder();

		CriteriaQuery<Funcionario> criteriaQuery = criteriaBuilder.createQuery(Funcionario.class);
		Root<Funcionario> root = criteriaQuery.from(Funcionario.class);

		criteriaQuery.select(root);

		TypedQuery<Funcionario> typedQuery = em.createQuery(criteriaQuery);	
		return typedQuery.getResultList();
		
	}

	@Override
	public Funcionario findById(Integer id) {
		EntityManager em = getEntityManager();
		Funcionario func = null;
		try {
			func = em.find(Funcionario.class, id);
		}catch(Exception e) {
			JOptionPane.showMessageDialog(null, "Funcionário não encontrado, verifique se o mesmo está na busca."
					+ "\n Caso o problema persista entre em contato com o desenvolvedor",
					"Erro na busca do funcionario",JOptionPane.ERROR_MESSAGE);
		}finally {
			em.close();
		}
		return func;
	}

	@Override
	public void delete(Integer id) {
		EntityManager em = getEntityManager();
		Funcionario func = em.find(Funcionario.class, id);
		try {
			em.getTransaction().begin();
			em.remove(func);
			em.getTransaction().commit();
		}catch(Exception e) {
			JOptionPane.showMessageDialog(null, "Não foi possível remover do banco de dados, "
					+ "verifique se o funcionario está selecionado."
					+ "\n Caso o problema persista entre em contato com o desenvolvedor",
					"Erro na busca do funcionario",JOptionPane.ERROR_MESSAGE);
		}finally {
			em.close();
		}

	}


}
