package com.pontoDigital.DAO;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.FlushModeType;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import javax.swing.JOptionPane;

import com.pontoDigital.Model.Funcionario;


public class InteractionDAO{
	
	
	public final EntityManager getEntityManager() {
		EntityManagerFactory factory = Persistence.createEntityManagerFactory("pontodigital");
		return factory.createEntityManager();
	}
	//Methods are realized two operation to insert and update
	
	public void save(Funcionario func) throws Exception {
		EntityManager em = getEntityManager();
		
			try {
				em.getTransaction().begin();
				if(func.getId() == null) {
					 em.persist(func); //run insert
					 
				}else {
					
					if(!em.contains(func)) {	
						
						if(em.find(Funcionario.class, func.getId()) == null) {
							throw new Exception("Erro ao atualizar o local");
						}
						
					}
					em.merge(func);
				}				
				em.getTransaction().commit();
				em.close();
			}
			finally {
				JOptionPane.showMessageDialog(null, "Concluído com sucesso");
			}
	}
	
	public List<Funcionario> listAll() {
		EntityManager em = getEntityManager(); 
		CriteriaBuilder criteriaBuilder = em.getCriteriaBuilder();
		
		CriteriaQuery<Funcionario> criteriaQuery = criteriaBuilder.createQuery(Funcionario.class);
		Root<Funcionario> root = criteriaQuery.from(Funcionario.class);
		
		criteriaQuery.select(root);
		
		TypedQuery<Funcionario> typedQuery = em.createQuery(criteriaQuery);	
		
		return typedQuery.getResultList();
	}


	public void delete(Integer id) {
		EntityManager em = getEntityManager();
		Funcionario func = em.find(Funcionario.class, id);
		try {
			em.getTransaction().begin();
			em.remove(func);
			em.getTransaction().commit();
		}finally {
			em.close();
		}
	}


	public Funcionario findById(Integer id) {
		EntityManager em = getEntityManager();
		Funcionario func = null;
		try {
			func = em.find(Funcionario.class, id);
		}finally {
			em.close();
		}
		return func;
	}
}
