package org.caalpeva.report.services;

import java.io.IOException;

import org.caalpeva.report.csv.CsvReportReader;
import org.caalpeva.report.csv.opencsv.OpenCsvReportWriter;

public interface DataReportService {
	public void importOrders(CsvReportReader csvReportReader);
	public void sortOrdersAndExport(OpenCsvReportWriter writer) throws IOException;
	public void generateOrderReports();
}
