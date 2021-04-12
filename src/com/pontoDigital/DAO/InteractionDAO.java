package com.pontoDigital.DAO;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import com.pontoDigital.Model.Funcionario;

public class InteractionDAO implements ResourceDAO{
	
	@Override
	public EntityManager getEntityManager() {
		EntityManagerFactory factory = Persistence.createEntityManagerFactory("pontodigital");
		return factory.createEntityManager();
	}
	//Methods are realized two operation to insert and update
	@Override
	public void save(Funcionario func) throws Exception {
		EntityManager em = getEntityManager();
		
			try {
				em.getTransaction().begin();
				if(func.getId() == null) {
					em.persist(func); //run insert
					System.out.println("Estou persistindo os dados");
				}else {
					if(!em.contains(func)) {
						if(em.find(Funcionario.class, func.getId()) == null) {
							throw new Exception("Erro ao atualizar o local");
						}
						em.merge(func); //run update
						System.out.println("Atualizando os dados");
					}
				}
				em.getTransaction().commit();
		
			}finally {
			em.close();
			}
	}

	@Override
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

	@Override
	public Object findById(Long id) {
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
