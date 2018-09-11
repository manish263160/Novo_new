package com.novoboot.model;

import java.io.Serializable;

public class Promocodes implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Long id;
	private Long serviceMasterId;
	private String promocode;
	private int discount_amount;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getServiceMasterId() {
		return serviceMasterId;
	}

	public void setServiceMasterId(Long serviceMasterId) {
		this.serviceMasterId = serviceMasterId;
	}

	public String getPromocode() {
		return promocode;
	}

	public void setPromocode(String promocode) {
		this.promocode = promocode;
	}

	public int getDiscount_amount() {
		return discount_amount;
	}

	public void setDiscount_amount(int discount_amount) {
		this.discount_amount = discount_amount;
	}

}
