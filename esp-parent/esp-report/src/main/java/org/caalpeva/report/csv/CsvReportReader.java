package org.caalpeva.report.csv;

import java.util.Iterator;
import java.util.List;

public interface CsvReportReader {
	public List<CsvReportLine> parse();
	public Iterator<CsvReportLine> iterator();
}