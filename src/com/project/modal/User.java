package com.project.modal;

import java.io.Serializable;
import java.util.Date;
import java.util.List;


public class User implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 2150125351904680509L;

	private Long id;
	private String nom;
	private String prenom;
	private String telephone;
	private String email;
	private Date dateNaiss;


	
	public User(String nom, String prenom, String telephone, String email, Date dateNaiss) {
		super();
		this.nom = nom;
		this.prenom = prenom;
		this.telephone = telephone;
		this.email = email;
		this.dateNaiss = dateNaiss;
	}


	public User() {
		super();
	}
	

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public String getPrenom() {
		return prenom;
	}

	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}

	public String getTelephone() {
		return telephone;
	}

	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}


	public Date getDateNaiss() {
		return dateNaiss;
	}


	public void setDateNaiss(Date dateNaiss) {
		this.dateNaiss = dateNaiss;
	}


	@Override
	public String toString() {
		return nom + " " + prenom;
	}
	
	

}
