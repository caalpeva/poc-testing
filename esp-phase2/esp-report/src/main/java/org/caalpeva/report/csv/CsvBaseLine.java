package org.caalpeva.report.csv;

import java.time.format.DateTimeFormatter;

public abstract class CsvBaseLine implements CsvReportLine {

	public static DateTimeFormatter formatter = DateTimeFormatter.ofPattern("M/d/yyyy");
	
	@Override
	public String[] getCsvLine() {
		return new String[] {
				getRegion(),
				getCountry(),
				getItemType(),
				getSalesChannel(), 
				getPriority(),
				getDate().format(formatter),
				String.valueOf(getId()),
				getShipDate().format(formatter),
				String.valueOf(getSoldUnits()),
				String.valueOf(getUnitPrice()),
				String.valueOf(getUnitCost()),
				String.valueOf(getTotalRevenue()),
				String.valueOf(getTotalCost()),
				String.valueOf(getTotalProfit())	
		};
	}
}