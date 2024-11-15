package com.aladdinworks44.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.ResponseEntity;

import com.aladdinworks44.domain.DataCenter;
import com.aladdinworks44.dto.DataCenterDTO;
import com.aladdinworks44.dto.DataCenterSearchDTO;
import com.aladdinworks44.dto.DataCenterPageDTO;
import com.aladdinworks44.dto.DataCenterConvertCriteriaDTO;
import com.aladdinworks44.service.GenericService;
import com.aladdinworks44.dto.common.RequestDTO;
import com.aladdinworks44.dto.common.ResultDTO;
import java.util.List;
import java.util.Optional;





public interface DataCenterService extends GenericService<DataCenter, Integer> {

	List<DataCenter> findAll();

	ResultDTO addDataCenter(DataCenterDTO dataCenterDTO, RequestDTO requestDTO);

	ResultDTO updateDataCenter(DataCenterDTO dataCenterDTO, RequestDTO requestDTO);

    Page<DataCenter> getAllDataCenters(Pageable pageable);

    Page<DataCenter> getAllDataCenters(Specification<DataCenter> spec, Pageable pageable);

	ResponseEntity<DataCenterPageDTO> getDataCenters(DataCenterSearchDTO dataCenterSearchDTO);
	
	List<DataCenterDTO> convertDataCentersToDataCenterDTOs(List<DataCenter> dataCenters, DataCenterConvertCriteriaDTO convertCriteria);

	DataCenterDTO getDataCenterDTOById(Integer dataCenterId);







}





