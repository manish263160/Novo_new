package com.novoboot.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.novoboot.dao.PartnerDao;
import com.novoboot.model.Partner;

@Service
public class PartnerService {
	
	@Autowired private PartnerDao partnerDao;
	
	public Partner addPartner(Partner partner) {
		 partnerDao.addPartner(partner);
		 return partner;
	}
}
