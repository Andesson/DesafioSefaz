package com.desafio.telefone.model;

import java.util.List;

import com.desafio.user.model.User;

public interface FoneDAO {
	
	public void salvarFone(Fone fone);
	public List<Fone> listarFone(User user);

}
