package org.chenche.webstore.domain;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToOne;

import org.springframework.test.context.ContextConfiguration;

@Entity
public class Customer implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -168411858821431055L;
	
	@Id
	private String customerId;
	
	@Column
	private String name;
	
	@OneToOne(cascade=CascadeType.PERSIST)
	private Address billingAddress;
	
	@Column
	private String phoneNumber;
	
	@Column
	private int noOfOrdersMade;

	public Customer(){
		super();
		this.billingAddress=new Address();
		
	}
	
	public Customer(String customerId, String name) {
		this();
		this.customerId = customerId;
		this.name = name;
		this.noOfOrdersMade=0;
	}

	public String getCustomerId() {
		return customerId;
	}

	public void setCustomerId(String customerId) {
		this.customerId = customerId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}


	public int getNoOfOrdersMade() {
		return noOfOrdersMade;
	}

	public void setNoOfOrdersMade(int noOfOrdersMade) {
		this.noOfOrdersMade = noOfOrdersMade;
	}

	public Address getBillingAddress() {
		return billingAddress;
	}
	public void setBillingAddress(Address billingAddress) {
		this.billingAddress = billingAddress;
	}
	public String getPhoneNumber() {
		return phoneNumber;
	}
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((customerId == null) ? 0 : customerId.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Customer other = (Customer) obj;
		if (customerId == null) {
			if (other.customerId != null)
				return false;
		} else if (!customerId.equals(other.customerId))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Customer [customerId=" + customerId + ", name=" + name + "]";
	}
	
	
}
