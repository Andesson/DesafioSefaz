package com.desafio.controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

import com.desafio.telefone.model.Fone;
import com.desafio.user.model.User;
import com.desafio.user.model.UserDaoJPA;

@ManagedBean(name = "userBean")
@RequestScoped
public class UserBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7187027331797693018L;

	// public static final String INJECTION_NAME = "#{UserBean}";

	private boolean isNovo = true;
	private boolean isEditar;

	private User user;
	private long id;

	private Fone fone;

	private List<Fone> listFone;

	// ~~~~~~~~~~~~~~~~~~~~~~~~~~~

	@PostConstruct
	public void init() {
		novo();
	}

	public void novo() {
		limpar();
		user = new User();
		fone = new Fone();
		listFone = new ArrayList<Fone>();
	}

	public void limpar() {
		this.user = null;
		this.fone = null;
	}

	public void editarUser(Integer i) {
		user = new UserDaoJPA().getById(i);
		this.isEditar = true;
		this.isNovo = false;
		setUser(user);
	}

	public void deletarUser(Integer id) {
		UserDaoJPA userDAO = new UserDaoJPA();
		userDAO.deletar(id);
		addMessage("Sucesso!", "Registro deletado com sucesso");
		novo();
	}

	public void salvar() {
		try {
			UserDaoJPA userDAO = new UserDaoJPA();
			if(isNovo()) {
				userDAO.salvar(getUser(), getListFone());
				addMessage("Perfeito!", "Registro salvo com sucesso");
			}
			if(isEditar()) {
				userDAO.update(getUser());
				addMessage("Perfeito!", "Registro editado com sucesso");
			}
			limpar();
		} catch (Exception e) {
			// TODO: handle exception
			addMessage("System Error!", "Erro ao salvar usuario");
		}
	}

	public void isValidoEmail() {
		UserDaoJPA userDAO = new UserDaoJPA();
		User u = userDAO.getByEmail(getUser().getEmail());
		if (u != null) {
			
			setEditar(true);
			setNovo(false);
			setUser(u);
			addMessage("Perfeito!", "Registro localizado no banco de dados");
		}else {
			setNovo(true);
			setEditar(false);
		}
	}

	public void addFone() {
		try {
			listFone.add(fone);
			fone = new Fone();
			addMessage("Perfeito!", "Telefone adicionado com sucesso");
		} catch (Exception e) {
			// TODO: handle exception
			addMessage("System Error!", "Erro ao adicionar Telefone");
		}
	}

	public List<Fone> listarFones() {
		return null;
	}

	public List<User> listarUser() {
		try {
			UserDaoJPA userDAO = new UserDaoJPA();
			return userDAO.listar();
		} catch (Exception e) {
			addMessage("System Error!", "Erro ao listar usuarios");
			return null;
		}
	}

	public String logout() {
		((HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(false)).invalidate();
		return "login";
	}

	public void addMessage(String summary, String detail) {
		FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, summary, detail);
		FacesContext.getCurrentInstance().addMessage(null, message);
	}

	// ~~~~~~~~~~ Get setsssssss ~~~~~~~~~~
	public boolean isNovo() {
		return isNovo;
	}

	public void setNovo(boolean novo) {
		this.isNovo = novo;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Fone getFone() {
		return fone;
	}

	public void setFone(Fone fone) {
		this.fone = fone;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public List<Fone> getListFone() {
		return listFone;
	}

	public void setListFone(List<Fone> listFone) {
		this.listFone = listFone;
	}

	public boolean isEditar() {
		return isEditar;
	}

	public void setEditar(boolean editar) {
		this.isEditar = editar;
	}

}
