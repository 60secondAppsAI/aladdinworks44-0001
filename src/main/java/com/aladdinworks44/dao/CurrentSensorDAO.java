package com.aladdinworks44.dao;

import java.util.List;

import com.aladdinworks44.dao.GenericDAO;
import com.aladdinworks44.domain.CurrentSensor;





public interface CurrentSensorDAO extends GenericDAO<CurrentSensor, Integer> {
  
	List<CurrentSensor> findAll();
	






}


