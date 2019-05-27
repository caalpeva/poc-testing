package org.caalpeva.report.services.impl;

import java.util.Iterator;
import java.util.Optional;

import org.caalpeva.report.csv.CsvReportLine;
import org.caalpeva.report.csv.CsvReportReader;
import org.caalpeva.report.model.Country;
import org.caalpeva.report.model.Item;
import org.caalpeva.report.model.ItemType;
import org.caalpeva.report.model.Order;
import org.caalpeva.report.model.Region;
import org.caalpeva.report.model.SalesChannel;
import org.caalpeva.report.repository.CountryRepository;
import org.caalpeva.report.repository.ItemRepository;
import org.caalpeva.report.repository.ItemTypeRepository;
import org.caalpeva.report.repository.OrderRepository;
import org.caalpeva.report.repository.RegionRepository;
import org.caalpeva.report.repository.SalesChannelRepository;
import org.caalpeva.report.services.DataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DataReportService implements DataService {

	@Autowired
	private SalesChannelRepository channelRepository;
	
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
	
	@Override
	public void importOrders(CsvReportReader csvReader) {
		//CsvReportReader csvReader = new OpenCsvReportReader(reader);
		Iterator<CsvReportLine> csvUserIterator = csvReader.iterator();
		while (csvUserIterator.hasNext()) {
			CsvReportLine csvReport = csvUserIterator.next();
			System.out.println(csvReport);
			Order order = new Order();
			order.setId(csvReport.getId());
			order.setPriority(csvReport.getPriority());
			order.setSalesChannel(findOrSaveSalesChannel(csvReport.getSalesChannel()));
			order.setDate(csvReport.getDate());
			order.setShipDate(csvReport.getShipDate());
			order.setSoldUnits(csvReport.getSoldUnits());
			order.setTotalCost(csvReport.getTotalCost());
			order.setTotalRevenue(csvReport.getTotalRevenue());
			order.setTotalProfit(csvReport.getTotalProfit());
			order.setCountry(findOrSaveCountry(csvReport.getCountry(), csvReport.getRegion()));
			order.setItem(findOrSaveItemType(csvReport.getItemType(), csvReport.getUnitCost(), csvReport.getUnitPrice()));
			orderRepository.save(order);
		}
	}

	@Override
	public void sortOrdersAndExport() {
		// TODO Auto-generated method stub
		
	}
	
	/******************************************************/
	/****************** METODOS PRIVADOS ******************/ 
	/******************************************************/
		
	public SalesChannel findOrSaveSalesChannel(String channelName) {
		SalesChannel salesChannel;
		Optional<SalesChannel> channelOptional = channelRepository.findByName(channelName);
		if (channelOptional.isPresent()) {
			salesChannel = channelOptional.get();
		} else {
			salesChannel = new SalesChannel();
			salesChannel.setName(channelName);
			channelRepository.save(salesChannel);
		}
		
		return salesChannel;
	}
	
	public Country findOrSaveCountry(String countryName, String regionName) {
		Country country;
		Optional<Country> countryOptional = countryRepository.findByName(countryName);
		if (countryOptional.isPresent()) {
			country = countryOptional.get();
		} else {
			country = new Country();
			country.setName(countryName);
			Region region;
			Optional<Region> regionOptional = regionRepository.findByName(regionName);
			if (regionOptional.isPresent()) {
				region = regionOptional.get();
			} else {
				region = new Region();
				region.setName(regionName);
				regionRepository.save(region);
			}
			country.setRegion(region);
			countryRepository.save(country);
		}
		
		return country;
	}
	
	public Item findOrSaveItemType(String itemTypeName, double unitCost, double unitPrice) {
		ItemType itemType;
		Optional<ItemType> itemTypeOptional = itemTypeRepository.findByName(itemTypeName);
		if (itemTypeOptional.isPresent()) {
			itemType = itemTypeOptional.get();
		} else {
			itemType = new ItemType();
			itemType.setName(itemTypeName);
			//itemTypeRepository.save(itemType);
		}
		
		Item item;
		Optional<Item> itemOptional = itemRepository.findByItemType_IdAndUnitCostAndUnitPrice(
				itemType.getId(), unitCost, unitPrice);
		if (itemOptional.isPresent()) {
			item = itemOptional.get();
		} else {
			item = new Item();
			item.setItemType(itemType);
			item.setUnitCost(unitCost);
			item.setUnitPrice(unitPrice);
			itemRepository.save(item);
		}
		
		return item;
	}

}