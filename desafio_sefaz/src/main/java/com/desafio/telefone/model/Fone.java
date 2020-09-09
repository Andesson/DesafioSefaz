package com.desafio.telefone.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.desafio.user.model.User;

@Entity
@Table(name ="telefone")
public class Fone implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1845220217233687516L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	@Column
	private int ddd;
	
	@Column
	private int numero;
	
	@Column
	private String tipo;
	
	@ManyToOne
	@JoinColumn(name = "id_usuario")
	private User usuario;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public int getDdd() {
		return ddd;
	}

	public void setDdd(int ddd) {
		this.ddd = ddd;
	}

	public int getNumero() {
		return numero;
	}

	public void setNumero(int numero) {
		this.numero = numero;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public User getUsuario() {
		return usuario;
	}

	public void setUsuario(User usuario) {
		this.usuario = usuario;
	}

	public Fone(long id, int ddd, int numero, String tipo, User usuario) {
		super();
		this.id = id;
		this.ddd = ddd;
		this.numero = numero;
		this.tipo = tipo;
		this.usuario = usuario;
	}

	public Fone() {
		super();
		// TODO Auto-generated constructor stub
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (int) (id ^ (id >>> 32));
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Fone other = (Fone) obj;
		if (id != other.id)
			return false;
		return true;
	}
	
	
	
	

}
