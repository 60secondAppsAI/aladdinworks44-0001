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
import com.aladdinworks44.dao.CoolingUnitAssignmentDAO;
import com.aladdinworks44.domain.CoolingUnitAssignment;
import com.aladdinworks44.dto.CoolingUnitAssignmentDTO;
import com.aladdinworks44.dto.CoolingUnitAssignmentSearchDTO;
import com.aladdinworks44.dto.CoolingUnitAssignmentPageDTO;
import com.aladdinworks44.dto.CoolingUnitAssignmentConvertCriteriaDTO;
import com.aladdinworks44.dto.common.RequestDTO;
import com.aladdinworks44.dto.common.ResultDTO;
import com.aladdinworks44.service.CoolingUnitAssignmentService;
import com.aladdinworks44.util.ControllerUtils;





@Service
public class CoolingUnitAssignmentServiceImpl extends GenericServiceImpl<CoolingUnitAssignment, Integer> implements CoolingUnitAssignmentService {

    private final static Logger logger = LoggerFactory.getLogger(CoolingUnitAssignmentServiceImpl.class);

	@Autowired
	CoolingUnitAssignmentDAO coolingUnitAssignmentDao;

	


	@Override
	public GenericDAO<CoolingUnitAssignment, Integer> getDAO() {
		return (GenericDAO<CoolingUnitAssignment, Integer>) coolingUnitAssignmentDao;
	}
	
	public List<CoolingUnitAssignment> findAll () {
		List<CoolingUnitAssignment> coolingUnitAssignments = coolingUnitAssignmentDao.findAll();
		
		return coolingUnitAssignments;	
		
	}

	public ResultDTO addCoolingUnitAssignment(CoolingUnitAssignmentDTO coolingUnitAssignmentDTO, RequestDTO requestDTO) {

		CoolingUnitAssignment coolingUnitAssignment = new CoolingUnitAssignment();

		coolingUnitAssignment.setCoolingUnitAssignmentId(coolingUnitAssignmentDTO.getCoolingUnitAssignmentId());


		LocalDate localDate = LocalDate.now();
		ZoneId defaultZoneId = ZoneId.systemDefault();
		Date date = Date.from(localDate.atStartOfDay(defaultZoneId).toInstant());

		coolingUnitAssignment = coolingUnitAssignmentDao.save(coolingUnitAssignment);
		
		ResultDTO result = new ResultDTO();
		return result;
	}
	
	public Page<CoolingUnitAssignment> getAllCoolingUnitAssignments(Pageable pageable) {
		return coolingUnitAssignmentDao.findAll(pageable);
	}

	public Page<CoolingUnitAssignment> getAllCoolingUnitAssignments(Specification<CoolingUnitAssignment> spec, Pageable pageable) {
		return coolingUnitAssignmentDao.findAll(spec, pageable);
	}

	public ResponseEntity<CoolingUnitAssignmentPageDTO> getCoolingUnitAssignments(CoolingUnitAssignmentSearchDTO coolingUnitAssignmentSearchDTO) {
	
			Integer coolingUnitAssignmentId = coolingUnitAssignmentSearchDTO.getCoolingUnitAssignmentId(); 
 			String sortBy = coolingUnitAssignmentSearchDTO.getSortBy();
			String sortOrder = coolingUnitAssignmentSearchDTO.getSortOrder();
			String searchQuery = coolingUnitAssignmentSearchDTO.getSearchQuery();
			Integer page = coolingUnitAssignmentSearchDTO.getPage();
			Integer size = coolingUnitAssignmentSearchDTO.getSize();

	        Specification<CoolingUnitAssignment> spec = Specification.where(null);

			spec = ControllerUtils.andIfNecessary(spec, coolingUnitAssignmentId, "coolingUnitAssignmentId"); 
			

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

		Page<CoolingUnitAssignment> coolingUnitAssignments = this.getAllCoolingUnitAssignments(spec, pageable);
		
		//System.out.println(String.valueOf(coolingUnitAssignments.getTotalElements()) + " total ${classNamelPlural}, viewing page X of " + String.valueOf(coolingUnitAssignments.getTotalPages()));
		
		List<CoolingUnitAssignment> coolingUnitAssignmentsList = coolingUnitAssignments.getContent();
		
		CoolingUnitAssignmentConvertCriteriaDTO convertCriteria = new CoolingUnitAssignmentConvertCriteriaDTO();
		List<CoolingUnitAssignmentDTO> coolingUnitAssignmentDTOs = this.convertCoolingUnitAssignmentsToCoolingUnitAssignmentDTOs(coolingUnitAssignmentsList,convertCriteria);
		
		CoolingUnitAssignmentPageDTO coolingUnitAssignmentPageDTO = new CoolingUnitAssignmentPageDTO();
		coolingUnitAssignmentPageDTO.setCoolingUnitAssignments(coolingUnitAssignmentDTOs);
		coolingUnitAssignmentPageDTO.setTotalElements(coolingUnitAssignments.getTotalElements());
		return ResponseEntity.ok(coolingUnitAssignmentPageDTO);
	}

	public List<CoolingUnitAssignmentDTO> convertCoolingUnitAssignmentsToCoolingUnitAssignmentDTOs(List<CoolingUnitAssignment> coolingUnitAssignments, CoolingUnitAssignmentConvertCriteriaDTO convertCriteria) {
		
		List<CoolingUnitAssignmentDTO> coolingUnitAssignmentDTOs = new ArrayList<CoolingUnitAssignmentDTO>();
		
		for (CoolingUnitAssignment coolingUnitAssignment : coolingUnitAssignments) {
			coolingUnitAssignmentDTOs.add(convertCoolingUnitAssignmentToCoolingUnitAssignmentDTO(coolingUnitAssignment,convertCriteria));
		}
		
		return coolingUnitAssignmentDTOs;

	}
	
	public CoolingUnitAssignmentDTO convertCoolingUnitAssignmentToCoolingUnitAssignmentDTO(CoolingUnitAssignment coolingUnitAssignment, CoolingUnitAssignmentConvertCriteriaDTO convertCriteria) {
		
		CoolingUnitAssignmentDTO coolingUnitAssignmentDTO = new CoolingUnitAssignmentDTO();
		
		coolingUnitAssignmentDTO.setCoolingUnitAssignmentId(coolingUnitAssignment.getCoolingUnitAssignmentId());

	

		
		return coolingUnitAssignmentDTO;
	}

	public ResultDTO updateCoolingUnitAssignment(CoolingUnitAssignmentDTO coolingUnitAssignmentDTO, RequestDTO requestDTO) {
		
		CoolingUnitAssignment coolingUnitAssignment = coolingUnitAssignmentDao.getById(coolingUnitAssignmentDTO.getCoolingUnitAssignmentId());

		coolingUnitAssignment.setCoolingUnitAssignmentId(ControllerUtils.setValue(coolingUnitAssignment.getCoolingUnitAssignmentId(), coolingUnitAssignmentDTO.getCoolingUnitAssignmentId()));



        coolingUnitAssignment = coolingUnitAssignmentDao.save(coolingUnitAssignment);
		
		ResultDTO result = new ResultDTO();
		return result;
	}

	public CoolingUnitAssignmentDTO getCoolingUnitAssignmentDTOById(Integer coolingUnitAssignmentId) {
	
		CoolingUnitAssignment coolingUnitAssignment = coolingUnitAssignmentDao.getById(coolingUnitAssignmentId);
			
		
		CoolingUnitAssignmentConvertCriteriaDTO convertCriteria = new CoolingUnitAssignmentConvertCriteriaDTO();
		return(this.convertCoolingUnitAssignmentToCoolingUnitAssignmentDTO(coolingUnitAssignment,convertCriteria));
	}







}
