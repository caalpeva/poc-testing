package org.caalpeva.report.repository;

import static org.junit.Assert.assertEquals;

import java.io.IOException;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"/spring-context.xml", "/spring-persistence.xml"})
public class OrderCountTest {

	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private RegionRepository regionRepository;
	
	@Autowired
	private CountryRepository countryRepository;
	
	@Autowired
	private OrderRepository orderRepository;

	@Autowired
	private ItemTypeRepository itemTypeRepository;
	
	@Autowired
	private SalesChannelRepository salesChannelRepository;
	
	@Test
	public void checkOrderCounts() throws IOException {
		long count = orderRepository.count();
		assertEquals(count, sumOrders(regionRepository.getOrderCountGroupByRegion()));
		assertEquals(count, sumOrders(countryRepository.getOrderCountGroupByCountry()));
		assertEquals(count, sumOrders(itemTypeRepository.getOrderCountGroupByItemType()));
		assertEquals(count, sumOrders(salesChannelRepository.getOrderCountGroupBySalesChannel()));
		assertEquals(count, sumOrders(orderRepository.getOrderCountGroupByPriority()));
	}
	
	private long sumOrders(List<Object[]> groupedOrders) {
		long total = 0;
		if (groupedOrders != null && groupedOrders.size() > 0) {
			for(Object[] group: groupedOrders) {
				total += Long.parseLong(group[1].toString());
				logger.debug(String.format("%10s | %s", group[1], group[0]));
			} // for
		}
		
		logger.debug("Order total: " + total);
		return total;
	}
}