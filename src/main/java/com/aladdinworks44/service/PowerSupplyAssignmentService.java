package com.aladdinworks44.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.ResponseEntity;

import com.aladdinworks44.domain.PowerSupplyAssignment;
import com.aladdinworks44.dto.PowerSupplyAssignmentDTO;
import com.aladdinworks44.dto.PowerSupplyAssignmentSearchDTO;
import com.aladdinworks44.dto.PowerSupplyAssignmentPageDTO;
import com.aladdinworks44.dto.PowerSupplyAssignmentConvertCriteriaDTO;
import com.aladdinworks44.service.GenericService;
import com.aladdinworks44.dto.common.RequestDTO;
import com.aladdinworks44.dto.common.ResultDTO;
import java.util.List;
import java.util.Optional;





public interface PowerSupplyAssignmentService extends GenericService<PowerSupplyAssignment, Integer> {

	List<PowerSupplyAssignment> findAll();

	ResultDTO addPowerSupplyAssignment(PowerSupplyAssignmentDTO powerSupplyAssignmentDTO, RequestDTO requestDTO);

	ResultDTO updatePowerSupplyAssignment(PowerSupplyAssignmentDTO powerSupplyAssignmentDTO, RequestDTO requestDTO);

    Page<PowerSupplyAssignment> getAllPowerSupplyAssignments(Pageable pageable);

    Page<PowerSupplyAssignment> getAllPowerSupplyAssignments(Specification<PowerSupplyAssignment> spec, Pageable pageable);

	ResponseEntity<PowerSupplyAssignmentPageDTO> getPowerSupplyAssignments(PowerSupplyAssignmentSearchDTO powerSupplyAssignmentSearchDTO);
	
	List<PowerSupplyAssignmentDTO> convertPowerSupplyAssignmentsToPowerSupplyAssignmentDTOs(List<PowerSupplyAssignment> powerSupplyAssignments, PowerSupplyAssignmentConvertCriteriaDTO convertCriteria);

	PowerSupplyAssignmentDTO getPowerSupplyAssignmentDTOById(Integer powerSupplyAssignmentId);







}





