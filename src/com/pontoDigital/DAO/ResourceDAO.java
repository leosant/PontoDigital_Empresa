package com.pontoDigital.DAO;

import javax.persistence.EntityManager;

public interface ResourceDAO {
	
	EntityManager getEntityManager();
	
	void save(Object obj) throws Exception;
	
	void delete(Long id);
	
	Object findById(Long id);
}
