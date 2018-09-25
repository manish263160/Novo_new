package com.novoboot.service;

import java.util.List;

import com.novoboot.model.ServiceModel;

public interface GeneralRestCallService {

	List<ServiceModel> searchService(String searchText);

}
