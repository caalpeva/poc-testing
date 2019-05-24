package org.caalpeva.report.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.opencsv.bean.CsvBindByName;
import com.opencsv.bean.CsvDate;

@Entity
@Table(name="ORDERS")
public class Order {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private String priority; // TODO: Cambiar a char
	
	private String salesChannel;
	
	@CsvDate(value="dd/MM/yyyy")
	@CsvBindByName(column = "Order Date")
	private Date date;
	
	@CsvDate(value="dd/MM/yyyy")
	@CsvBindByName(column = "Ship Date")
	private Date shipDate;
	
	private float unitCost;
	private float unitPrice;
	private int soldUnits;
	
	private double totalCost;
	private double totalRevenue;
	private double totalProfit;
	
	private String country;
	private String region;
	private String itemType;
		
	public int getId() {
		return id;
	}

	

	public void setId(int id) {
		this.id = id;
	}



	public String getPriority() {
		return priority;
	}



	public void setPriority(String priority) {
		this.priority = priority;
	}



	public String getSalesChannel() {
		return salesChannel;
	}



	public void setSalesChannel(String salesChannel) {
		this.salesChannel = salesChannel;
	}



	public Date getDate() {
		return date;
	}



	public void setDate(Date date) {
		this.date = date;
	}



	public Date getShipDate() {
		return shipDate;
	}



	public void setShipDate(Date shipDate) {
		this.shipDate = shipDate;
	}



	public float getUnitCost() {
		return unitCost;
	}



	public void setUnitCost(float unitCost) {
		this.unitCost = unitCost;
	}



	public float getUnitPrice() {
		return unitPrice;
	}



	public void setUnitPrice(float unitPrice) {
		this.unitPrice = unitPrice;
	}



	public int getSoldUnits() {
		return soldUnits;
	}



	public void setSoldUnits(int soldUnits) {
		this.soldUnits = soldUnits;
	}



	public double getTotalCost() {
		return totalCost;
	}



	public void setTotalCost(double totalCost) {
		this.totalCost = totalCost;
	}



	public double getTotalRevenue() {
		return totalRevenue;
	}



	public void setTotalRevenue(double totalRevenue) {
		this.totalRevenue = totalRevenue;
	}



	public double getTotalProfit() {
		return totalProfit;
	}



	public void setTotalProfit(double totalProfit) {
		this.totalProfit = totalProfit;
	}



	public String getCountry() {
		return country;
	}



	public void setCountry(String country) {
		this.country = country;
	}



	public String getRegion() {
		return region;
	}



	public void setRegion(String region) {
		this.region = region;
	}



	public String getItemType() {
		return itemType;
	}



	public void setItemType(String itemType) {
		this.itemType = itemType;
	}



	@Override
	public String toString() {
		return "Order [id=" + id + ", priority=" + priority + ", salesChannel=" + salesChannel + ", date=" + date
				+ ", shipDate=" + shipDate + ", unitCost=" + unitCost + ", unitPrice=" + unitPrice + ", soldUnits="
				+ soldUnits + ", totalCost=" + totalCost + ", totalRevenue=" + totalRevenue + ", totalProfit="
				+ totalProfit + ", country=" + country + ", region=" + region + ", itemType=" + itemType + "]";
	}
	
}