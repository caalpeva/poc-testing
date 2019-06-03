package org.caalpeva.report.csv.opencsv;

import java.time.LocalDate;

import org.caalpeva.report.csv.CsvBaseLine;
import org.caalpeva.report.csv.CsvHeader;

import com.opencsv.bean.CsvBindByName;
import com.opencsv.bean.CsvCustomBindByName;

public class OpenCsvReportLine extends CsvBaseLine {

	@CsvBindByName(column = CsvHeader.ORDER_ID)
	private int id;
	@CsvBindByName(column = CsvHeader.ORDER_PRIORITY)
	private String priority; // TODO: Decidir si cambiar a char
	
	@CsvBindByName(column = CsvHeader.SALES_CHANNEL)
	private String salesChannel;
	
	//@CsvDate(value="dd/MM/yyyy")
	@CsvCustomBindByName(column = CsvHeader.ORDER_DATE, converter = LocalDateConverter.class)
	private LocalDate date;
	
	//@CsvDate(value="dd/MM/yyyy")
	@CsvCustomBindByName(column = CsvHeader.SHIP_DATE, converter = LocalDateConverter.class)
	private LocalDate shipDate;
	
	@CsvBindByName(column = CsvHeader.UNIT_COST)
	private double unitCost;
	@CsvBindByName(column = CsvHeader.UNIT_PRICE)
	private double unitPrice;
	@CsvBindByName(column = CsvHeader.UNITS_SOLD)
	private int soldUnits;
	
	@CsvBindByName(column = CsvHeader.TOTAL_COST)
	private double totalCost;
	@CsvBindByName(column = CsvHeader.TOTAL_REVENUE)
	private double totalRevenue;
	@CsvBindByName(column = CsvHeader.TOTAL_PROFIT)
	private double totalProfit;
	
	@CsvBindByName
	private String country;
	@CsvBindByName
	private String region;
	@CsvBindByName(column = CsvHeader.ITEM_TYPE)
	private String itemType;
	
	@Override
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	@Override
	public String getPriority() {
		return priority;
	}
	public void setPriority(String priority) {
		this.priority = priority;
	}
	@Override
	public String getSalesChannel() {
		return salesChannel;
	}
	public void setSalesChannel(String salesChannel) {
		this.salesChannel = salesChannel;
	}
	@Override
	public LocalDate getDate() {
		return date;
	}
	public void setDate(LocalDate date) {
		this.date = date;
	}
	@Override
	public LocalDate getShipDate() {
		return shipDate;
	}
	public void setShipDate(LocalDate shipDate) {
		this.shipDate = shipDate;
	}
	@Override
	public double getUnitCost() {
		return unitCost;
	}
	public void setUnitCost(double unitCost) {
		this.unitCost = unitCost;
	}
	@Override
	public double getUnitPrice() {
		return unitPrice;
	}
	public void setUnitPrice(double unitPrice) {
		this.unitPrice = unitPrice;
	}
	@Override
	public int getSoldUnits() {
		return soldUnits;
	}
	public void setSoldUnits(int soldUnits) {
		this.soldUnits = soldUnits;
	}
	@Override
	public double getTotalCost() {
		return totalCost;
	}
	public void setTotalCost(double totalCost) {
		this.totalCost = totalCost;
	}
	@Override
	public double getTotalRevenue() {
		return totalRevenue;
	}
	public void setTotalRevenue(double totalRevenue) {
		this.totalRevenue = totalRevenue;
	}
	@Override
	public double getTotalProfit() {
		return totalProfit;
	}
	public void setTotalProfit(double totalProfit) {
		this.totalProfit = totalProfit;
	}
	@Override
	public String getCountry() {
		return country;
	}
	public void setCountry(String country) {
		this.country = country;
	}
	@Override
	public String getRegion() {
		return region;
	}
	public void setRegion(String region) {
		this.region = region;
	}
	@Override
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