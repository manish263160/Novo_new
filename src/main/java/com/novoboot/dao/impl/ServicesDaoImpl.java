package com.novoboot.dao.impl;

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

import com.novoboot.Enums.CommonEnums;
import com.novoboot.Enums.CommonEnums.STATUS;
import com.novoboot.dao.ServicesDao;
import com.novoboot.jdbcTemplate.NovoJdbcTemplate;
import com.novoboot.model.ServiceModel;
import com.novoboot.model.ServiceCategory;
import com.novoboot.model.ServiceCost;
import com.novoboot.model.ServiceEnquire;

@Repository
public class ServicesDaoImpl extends NovoJdbcTemplate implements ServicesDao {

	private static final Logger logger = Logger.getLogger(ServicesDaoImpl.class);
	
	private static final String GET_ALL_SERVICE_CAT = "select * from service_cat_master where status = ?";

	private static final String GET_ALL_SERVICES = "select * from service_master where status = ?";

	private static final String GET_SERVICES_COST = "select * from service_cost_master where status = ? and service_master_id=? ";

	private static final String BOOK_SERVICE_SQL = "insert into booking_details(service_id,service_cost_id,service_date,house,landmark,locality,name,phone,email,status,created_on,created_by) "
			+ "						values(?,?,?,?,?,?,?,?,?,?,now(),?)";
	
/*	private static final String ALL_SERVICES = "select scm.id ,scm.cat_name,scm.cat_desc,scm.image_url cat_image_url, " +
			" sm.id as ser_id ,sm.service_cat_id,sm.service_name, sm.service_desc,sm.service_type,sm.image_url service_image_url, " +
			" sc.id as service_cost_id, sc.service_id, sc.cost_details, sc.price  " +
			" from service_cat_master scm " +
			" left join  service_master sm on sm.service_cat_id = scm.id and sm.status = " + STATUS.ACTIVE.ID +
			" left join service_cost sc on sc.service_id = sm.id and sc.status = " + STATUS.ACTIVE.ID +
			" where scm.status = " + STATUS.ACTIVE.ID;*/
	
	/*@Override
	public List<ServiceCategory> getAllServicesDetails() {
		return getJdbcTemplate().query(ALL_SERVICES, new ServiceRowMapper());
	}*/
	
	@Override
	public List<ServiceCategory> getAllServiceCategories() {
		return getJdbcTemplate().query(GET_ALL_SERVICE_CAT, new BeanPropertyRowMapper<ServiceCategory>(ServiceCategory.class), STATUS.ACTIVE.ID);
	}
	
	@Override
	public List<ServiceModel> getAllServicesByCatId(long categoryId) {
		String sql = GET_ALL_SERVICES + " and service_cat_id = ?";
		return getJdbcTemplate().query(sql, new BeanPropertyRowMapper<ServiceModel>(ServiceModel.class), STATUS.ACTIVE.ID, categoryId);
	}

	@Override
	public List<ServiceModel> getAllServices(String searchService) {
		if(searchService == null)
		return getJdbcTemplate().query(GET_ALL_SERVICES, new BeanPropertyRowMapper<ServiceModel>(ServiceModel.class), STATUS.ACTIVE.ID);
		else {
			String searchQuery = "SELECT * FROM service_master WHERE service_name LIKE ? OR service_desc LIKE ? ";
			 
			    List<ServiceModel> list = getJdbcTemplate().query(searchQuery, new BeanPropertyRowMapper<ServiceModel>(ServiceModel.class), "%"+searchService+"%" ,"%"+searchQuery+"5");
			return list;
		}
			
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
	public ServiceModel getServiceById(long id) {
		String sql = GET_ALL_SERVICES + " and id = ?";
		return getJdbcTemplate().queryForObject(sql, new BeanPropertyRowMapper<ServiceModel>(ServiceModel.class), STATUS.ACTIVE.ID, id);
	}

	@Override
	public List<ServiceModel> getRecomendedService() {
		
		String query ="SELECT sm.id as id, sm.service_cat_id,sm.service_name , sm.service_cat_id,sm.service_desc,sm.service_type , rs.image_url , sm.status , sm.service_for , sm.created_on , sm.created_by FROM service_master sm INNER JOIN recommended_services rs ON sm.id = rs.service_master_id  ";
		return getJdbcTemplate().query(query, new BeanPropertyRowMapper<ServiceModel>(ServiceModel.class));
	}
}
 class ServiceRowMapper implements ResultSetExtractor<List<ServiceCategory>> {
	
	@Override
	public List<ServiceCategory> extractData(ResultSet rs) throws SQLException, DataAccessException {
		Map<Long, ServiceCategory> categoryMap = new HashMap<Long, ServiceCategory>();
		Map<Long, ServiceModel> serviceMap = new HashMap<Long, ServiceModel>();
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