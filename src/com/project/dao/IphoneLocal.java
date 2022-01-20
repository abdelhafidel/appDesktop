package com.project.dao;

import java.util.List;

import javax.ejb.Local;

import com.project.modal.Smartphone;

@Local
public interface IphoneLocal{

	void create(Smartphone t);

	void delteById(Smartphone t);

	Smartphone update(Smartphone t);

	Smartphone findById(Smartphone t);

	List<Smartphone> findAll();

}
