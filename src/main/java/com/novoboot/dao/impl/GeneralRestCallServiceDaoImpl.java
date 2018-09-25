package com.novoboot.dao.impl;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.stereotype.Repository;

import com.novoboot.Enums.CommonEnums.STATUS;
import com.novoboot.dao.GeneralRestCallServiceDao;
import com.novoboot.jdbcTemplate.NovoJdbcTemplate;
import com.novoboot.model.PackageCost;
import com.novoboot.model.ServiceModel;

@Repository
public class GeneralRestCallServiceDaoImpl extends NovoJdbcTemplate implements GeneralRestCallServiceDao {

	
	private static final Logger logger = Logger.getLogger(GeneralRestCallServiceDaoImpl.class);
	@Override
	public List<ServiceModel> searchService(String searchText) {
		
		String searchQuery = "SELECT * FROM service_master where service_name like ? or service_desc like ? " ;
		
		 return getJdbcTemplate().query(searchQuery,
					new BeanPropertyRowMapper<ServiceModel>(ServiceModel.class),"%"+searchText+"%", "%"+searchText+"%");
	}

}
