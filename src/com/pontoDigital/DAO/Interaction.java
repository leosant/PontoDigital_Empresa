package com.pontoDigital.DAO;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import com.pontoDigital.DAO.Exceptions.DAOException;

public interface Interaction<T>{
	
	default EntityManager getEntityManager() {
		EntityManagerFactory factory = Persistence.createEntityManagerFactory("pontodigital");
		return factory.createEntityManager();
	}
	
	void save(T t) throws DAOException;
	
	List<T> listAll();
	
	T findById(Integer id);
	
	void delete(Integer id);
}
