package com.aladdinworks44.dao;

import java.util.List;

import com.aladdinworks44.dao.GenericDAO;
import com.aladdinworks44.domain.Server;





public interface ServerDAO extends GenericDAO<Server, Integer> {
  
	List<Server> findAll();
	






}


