package com.aladdinworks44.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.ResponseEntity;

import com.aladdinworks44.domain.RackAssignment;
import com.aladdinworks44.dto.RackAssignmentDTO;
import com.aladdinworks44.dto.RackAssignmentSearchDTO;
import com.aladdinworks44.dto.RackAssignmentPageDTO;
import com.aladdinworks44.dto.RackAssignmentConvertCriteriaDTO;
import com.aladdinworks44.service.GenericService;
import com.aladdinworks44.dto.common.RequestDTO;
import com.aladdinworks44.dto.common.ResultDTO;
import java.util.List;
import java.util.Optional;





public interface RackAssignmentService extends GenericService<RackAssignment, Integer> {

	List<RackAssignment> findAll();

	ResultDTO addRackAssignment(RackAssignmentDTO rackAssignmentDTO, RequestDTO requestDTO);

	ResultDTO updateRackAssignment(RackAssignmentDTO rackAssignmentDTO, RequestDTO requestDTO);

    Page<RackAssignment> getAllRackAssignments(Pageable pageable);

    Page<RackAssignment> getAllRackAssignments(Specification<RackAssignment> spec, Pageable pageable);

	ResponseEntity<RackAssignmentPageDTO> getRackAssignments(RackAssignmentSearchDTO rackAssignmentSearchDTO);
	
	List<RackAssignmentDTO> convertRackAssignmentsToRackAssignmentDTOs(List<RackAssignment> rackAssignments, RackAssignmentConvertCriteriaDTO convertCriteria);

	RackAssignmentDTO getRackAssignmentDTOById(Integer rackAssignmentId);







}





