package org.caalpeva.report.csv.opencsv;

import java.io.FileReader;
import java.util.Iterator;
import java.util.List;

import org.caalpeva.report.csv.CsvReportLine;
import org.caalpeva.report.csv.CsvReportReader;

import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;

public class OpenCsvReportReader implements CsvReportReader {

	private CsvToBean<CsvReportLine> csvToBean;
	
	public OpenCsvReportReader(FileReader fileReader) {
		csvToBean = new CsvToBeanBuilder<CsvReportLine>(fileReader)
                .withType(OpenCsvReportLine.class)
                .withIgnoreLeadingWhiteSpace(true)
                .build();
	}
	
	public List<CsvReportLine> parse() {
		return csvToBean.parse();
	}

	public Iterator<CsvReportLine> iterator() {
		return csvToBean.iterator();
	}

}
