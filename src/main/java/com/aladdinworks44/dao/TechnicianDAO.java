package com.aladdinworks44.dao;

import java.util.List;

import com.aladdinworks44.dao.GenericDAO;
import com.aladdinworks44.domain.Technician;





public interface TechnicianDAO extends GenericDAO<Technician, Integer> {
  
	List<Technician> findAll();
	






}


