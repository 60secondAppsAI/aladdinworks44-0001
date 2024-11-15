package com.aladdinworks44.dao;

import java.util.List;

import com.aladdinworks44.dao.GenericDAO;
import com.aladdinworks44.domain.DataCenter;





public interface DataCenterDAO extends GenericDAO<DataCenter, Integer> {
  
	List<DataCenter> findAll();
	






}


