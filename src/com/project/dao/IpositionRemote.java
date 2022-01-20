package com.project.dao;

import java.util.List;

import javax.ejb.Remote;

import com.project.modal.Position;

@Remote
public interface IpositionRemote{

	void create(Position t);

	void delteById(Position t);

	Position update(Position t);

	Position findById(Position t);

	List<Position> findAll();

}
