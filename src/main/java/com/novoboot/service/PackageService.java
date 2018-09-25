package com.novoboot.service;

import java.util.List;

import com.novoboot.model.PackageCategory;
import com.novoboot.model.PackageCost;
import com.novoboot.model.PackagesMaster;

public interface PackageService {

	List<PackageCategory> getAllPackageCategories();

	List<PackagesMaster> getPackagesByCatId(long packageCatId);

	List<PackageCost> getPackageCost(long packageMasterId);
	

}
