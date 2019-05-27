package org.caalpeva.report;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;

import org.caalpeva.report.csv.CsvReportReader;
import org.caalpeva.report.csv.opencsv.OpenCsvReportReader;
import org.caalpeva.report.services.DataService;
import org.caalpeva.report.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Component;

@Component
public class Init {

	@Autowired
	private DataService dataService;
	
	private long startTimeInMillis;
	
	public void start(String[] args) {
		startTimeInMillis = System.currentTimeMillis();
		String fileName = "F:/ALBERTO/[EMPRESAS]/Otras/EsPublico/RegistroVentas1.csv";
		File file = new File(fileName);
		System.out.println(file.getParentFile());
		try {
			CsvReportReader csvReader = new OpenCsvReportReader(new FileReader(fileName));
			//dataService.importOrders(csvReader);
			File file2 = new File(file.getParentFile().getPath(), "sorted_" + file.getName());
			dataService.sortOrdersAndExport(file2.getPath());
			System.out.println("Time elapsed: " + DateUtils.formatElapsedTime(System.currentTimeMillis() - startTimeInMillis, true, true));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		ApplicationContext context = new ClassPathXmlApplicationContext(new String[] {
				"spring-context.xml", "spring-persistence.xml"});
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