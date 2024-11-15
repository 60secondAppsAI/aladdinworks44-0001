package com.aladdinworks44.controller;

import java.io.IOException;
import java.text.MessageFormat;
import java.util.List;
import java.util.ArrayList;


import com.aladdinworks44.util.Util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.sql.Timestamp;
import java.util.Date;

import com.aladdinworks44.domain.NetworkSwitchAssignment;
import com.aladdinworks44.dto.NetworkSwitchAssignmentDTO;
import com.aladdinworks44.dto.NetworkSwitchAssignmentSearchDTO;
import com.aladdinworks44.dto.NetworkSwitchAssignmentPageDTO;
import com.aladdinworks44.service.NetworkSwitchAssignmentService;
import com.aladdinworks44.dto.common.RequestDTO;
import com.aladdinworks44.dto.common.ResultDTO;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.web.multipart.MultipartFile;




@CrossOrigin(origins = "*")
@RequestMapping("/networkSwitchAssignment")
@RestController
public class NetworkSwitchAssignmentController {

	private final static Logger logger = LoggerFactory.getLogger(NetworkSwitchAssignmentController.class);

	@Autowired
	NetworkSwitchAssignmentService networkSwitchAssignmentService;



	@RequestMapping(value="/", method = RequestMethod.GET)
	public List<NetworkSwitchAssignment> getAll() {

		List<NetworkSwitchAssignment> networkSwitchAssignments = networkSwitchAssignmentService.findAll();
		
		return networkSwitchAssignments;	
	}

	@GetMapping(value = "/{networkSwitchAssignmentId}")
	@ResponseBody
	public NetworkSwitchAssignmentDTO getNetworkSwitchAssignment(@PathVariable Integer networkSwitchAssignmentId) {
		
		return (networkSwitchAssignmentService.getNetworkSwitchAssignmentDTOById(networkSwitchAssignmentId));
	}

 	@RequestMapping(value = "/addNetworkSwitchAssignment", method = RequestMethod.POST)
	public ResponseEntity<?> addNetworkSwitchAssignment(@RequestBody NetworkSwitchAssignmentDTO networkSwitchAssignmentDTO, HttpServletRequest request) {

		RequestDTO requestDTO = new RequestDTO(request);
		ResultDTO result = networkSwitchAssignmentService.addNetworkSwitchAssignment(networkSwitchAssignmentDTO, requestDTO);
		
		return result.asResponseEntity();
	}

	@GetMapping("/networkSwitchAssignments")
	public ResponseEntity<NetworkSwitchAssignmentPageDTO> getNetworkSwitchAssignments(NetworkSwitchAssignmentSearchDTO networkSwitchAssignmentSearchDTO) {
 
		return networkSwitchAssignmentService.getNetworkSwitchAssignments(networkSwitchAssignmentSearchDTO);
	}	

	@RequestMapping(value = "/updateNetworkSwitchAssignment", method = RequestMethod.POST)
	public ResponseEntity<?> updateNetworkSwitchAssignment(@RequestBody NetworkSwitchAssignmentDTO networkSwitchAssignmentDTO, HttpServletRequest request) {
		RequestDTO requestDTO = new RequestDTO(request);
		ResultDTO result = networkSwitchAssignmentService.updateNetworkSwitchAssignment(networkSwitchAssignmentDTO, requestDTO);
		
//		if (result.isSuccessful()) {
//		}

		return result.asResponseEntity();
	}



}
