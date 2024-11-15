package com.aladdinworks44.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.ResponseEntity;

import com.aladdinworks44.domain.PowerStrip;
import com.aladdinworks44.dto.PowerStripDTO;
import com.aladdinworks44.dto.PowerStripSearchDTO;
import com.aladdinworks44.dto.PowerStripPageDTO;
import com.aladdinworks44.dto.PowerStripConvertCriteriaDTO;
import com.aladdinworks44.service.GenericService;
import com.aladdinworks44.dto.common.RequestDTO;
import com.aladdinworks44.dto.common.ResultDTO;
import java.util.List;
import java.util.Optional;





public interface PowerStripService extends GenericService<PowerStrip, Integer> {

	List<PowerStrip> findAll();

	ResultDTO addPowerStrip(PowerStripDTO powerStripDTO, RequestDTO requestDTO);

	ResultDTO updatePowerStrip(PowerStripDTO powerStripDTO, RequestDTO requestDTO);

    Page<PowerStrip> getAllPowerStrips(Pageable pageable);

    Page<PowerStrip> getAllPowerStrips(Specification<PowerStrip> spec, Pageable pageable);

	ResponseEntity<PowerStripPageDTO> getPowerStrips(PowerStripSearchDTO powerStripSearchDTO);
	
	List<PowerStripDTO> convertPowerStripsToPowerStripDTOs(List<PowerStrip> powerStrips, PowerStripConvertCriteriaDTO convertCriteria);

	PowerStripDTO getPowerStripDTOById(Integer powerStripId);







}





