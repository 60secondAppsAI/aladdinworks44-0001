package com.aladdinworks44.dao;

import java.util.List;

import com.aladdinworks44.dao.GenericDAO;
import com.aladdinworks44.domain.NetworkSwitchAssignment;





public interface NetworkSwitchAssignmentDAO extends GenericDAO<NetworkSwitchAssignment, Integer> {
  
	List<NetworkSwitchAssignment> findAll();
	






}


