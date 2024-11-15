package com.aladdinworks44.dao;

import java.util.List;

import com.aladdinworks44.dao.GenericDAO;
import com.aladdinworks44.domain.MaintenanceSchedule;





public interface MaintenanceScheduleDAO extends GenericDAO<MaintenanceSchedule, Integer> {
  
	List<MaintenanceSchedule> findAll();
	






}


