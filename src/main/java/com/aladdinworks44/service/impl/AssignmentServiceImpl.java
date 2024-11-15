package com.aladdinworks44.service.impl;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.HashMap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;



import com.aladdinworks44.dao.GenericDAO;
import com.aladdinworks44.service.GenericService;
import com.aladdinworks44.service.impl.GenericServiceImpl;
import com.aladdinworks44.dao.AssignmentDAO;
import com.aladdinworks44.domain.Assignment;
import com.aladdinworks44.dto.AssignmentDTO;
import com.aladdinworks44.dto.AssignmentSearchDTO;
import com.aladdinworks44.dto.AssignmentPageDTO;
import com.aladdinworks44.dto.AssignmentConvertCriteriaDTO;
import com.aladdinworks44.dto.common.RequestDTO;
import com.aladdinworks44.dto.common.ResultDTO;
import com.aladdinworks44.service.AssignmentService;
import com.aladdinworks44.util.ControllerUtils;





@Service
public class AssignmentServiceImpl extends GenericServiceImpl<Assignment, Integer> implements AssignmentService {

    private final static Logger logger = LoggerFactory.getLogger(AssignmentServiceImpl.class);

	@Autowired
	AssignmentDAO assignmentDao;

	


	@Override
	public GenericDAO<Assignment, Integer> getDAO() {
		return (GenericDAO<Assignment, Integer>) assignmentDao;
	}
	
	public List<Assignment> findAll () {
		List<Assignment> assignments = assignmentDao.findAll();
		
		return assignments;	
		
	}

	public ResultDTO addAssignment(AssignmentDTO assignmentDTO, RequestDTO requestDTO) {

		Assignment assignment = new Assignment();

		assignment.setAssignmentId(assignmentDTO.getAssignmentId());


		assignment.setDescription(assignmentDTO.getDescription());


		LocalDate localDate = LocalDate.now();
		ZoneId defaultZoneId = ZoneId.systemDefault();
		Date date = Date.from(localDate.atStartOfDay(defaultZoneId).toInstant());

		assignment = assignmentDao.save(assignment);
		
		ResultDTO result = new ResultDTO();
		return result;
	}
	
	public Page<Assignment> getAllAssignments(Pageable pageable) {
		return assignmentDao.findAll(pageable);
	}

	public Page<Assignment> getAllAssignments(Specification<Assignment> spec, Pageable pageable) {
		return assignmentDao.findAll(spec, pageable);
	}

	public ResponseEntity<AssignmentPageDTO> getAssignments(AssignmentSearchDTO assignmentSearchDTO) {
	
			Integer assignmentId = assignmentSearchDTO.getAssignmentId(); 
 			String description = assignmentSearchDTO.getDescription(); 
 			String sortBy = assignmentSearchDTO.getSortBy();
			String sortOrder = assignmentSearchDTO.getSortOrder();
			String searchQuery = assignmentSearchDTO.getSearchQuery();
			Integer page = assignmentSearchDTO.getPage();
			Integer size = assignmentSearchDTO.getSize();

	        Specification<Assignment> spec = Specification.where(null);

			spec = ControllerUtils.andIfNecessary(spec, assignmentId, "assignmentId"); 
			
			spec = ControllerUtils.andIfNecessary(spec, description, "description"); 
			

		if (searchQuery != null && !searchQuery.isEmpty()) {
			spec = spec.and((root, query, cb) -> cb.or(

             cb.like(cb.lower(root.get("description")), "%" + searchQuery.toLowerCase() + "%") 
		));}
		
		Sort sort = Sort.unsorted();
		if (sortBy != null && !sortBy.isEmpty() && sortOrder != null && !sortOrder.isEmpty()) {
			if (sortOrder.equalsIgnoreCase("asc")) {
				sort = Sort.by(sortBy).ascending();
			} else if (sortOrder.equalsIgnoreCase("desc")) {
				sort = Sort.by(sortBy).descending();
			}
		}
		Pageable pageable = PageRequest.of(page, size, sort);

		Page<Assignment> assignments = this.getAllAssignments(spec, pageable);
		
		//System.out.println(String.valueOf(assignments.getTotalElements()) + " total ${classNamelPlural}, viewing page X of " + String.valueOf(assignments.getTotalPages()));
		
		List<Assignment> assignmentsList = assignments.getContent();
		
		AssignmentConvertCriteriaDTO convertCriteria = new AssignmentConvertCriteriaDTO();
		List<AssignmentDTO> assignmentDTOs = this.convertAssignmentsToAssignmentDTOs(assignmentsList,convertCriteria);
		
		AssignmentPageDTO assignmentPageDTO = new AssignmentPageDTO();
		assignmentPageDTO.setAssignments(assignmentDTOs);
		assignmentPageDTO.setTotalElements(assignments.getTotalElements());
		return ResponseEntity.ok(assignmentPageDTO);
	}

	public List<AssignmentDTO> convertAssignmentsToAssignmentDTOs(List<Assignment> assignments, AssignmentConvertCriteriaDTO convertCriteria) {
		
		List<AssignmentDTO> assignmentDTOs = new ArrayList<AssignmentDTO>();
		
		for (Assignment assignment : assignments) {
			assignmentDTOs.add(convertAssignmentToAssignmentDTO(assignment,convertCriteria));
		}
		
		return assignmentDTOs;

	}
	
	public AssignmentDTO convertAssignmentToAssignmentDTO(Assignment assignment, AssignmentConvertCriteriaDTO convertCriteria) {
		
		AssignmentDTO assignmentDTO = new AssignmentDTO();
		
		assignmentDTO.setAssignmentId(assignment.getAssignmentId());

	
		assignmentDTO.setDescription(assignment.getDescription());

	

		
		return assignmentDTO;
	}

	public ResultDTO updateAssignment(AssignmentDTO assignmentDTO, RequestDTO requestDTO) {
		
		Assignment assignment = assignmentDao.getById(assignmentDTO.getAssignmentId());

		assignment.setAssignmentId(ControllerUtils.setValue(assignment.getAssignmentId(), assignmentDTO.getAssignmentId()));

		assignment.setDescription(ControllerUtils.setValue(assignment.getDescription(), assignmentDTO.getDescription()));



        assignment = assignmentDao.save(assignment);
		
		ResultDTO result = new ResultDTO();
		return result;
	}

	public AssignmentDTO getAssignmentDTOById(Integer assignmentId) {
	
		Assignment assignment = assignmentDao.getById(assignmentId);
			
		
		AssignmentConvertCriteriaDTO convertCriteria = new AssignmentConvertCriteriaDTO();
		return(this.convertAssignmentToAssignmentDTO(assignment,convertCriteria));
	}







}
