package com.desafio.user.model;

import java.util.List;

import com.desafio.telefone.model.Fone;

public interface UserDao {
	
	public void salvar(User user,List<Fone> listFone);
	public void update(User user);
	public void deletar(Integer id);
	public User getById(Integer id);
	public User getByEmail(String email);
	public List<User> listar();
	

}
