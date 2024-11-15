package com.aladdinworks44.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.ResponseEntity;

import com.aladdinworks44.domain.Rack;
import com.aladdinworks44.dto.RackDTO;
import com.aladdinworks44.dto.RackSearchDTO;
import com.aladdinworks44.dto.RackPageDTO;
import com.aladdinworks44.dto.RackConvertCriteriaDTO;
import com.aladdinworks44.service.GenericService;
import com.aladdinworks44.dto.common.RequestDTO;
import com.aladdinworks44.dto.common.ResultDTO;
import java.util.List;
import java.util.Optional;





public interface RackService extends GenericService<Rack, Integer> {

	List<Rack> findAll();

	ResultDTO addRack(RackDTO rackDTO, RequestDTO requestDTO);

	ResultDTO updateRack(RackDTO rackDTO, RequestDTO requestDTO);

    Page<Rack> getAllRacks(Pageable pageable);

    Page<Rack> getAllRacks(Specification<Rack> spec, Pageable pageable);

	ResponseEntity<RackPageDTO> getRacks(RackSearchDTO rackSearchDTO);
	
	List<RackDTO> convertRacksToRackDTOs(List<Rack> racks, RackConvertCriteriaDTO convertCriteria);

	RackDTO getRackDTOById(Integer rackId);







}





