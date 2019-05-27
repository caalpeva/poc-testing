package org.caalpeva.report.csv.opencsv;

import java.io.FileWriter;
import java.io.IOException;

import org.caalpeva.report.csv.CsvReportLine;
import org.caalpeva.report.csv.CsvReportWriter;

import com.opencsv.CSVWriter;

public class OpenCsvReportWriter implements CsvReportWriter {

	private CSVWriter writer;

	public OpenCsvReportWriter(FileWriter fileWriter) {
		writer = new CSVWriter(fileWriter);
	}

	@Override
	public void writeLine(CsvReportLine csvReportLine) throws IOException {
		String[] record = "4,David,Miller,Australia,30".split(",");
		writer.writeNext(record);

		// close the writer
		writer.close();
	}

}