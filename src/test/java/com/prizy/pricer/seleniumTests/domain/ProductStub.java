package com.prizy.pricer.seleniumTests.domain;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ProductStub {

	private String barCode;
	private String description;
	private List<PriceStub> prices;
	private List<BigDecimal> priceValues;
	private BigDecimal avgPrice;
	private BigDecimal lowestPrice;
	private BigDecimal highestPrice;
	private BigDecimal idealPrice;
	
	public ProductStub() {
		this.prices = new ArrayList<PriceStub>();
		this.priceValues = new ArrayList<BigDecimal>();
	}
	
	public String getBarCode() {
		return barCode;
	}
	
	public void setBarCode(String barCode) {
		this.barCode = barCode;
	}
	
	public String getDescription() {
		return description;
	}
	
	public void setDescription(String description) {
		this.description = description;
	}
	
	public List<PriceStub> getPrices() {
		return prices;
	}
	
	public void setPrices(List<PriceStub> prices) {
		this.prices = prices;
	}
	
	public BigDecimal getAvgPrice() {
		return avgPrice;
	}
	
	public void setAvgPrice() {
		List<BigDecimal> orderedPrices = this.getPriceValues();
		BigDecimal total = new BigDecimal(0);
		BigDecimal numberOfRecords = new BigDecimal(0);
		for(BigDecimal price : orderedPrices) {
			total = total.add(price);
			numberOfRecords = numberOfRecords.add(new BigDecimal(1));
		}
		this.avgPrice = total.divide(numberOfRecords).setScale(2, BigDecimal.ROUND_HALF_UP);
	}
	
	public BigDecimal getLowestPrice() {
		return lowestPrice;
	}
	
	public void setLowestPrice() {
		List<BigDecimal> prices = this.getPriceValues();
		Collections.sort(prices);
		this.lowestPrice = prices.get(0);
	}
	
	public BigDecimal getHighestPrice() {
		return highestPrice;
	}
	
	public void setHighestPrice() {
		List<BigDecimal> prices = this.getPriceValues();
		Collections.sort(prices);
		this.highestPrice = prices.get(prices.size() - 1);
	}
	
	public BigDecimal getIdealPrice() {
		return idealPrice;
	}
	
	public void setIdealPrice() {
		List<BigDecimal> prices = this.getPriceValues();
		Collections.sort(prices);
		prices.remove(1);
		prices.remove(0);
		prices.remove(prices.size() - 2);
		prices.remove(prices.size() - 1);
		BigDecimal total = new BigDecimal(0);
		BigDecimal numberOfRecords = new BigDecimal(0);
		for(BigDecimal price : prices) {
			total = total.add(price);
			numberOfRecords = numberOfRecords.add(new BigDecimal(1));
		}
		BigDecimal average = total.divide(numberOfRecords).setScale(2, BigDecimal.ROUND_HALF_UP);
		BigDecimal percentage = average.multiply(new BigDecimal(0.20)).setScale(2, BigDecimal.ROUND_HALF_UP);
		this.idealPrice = average.add(percentage).setScale(2, BigDecimal.ROUND_HALF_UP);
	}
	
	public List<BigDecimal> getPriceValues() {
		return this.priceValues;
	}
	
	public void setPriceValues() {
		priceValues = new ArrayList<BigDecimal>();
		for(PriceStub price : this.prices)
			priceValues.add(price.getPrice());
		Collections.sort(this.priceValues);
	}
	
	public void addPrice(PriceStub price) {
		prices.add(price);
	}
	
	public void addPriceValue(BigDecimal priceValue) {
		priceValues.add(priceValue);
	}
}
