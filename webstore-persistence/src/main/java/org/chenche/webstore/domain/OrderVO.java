package org.chenche.webstore.domain;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.validation.constraints.NotNull;

@Entity(name="PEDIDO")
public class OrderVO {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column
	private long orderId;
	
	@ManyToOne(optional=false,cascade=CascadeType.PERSIST)
	private Customer customer;
	
	@Column
	@NotNull
	private Date shippingDate;
	
	@OneToOne(optional=false,cascade=CascadeType.MERGE)
	private Address shippingAddress;
	
	@OneToMany(cascade=CascadeType.ALL,orphanRemoval=true)
	@JoinColumn(name="orderId",nullable=false)
	private Collection<OrderItemVO> items =new ArrayList<OrderItemVO>();

	public long getOrderId() {
		return orderId;
	}

	public void setOrderId(long orderId) {
		this.orderId = orderId;
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	public Date getShippingDate() {
		return shippingDate;
	}

	public void setShippingDate(Date shippingDate) {
		this.shippingDate = shippingDate;
	}

	public Address getShippingAddress() {
		return shippingAddress;
	}

	public void setShippingAddress(Address shippingAddress) {
		this.shippingAddress = shippingAddress;
	}

	public Collection<OrderItemVO> getItems() {
		return items;
	}

	public void setItems(Collection<OrderItemVO> items) {
		this.items = items;
	}
	
}
