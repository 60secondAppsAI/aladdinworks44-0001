package com.aladdinworks44.dto;

import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class CoolingUnitAssignmentPageDTO {

	private Integer page = 0;
	private Long totalElements = 0L;

	private List<CoolingUnitAssignmentDTO> coolingUnitAssignments;
}





