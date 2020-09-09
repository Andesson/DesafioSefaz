package com.desafio.user.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import com.desafio.telefone.model.Fone;
import com.desafio.telefone.model.FoneDAOJPA;
import com.hibernate.util.JPAUtil;

public class UserDaoJPA implements UserDao {

	EntityManager entity = JPAUtil.getEntityManagerFactory().createEntityManager();

	@Override
	public void salvar(User user, List<Fone> listFone ){
		try {
			entity.getTransaction().begin();
			entity.merge(user);
			entity.getTransaction().commit();
			FoneDAOJPA f = new FoneDAOJPA();
			for (Fone fone  : listFone) {
				fone.setUsuario(user);
				f.salvarFone(fone);
			}
		} catch (Exception e) {
			// TODO: handle exception
			entity.getTransaction().rollback();
		}
		
		finally {
			
		}

	}

	@Override
	public void deletar(Integer id) {
		try {
			User u = new User();
			entity.getTransaction().begin();
			u = entity.find(User.class, id);
			entity.remove(u);
			entity.getTransaction().commit();
			
		} catch (Exception e) {
			// TODO: handle exception
			entity.getTransaction().rollback();
		}

	}

	@Override
	public User getById(Integer id) {
		// TODO Auto-generated method stub
		User user = entity.find(User.class, id);
		return user;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<User> listar() {
		try {
			List<User> listaUser = new ArrayList<>();
			Query q = entity.createQuery("SELECT u FROM User u");
			listaUser = q.getResultList();
			return listaUser;
		} catch (Exception e) {
			// TODO: handle exception
			return null;
		}
	}

	public boolean Login(String email, String senha) {
		return true;
	}

	@Override
	public User getByEmail(String email) {
		try {
			User user = new User();
			Query query = entity.createQuery("from User where email = :e ");
			query.setParameter("e", email);
			user = (User) query.getSingleResult();
			return user;
			
		} catch (Exception e) {
			// TODO: handle exception
			return null;
		}
		// TODO Auto-generated method stub
	}

	@Override
	public void update(User user) {
		// TODO Auto-generated method stub
		try {
			 
			user = getByEmail(user.getEmail());
			entity.merge(user);
			FoneDAOJPA f = new FoneDAOJPA();
			for (Fone fone  : user.getTelefone()) {
				fone.setUsuario(user);
				f.salvarFone(fone);
			}
			
			
			
		} catch (Exception e) {
			// TODO: handle exception
			entity.getTransaction().rollback();
		}
		
		finally {
			
		}
	}
	

}
