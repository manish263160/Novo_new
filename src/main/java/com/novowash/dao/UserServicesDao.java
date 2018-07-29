package com.novowash.dao;

import java.util.List;

import com.novowash.model.Service;
import com.novowash.model.ServiceCategory;
import com.novowash.model.ServiceCost;
import com.novowash.model.ServiceEnquire;

public interface UserServicesDao {
	
	public List<ServiceCategory> getAllServicesDetails();
	
	public List<ServiceCategory> getAllServiceCategories();
	
	public List<Service> getAllServicesByCatId(long categoryId);
	
	public List<Service> getAllServices();
	
	public List<ServiceCost> getServicesCostById(long serviceId);
	
	public void bookOrEnquireService(ServiceEnquire enquire);
	
	public Service getServiceById(long id);

}
