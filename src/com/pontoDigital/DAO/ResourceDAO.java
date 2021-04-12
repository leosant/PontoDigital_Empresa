package com.pontoDigital.DAO;

import javax.persistence.EntityManager;

import com.pontoDigital.Model.Funcionario;

public interface ResourceDAO {
	
	EntityManager getEntityManager();
	
	void save(Funcionario func) throws Exception;
	
	void delete(Long id);
	
	Object findById(Long id);
}
