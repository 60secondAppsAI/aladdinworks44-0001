package com.aladdinworks44.dao;

import java.util.List;

import com.aladdinworks44.dao.GenericDAO;
import com.aladdinworks44.domain.TemperatureSensor;





public interface TemperatureSensorDAO extends GenericDAO<TemperatureSensor, Integer> {
  
	List<TemperatureSensor> findAll();
	






}


