package com.novowash.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.novowash.dao.PartnerDao;
import com.novowash.model.Partner;

@Service
public class PartnerService {
	
	@Autowired private PartnerDao partnerDao;
	
	public Partner addPartner(Partner partner) {
		 partnerDao.addPartner(partner);
		 return partner;
	}
}
