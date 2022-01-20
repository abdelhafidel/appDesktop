package com.project.dao;

import java.util.List;

import javax.ejb.Local;

import com.project.modal.Position;

@Local
public interface IpositionLocal{

	void create(Position t);

	void delteById(Position t);

	Position update(Position t);

	Position findById(Position t);

	List<Position> findAll();

}
