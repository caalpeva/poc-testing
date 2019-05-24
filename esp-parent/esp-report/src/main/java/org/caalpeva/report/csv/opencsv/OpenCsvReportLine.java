package org.caalpeva.report.csv.opencsv;

import java.util.Date;

import org.caalpeva.report.csv.CsvReportLine;

import com.opencsv.bean.CsvBindByName;
import com.opencsv.bean.CsvDate;

public class OpenCsvReportLine implements CsvReportLine {

	@CsvBindByName(column = "Order ID")
	private int id;
	@CsvBindByName(column = "Order Priority")
	private String priority; // TODO: Cambiar a char
	
	@CsvBindByName(column = "Sales Channel")
	private String salesChannel;
	
	@CsvDate(value="dd/MM/yyyy")
	@CsvBindByName(column = "Order Date")
	private Date date;
	
	@CsvDate(value="dd/MM/yyyy")
	@CsvBindByName(column = "Ship Date")
	private Date shipDate;
	
	@CsvBindByName(column = "Unit Cost")
	private float unitCost;
	@CsvBindByName(column = "Unit Price")
	private float unitPrice;
	@CsvBindByName(column = "Units Sold")
	private int soldUnits;
	
	@CsvBindByName(column = "Total Cost")
	private double totalCost;
	@CsvBindByName(column = "Total Revenue")
	private double totalRevenue;
	@CsvBindByName(column = "Total Profit")
	private double totalProfit;
	
	@CsvBindByName
	private String country;
	@CsvBindByName
	private String region;
	@CsvBindByName(column = "Item Type")
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
		return "CsvReportLine [id=" + id + ", priority=" + priority + ", salesChannel=" + salesChannel + ", date="
				+ date + ", shipDate=" + shipDate + ", unitCost=" + unitCost + ", unitPrice=" + unitPrice
				+ ", soldUnits=" + soldUnits + ", totalCost=" + totalCost + ", totalRevenue=" + totalRevenue
				+ ", totalProfit=" + totalProfit + ", country=" + country + ", region=" + region + ", itemType="
				+ itemType + "]";
	}
}