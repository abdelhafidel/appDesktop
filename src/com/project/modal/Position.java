package com.project.modal;

import java.io.Serializable;
import java.util.Date;


public class Position implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -586370704550585044L;

	private Long id;
	private String latitude;
	private String longitude;

	private Date date;

	private Smartphone smartphone;
	
	public Position(String latitude, String longitude, Date date) {
		super();
		this.latitude = latitude;
		this.longitude = longitude;
		this.date = date;
	}
	
	public Position() {
		super();
	}

	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getLatitude() {
		return latitude;
	}

	public void setLatitude(String latitude) {
		this.latitude = latitude;
	}

	public String getLongitude() {
		return longitude;
	}

	public void setLongitude(String longitude) {
		this.longitude = longitude;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public Smartphone getSmartphone() {
		return smartphone;
	}

	public void setSmartphone(Smartphone smartphone) {
		this.smartphone = smartphone;
	}

	@Override
	public String toString() {
		return "Position [latitude=" + latitude + ", longitude=" + longitude + ", date=" + date + "]";
	}
	
	
}
