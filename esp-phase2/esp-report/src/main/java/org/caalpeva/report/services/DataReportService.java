package org.caalpeva.report.services;

import org.caalpeva.report.csv.CsvReportReader;

public interface DataReportService {
	public void importOrders(CsvReportReader csvReportReader);
	public void sortOrdersAndExport(String filename);
	public void printOrderSummary();
}
