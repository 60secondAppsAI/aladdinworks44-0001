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
import com.aladdinworks44.dao.NetworkSwitchAssignmentDAO;
import com.aladdinworks44.domain.NetworkSwitchAssignment;
import com.aladdinworks44.dto.NetworkSwitchAssignmentDTO;
import com.aladdinworks44.dto.NetworkSwitchAssignmentSearchDTO;
import com.aladdinworks44.dto.NetworkSwitchAssignmentPageDTO;
import com.aladdinworks44.dto.NetworkSwitchAssignmentConvertCriteriaDTO;
import com.aladdinworks44.dto.common.RequestDTO;
import com.aladdinworks44.dto.common.ResultDTO;
import com.aladdinworks44.service.NetworkSwitchAssignmentService;
import com.aladdinworks44.util.ControllerUtils;





@Service
public class NetworkSwitchAssignmentServiceImpl extends GenericServiceImpl<NetworkSwitchAssignment, Integer> implements NetworkSwitchAssignmentService {

    private final static Logger logger = LoggerFactory.getLogger(NetworkSwitchAssignmentServiceImpl.class);

	@Autowired
	NetworkSwitchAssignmentDAO networkSwitchAssignmentDao;

	


	@Override
	public GenericDAO<NetworkSwitchAssignment, Integer> getDAO() {
		return (GenericDAO<NetworkSwitchAssignment, Integer>) networkSwitchAssignmentDao;
	}
	
	public List<NetworkSwitchAssignment> findAll () {
		List<NetworkSwitchAssignment> networkSwitchAssignments = networkSwitchAssignmentDao.findAll();
		
		return networkSwitchAssignments;	
		
	}

	public ResultDTO addNetworkSwitchAssignment(NetworkSwitchAssignmentDTO networkSwitchAssignmentDTO, RequestDTO requestDTO) {

		NetworkSwitchAssignment networkSwitchAssignment = new NetworkSwitchAssignment();

		networkSwitchAssignment.setNetworkSwitchAssignmentId(networkSwitchAssignmentDTO.getNetworkSwitchAssignmentId());


		LocalDate localDate = LocalDate.now();
		ZoneId defaultZoneId = ZoneId.systemDefault();
		Date date = Date.from(localDate.atStartOfDay(defaultZoneId).toInstant());

		networkSwitchAssignment = networkSwitchAssignmentDao.save(networkSwitchAssignment);
		
		ResultDTO result = new ResultDTO();
		return result;
	}
	
	public Page<NetworkSwitchAssignment> getAllNetworkSwitchAssignments(Pageable pageable) {
		return networkSwitchAssignmentDao.findAll(pageable);
	}

	public Page<NetworkSwitchAssignment> getAllNetworkSwitchAssignments(Specification<NetworkSwitchAssignment> spec, Pageable pageable) {
		return networkSwitchAssignmentDao.findAll(spec, pageable);
	}

	public ResponseEntity<NetworkSwitchAssignmentPageDTO> getNetworkSwitchAssignments(NetworkSwitchAssignmentSearchDTO networkSwitchAssignmentSearchDTO) {
	
			Integer networkSwitchAssignmentId = networkSwitchAssignmentSearchDTO.getNetworkSwitchAssignmentId(); 
 			String sortBy = networkSwitchAssignmentSearchDTO.getSortBy();
			String sortOrder = networkSwitchAssignmentSearchDTO.getSortOrder();
			String searchQuery = networkSwitchAssignmentSearchDTO.getSearchQuery();
			Integer page = networkSwitchAssignmentSearchDTO.getPage();
			Integer size = networkSwitchAssignmentSearchDTO.getSize();

	        Specification<NetworkSwitchAssignment> spec = Specification.where(null);

			spec = ControllerUtils.andIfNecessary(spec, networkSwitchAssignmentId, "networkSwitchAssignmentId"); 
			

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

		Page<NetworkSwitchAssignment> networkSwitchAssignments = this.getAllNetworkSwitchAssignments(spec, pageable);
		
		//System.out.println(String.valueOf(networkSwitchAssignments.getTotalElements()) + " total ${classNamelPlural}, viewing page X of " + String.valueOf(networkSwitchAssignments.getTotalPages()));
		
		List<NetworkSwitchAssignment> networkSwitchAssignmentsList = networkSwitchAssignments.getContent();
		
		NetworkSwitchAssignmentConvertCriteriaDTO convertCriteria = new NetworkSwitchAssignmentConvertCriteriaDTO();
		List<NetworkSwitchAssignmentDTO> networkSwitchAssignmentDTOs = this.convertNetworkSwitchAssignmentsToNetworkSwitchAssignmentDTOs(networkSwitchAssignmentsList,convertCriteria);
		
		NetworkSwitchAssignmentPageDTO networkSwitchAssignmentPageDTO = new NetworkSwitchAssignmentPageDTO();
		networkSwitchAssignmentPageDTO.setNetworkSwitchAssignments(networkSwitchAssignmentDTOs);
		networkSwitchAssignmentPageDTO.setTotalElements(networkSwitchAssignments.getTotalElements());
		return ResponseEntity.ok(networkSwitchAssignmentPageDTO);
	}

	public List<NetworkSwitchAssignmentDTO> convertNetworkSwitchAssignmentsToNetworkSwitchAssignmentDTOs(List<NetworkSwitchAssignment> networkSwitchAssignments, NetworkSwitchAssignmentConvertCriteriaDTO convertCriteria) {
		
		List<NetworkSwitchAssignmentDTO> networkSwitchAssignmentDTOs = new ArrayList<NetworkSwitchAssignmentDTO>();
		
		for (NetworkSwitchAssignment networkSwitchAssignment : networkSwitchAssignments) {
			networkSwitchAssignmentDTOs.add(convertNetworkSwitchAssignmentToNetworkSwitchAssignmentDTO(networkSwitchAssignment,convertCriteria));
		}
		
		return networkSwitchAssignmentDTOs;

	}
	
	public NetworkSwitchAssignmentDTO convertNetworkSwitchAssignmentToNetworkSwitchAssignmentDTO(NetworkSwitchAssignment networkSwitchAssignment, NetworkSwitchAssignmentConvertCriteriaDTO convertCriteria) {
		
		NetworkSwitchAssignmentDTO networkSwitchAssignmentDTO = new NetworkSwitchAssignmentDTO();
		
		networkSwitchAssignmentDTO.setNetworkSwitchAssignmentId(networkSwitchAssignment.getNetworkSwitchAssignmentId());

	

		
		return networkSwitchAssignmentDTO;
	}

	public ResultDTO updateNetworkSwitchAssignment(NetworkSwitchAssignmentDTO networkSwitchAssignmentDTO, RequestDTO requestDTO) {
		
		NetworkSwitchAssignment networkSwitchAssignment = networkSwitchAssignmentDao.getById(networkSwitchAssignmentDTO.getNetworkSwitchAssignmentId());

		networkSwitchAssignment.setNetworkSwitchAssignmentId(ControllerUtils.setValue(networkSwitchAssignment.getNetworkSwitchAssignmentId(), networkSwitchAssignmentDTO.getNetworkSwitchAssignmentId()));



        networkSwitchAssignment = networkSwitchAssignmentDao.save(networkSwitchAssignment);
		
		ResultDTO result = new ResultDTO();
		return result;
	}

	public NetworkSwitchAssignmentDTO getNetworkSwitchAssignmentDTOById(Integer networkSwitchAssignmentId) {
	
		NetworkSwitchAssignment networkSwitchAssignment = networkSwitchAssignmentDao.getById(networkSwitchAssignmentId);
			
		
		NetworkSwitchAssignmentConvertCriteriaDTO convertCriteria = new NetworkSwitchAssignmentConvertCriteriaDTO();
		return(this.convertNetworkSwitchAssignmentToNetworkSwitchAssignmentDTO(networkSwitchAssignment,convertCriteria));
	}







}
