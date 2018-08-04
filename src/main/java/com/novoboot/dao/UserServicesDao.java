package com.novoboot.dao;

import java.util.List;

import com.novoboot.model.ServiceModel;
import com.novoboot.model.ServiceCategory;
import com.novoboot.model.ServiceCost;
import com.novoboot.model.ServiceEnquire;

public interface UserServicesDao {
	
	public List<ServiceCategory> getAllServicesDetails();
	
	public List<ServiceCategory> getAllServiceCategories();
	
	public List<ServiceModel> getAllServicesByCatId(long categoryId);
	
	public List<ServiceModel> getAllServices();
	
	public List<ServiceCost> getServicesCostById(long serviceId);
	
	public void bookOrEnquireService(ServiceEnquire enquire);
	
	public ServiceModel getServiceById(long id);

}