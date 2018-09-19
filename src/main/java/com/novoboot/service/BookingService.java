package com.novoboot.service;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import com.novoboot.model.BookingDetails;
import com.novoboot.model.ServiceCategory;
import com.novoboot.model.ServiceCost;
import com.novoboot.model.ServiceEnquire;
import com.novoboot.model.ServiceModel;

public interface BookingService extends Serializable {

//	public List<ServiceCategory> getAllServicesDetails();

	public List<ServiceCategory> getAllServiceCategories();

	public List<ServiceModel> getAllServicsByCatId(long categoryId);

	public List<ServiceModel> getAllServices();

	public List<ServiceCost> getServicesCostById(long serviceId);

	public void bookOrEnquireService(ServiceEnquire enquire);

	public List<BookingDetails> getAllBooking();

	public Map<String, Map<String, Boolean>> getServiceDateTimeSlot(long serviceId);

}
