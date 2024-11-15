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
import com.aladdinworks44.dao.RackAssignmentDAO;
import com.aladdinworks44.domain.RackAssignment;
import com.aladdinworks44.dto.RackAssignmentDTO;
import com.aladdinworks44.dto.RackAssignmentSearchDTO;
import com.aladdinworks44.dto.RackAssignmentPageDTO;
import com.aladdinworks44.dto.RackAssignmentConvertCriteriaDTO;
import com.aladdinworks44.dto.common.RequestDTO;
import com.aladdinworks44.dto.common.ResultDTO;
import com.aladdinworks44.service.RackAssignmentService;
import com.aladdinworks44.util.ControllerUtils;





@Service
public class RackAssignmentServiceImpl extends GenericServiceImpl<RackAssignment, Integer> implements RackAssignmentService {

    private final static Logger logger = LoggerFactory.getLogger(RackAssignmentServiceImpl.class);

	@Autowired
	RackAssignmentDAO rackAssignmentDao;

	


	@Override
	public GenericDAO<RackAssignment, Integer> getDAO() {
		return (GenericDAO<RackAssignment, Integer>) rackAssignmentDao;
	}
	
	public List<RackAssignment> findAll () {
		List<RackAssignment> rackAssignments = rackAssignmentDao.findAll();
		
		return rackAssignments;	
		
	}

	public ResultDTO addRackAssignment(RackAssignmentDTO rackAssignmentDTO, RequestDTO requestDTO) {

		RackAssignment rackAssignment = new RackAssignment();

		rackAssignment.setRackAssignmentId(rackAssignmentDTO.getRackAssignmentId());


		LocalDate localDate = LocalDate.now();
		ZoneId defaultZoneId = ZoneId.systemDefault();
		Date date = Date.from(localDate.atStartOfDay(defaultZoneId).toInstant());

		rackAssignment = rackAssignmentDao.save(rackAssignment);
		
		ResultDTO result = new ResultDTO();
		return result;
	}
	
	public Page<RackAssignment> getAllRackAssignments(Pageable pageable) {
		return rackAssignmentDao.findAll(pageable);
	}

	public Page<RackAssignment> getAllRackAssignments(Specification<RackAssignment> spec, Pageable pageable) {
		return rackAssignmentDao.findAll(spec, pageable);
	}

	public ResponseEntity<RackAssignmentPageDTO> getRackAssignments(RackAssignmentSearchDTO rackAssignmentSearchDTO) {
	
			Integer rackAssignmentId = rackAssignmentSearchDTO.getRackAssignmentId(); 
 			String sortBy = rackAssignmentSearchDTO.getSortBy();
			String sortOrder = rackAssignmentSearchDTO.getSortOrder();
			String searchQuery = rackAssignmentSearchDTO.getSearchQuery();
			Integer page = rackAssignmentSearchDTO.getPage();
			Integer size = rackAssignmentSearchDTO.getSize();

	        Specification<RackAssignment> spec = Specification.where(null);

			spec = ControllerUtils.andIfNecessary(spec, rackAssignmentId, "rackAssignmentId"); 
			

		if (searchQuery != null && !searchQuery.isEmpty()) {
			spec = spec.and((root, query, cb) -> cb.or(

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

		Page<RackAssignment> rackAssignments = this.getAllRackAssignments(spec, pageable);
		
		//System.out.println(String.valueOf(rackAssignments.getTotalElements()) + " total ${classNamelPlural}, viewing page X of " + String.valueOf(rackAssignments.getTotalPages()));
		
		List<RackAssignment> rackAssignmentsList = rackAssignments.getContent();
		
		RackAssignmentConvertCriteriaDTO convertCriteria = new RackAssignmentConvertCriteriaDTO();
		List<RackAssignmentDTO> rackAssignmentDTOs = this.convertRackAssignmentsToRackAssignmentDTOs(rackAssignmentsList,convertCriteria);
		
		RackAssignmentPageDTO rackAssignmentPageDTO = new RackAssignmentPageDTO();
		rackAssignmentPageDTO.setRackAssignments(rackAssignmentDTOs);
		rackAssignmentPageDTO.setTotalElements(rackAssignments.getTotalElements());
		return ResponseEntity.ok(rackAssignmentPageDTO);
	}

	public List<RackAssignmentDTO> convertRackAssignmentsToRackAssignmentDTOs(List<RackAssignment> rackAssignments, RackAssignmentConvertCriteriaDTO convertCriteria) {
		
		List<RackAssignmentDTO> rackAssignmentDTOs = new ArrayList<RackAssignmentDTO>();
		
		for (RackAssignment rackAssignment : rackAssignments) {
			rackAssignmentDTOs.add(convertRackAssignmentToRackAssignmentDTO(rackAssignment,convertCriteria));
		}
		
		return rackAssignmentDTOs;

	}
	
	public RackAssignmentDTO convertRackAssignmentToRackAssignmentDTO(RackAssignment rackAssignment, RackAssignmentConvertCriteriaDTO convertCriteria) {
		
		RackAssignmentDTO rackAssignmentDTO = new RackAssignmentDTO();
		
		rackAssignmentDTO.setRackAssignmentId(rackAssignment.getRackAssignmentId());

	

		
		return rackAssignmentDTO;
	}

	public ResultDTO updateRackAssignment(RackAssignmentDTO rackAssignmentDTO, RequestDTO requestDTO) {
		
		RackAssignment rackAssignment = rackAssignmentDao.getById(rackAssignmentDTO.getRackAssignmentId());

		rackAssignment.setRackAssignmentId(ControllerUtils.setValue(rackAssignment.getRackAssignmentId(), rackAssignmentDTO.getRackAssignmentId()));



        rackAssignment = rackAssignmentDao.save(rackAssignment);
		
		ResultDTO result = new ResultDTO();
		return result;
	}

	public RackAssignmentDTO getRackAssignmentDTOById(Integer rackAssignmentId) {
	
		RackAssignment rackAssignment = rackAssignmentDao.getById(rackAssignmentId);
			
		
		RackAssignmentConvertCriteriaDTO convertCriteria = new RackAssignmentConvertCriteriaDTO();
		return(this.convertRackAssignmentToRackAssignmentDTO(rackAssignment,convertCriteria));
	}







}
