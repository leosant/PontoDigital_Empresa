package com.pontoDigital.DAO;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import com.pontoDigital.Model.Funcionario;


public class InteractionDAO{
	
	
	public EntityManager getEntityManager() {
		EntityManagerFactory factory = Persistence.createEntityManagerFactory("pontodigital");
		return factory.createEntityManager();
	}
	//Methods are realized two operation to insert and update
	
	public Funcionario save(Funcionario func) throws Exception {
		EntityManager em = getEntityManager();
		
			try {
				em.getTransaction().begin();
				
				if(func.getId() == null) {
					this.findById(func.getId());
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
				}
			finally {
			em.close();
			}
			return func;
	}


	public void delete(Long id) {
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


	public Funcionario findById(int id) {
		EntityManager em = getEntityManager();
		Funcionario func = null;
		try {
			func = em.find(Funcionario.class, id);
		}finally {
			em.close();
		}
		return func;
	}
	
	public void altualizar(Funcionario fun) {
		EntityManager em = getEntityManager();
		fun = em.find(Funcionario.class, 1);
		em.getTransaction().begin();
		em.merge(fun);
		em.getTransaction().commit();
		em.close();
	}
}
