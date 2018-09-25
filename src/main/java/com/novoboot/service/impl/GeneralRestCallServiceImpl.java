package com.novoboot.service.impl;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.novoboot.dao.GeneralRestCallServiceDao;
import com.novoboot.model.ServiceModel;
import com.novoboot.service.GeneralRestCallService;

@Service
public class GeneralRestCallServiceImpl implements GeneralRestCallService {

	private static final Logger logger = Logger.getLogger(GeneralRestCallServiceImpl.class);
	@Autowired
	GeneralRestCallServiceDao generalRestCallServiceDao;

	@Override
	public List<ServiceModel> searchService(String searchText) {
		// TODO Auto-generated method stub
		return generalRestCallServiceDao.searchService(searchText);
	}
}
