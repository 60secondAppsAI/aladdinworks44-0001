package com.aladdinworks44.dao;

import java.util.List;

import com.aladdinworks44.dao.GenericDAO;
import com.aladdinworks44.domain.PowerSupplyAssignment;





public interface PowerSupplyAssignmentDAO extends GenericDAO<PowerSupplyAssignment, Integer> {
  
	List<PowerSupplyAssignment> findAll();
	






}


