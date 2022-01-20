package com.project.dao;

import java.util.List;

import javax.ejb.Remote;

import com.project.modal.Smartphone;
import com.project.modal.User;

@Remote
public interface IphoneRemote{

	void create(Smartphone t);

	void delteById(Smartphone t);

	Smartphone update(Smartphone t);

	Smartphone findById(Smartphone t);

	List<Smartphone> findAll();

}
