package com.novoboot.controller;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.novoboot.service.SearchService;

@RestController
@RequestMapping("/search")
public class SearchController {

	private static final Logger logger = Logger.getLogger(SearchController.class); 
	
	@Autowired
	SearchService searchService;
	
	
}
