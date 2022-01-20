package com.project.dao;

import java.util.List;

import javax.ejb.Remote;

import com.project.modal.User;

@Remote
public interface IuserRemote{

	void create(User t);

	void delteById(User t);

	User update(User t);

	User findById(User t);

	List<User> findAll();

}
