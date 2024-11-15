package com.aladdinworks44.dao;

import java.util.List;

import com.aladdinworks44.dao.GenericDAO;
import com.aladdinworks44.domain.CoolingUnit;





public interface CoolingUnitDAO extends GenericDAO<CoolingUnit, Integer> {
  
	List<CoolingUnit> findAll();
	






}


