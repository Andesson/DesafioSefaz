package com.desafio.controller;

import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;

import com.desafio.user.model.User;
import com.desafio.user.model.UserDaoJPA;

@ManagedBean(name = "loginBean")
@RequestScoped
public class LoginBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5540895106430947645L;

	//@ManagedProperty(value = UserBean.INJECTION_NAME)
	private UserBean userBean;
	
	private User usuario;
	
	private String email;
	private String pass;

	private String mensagem = "";

	@PostConstruct
	public void init() {
		usuario = new User();
		limpar();
	}

	public void limpar() {
		email = "";
		pass = "";
	}
	
	private User isValidoLogin(String email, String senha) {
		User user = new UserDaoJPA().getByEmail(email);
		
		if(user == null || !senha.equals(user.getSenha())) {
			return null;
		}
		return user;
	}

	public String Login() {
		User user = isValidoLogin(email, pass);
		
		if (user != null) {
			//userBean.setUser(user);
			//FacesContext context = FacesContext.getCurrentInstance();
			//HttpServletRequest request = (HttpServletRequest) context.getExternalContext().getRequest();
			//request.getSession().setAttribute("user", user);
			return "default";
		}
		addMessage("Error!", "Verifique seu Login e senha");
		return null;

	}
	
	public void addMessage(String summary, String detail) {
		FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, summary, detail);
		FacesContext.getCurrentInstance().addMessage(null, message);
	}

	
	//~~~~~~~~~~~~~~~~~~~ GET SET ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
	public User getUsuario() {
		return usuario;
	}

	public void setUsuario(User usuario) {
		this.usuario = usuario;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPass() {
		return pass;
	}

	public void setPass(String pass) {
		this.pass = pass;
	}

	public String getMensagem() {
		return mensagem;
	}

	public void setMensagem(String mensagem) {
		this.mensagem = mensagem;
	}

}
