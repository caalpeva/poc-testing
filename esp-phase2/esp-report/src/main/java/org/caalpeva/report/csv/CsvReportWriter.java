package org.caalpeva.report.csv;

import java.io.IOException;

public interface CsvReportWriter {
	public void writeLine(CsvReportLine csvReportLine) throws IOException;
}