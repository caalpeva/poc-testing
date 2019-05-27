package org.caalpeva.report;

import java.io.FileNotFoundException;
import java.io.FileReader;

import org.caalpeva.report.csv.CsvReportReader;
import org.caalpeva.report.csv.opencsv.OpenCsvReportReader;
import org.caalpeva.report.services.DataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Component;

@Component
public class Init {

	@Autowired
	private DataService dataService;
	
	public void start(String[] args) {
		String fileName = "F:/ALBERTO/[EMPRESAS]/Otras/EsPublico/RegistroVentas1.csv";
		try {
			CsvReportReader csvReader = new OpenCsvReportReader(new FileReader(fileName));
			dataService.importOrders(csvReader);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		ApplicationContext context = new ClassPathXmlApplicationContext("spring-datasource-hibernate.xml");
		Init init = context.getBean(Init.class);
		init.start(args);
	}
	
	public static String printLine(String[] line) {
		StringBuffer buffer = new StringBuffer();
		for (String field : line) {
			buffer.append(field + "\t");
		} // for

		return buffer.toString();
	}
}