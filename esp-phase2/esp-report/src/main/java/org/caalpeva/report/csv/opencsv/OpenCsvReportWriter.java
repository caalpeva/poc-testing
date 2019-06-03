package org.caalpeva.report.csv.opencsv;

import java.io.FileWriter;
import java.io.IOException;

import org.caalpeva.report.csv.CsvHeader;
import org.caalpeva.report.csv.CsvReportLine;
import org.caalpeva.report.csv.CsvReportWriter;

import com.opencsv.CSVWriter;

/**
 * Clase encargada de facilitar escritura de los datos de los pedidos en formato CSV  
 * @author Alberto
 */
public class OpenCsvReportWriter implements CsvReportWriter {

	private CSVWriter writer;

	public OpenCsvReportWriter(FileWriter fileWriter) {
		writer = new CSVWriter(fileWriter);
	}

	@Override
	public void writeHeader() throws IOException {
		writer.writeNext(CsvHeader.getHeaders());
	}
	
	@Override
	public void writeLine(CsvReportLine csvReportLine) throws IOException {
		writer.writeNext(csvReportLine.getCsvLine());
	}
	
	@Override
	public void close() throws IOException {
		writer.close();		
	}
}