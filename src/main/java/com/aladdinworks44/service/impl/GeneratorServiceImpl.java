package com.aladdinworks44.service.impl;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.HashMap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;



import com.aladdinworks44.dao.GenericDAO;
import com.aladdinworks44.service.GenericService;
import com.aladdinworks44.service.impl.GenericServiceImpl;
import com.aladdinworks44.dao.GeneratorDAO;
import com.aladdinworks44.domain.Generator;
import com.aladdinworks44.dto.GeneratorDTO;
import com.aladdinworks44.dto.GeneratorSearchDTO;
import com.aladdinworks44.dto.GeneratorPageDTO;
import com.aladdinworks44.dto.GeneratorConvertCriteriaDTO;
import com.aladdinworks44.dto.common.RequestDTO;
import com.aladdinworks44.dto.common.ResultDTO;
import com.aladdinworks44.service.GeneratorService;
import com.aladdinworks44.util.ControllerUtils;





@Service
public class GeneratorServiceImpl extends GenericServiceImpl<Generator, Integer> implements GeneratorService {

    private final static Logger logger = LoggerFactory.getLogger(GeneratorServiceImpl.class);

	@Autowired
	GeneratorDAO generatorDao;

	


	@Override
	public GenericDAO<Generator, Integer> getDAO() {
		return (GenericDAO<Generator, Integer>) generatorDao;
	}
	
	public List<Generator> findAll () {
		List<Generator> generators = generatorDao.findAll();
		
		return generators;	
		
	}

	public ResultDTO addGenerator(GeneratorDTO generatorDTO, RequestDTO requestDTO) {

		Generator generator = new Generator();

		generator.setGeneratorId(generatorDTO.getGeneratorId());


		generator.setModel(generatorDTO.getModel());


		generator.setCapacity(generatorDTO.getCapacity());


		LocalDate localDate = LocalDate.now();
		ZoneId defaultZoneId = ZoneId.systemDefault();
		Date date = Date.from(localDate.atStartOfDay(defaultZoneId).toInstant());

		generator = generatorDao.save(generator);
		
		ResultDTO result = new ResultDTO();
		return result;
	}
	
	public Page<Generator> getAllGenerators(Pageable pageable) {
		return generatorDao.findAll(pageable);
	}

	public Page<Generator> getAllGenerators(Specification<Generator> spec, Pageable pageable) {
		return generatorDao.findAll(spec, pageable);
	}

	public ResponseEntity<GeneratorPageDTO> getGenerators(GeneratorSearchDTO generatorSearchDTO) {
	
			Integer generatorId = generatorSearchDTO.getGeneratorId(); 
 			String model = generatorSearchDTO.getModel(); 
  			String sortBy = generatorSearchDTO.getSortBy();
			String sortOrder = generatorSearchDTO.getSortOrder();
			String searchQuery = generatorSearchDTO.getSearchQuery();
			Integer page = generatorSearchDTO.getPage();
			Integer size = generatorSearchDTO.getSize();

	        Specification<Generator> spec = Specification.where(null);

			spec = ControllerUtils.andIfNecessary(spec, generatorId, "generatorId"); 
			
			spec = ControllerUtils.andIfNecessary(spec, model, "model"); 
			
			

		if (searchQuery != null && !searchQuery.isEmpty()) {
			spec = spec.and((root, query, cb) -> cb.or(

             cb.like(cb.lower(root.get("model")), "%" + searchQuery.toLowerCase() + "%") 
		));}
		
		Sort sort = Sort.unsorted();
		if (sortBy != null && !sortBy.isEmpty() && sortOrder != null && !sortOrder.isEmpty()) {
			if (sortOrder.equalsIgnoreCase("asc")) {
				sort = Sort.by(sortBy).ascending();
			} else if (sortOrder.equalsIgnoreCase("desc")) {
				sort = Sort.by(sortBy).descending();
			}
		}
		Pageable pageable = PageRequest.of(page, size, sort);

		Page<Generator> generators = this.getAllGenerators(spec, pageable);
		
		//System.out.println(String.valueOf(generators.getTotalElements()) + " total ${classNamelPlural}, viewing page X of " + String.valueOf(generators.getTotalPages()));
		
		List<Generator> generatorsList = generators.getContent();
		
		GeneratorConvertCriteriaDTO convertCriteria = new GeneratorConvertCriteriaDTO();
		List<GeneratorDTO> generatorDTOs = this.convertGeneratorsToGeneratorDTOs(generatorsList,convertCriteria);
		
		GeneratorPageDTO generatorPageDTO = new GeneratorPageDTO();
		generatorPageDTO.setGenerators(generatorDTOs);
		generatorPageDTO.setTotalElements(generators.getTotalElements());
		return ResponseEntity.ok(generatorPageDTO);
	}

	public List<GeneratorDTO> convertGeneratorsToGeneratorDTOs(List<Generator> generators, GeneratorConvertCriteriaDTO convertCriteria) {
		
		List<GeneratorDTO> generatorDTOs = new ArrayList<GeneratorDTO>();
		
		for (Generator generator : generators) {
			generatorDTOs.add(convertGeneratorToGeneratorDTO(generator,convertCriteria));
		}
		
		return generatorDTOs;

	}
	
	public GeneratorDTO convertGeneratorToGeneratorDTO(Generator generator, GeneratorConvertCriteriaDTO convertCriteria) {
		
		GeneratorDTO generatorDTO = new GeneratorDTO();
		
		generatorDTO.setGeneratorId(generator.getGeneratorId());

	
		generatorDTO.setModel(generator.getModel());

	
		generatorDTO.setCapacity(generator.getCapacity());

	

		
		return generatorDTO;
	}

	public ResultDTO updateGenerator(GeneratorDTO generatorDTO, RequestDTO requestDTO) {
		
		Generator generator = generatorDao.getById(generatorDTO.getGeneratorId());

		generator.setGeneratorId(ControllerUtils.setValue(generator.getGeneratorId(), generatorDTO.getGeneratorId()));

		generator.setModel(ControllerUtils.setValue(generator.getModel(), generatorDTO.getModel()));

		generator.setCapacity(ControllerUtils.setValue(generator.getCapacity(), generatorDTO.getCapacity()));



        generator = generatorDao.save(generator);
		
		ResultDTO result = new ResultDTO();
		return result;
	}

	public GeneratorDTO getGeneratorDTOById(Integer generatorId) {
	
		Generator generator = generatorDao.getById(generatorId);
			
		
		GeneratorConvertCriteriaDTO convertCriteria = new GeneratorConvertCriteriaDTO();
		return(this.convertGeneratorToGeneratorDTO(generator,convertCriteria));
	}







}
