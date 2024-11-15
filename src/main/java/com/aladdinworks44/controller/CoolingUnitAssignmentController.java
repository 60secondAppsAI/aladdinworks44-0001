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

import com.aladdinworks44.domain.CoolingUnitAssignment;
import com.aladdinworks44.dto.CoolingUnitAssignmentDTO;
import com.aladdinworks44.dto.CoolingUnitAssignmentSearchDTO;
import com.aladdinworks44.dto.CoolingUnitAssignmentPageDTO;
import com.aladdinworks44.service.CoolingUnitAssignmentService;
import com.aladdinworks44.dto.common.RequestDTO;
import com.aladdinworks44.dto.common.ResultDTO;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.web.multipart.MultipartFile;




@CrossOrigin(origins = "*")
@RequestMapping("/coolingUnitAssignment")
@RestController
public class CoolingUnitAssignmentController {

	private final static Logger logger = LoggerFactory.getLogger(CoolingUnitAssignmentController.class);

	@Autowired
	CoolingUnitAssignmentService coolingUnitAssignmentService;



	@RequestMapping(value="/", method = RequestMethod.GET)
	public List<CoolingUnitAssignment> getAll() {

		List<CoolingUnitAssignment> coolingUnitAssignments = coolingUnitAssignmentService.findAll();
		
		return coolingUnitAssignments;	
	}

	@GetMapping(value = "/{coolingUnitAssignmentId}")
	@ResponseBody
	public CoolingUnitAssignmentDTO getCoolingUnitAssignment(@PathVariable Integer coolingUnitAssignmentId) {
		
		return (coolingUnitAssignmentService.getCoolingUnitAssignmentDTOById(coolingUnitAssignmentId));
	}

 	@RequestMapping(value = "/addCoolingUnitAssignment", method = RequestMethod.POST)
	public ResponseEntity<?> addCoolingUnitAssignment(@RequestBody CoolingUnitAssignmentDTO coolingUnitAssignmentDTO, HttpServletRequest request) {

		RequestDTO requestDTO = new RequestDTO(request);
		ResultDTO result = coolingUnitAssignmentService.addCoolingUnitAssignment(coolingUnitAssignmentDTO, requestDTO);
		
		return result.asResponseEntity();
	}

	@GetMapping("/coolingUnitAssignments")
	public ResponseEntity<CoolingUnitAssignmentPageDTO> getCoolingUnitAssignments(CoolingUnitAssignmentSearchDTO coolingUnitAssignmentSearchDTO) {
 
		return coolingUnitAssignmentService.getCoolingUnitAssignments(coolingUnitAssignmentSearchDTO);
	}	

	@RequestMapping(value = "/updateCoolingUnitAssignment", method = RequestMethod.POST)
	public ResponseEntity<?> updateCoolingUnitAssignment(@RequestBody CoolingUnitAssignmentDTO coolingUnitAssignmentDTO, HttpServletRequest request) {
		RequestDTO requestDTO = new RequestDTO(request);
		ResultDTO result = coolingUnitAssignmentService.updateCoolingUnitAssignment(coolingUnitAssignmentDTO, requestDTO);
		
//		if (result.isSuccessful()) {
//		}

		return result.asResponseEntity();
	}



}
