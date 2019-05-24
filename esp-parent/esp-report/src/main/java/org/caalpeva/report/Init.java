package org.caalpeva.report;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Iterator;

import org.caalpeva.report.csv.CsvReportLine;
import org.caalpeva.report.csv.CsvReportReader;
import org.caalpeva.report.csv.opencsv.OpenCsvReportReader;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Init {

	public static void main(String[] args) {

		String fileName = "F:/ALBERTO/[EMPRESAS]/Otras/EsPublico/RegistroVentas1.csv";
		
		try {
			CsvReportReader csvReader = new OpenCsvReportReader(new FileReader(fileName));
			Iterator<CsvReportLine> csvUserIterator = csvReader.iterator();

            while (csvUserIterator.hasNext()) {
            	CsvReportLine csvReport = csvUserIterator.next();
                System.out.println(csvReport);
            }
		} catch (FileNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
        		        
		ApplicationContext context = new ClassPathXmlApplicationContext("spring-datasource-hibernate.xml");

	}

	public static String printLine(String[] line) {
		StringBuffer buffer = new StringBuffer();
		for (String field : line) {
			buffer.append(field + "\t");
		} // for

		return buffer.toString();
	}
}