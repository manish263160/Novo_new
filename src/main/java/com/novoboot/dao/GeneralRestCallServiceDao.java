package com.novoboot.dao;

import java.util.List;

import com.novoboot.model.ServiceModel;

public interface GeneralRestCallServiceDao {

	List<ServiceModel> searchService(String searchText);

}
