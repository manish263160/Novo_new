package com.novoboot.service.impl;


import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.novoboot.dao.SearchserviceDao;
import com.novoboot.service.SearchService;

@Service
public class SearchServiceImpl implements SearchService {

	private static final Logger logger = Logger.getLogger(SearchServiceImpl.class);
	
	@Autowired
	SearchserviceDao searchserviceDao; 
}
