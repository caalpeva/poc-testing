package org.caalpeva.report.manager;

import java.io.FileReader;
import java.util.Iterator;
import java.util.Optional;

import org.caalpeva.report.csv.CsvReportLine;
import org.caalpeva.report.csv.CsvReportReader;
import org.caalpeva.report.csv.opencsv.OpenCsvReportReader;
import org.caalpeva.report.model.Country;
import org.caalpeva.report.model.Item;
import org.caalpeva.report.model.ItemType;
import org.caalpeva.report.model.Order;
import org.caalpeva.report.model.Region;
import org.caalpeva.report.repository.CountryRepository;
import org.caalpeva.report.repository.ItemRepository;
import org.caalpeva.report.repository.ItemTypeRepository;
import org.caalpeva.report.repository.OrderRepository;
import org.caalpeva.report.repository.RegionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

public class CsvReportManager implements CsvFileManager {

	@Autowired
	private OrderRepository orderRepository;

	@Autowired
	private CountryRepository countryRepository;

	@Autowired
	private RegionRepository regionRepository;

	@Autowired
	private ItemRepository itemRepository;
	
	@Autowired
	private ItemTypeRepository itemTypeRepository;

	//@Transactional
	public void handle(FileReader reader) {
		CsvReportReader csvReader = new OpenCsvReportReader(reader);
		Iterator<CsvReportLine> csvUserIterator = csvReader.iterator();
		while (csvUserIterator.hasNext()) {
			CsvReportLine csvReport = csvUserIterator.next();
			System.out.println(csvReport);
			Order order = new Order();
			order.setId(csvReport.getId());
			order.setPriority(csvReport.getPriority());
			order.setDate(csvReport.getDate());
			order.setShipDate(csvReport.getShipDate());
			order.setSalesChannel(csvReport.getSalesChannel());
			order.setSoldUnits(csvReport.getSoldUnits());
			order.setTotalCost(csvReport.getTotalCost());
			order.setTotalRevenue(csvReport.getTotalRevenue());
			order.setTotalProfit(csvReport.getTotalProfit());

			Country country;
			Optional<Country> countryOptional = countryRepository.findByName(csvReport.getCountry());
			if (countryOptional.isPresent()) {
				country = countryOptional.get();
			} else {
				country = new Country();
				country.setName(csvReport.getCountry());
				Region region;
				Optional<Region> regionOptional = regionRepository.findByName(csvReport.getRegion());
				if (regionOptional.isPresent()) {
					region = regionOptional.get();
				} else {
					region = new Region();
					region.setName(csvReport.getRegion());
					regionRepository.save(region);
				}
				country.setRegion(region);
				countryRepository.save(country);
			}
			
			order.setCountry(country);

			ItemType itemType;
			Optional<ItemType> itemTypeOptional = itemTypeRepository.findByName(csvReport.getItemType());
			if (itemTypeOptional.isPresent()) {
				itemType = itemTypeOptional.get();
			} else {
				itemType = new ItemType();
				itemType.setName(csvReport.getItemType());
				//itemTypeRepository.save(itemType);
			}
			
			Item item;
			Optional<Item> itemOptional = itemRepository.findByItemType_IdAndUnitCostAndUnitPrice(itemType.getId(), csvReport.getUnitCost(), csvReport.getUnitPrice());
			if (itemOptional.isPresent()) {
				item = itemOptional.get();
			} else {
				item = new Item();
				item.setItemType(itemType);
				item.setUnitCost(csvReport.getUnitCost());
				item.setUnitPrice(csvReport.getUnitPrice());
				itemRepository.save(item);
			}
			order.setItem(item);

			orderRepository.save(order);

		}

	}

}
