package com.novoboot.service.impl;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.novoboot.dao.PromocodeDao;
import com.novoboot.model.Promocodes;
import com.novoboot.service.PromocodeService;

@Service
public class PromocodeServiceImpl implements PromocodeService {
	
	private static final Logger logger = Logger.getLogger(PromocodeServiceImpl.class); 
	
	@Autowired
	PromocodeDao promocodeDao;

	@Override
	public Promocodes getPromocodeById(String coupanCode) {
		return promocodeDao.getPromocodeById(coupanCode);
	} 

}
