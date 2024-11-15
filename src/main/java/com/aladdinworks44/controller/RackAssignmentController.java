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

import com.aladdinworks44.domain.RackAssignment;
import com.aladdinworks44.dto.RackAssignmentDTO;
import com.aladdinworks44.dto.RackAssignmentSearchDTO;
import com.aladdinworks44.dto.RackAssignmentPageDTO;
import com.aladdinworks44.service.RackAssignmentService;
import com.aladdinworks44.dto.common.RequestDTO;
import com.aladdinworks44.dto.common.ResultDTO;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.web.multipart.MultipartFile;




@CrossOrigin(origins = "*")
@RequestMapping("/rackAssignment")
@RestController
public class RackAssignmentController {

	private final static Logger logger = LoggerFactory.getLogger(RackAssignmentController.class);

	@Autowired
	RackAssignmentService rackAssignmentService;



	@RequestMapping(value="/", method = RequestMethod.GET)
	public List<RackAssignment> getAll() {

		List<RackAssignment> rackAssignments = rackAssignmentService.findAll();
		
		return rackAssignments;	
	}

	@GetMapping(value = "/{rackAssignmentId}")
	@ResponseBody
	public RackAssignmentDTO getRackAssignment(@PathVariable Integer rackAssignmentId) {
		
		return (rackAssignmentService.getRackAssignmentDTOById(rackAssignmentId));
	}

 	@RequestMapping(value = "/addRackAssignment", method = RequestMethod.POST)
	public ResponseEntity<?> addRackAssignment(@RequestBody RackAssignmentDTO rackAssignmentDTO, HttpServletRequest request) {

		RequestDTO requestDTO = new RequestDTO(request);
		ResultDTO result = rackAssignmentService.addRackAssignment(rackAssignmentDTO, requestDTO);
		
		return result.asResponseEntity();
	}

	@GetMapping("/rackAssignments")
	public ResponseEntity<RackAssignmentPageDTO> getRackAssignments(RackAssignmentSearchDTO rackAssignmentSearchDTO) {
 
		return rackAssignmentService.getRackAssignments(rackAssignmentSearchDTO);
	}	

	@RequestMapping(value = "/updateRackAssignment", method = RequestMethod.POST)
	public ResponseEntity<?> updateRackAssignment(@RequestBody RackAssignmentDTO rackAssignmentDTO, HttpServletRequest request) {
		RequestDTO requestDTO = new RequestDTO(request);
		ResultDTO result = rackAssignmentService.updateRackAssignment(rackAssignmentDTO, requestDTO);
		
//		if (result.isSuccessful()) {
//		}

		return result.asResponseEntity();
	}



}
