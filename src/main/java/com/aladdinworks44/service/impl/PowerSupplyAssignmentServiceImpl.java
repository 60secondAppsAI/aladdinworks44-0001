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
import com.aladdinworks44.dao.PowerSupplyAssignmentDAO;
import com.aladdinworks44.domain.PowerSupplyAssignment;
import com.aladdinworks44.dto.PowerSupplyAssignmentDTO;
import com.aladdinworks44.dto.PowerSupplyAssignmentSearchDTO;
import com.aladdinworks44.dto.PowerSupplyAssignmentPageDTO;
import com.aladdinworks44.dto.PowerSupplyAssignmentConvertCriteriaDTO;
import com.aladdinworks44.dto.common.RequestDTO;
import com.aladdinworks44.dto.common.ResultDTO;
import com.aladdinworks44.service.PowerSupplyAssignmentService;
import com.aladdinworks44.util.ControllerUtils;





@Service
public class PowerSupplyAssignmentServiceImpl extends GenericServiceImpl<PowerSupplyAssignment, Integer> implements PowerSupplyAssignmentService {

    private final static Logger logger = LoggerFactory.getLogger(PowerSupplyAssignmentServiceImpl.class);

	@Autowired
	PowerSupplyAssignmentDAO powerSupplyAssignmentDao;

	


	@Override
	public GenericDAO<PowerSupplyAssignment, Integer> getDAO() {
		return (GenericDAO<PowerSupplyAssignment, Integer>) powerSupplyAssignmentDao;
	}
	
	public List<PowerSupplyAssignment> findAll () {
		List<PowerSupplyAssignment> powerSupplyAssignments = powerSupplyAssignmentDao.findAll();
		
		return powerSupplyAssignments;	
		
	}

	public ResultDTO addPowerSupplyAssignment(PowerSupplyAssignmentDTO powerSupplyAssignmentDTO, RequestDTO requestDTO) {

		PowerSupplyAssignment powerSupplyAssignment = new PowerSupplyAssignment();

		powerSupplyAssignment.setPowerSupplyAssignmentId(powerSupplyAssignmentDTO.getPowerSupplyAssignmentId());


		LocalDate localDate = LocalDate.now();
		ZoneId defaultZoneId = ZoneId.systemDefault();
		Date date = Date.from(localDate.atStartOfDay(defaultZoneId).toInstant());

		powerSupplyAssignment = powerSupplyAssignmentDao.save(powerSupplyAssignment);
		
		ResultDTO result = new ResultDTO();
		return result;
	}
	
	public Page<PowerSupplyAssignment> getAllPowerSupplyAssignments(Pageable pageable) {
		return powerSupplyAssignmentDao.findAll(pageable);
	}

	public Page<PowerSupplyAssignment> getAllPowerSupplyAssignments(Specification<PowerSupplyAssignment> spec, Pageable pageable) {
		return powerSupplyAssignmentDao.findAll(spec, pageable);
	}

	public ResponseEntity<PowerSupplyAssignmentPageDTO> getPowerSupplyAssignments(PowerSupplyAssignmentSearchDTO powerSupplyAssignmentSearchDTO) {
	
			Integer powerSupplyAssignmentId = powerSupplyAssignmentSearchDTO.getPowerSupplyAssignmentId(); 
 			String sortBy = powerSupplyAssignmentSearchDTO.getSortBy();
			String sortOrder = powerSupplyAssignmentSearchDTO.getSortOrder();
			String searchQuery = powerSupplyAssignmentSearchDTO.getSearchQuery();
			Integer page = powerSupplyAssignmentSearchDTO.getPage();
			Integer size = powerSupplyAssignmentSearchDTO.getSize();

	        Specification<PowerSupplyAssignment> spec = Specification.where(null);

			spec = ControllerUtils.andIfNecessary(spec, powerSupplyAssignmentId, "powerSupplyAssignmentId"); 
			

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

		Page<PowerSupplyAssignment> powerSupplyAssignments = this.getAllPowerSupplyAssignments(spec, pageable);
		
		//System.out.println(String.valueOf(powerSupplyAssignments.getTotalElements()) + " total ${classNamelPlural}, viewing page X of " + String.valueOf(powerSupplyAssignments.getTotalPages()));
		
		List<PowerSupplyAssignment> powerSupplyAssignmentsList = powerSupplyAssignments.getContent();
		
		PowerSupplyAssignmentConvertCriteriaDTO convertCriteria = new PowerSupplyAssignmentConvertCriteriaDTO();
		List<PowerSupplyAssignmentDTO> powerSupplyAssignmentDTOs = this.convertPowerSupplyAssignmentsToPowerSupplyAssignmentDTOs(powerSupplyAssignmentsList,convertCriteria);
		
		PowerSupplyAssignmentPageDTO powerSupplyAssignmentPageDTO = new PowerSupplyAssignmentPageDTO();
		powerSupplyAssignmentPageDTO.setPowerSupplyAssignments(powerSupplyAssignmentDTOs);
		powerSupplyAssignmentPageDTO.setTotalElements(powerSupplyAssignments.getTotalElements());
		return ResponseEntity.ok(powerSupplyAssignmentPageDTO);
	}

	public List<PowerSupplyAssignmentDTO> convertPowerSupplyAssignmentsToPowerSupplyAssignmentDTOs(List<PowerSupplyAssignment> powerSupplyAssignments, PowerSupplyAssignmentConvertCriteriaDTO convertCriteria) {
		
		List<PowerSupplyAssignmentDTO> powerSupplyAssignmentDTOs = new ArrayList<PowerSupplyAssignmentDTO>();
		
		for (PowerSupplyAssignment powerSupplyAssignment : powerSupplyAssignments) {
			powerSupplyAssignmentDTOs.add(convertPowerSupplyAssignmentToPowerSupplyAssignmentDTO(powerSupplyAssignment,convertCriteria));
		}
		
		return powerSupplyAssignmentDTOs;

	}
	
	public PowerSupplyAssignmentDTO convertPowerSupplyAssignmentToPowerSupplyAssignmentDTO(PowerSupplyAssignment powerSupplyAssignment, PowerSupplyAssignmentConvertCriteriaDTO convertCriteria) {
		
		PowerSupplyAssignmentDTO powerSupplyAssignmentDTO = new PowerSupplyAssignmentDTO();
		
		powerSupplyAssignmentDTO.setPowerSupplyAssignmentId(powerSupplyAssignment.getPowerSupplyAssignmentId());

	

		
		return powerSupplyAssignmentDTO;
	}

	public ResultDTO updatePowerSupplyAssignment(PowerSupplyAssignmentDTO powerSupplyAssignmentDTO, RequestDTO requestDTO) {
		
		PowerSupplyAssignment powerSupplyAssignment = powerSupplyAssignmentDao.getById(powerSupplyAssignmentDTO.getPowerSupplyAssignmentId());

		powerSupplyAssignment.setPowerSupplyAssignmentId(ControllerUtils.setValue(powerSupplyAssignment.getPowerSupplyAssignmentId(), powerSupplyAssignmentDTO.getPowerSupplyAssignmentId()));



        powerSupplyAssignment = powerSupplyAssignmentDao.save(powerSupplyAssignment);
		
		ResultDTO result = new ResultDTO();
		return result;
	}

	public PowerSupplyAssignmentDTO getPowerSupplyAssignmentDTOById(Integer powerSupplyAssignmentId) {
	
		PowerSupplyAssignment powerSupplyAssignment = powerSupplyAssignmentDao.getById(powerSupplyAssignmentId);
			
		
		PowerSupplyAssignmentConvertCriteriaDTO convertCriteria = new PowerSupplyAssignmentConvertCriteriaDTO();
		return(this.convertPowerSupplyAssignmentToPowerSupplyAssignmentDTO(powerSupplyAssignment,convertCriteria));
	}







}
