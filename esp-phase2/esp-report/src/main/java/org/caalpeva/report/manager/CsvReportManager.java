package org.caalpeva.report.manager;

import java.io.FileReader;
import java.util.Iterator;

import org.caalpeva.report.csv.CsvReportLine;
import org.caalpeva.report.csv.CsvReportReader;
import org.caalpeva.report.csv.opencsv.OpenCsvReportReader;
import org.caalpeva.report.model.Country;
import org.caalpeva.report.model.Order;
import org.caalpeva.report.model.Region;
import org.caalpeva.report.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;

public class CsvReportManager implements CsvFileManager {

	@Autowired
	private OrderRepository orderRepository;
	
	public void handle(FileReader reader) {
		CsvReportReader csvReader = new OpenCsvReportReader(reader);
		Iterator<CsvReportLine> csvUserIterator = csvReader.iterator();

        while (csvUserIterator.hasNext()) {
        	CsvReportLine csvReport = csvUserIterator.next();
            System.out.println(csvReport);
            Order order = new Order();
            order.setId(csvReport.getId());
            Region region = new Region();
            region.setName(csvReport.getRegion());
            
            Country country = new Country();
            country.setName(csvReport.getCountry());
            country.setRegion(region);
            order.setCountry(country);
            order.setDate(csvReport.getDate());
            
            orderRepository.save(order);
            
        }

		
	}

}
