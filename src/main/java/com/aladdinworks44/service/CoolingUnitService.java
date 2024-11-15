package com.aladdinworks44.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.ResponseEntity;

import com.aladdinworks44.domain.CoolingUnit;
import com.aladdinworks44.dto.CoolingUnitDTO;
import com.aladdinworks44.dto.CoolingUnitSearchDTO;
import com.aladdinworks44.dto.CoolingUnitPageDTO;
import com.aladdinworks44.dto.CoolingUnitConvertCriteriaDTO;
import com.aladdinworks44.service.GenericService;
import com.aladdinworks44.dto.common.RequestDTO;
import com.aladdinworks44.dto.common.ResultDTO;
import java.util.List;
import java.util.Optional;





public interface CoolingUnitService extends GenericService<CoolingUnit, Integer> {

	List<CoolingUnit> findAll();

	ResultDTO addCoolingUnit(CoolingUnitDTO coolingUnitDTO, RequestDTO requestDTO);

	ResultDTO updateCoolingUnit(CoolingUnitDTO coolingUnitDTO, RequestDTO requestDTO);

    Page<CoolingUnit> getAllCoolingUnits(Pageable pageable);

    Page<CoolingUnit> getAllCoolingUnits(Specification<CoolingUnit> spec, Pageable pageable);

	ResponseEntity<CoolingUnitPageDTO> getCoolingUnits(CoolingUnitSearchDTO coolingUnitSearchDTO);
	
	List<CoolingUnitDTO> convertCoolingUnitsToCoolingUnitDTOs(List<CoolingUnit> coolingUnits, CoolingUnitConvertCriteriaDTO convertCriteria);

	CoolingUnitDTO getCoolingUnitDTOById(Integer coolingUnitId);







}





