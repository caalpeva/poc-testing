package org.caalpeva.report.csv;

import java.time.LocalDate;

public interface CsvReportLine {
	public int getId();
	public String getPriority();
	public String getSalesChannel();
	public LocalDate getDate();
	public LocalDate getShipDate();
	public double getUnitCost();
	public double getUnitPrice();
	public int getSoldUnits();
	public double getTotalCost();
	public double getTotalRevenue();
	public double getTotalProfit();
	public String getCountry();
	public String getRegion();
	public String getItemType();
	public String[] getCsvLine();
}