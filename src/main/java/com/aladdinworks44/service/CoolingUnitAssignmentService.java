package com.aladdinworks44.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.ResponseEntity;

import com.aladdinworks44.domain.CoolingUnitAssignment;
import com.aladdinworks44.dto.CoolingUnitAssignmentDTO;
import com.aladdinworks44.dto.CoolingUnitAssignmentSearchDTO;
import com.aladdinworks44.dto.CoolingUnitAssignmentPageDTO;
import com.aladdinworks44.dto.CoolingUnitAssignmentConvertCriteriaDTO;
import com.aladdinworks44.service.GenericService;
import com.aladdinworks44.dto.common.RequestDTO;
import com.aladdinworks44.dto.common.ResultDTO;
import java.util.List;
import java.util.Optional;





public interface CoolingUnitAssignmentService extends GenericService<CoolingUnitAssignment, Integer> {

	List<CoolingUnitAssignment> findAll();

	ResultDTO addCoolingUnitAssignment(CoolingUnitAssignmentDTO coolingUnitAssignmentDTO, RequestDTO requestDTO);

	ResultDTO updateCoolingUnitAssignment(CoolingUnitAssignmentDTO coolingUnitAssignmentDTO, RequestDTO requestDTO);

    Page<CoolingUnitAssignment> getAllCoolingUnitAssignments(Pageable pageable);

    Page<CoolingUnitAssignment> getAllCoolingUnitAssignments(Specification<CoolingUnitAssignment> spec, Pageable pageable);

	ResponseEntity<CoolingUnitAssignmentPageDTO> getCoolingUnitAssignments(CoolingUnitAssignmentSearchDTO coolingUnitAssignmentSearchDTO);
	
	List<CoolingUnitAssignmentDTO> convertCoolingUnitAssignmentsToCoolingUnitAssignmentDTOs(List<CoolingUnitAssignment> coolingUnitAssignments, CoolingUnitAssignmentConvertCriteriaDTO convertCriteria);

	CoolingUnitAssignmentDTO getCoolingUnitAssignmentDTOById(Integer coolingUnitAssignmentId);







}





