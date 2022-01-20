package com.project.dao;

import java.util.List;

import javax.ejb.Local;

import com.project.modal.User;

@Local
public interface IuserLocal{

	void create(User t);

	void delteById(User t);

	User update(User t);

	User findById(User t);

	List<User> findAll();

}
