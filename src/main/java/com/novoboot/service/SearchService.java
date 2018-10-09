package com.novoboot.service;

import java.util.List;

import com.novoboot.model.ServiceModel;

public interface SearchService{

	List<ServiceModel> getAllService(String searchString);

}
