package com.novoboot.service.impl;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.novoboot.controller.PackageController;
import com.novoboot.dao.PackageDao;
import com.novoboot.model.PackageCategory;
import com.novoboot.model.PackageCost;
import com.novoboot.model.PackagesMaster;
import com.novoboot.service.PackageService;

@Service
public class PackageServiceImpl implements PackageService {

	private static final Logger logger = Logger.getLogger(PackageServiceImpl.class);
	@Autowired
	PackageDao packageDao;

	@Override
	public List<PackageCategory> getAllPackageCategories() {
		return packageDao.getAllPackageCategories();
	}

	@Override
	public List<PackagesMaster> getPackagesByCatId(long packageCatId) {
		return packageDao.getPackagesByCatId(packageCatId);
	}

	@Override
	public List<PackageCost> getPackageCost(long packageMasterId) {
		return packageDao.getPackageCost(packageMasterId);
	}
}
