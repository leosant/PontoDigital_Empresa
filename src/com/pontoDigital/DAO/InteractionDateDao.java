package com.pontoDigital.DAO;

import java.util.List;

import javax.persistence.EntityManager;

import com.pontoDigital.DAO.Exceptions.DAOException;
import com.pontoDigital.Model.Entity.DataYear;

public class InteractionDateDao implements Interaction<DataYear> {

	@Override
	public void save(DataYear data) throws DAOException {
		EntityManager em = getEntityManager();
		
		em.getTransaction().begin();
		if(data.getId() == null) {
			em.persist(data);
		}else {
			//Building exception
			throw new DAOException("Verifique a conexão com o banco de dados,"
					+ " caso o problema persista contate o desenvolvedor");
		}
		em.getTransaction().commit();
		em.close();
	}
	
	@Override
	public List<DataYear> listAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public DataYear findById(Integer id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void delete(Integer id) {
		// TODO Auto-generated method stub

	}

}
