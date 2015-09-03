package com.prizy.pricer.pojos;

import java.math.BigDecimal;

public class Price {
	private String store;
	private BigDecimal price;
	private String notes;
	private Product product;
	
	public Price(String store, BigDecimal price, String notes, Product product) {
		this.store = store;
		this.price = price;
		this.notes = notes;
		this.product = product;
	}

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

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}
}
