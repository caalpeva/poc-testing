package org.caalpeva.report;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Iterator;

import org.caalpeva.report.csv.CsvReportLine;
import org.caalpeva.report.csv.CsvReportReader;
import org.caalpeva.report.csv.opencsv.OpenCsvReportReader;
import org.caalpeva.report.manager.CsvFileManager;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Init {

	public static void main(String[] args) {

		ApplicationContext context = new ClassPathXmlApplicationContext("spring-datasource-hibernate.xml");
		
		String fileName = "F:/ALBERTO/[EMPRESAS]/Otras/EsPublico/RegistroVentas1.csv";
		
		CsvFileManager csvFileManager = (CsvFileManager) context.getBean("csvReportManager");
		try {
			csvFileManager.handle(new FileReader(fileName));
		} catch (FileNotFoundException e1) {
			e1.printStackTrace();
		}
	}

	public static String printLine(String[] line) {
		StringBuffer buffer = new StringBuffer();
		for (String field : line) {
			buffer.append(field + "\t");
		} // for

		return buffer.toString();
	}
}