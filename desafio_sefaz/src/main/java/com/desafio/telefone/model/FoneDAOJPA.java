package com.desafio.telefone.model;

import java.util.List;

import javax.persistence.EntityManager;

import com.desafio.user.model.User;
import com.hibernate.util.JPAUtil;

public class FoneDAOJPA implements FoneDAO {

	EntityManager entity = JPAUtil.getEntityManagerFactory().createEntityManager();

	@Override
	public void salvarFone(Fone fone) {
		try {
			entity.getTransaction().begin();
			entity.merge(fone);
			entity.flush();

		} catch (Exception e) {
			// TODO: handle exception
			entity.getTransaction().rollback();
		}
	}

	@Override
	public List<Fone> listarFone(User user) {
		// TODO Auto-generated method stub
		return null;
	}

}
