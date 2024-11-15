package com.aladdinworks44.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.ResponseEntity;

import com.aladdinworks44.domain.MaintenanceSchedule;
import com.aladdinworks44.dto.MaintenanceScheduleDTO;
import com.aladdinworks44.dto.MaintenanceScheduleSearchDTO;
import com.aladdinworks44.dto.MaintenanceSchedulePageDTO;
import com.aladdinworks44.dto.MaintenanceScheduleConvertCriteriaDTO;
import com.aladdinworks44.service.GenericService;
import com.aladdinworks44.dto.common.RequestDTO;
import com.aladdinworks44.dto.common.ResultDTO;
import java.util.List;
import java.util.Optional;





public interface MaintenanceScheduleService extends GenericService<MaintenanceSchedule, Integer> {

	List<MaintenanceSchedule> findAll();

	ResultDTO addMaintenanceSchedule(MaintenanceScheduleDTO maintenanceScheduleDTO, RequestDTO requestDTO);

	ResultDTO updateMaintenanceSchedule(MaintenanceScheduleDTO maintenanceScheduleDTO, RequestDTO requestDTO);

    Page<MaintenanceSchedule> getAllMaintenanceSchedules(Pageable pageable);

    Page<MaintenanceSchedule> getAllMaintenanceSchedules(Specification<MaintenanceSchedule> spec, Pageable pageable);

	ResponseEntity<MaintenanceSchedulePageDTO> getMaintenanceSchedules(MaintenanceScheduleSearchDTO maintenanceScheduleSearchDTO);
	
	List<MaintenanceScheduleDTO> convertMaintenanceSchedulesToMaintenanceScheduleDTOs(List<MaintenanceSchedule> maintenanceSchedules, MaintenanceScheduleConvertCriteriaDTO convertCriteria);

	MaintenanceScheduleDTO getMaintenanceScheduleDTOById(Integer maintenanceScheduleId);







}





