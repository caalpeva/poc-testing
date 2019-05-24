package org.caalpeva.report.model;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name="ORDERS")
public class Order {
	@Id
	//@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private String priority; // TODO: Cambiar a char
	
	private String salesChannel;
	private Date date;
	private Date shipDate;
	
	private int soldUnits;
	private double totalCost;
	private double totalRevenue;
	private double totalProfit;
	
	@OneToOne(cascade={CascadeType.PERSIST,CascadeType.MERGE})
	@JoinColumn(nullable=false)
	private Country country;
	
	@OneToOne(cascade={CascadeType.PERSIST})
	@JoinColumn(nullable=false)
	private Item item;
		
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
	public Country getCountry() {
		return country;
	}
	public void setCountry(Country country) {
		this.country = country;
	}
	public Item getItem() {
		return item;
	}
	public void setItem(Item item) {
		this.item = item;
	}
}