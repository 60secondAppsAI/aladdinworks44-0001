package com.aladdinworks44.dao;

import java.util.List;

import com.aladdinworks44.dao.GenericDAO;
import com.aladdinworks44.domain.Assignment;





public interface AssignmentDAO extends GenericDAO<Assignment, Integer> {
  
	List<Assignment> findAll();
	






}


