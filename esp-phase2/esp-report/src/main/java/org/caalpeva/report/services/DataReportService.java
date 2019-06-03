package org.caalpeva.report.services;

import java.io.IOException;

import org.caalpeva.report.csv.CsvReportReader;

public interface DataReportService {
	public void importOrders(CsvReportReader csvReportReader);
	public void sortOrdersAndExport(String filename) throws IOException;
	public void printOrderSummary();
}
