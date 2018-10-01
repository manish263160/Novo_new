package com.novoboot.dao.impl;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.stereotype.Repository;

import com.novoboot.Enums.CommonEnums.STATUS;
import com.novoboot.dao.PackageDao;
import com.novoboot.jdbcTemplate.NovoJdbcTemplate;
import com.novoboot.model.PackageCategory;
import com.novoboot.model.PackageCost;
import com.novoboot.model.PackagesMaster;

@Repository
public class PackageDaoImpl extends NovoJdbcTemplate implements PackageDao {

	private static final Logger logger = Logger.getLogger(PackageDaoImpl.class);

	@Override
	public List<PackageCategory> getAllPackageCategories() {
		String GET_ALL_PACKAGE_CAT = "select * from packages_cat_master where status = ?";
		return getJdbcTemplate().query(GET_ALL_PACKAGE_CAT,
				new BeanPropertyRowMapper<PackageCategory>(PackageCategory.class), STATUS.ACTIVE.ID);
	}

	@Override
	public List<PackagesMaster> getPackagesByCatId(long packageCatId) {
		String query = "select * from packages_master where package_cat_id=? and status = ?";
		 return getJdbcTemplate().query(query,
				new BeanPropertyRowMapper<PackagesMaster>(PackagesMaster.class),packageCatId, STATUS.ACTIVE.ID);
	}

	@Override
	public List<PackageCost> getPackageCost(long packageMasterId) {
		String query = "select * from packages_cost_master where packages_master_id=? ";
		 return getJdbcTemplate().query(query,
				new BeanPropertyRowMapper<PackageCost>(PackageCost.class),packageMasterId);
	}

}
