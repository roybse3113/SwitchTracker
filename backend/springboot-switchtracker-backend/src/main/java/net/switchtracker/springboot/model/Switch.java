package net.switchtracker.springboot.model;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

@Entity
@Table(name = "switches")
public class Switch {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	@Column(name="name")
	private String name;
	
	@Column(name="price")
	private double price;
	
	@Column(name="in_stock")
	private boolean inStock;
	
//	private String vendor;
	
//	@Autowired
//	private List<Switch> subSwitches;

	public Switch() {
		
	}
	
	public Switch(String name) {
		this.name = name;
	}
	
	public Switch(String name, double price, boolean inStock) {
		super();
		this.name = name;
		this.price = price;
		this.inStock = inStock;
	}
	
//	public String getVendor() {
//		return vendor;
//	}
//
//	public void setVendor(String vendor) {
//		this.vendor = vendor;
//	}

//	public List<Switch> getSubSwitches() {
//		return subSwitches;
//	}
//
//	public void setSubSwitches(List<Switch> subSwitches) {
//		this.subSwitches = subSwitches;
//	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public boolean isInStock() {
		return inStock;
	}

	public void setInStock(boolean inStock) {
		this.inStock = inStock;
	}
	
	
}
