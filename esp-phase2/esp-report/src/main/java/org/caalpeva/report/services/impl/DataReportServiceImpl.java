package org.caalpeva.report.services.impl;

import java.io.IOException;
import java.time.LocalDate;
import java.util.Iterator;
import java.util.Optional;

import javax.transaction.Transactional;

import org.caalpeva.report.csv.CsvBaseLine;
import org.caalpeva.report.csv.CsvReportLine;
import org.caalpeva.report.csv.CsvReportReader;
import org.caalpeva.report.csv.opencsv.OpenCsvReportWriter;
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
import org.caalpeva.report.services.DataReportService;
import org.caalpeva.report.utils.ConsoleUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

/**
 * Clase encargada de realizar la funcionalidad relacionada con la base de datos.
 * Se encarga del tratamiento, importación, exportacion de datos y generación de informes. 
 * @author Alberto
 */
@Service
public class DataReportServiceImpl implements  DataReportService {

	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	
	private final int PAGE_SIZE = 100; // Paginar el procesamiento de líneas para no desbordar la memoria

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
	
	@Autowired
	private SalesChannelRepository salesChannelRepository;

	@Override
	@Transactional
	public void importOrders(CsvReportReader csvReader) {
		// CsvReportReader csvReader = new OpenCsvReportReader(reader);
		Iterator<CsvReportLine> csvLineIterator = csvReader.iterator();
		while (csvLineIterator.hasNext()) {
			CsvReportLine csvLine = csvLineIterator.next();
			logger.debug(csvLine.toString());
			orderRepository.save(convertFrom(csvLine));
		} // while
	}

	@Override
	public void sortOrdersAndExport(OpenCsvReportWriter csvWriter) throws IOException {
		try {
			long count = orderRepository.count();
			csvWriter.writeHeader();
			int pageNums = (int) ((count / PAGE_SIZE) + ((count % PAGE_SIZE) > 0 ? 1 : 0));
			for (int index = 0; index < pageNums; index++) {
				Page<Order> orderPages = orderRepository
						.findAll(PageRequest.of(index, PAGE_SIZE, Sort.by(Sort.Direction.ASC, "id")));
				for (Order order : orderPages) {
					csvWriter.writeLine(convertFrom(order));
//					ColumnPositionMappingStrategy<CsvReportLine> mappingStrategy = new ColumnPositionMappingStrategy<CsvReportLine>();
//					mappingStrategy.setType(CsvReportLine.class);
//
//					// Se organiza el orden de las columnas
//					//Region,Country,Item Type,Sales Channel,Order Priority,Order Date,Order ID,Ship Date,
//					//Units Sold,Unit Price,Unit Cost,Total Revenue,Total Cost,Total Profit
//					String[] columns = new String[] { "region", "country", "itemType", "salesChannel", "orderPriority", "date", "id", "shipDate",
//							"soldUnits", "unitPrice", "unitCost", "totalRevenue", "totalCost", "totalProfit" };
//					mappingStrategy.setColumnMapping(columns);
//
//					StatefulBeanToCsvBuilder<CsvReportLine> builder = new StatefulBeanToCsvBuilder<CsvReportLine>(writer);
//					StatefulBeanToCsv<CsvReportLine> beanWriter = builder.withMappingStrategy(mappingStrategy).build();
//					beanWriter.write(convertFrom(order));
				} // for
			} // for
		} catch (Exception e) {
			logger.error(e.getMessage());
		} finally {
			try {
				csvWriter.close();
			} catch (IOException e) {
				// Ignore
			}

		}
	}

	/**
	 * Método encargado de mostrar por consola el resultado de los informes
	 */
	@Override
	public void generateOrderReports() {
		System.out.println("# Orders grouped by regions:");
		ConsoleUtils.printSumOrderReport(regionRepository.getOrderCountGroupByRegion());
		System.out.println("# Orders grouped by countries:");
		ConsoleUtils.printSumOrderReport(countryRepository.getOrderCountGroupByCountry());
		System.out.println("# Orders grouped by item type:");
		ConsoleUtils.printSumOrderReport(itemTypeRepository.getOrderCountGroupByItemType());
		System.out.println("# Orders grouped by sale channels:");
		ConsoleUtils.printSumOrderReport(salesChannelRepository.getOrderCountGroupBySalesChannel());
		System.out.println("# Orders grouped by priority:");
		ConsoleUtils.printSumOrderReport(orderRepository.getOrderCountGroupByPriority());
	}
	
	/******************************************************/
	/****************** METODOS PRIVADOS ******************/
	/******************************************************/

	/**
	 * Método auxiliar encargado de extraer la información de canales
	 * @param channelStatus
	 * @return
	 */
	private SalesChannel findOrSaveSalesChannel(String channelStatus) {
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

	/**
	 * Método auxiliar encargado de extraer la información de paises
	 * @param countryName
	 * @param regionName
	 * @return
	 */
	private Country findOrSaveCountry(String countryName, String regionName) {
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

	/**
	 * Método auxiliar encargado de extraer la información de tipos de items
	 * @param itemTypeName
	 * @param unitCost
	 * @param unitPrice
	 * @return
	 */
	private Item findOrSaveItemType(String itemTypeName, double unitCost, double unitPrice) {
		ItemType itemType;
		Optional<ItemType> itemTypeOptional = itemTypeRepository.findByName(itemTypeName);
		if (itemTypeOptional.isPresent()) {
			itemType = itemTypeOptional.get();
		} else {
			itemType = new ItemType();
			itemType.setName(itemTypeName);
			// itemTypeRepository.save(itemType);
		}

		Item item;
		Optional<Item> itemOptional = itemRepository.findByItemType_IdAndUnitCostAndUnitPrice(itemType.getId(),
				unitCost, unitPrice);
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

	/**
	 * Método auxiliar encargado de convertir un objeto csv a Order
	 * @param csvLine
	 * @return
	 */
	private Order convertFrom(CsvReportLine csvLine) {
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

	/**
	 * Método auxiliar encargado de convertir un objeto Order a Csv
	 * @param order
	 * @return
	 */
	private CsvReportLine convertFrom(Order order) {
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
			public LocalDate getShipDate() {
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
			public LocalDate getDate() {
				return order.getDate();
			}

			@Override
			public String getCountry() {
				return order.getCountry().getName();
			}
		};
	}
}