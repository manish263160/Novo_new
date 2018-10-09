package com.novoboot.service.impl;


import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.novoboot.dao.SearchserviceDao;
import com.novoboot.dao.ServicesDao;
import com.novoboot.model.ServiceModel;
import com.novoboot.service.SearchService;

@Service
public class SearchServiceImpl implements SearchService {

	private static final Logger logger = Logger.getLogger(SearchServiceImpl.class);
	
	@Autowired
	SearchserviceDao searchserviceDao;

	@Autowired
	ServicesDao servicesDao; 
	@Override
	public List<ServiceModel> getAllService(String searchString) {
		return servicesDao.getAllServices(searchString);
	} 
}
