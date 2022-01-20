package com.project.modal;

import java.io.Serializable;
import java.util.List;


public class Smartphone implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -1326448912917383902L;

	private Long id;
	private String imei;

	private User user;
	


	public Smartphone(String imei) {
		super();
		this.imei = imei;
	}
	
	public Smartphone() {
		super();
	}
	
	

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getImei() {
		return imei;
	}

	public void setImei(String imei) {
		this.imei = imei;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}


	@Override
	public String toString() {
		return imei ;
	}
	
	
}
