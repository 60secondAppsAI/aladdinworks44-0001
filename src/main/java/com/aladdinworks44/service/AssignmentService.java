package com.aladdinworks44.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.ResponseEntity;

import com.aladdinworks44.domain.Assignment;
import com.aladdinworks44.dto.AssignmentDTO;
import com.aladdinworks44.dto.AssignmentSearchDTO;
import com.aladdinworks44.dto.AssignmentPageDTO;
import com.aladdinworks44.dto.AssignmentConvertCriteriaDTO;
import com.aladdinworks44.service.GenericService;
import com.aladdinworks44.dto.common.RequestDTO;
import com.aladdinworks44.dto.common.ResultDTO;
import java.util.List;
import java.util.Optional;





public interface AssignmentService extends GenericService<Assignment, Integer> {

	List<Assignment> findAll();

	ResultDTO addAssignment(AssignmentDTO assignmentDTO, RequestDTO requestDTO);

	ResultDTO updateAssignment(AssignmentDTO assignmentDTO, RequestDTO requestDTO);

    Page<Assignment> getAllAssignments(Pageable pageable);

    Page<Assignment> getAllAssignments(Specification<Assignment> spec, Pageable pageable);

	ResponseEntity<AssignmentPageDTO> getAssignments(AssignmentSearchDTO assignmentSearchDTO);
	
	List<AssignmentDTO> convertAssignmentsToAssignmentDTOs(List<Assignment> assignments, AssignmentConvertCriteriaDTO convertCriteria);

	AssignmentDTO getAssignmentDTOById(Integer assignmentId);







}





