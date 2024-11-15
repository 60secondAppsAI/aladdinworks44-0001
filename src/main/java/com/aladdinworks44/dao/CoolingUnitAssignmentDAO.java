package com.aladdinworks44.dao;

import java.util.List;

import com.aladdinworks44.dao.GenericDAO;
import com.aladdinworks44.domain.CoolingUnitAssignment;





public interface CoolingUnitAssignmentDAO extends GenericDAO<CoolingUnitAssignment, Integer> {
  
	List<CoolingUnitAssignment> findAll();
	






}


