package org.caalpeva.report.repository.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="SALES_CHANNEL")
public class SalesChannel {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	@Column(nullable = false, unique = true)
	private String name;
	
//	@OneToMany(cascade = {CascadeType.MERGE, CascadeType.REFRESH },
//			mappedBy= "salesChannel", fetch = FetchType.LAZY)
//	@Column(name = "salesChannel_id")
//	private Set<Order> orders;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
//	public Set<Order> getOrders() {
//		return orders;
//	}
//	public void setOrders(Set<Order> orders) {
//		this.orders = orders;
//	}	
}