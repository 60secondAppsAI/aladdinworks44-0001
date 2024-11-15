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
import com.aladdinworks44.dao.TechnicianDAO;
import com.aladdinworks44.domain.Technician;
import com.aladdinworks44.dto.TechnicianDTO;
import com.aladdinworks44.dto.TechnicianSearchDTO;
import com.aladdinworks44.dto.TechnicianPageDTO;
import com.aladdinworks44.dto.TechnicianConvertCriteriaDTO;
import com.aladdinworks44.dto.common.RequestDTO;
import com.aladdinworks44.dto.common.ResultDTO;
import com.aladdinworks44.service.TechnicianService;
import com.aladdinworks44.util.ControllerUtils;





@Service
public class TechnicianServiceImpl extends GenericServiceImpl<Technician, Integer> implements TechnicianService {

    private final static Logger logger = LoggerFactory.getLogger(TechnicianServiceImpl.class);

	@Autowired
	TechnicianDAO technicianDao;

	


	@Override
	public GenericDAO<Technician, Integer> getDAO() {
		return (GenericDAO<Technician, Integer>) technicianDao;
	}
	
	public List<Technician> findAll () {
		List<Technician> technicians = technicianDao.findAll();
		
		return technicians;	
		
	}

	public ResultDTO addTechnician(TechnicianDTO technicianDTO, RequestDTO requestDTO) {

		Technician technician = new Technician();

		technician.setTechnicianId(technicianDTO.getTechnicianId());


		technician.setName(technicianDTO.getName());


		technician.setContactInfo(technicianDTO.getContactInfo());


		LocalDate localDate = LocalDate.now();
		ZoneId defaultZoneId = ZoneId.systemDefault();
		Date date = Date.from(localDate.atStartOfDay(defaultZoneId).toInstant());

		technician = technicianDao.save(technician);
		
		ResultDTO result = new ResultDTO();
		return result;
	}
	
	public Page<Technician> getAllTechnicians(Pageable pageable) {
		return technicianDao.findAll(pageable);
	}

	public Page<Technician> getAllTechnicians(Specification<Technician> spec, Pageable pageable) {
		return technicianDao.findAll(spec, pageable);
	}

	public ResponseEntity<TechnicianPageDTO> getTechnicians(TechnicianSearchDTO technicianSearchDTO) {
	
			Integer technicianId = technicianSearchDTO.getTechnicianId(); 
 			String name = technicianSearchDTO.getName(); 
 			String contactInfo = technicianSearchDTO.getContactInfo(); 
 			String sortBy = technicianSearchDTO.getSortBy();
			String sortOrder = technicianSearchDTO.getSortOrder();
			String searchQuery = technicianSearchDTO.getSearchQuery();
			Integer page = technicianSearchDTO.getPage();
			Integer size = technicianSearchDTO.getSize();

	        Specification<Technician> spec = Specification.where(null);

			spec = ControllerUtils.andIfNecessary(spec, technicianId, "technicianId"); 
			
			spec = ControllerUtils.andIfNecessary(spec, name, "name"); 
			
			spec = ControllerUtils.andIfNecessary(spec, contactInfo, "contactInfo"); 
			

		if (searchQuery != null && !searchQuery.isEmpty()) {
			spec = spec.and((root, query, cb) -> cb.or(

             cb.like(cb.lower(root.get("name")), "%" + searchQuery.toLowerCase() + "%") 
             , cb.like(cb.lower(root.get("contactInfo")), "%" + searchQuery.toLowerCase() + "%") 
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

		Page<Technician> technicians = this.getAllTechnicians(spec, pageable);
		
		//System.out.println(String.valueOf(technicians.getTotalElements()) + " total ${classNamelPlural}, viewing page X of " + String.valueOf(technicians.getTotalPages()));
		
		List<Technician> techniciansList = technicians.getContent();
		
		TechnicianConvertCriteriaDTO convertCriteria = new TechnicianConvertCriteriaDTO();
		List<TechnicianDTO> technicianDTOs = this.convertTechniciansToTechnicianDTOs(techniciansList,convertCriteria);
		
		TechnicianPageDTO technicianPageDTO = new TechnicianPageDTO();
		technicianPageDTO.setTechnicians(technicianDTOs);
		technicianPageDTO.setTotalElements(technicians.getTotalElements());
		return ResponseEntity.ok(technicianPageDTO);
	}

	public List<TechnicianDTO> convertTechniciansToTechnicianDTOs(List<Technician> technicians, TechnicianConvertCriteriaDTO convertCriteria) {
		
		List<TechnicianDTO> technicianDTOs = new ArrayList<TechnicianDTO>();
		
		for (Technician technician : technicians) {
			technicianDTOs.add(convertTechnicianToTechnicianDTO(technician,convertCriteria));
		}
		
		return technicianDTOs;

	}
	
	public TechnicianDTO convertTechnicianToTechnicianDTO(Technician technician, TechnicianConvertCriteriaDTO convertCriteria) {
		
		TechnicianDTO technicianDTO = new TechnicianDTO();
		
		technicianDTO.setTechnicianId(technician.getTechnicianId());

	
		technicianDTO.setName(technician.getName());

	
		technicianDTO.setContactInfo(technician.getContactInfo());

	

		
		return technicianDTO;
	}

	public ResultDTO updateTechnician(TechnicianDTO technicianDTO, RequestDTO requestDTO) {
		
		Technician technician = technicianDao.getById(technicianDTO.getTechnicianId());

		technician.setTechnicianId(ControllerUtils.setValue(technician.getTechnicianId(), technicianDTO.getTechnicianId()));

		technician.setName(ControllerUtils.setValue(technician.getName(), technicianDTO.getName()));

		technician.setContactInfo(ControllerUtils.setValue(technician.getContactInfo(), technicianDTO.getContactInfo()));



        technician = technicianDao.save(technician);
		
		ResultDTO result = new ResultDTO();
		return result;
	}

	public TechnicianDTO getTechnicianDTOById(Integer technicianId) {
	
		Technician technician = technicianDao.getById(technicianId);
			
		
		TechnicianConvertCriteriaDTO convertCriteria = new TechnicianConvertCriteriaDTO();
		return(this.convertTechnicianToTechnicianDTO(technician,convertCriteria));
	}







}
