package com.aladdinworks44.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.ResponseEntity;

import com.aladdinworks44.domain.NetworkSwitchAssignment;
import com.aladdinworks44.dto.NetworkSwitchAssignmentDTO;
import com.aladdinworks44.dto.NetworkSwitchAssignmentSearchDTO;
import com.aladdinworks44.dto.NetworkSwitchAssignmentPageDTO;
import com.aladdinworks44.dto.NetworkSwitchAssignmentConvertCriteriaDTO;
import com.aladdinworks44.service.GenericService;
import com.aladdinworks44.dto.common.RequestDTO;
import com.aladdinworks44.dto.common.ResultDTO;
import java.util.List;
import java.util.Optional;





public interface NetworkSwitchAssignmentService extends GenericService<NetworkSwitchAssignment, Integer> {

	List<NetworkSwitchAssignment> findAll();

	ResultDTO addNetworkSwitchAssignment(NetworkSwitchAssignmentDTO networkSwitchAssignmentDTO, RequestDTO requestDTO);

	ResultDTO updateNetworkSwitchAssignment(NetworkSwitchAssignmentDTO networkSwitchAssignmentDTO, RequestDTO requestDTO);

    Page<NetworkSwitchAssignment> getAllNetworkSwitchAssignments(Pageable pageable);

    Page<NetworkSwitchAssignment> getAllNetworkSwitchAssignments(Specification<NetworkSwitchAssignment> spec, Pageable pageable);

	ResponseEntity<NetworkSwitchAssignmentPageDTO> getNetworkSwitchAssignments(NetworkSwitchAssignmentSearchDTO networkSwitchAssignmentSearchDTO);
	
	List<NetworkSwitchAssignmentDTO> convertNetworkSwitchAssignmentsToNetworkSwitchAssignmentDTOs(List<NetworkSwitchAssignment> networkSwitchAssignments, NetworkSwitchAssignmentConvertCriteriaDTO convertCriteria);

	NetworkSwitchAssignmentDTO getNetworkSwitchAssignmentDTOById(Integer networkSwitchAssignmentId);







}





