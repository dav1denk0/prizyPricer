package com.prizy.pricer.pojos;

import java.math.BigDecimal;
import java.util.SortedSet;

public class Product {

	private Long barCode;
	private String description;
	private SortedSet<Price> prices;
	private BigDecimal avgPrice;
	private BigDecimal lowestPrice;
	private BigDecimal highestPrice;
	
	public Product(Long barCode, String description, SortedSet<Price> prices,
			BigDecimal avgPrice, BigDecimal lowestPrice, BigDecimal highestPrice) {
		this.barCode = barCode;
		this.description = description;
		this.prices = prices;
		this.avgPrice = avgPrice;
		this.lowestPrice = lowestPrice;
		this.highestPrice = highestPrice;
	}
	
	public Long getBarCode() {
		return barCode;
	}
	
	public void setBarCode(Long barCode) {
		this.barCode = barCode;
	}
	
	public String getDescription() {
		return description;
	}
	
	public void setDescription(String description) {
		this.description = description;
	}
	
	public SortedSet<Price> getPrices() {
		return prices;
	}
	
	public void setPrices(SortedSet<Price> prices) {
		this.prices = prices;
	}
	
	public BigDecimal getAvgPrice() {
		return avgPrice;
	}
	
	public void setAvgPrice(BigDecimal avgPrice) {
		this.avgPrice = avgPrice;
	}
	
	public BigDecimal getLowestPrice() {
		return lowestPrice;
	}
	
	public void setLowestPrice(BigDecimal lowestPrice) {
		this.lowestPrice = lowestPrice;
	}
	
	public BigDecimal getHighestPrice() {
		return highestPrice;
	}
	
	public void setHighestPrice(BigDecimal highestPrice) {
		this.highestPrice = highestPrice;
	}
}
