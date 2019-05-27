package org.caalpeva.report.services;

import org.caalpeva.report.csv.CsvReportReader;

public interface DataService {
	public void importOrders(CsvReportReader csvReportReader);
	public void sortOrdersAndExport(String filename);
}
