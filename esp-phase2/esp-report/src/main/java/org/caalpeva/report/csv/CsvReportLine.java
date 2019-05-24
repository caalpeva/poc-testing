package org.caalpeva.report.csv;

import java.util.Date;

public interface CsvReportLine {
	public int getId();
	public String getPriority();
	public String getSalesChannel();
	public Date getDate();
	public Date getShipDate();
	public double getUnitCost();
	public double getUnitPrice();
	public int getSoldUnits();
	public double getTotalCost();
	public double getTotalRevenue();
	public double getTotalProfit();
	public String getCountry();
	public String getRegion();
	public String getItemType();
}