package com.prizy.pricer.seleniumTests.domain;

import java.math.BigDecimal;

public class PriceStub {

	private String store;
	private BigDecimal price;
	private String notes;
	
	public String getStore() {
		return store;
	}
	
	public void setStore(String store) {
		this.store = store;
	}
	
	public BigDecimal getPrice() {
		return price;
	}
	
	public void setPrice(BigDecimal price) {
		this.price = price;
	}
	
	public String getNotes() {
		return notes;
	}
	
	public void setNotes(String notes) {
		this.notes = notes;
	}
}
