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

import com.aladdinworks44.domain.PowerSupplyAssignment;
import com.aladdinworks44.dto.PowerSupplyAssignmentDTO;
import com.aladdinworks44.dto.PowerSupplyAssignmentSearchDTO;
import com.aladdinworks44.dto.PowerSupplyAssignmentPageDTO;
import com.aladdinworks44.service.PowerSupplyAssignmentService;
import com.aladdinworks44.dto.common.RequestDTO;
import com.aladdinworks44.dto.common.ResultDTO;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.web.multipart.MultipartFile;




@CrossOrigin(origins = "*")
@RequestMapping("/powerSupplyAssignment")
@RestController
public class PowerSupplyAssignmentController {

	private final static Logger logger = LoggerFactory.getLogger(PowerSupplyAssignmentController.class);

	@Autowired
	PowerSupplyAssignmentService powerSupplyAssignmentService;



	@RequestMapping(value="/", method = RequestMethod.GET)
	public List<PowerSupplyAssignment> getAll() {

		List<PowerSupplyAssignment> powerSupplyAssignments = powerSupplyAssignmentService.findAll();
		
		return powerSupplyAssignments;	
	}

	@GetMapping(value = "/{powerSupplyAssignmentId}")
	@ResponseBody
	public PowerSupplyAssignmentDTO getPowerSupplyAssignment(@PathVariable Integer powerSupplyAssignmentId) {
		
		return (powerSupplyAssignmentService.getPowerSupplyAssignmentDTOById(powerSupplyAssignmentId));
	}

 	@RequestMapping(value = "/addPowerSupplyAssignment", method = RequestMethod.POST)
	public ResponseEntity<?> addPowerSupplyAssignment(@RequestBody PowerSupplyAssignmentDTO powerSupplyAssignmentDTO, HttpServletRequest request) {

		RequestDTO requestDTO = new RequestDTO(request);
		ResultDTO result = powerSupplyAssignmentService.addPowerSupplyAssignment(powerSupplyAssignmentDTO, requestDTO);
		
		return result.asResponseEntity();
	}

	@GetMapping("/powerSupplyAssignments")
	public ResponseEntity<PowerSupplyAssignmentPageDTO> getPowerSupplyAssignments(PowerSupplyAssignmentSearchDTO powerSupplyAssignmentSearchDTO) {
 
		return powerSupplyAssignmentService.getPowerSupplyAssignments(powerSupplyAssignmentSearchDTO);
	}	

	@RequestMapping(value = "/updatePowerSupplyAssignment", method = RequestMethod.POST)
	public ResponseEntity<?> updatePowerSupplyAssignment(@RequestBody PowerSupplyAssignmentDTO powerSupplyAssignmentDTO, HttpServletRequest request) {
		RequestDTO requestDTO = new RequestDTO(request);
		ResultDTO result = powerSupplyAssignmentService.updatePowerSupplyAssignment(powerSupplyAssignmentDTO, requestDTO);
		
//		if (result.isSuccessful()) {
//		}

		return result.asResponseEntity();
	}



}
