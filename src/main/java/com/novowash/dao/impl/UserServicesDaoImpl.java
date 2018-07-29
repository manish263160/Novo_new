package com.novowash.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.sql.Types;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.apache.log4j.Logger;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import com.novowash.Enums.CommonEnums;
import com.novowash.Enums.CommonEnums.STATUS;
import com.novowash.dao.UserServicesDao;
import com.novowash.jdbcTemplate.NovoJdbcTemplate;
import com.novowash.model.Service;
import com.novowash.model.ServiceCategory;
import com.novowash.model.ServiceCost;
import com.novowash.model.ServiceEnquire;

@Repository
public class UserServicesDaoImpl extends NovoJdbcTemplate implements UserServicesDao {

	private static final Logger logger = Logger.getLogger(UserServicesDaoImpl.class);
	
	private static final String GET_ALL_SERVICE_CAT = "select * from service_cat_m where status = ?";

	private static final String GET_ALL_SERVICES = "select * from service_m where status = ?";

	private static final String GET_SERVICES_COST = "select * from service_cost where status = ? and service_id=? order by price";

	private static final String BOOK_SERVICE_SQL = "insert into service_enquire(service_id,service_cost_id,service_date,house,landmark,locality,name,phone,email,status,created_on,created_by) "
			+ "						values(?,?,?,?,?,?,?,?,?,?,now(),?)";
	
	private static final String ALL_SERVICES = "select scm.id ,scm.cat_name,scm.cat_desc,scm.image_url cat_image_url, " +
			" sm.id as ser_id ,sm.service_cat_id,sm.service_name, sm.service_desc,sm.service_type,sm.image_url service_image_url, " +
			" sc.id as service_cost_id, sc.service_id, sc.cost_details, sc.price  " +
			" from service_cat_m scm " +
			" left join  service_m sm on sm.service_cat_id = scm.id and sm.status = " + STATUS.ACTIVE.ID +
			" left join service_cost sc on sc.service_id = sm.id and sc.status = " + STATUS.ACTIVE.ID +
			" where scm.status = " + STATUS.ACTIVE.ID;
	
	@Override
	public List<ServiceCategory> getAllServicesDetails() {
		return getJdbcTemplate().query(ALL_SERVICES, new ServiceRowMapper());
	}
	
	@Override
	public List<ServiceCategory> getAllServiceCategories() {
		return getJdbcTemplate().query(GET_ALL_SERVICE_CAT, new BeanPropertyRowMapper<ServiceCategory>(ServiceCategory.class), STATUS.ACTIVE.ID);
	}
	
	@Override
	public List<Service> getAllServicesByCatId(long categoryId) {
		String sql = GET_ALL_SERVICES + " and service_cat_id = ?";
		return getJdbcTemplate().query(sql, new BeanPropertyRowMapper<Service>(Service.class), STATUS.ACTIVE.ID, categoryId);
	}

	@Override
	public List<Service> getAllServices() {
		return getJdbcTemplate().query(GET_ALL_SERVICES, new BeanPropertyRowMapper<Service>(Service.class), STATUS.ACTIVE.ID);
	}

	@Override
	public List<ServiceCost> getServicesCostById(long serviceId) {
		return getJdbcTemplate().query(GET_SERVICES_COST, new BeanPropertyRowMapper<ServiceCost>(ServiceCost.class),
				new Object[] { CommonEnums.STATUS.ACTIVE.ID, serviceId });
	}

	@Override
	public void bookOrEnquireService(ServiceEnquire enquire) {
		logger.info("UserServicesDaoImpl:bookOrEnquireService() Booking/equiring service for email: " + enquire.getEmail());
		KeyHolder keyHolder = new GeneratedKeyHolder();
		getJdbcTemplate().update(new PreparedStatementCreator() {
			@Override
			public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
				PreparedStatement pstmt = connection.prepareStatement(BOOK_SERVICE_SQL, PreparedStatement.RETURN_GENERATED_KEYS);
				int index = 1;
				// service_id,service_cost_id,service_date,house,landmark,locality,name,phone,email,status,created_on,created_by
				pstmt.setLong(index++, enquire.getServiceId());
				pstmt.setLong(index++, enquire.getServiceCostId());
				if (null != enquire.getServiceDate()) {
					pstmt.setTimestamp(index++, new Timestamp(enquire.getServiceDate().getTime()));
				} else {
					pstmt.setNull(index++, Types.TIMESTAMP);

				}
				pstmt.setString(index++, enquire.getHouse());
				pstmt.setString(index++, enquire.getLandmark());
				pstmt.setString(index++, enquire.getLocality());
				pstmt.setString(index++, enquire.getName());
				pstmt.setString(index++, enquire.getPhone());
				pstmt.setString(index++, enquire.getEmail());
				pstmt.setInt(index++, STATUS.ACTIVE.ID);
				pstmt.setString(index++, enquire.getEmail());

				return pstmt;
			}
		}, keyHolder);
		enquire.setId(keyHolder.getKey().longValue());
	}

	@Override
	public Service getServiceById(long id) {
		String sql = GET_ALL_SERVICES + " and id = ?";
		return getJdbcTemplate().queryForObject(sql, new BeanPropertyRowMapper<Service>(Service.class), STATUS.ACTIVE.ID, id);
	}
}
 class ServiceRowMapper implements ResultSetExtractor<List<ServiceCategory>> {
	
	@Override
	public List<ServiceCategory> extractData(ResultSet rs) throws SQLException, DataAccessException {
		Map<Long, ServiceCategory> categoryMap = new HashMap<Long, ServiceCategory>();
		Map<Long, Service> serviceMap = new HashMap<Long, Service>();
		List<ServiceCategory> categories = new ArrayList<ServiceCategory>();
		while(rs.next()) {
			ServiceCategory  category = new ServiceCategory();
			category.setId(rs.getLong("id"));
			category.setCatName(rs.getString("cat_name"));
			category.setCatDesc(rs.getString("cat_desc"));
			category.setImageUrl(rs.getString("cat_image_url"));
			category.setStatus(STATUS.ACTIVE.ID);
			categoryMap.put(rs.getLong("id"), category);
		}
		categories.addAll(categoryMap.values().stream().collect(Collectors.toList()));
		return categories;
	}
 }