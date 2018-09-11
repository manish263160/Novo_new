package com.novoboot.dao.impl;

import org.apache.log4j.Logger;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.stereotype.Repository;

import com.novoboot.dao.PromocodeDao;
import com.novoboot.jdbcTemplate.NovoJdbcTemplate;
import com.novoboot.model.Promocodes;

@Repository
public class PromocodeDaoImpl extends NovoJdbcTemplate implements PromocodeDao  {

	private static final Logger logger = Logger.getLogger(PromocodeDaoImpl.class);

	@Override
	public Promocodes getPromocodeById(long serviceId) {

		logger.debug("service id here=="+serviceId);
		String query = "select * from promocodes where service_master_id = ?";
		return getJdbcTemplate().queryForObject(query, new BeanPropertyRowMapper<Promocodes>(Promocodes.class), serviceId);
	} 
}
