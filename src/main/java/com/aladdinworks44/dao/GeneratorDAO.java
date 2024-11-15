package com.aladdinworks44.dao;

import java.util.List;

import com.aladdinworks44.dao.GenericDAO;
import com.aladdinworks44.domain.Generator;





public interface GeneratorDAO extends GenericDAO<Generator, Integer> {
  
	List<Generator> findAll();
	






}


