package com.aladdinworks44.dao;

import java.util.List;

import com.aladdinworks44.dao.GenericDAO;
import com.aladdinworks44.domain.NetworkSwitch;





public interface NetworkSwitchDAO extends GenericDAO<NetworkSwitch, Integer> {
  
	List<NetworkSwitch> findAll();
	






}


