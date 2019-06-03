package org.caalpeva.report.services.impl;

import java.io.FileWriter;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;

import org.caalpeva.report.csv.CsvBaseLine;
import org.caalpeva.report.csv.CsvReportLine;
import org.caalpeva.report.csv.CsvReportReader;
import org.caalpeva.report.csv.CsvReportWriter;
import org.caalpeva.report.repository.CountryRepository;
import org.caalpeva.report.repository.ItemRepository;
import org.caalpeva.report.repository.ItemTypeRepository;
import org.caalpeva.report.repository.OrderRepository;
import org.caalpeva.report.repository.RegionRepository;
import org.caalpeva.report.repository.SalesChannelRepository;
import org.caalpeva.report.repository.model.Country;
import org.caalpeva.report.repository.model.Item;
import org.caalpeva.report.repository.model.ItemType;
import org.caalpeva.report.repository.model.Order;
import org.caalpeva.report.repository.model.Region;
import org.caalpeva.report.repository.model.SalesChannel;
import org.caalpeva.report.services.DataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.opencsv.bean.ColumnPositionMappingStrategy;
import com.opencsv.bean.StatefulBeanToCsv;
import com.opencsv.bean.StatefulBeanToCsvBuilder;

@Service
public class DataReportService implements DataService {

	private final int PAGE_SIZE = 100;
	
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
		Iterator<CsvReportLine> csvLineIterator = csvReader.iterator();
		while (csvLineIterator.hasNext()) {
			CsvReportLine csvLine = csvLineIterator.next();
			System.out.println(csvLine);
			orderRepository.save(convertFrom(csvLine));
		} // while
	}

	@Override
	public void sortOrdersAndExport(String fileName) {
        FileWriter writer;
		try {
			writer = new FileWriter(fileName);
		} catch (IOException e1) {
			e1.printStackTrace();
			return;
		}
		try {
		long count = orderRepository.count();
		int pageNums = (int) ((count / PAGE_SIZE) + ((count % PAGE_SIZE) > 0? 1: 0));
		for(int index = 0; index < pageNums; index++) {
			Page<Order> orderPages = orderRepository.findAll(
					PageRequest.of(index, PAGE_SIZE, Sort.by(Sort.Direction.ASC, "id")));
			for (Order order: orderPages) {
		            // Create Mapping Strategy to arrange the  
		            // column name in order 
		            ColumnPositionMappingStrategy mappingStrategy= 
		                        new ColumnPositionMappingStrategy(); 
		            mappingStrategy.setType(CsvReportLine.class); 
		  
		            // Arrange column name as provided in below array.
		            String[] columns = new String[]  
		                    { "id", "date", "shipDate", "country" };
		            mappingStrategy.setColumnMapping(columns);
		  
		            // Createing StatefulBeanToCsv object 
		            StatefulBeanToCsvBuilder<CsvReportLine> builder= 
		                        new StatefulBeanToCsvBuilder(writer); 
		            StatefulBeanToCsv beanWriter = builder.withMappingStrategy(mappingStrategy).build(); 
		  
		            // Write list to StatefulBeanToCsv object 
		            beanWriter.write(convertFrom(order));
			} // for
		} // for
		} 
        catch (Exception e) { 
            e.printStackTrace();
            System.err.println(e.getMessage());
        } finally {
            // closing the writer object 
            try {
				writer.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} 

        }

	}
	
	/******************************************************/
	/****************** METODOS PRIVADOS ******************/ 
	/******************************************************/
		
	public SalesChannel findOrSaveSalesChannel(String channelStatus) {
		SalesChannel salesChannel;
		Optional<SalesChannel> channelOptional = channelRepository.findByName(channelStatus);
		if (channelOptional.isPresent()) {
			salesChannel = channelOptional.get();
		} else {
			salesChannel = new SalesChannel();
			salesChannel.setName(channelStatus);
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
	
	public Order convertFrom(CsvReportLine csvLine) {
		Order order = new Order();
		order.setId(csvLine.getId());
		order.setPriority(csvLine.getPriority());
		order.setSalesChannel(findOrSaveSalesChannel(csvLine.getSalesChannel()));
		order.setDate(csvLine.getDate());
		order.setShipDate(csvLine.getShipDate());
		order.setSoldUnits(csvLine.getSoldUnits());
		order.setTotalCost(csvLine.getTotalCost());
		order.setTotalRevenue(csvLine.getTotalRevenue());
		order.setTotalProfit(csvLine.getTotalProfit());
		order.setCountry(findOrSaveCountry(csvLine.getCountry(), csvLine.getRegion()));
		order.setItem(findOrSaveItemType(csvLine.getItemType(), csvLine.getUnitCost(), csvLine.getUnitPrice()));
		return order;
	}
	
	public CsvReportLine convertFrom(Order order) {
		return new CsvBaseLine() {
			
			@Override
			public double getUnitPrice() {
				return order.getItem().getUnitPrice();
			}
			
			@Override
			public double getUnitCost() {
				return order.getItem().getUnitCost();
			}
			
			@Override
			public double getTotalRevenue() {
				return order.getTotalRevenue();
			}
			
			@Override
			public double getTotalProfit() {
				return order.getTotalProfit();
			}
			
			@Override
			public double getTotalCost() {
				return order.getTotalCost();
			}
			
			@Override
			public int getSoldUnits() {
				return order.getSoldUnits();
			}
			
			@Override
			public Date getShipDate() {
				return order.getShipDate();
			}
			
			@Override
			public String getSalesChannel() {
				return order.getSalesChannel().getName();
			}
			
			@Override
			public String getRegion() {
				return order.getCountry().getRegion().getName();
			}
			
			@Override
			public String getPriority() {
				return order.getPriority();
			}
			
			@Override
			public String getItemType() {
				return order.getItem().getItemType().getName();
			}
			
			@Override
			public int getId() {
				return order.getId();
			}
			
			@Override
			public Date getDate() {
				return order.getDate();
			}
			
			@Override
			public String getCountry() {
				return order.getCountry().getName();
			}
		};
	}

}