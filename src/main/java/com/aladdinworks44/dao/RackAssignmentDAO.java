package com.aladdinworks44.dao;

import java.util.List;

import com.aladdinworks44.dao.GenericDAO;
import com.aladdinworks44.domain.RackAssignment;





public interface RackAssignmentDAO extends GenericDAO<RackAssignment, Integer> {
  
	List<RackAssignment> findAll();
	






}


