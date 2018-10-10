package com.novoboot.controller;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.novoboot.model.ServiceModel;
import com.novoboot.service.SearchService;

@RestController
@RequestMapping("/permitall/search")
public class SearchController {

	private static final Logger logger = Logger.getLogger(SearchController.class); 
	
	@Autowired
	SearchService searchService;
	
	
	
	
	@RequestMapping(value = "/getSearch/{searchString}", method = RequestMethod.GET)
	public ResponseEntity<List<ServiceModel>> getSearch(@PathVariable("searchString") String searchString) {

		logger.info("searchString === "+searchString);
		List<ServiceModel> listOfserviceMaster = searchService.getAllService(searchString);
		logger.info("--listOfserviceMaster-"+listOfserviceMaster);
		return new ResponseEntity<List<ServiceModel>>(listOfserviceMaster, HttpStatus.OK);
//		return null;
	}
	
}
