package org.caalpeva.report.csv;

import java.text.SimpleDateFormat;

public abstract class CsvBaseLine implements CsvReportLine {

	SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
	
	@Override
	public String[] getCsvLine() {
		return new String[] {
				getRegion(),
				getCountry(),
				getItemType(),
				getSalesChannel(), 
				getPriority(),
				sdf.format(getDate()),
				String.valueOf(getId()),
				sdf.format(getShipDate()),
				String.valueOf(getSoldUnits()),
				String.valueOf(getUnitPrice()),
				String.valueOf(getUnitCost()),
				String.valueOf(getTotalRevenue()),
				String.valueOf(getTotalCost()),
				String.valueOf(getTotalProfit())	
		};
	}
}