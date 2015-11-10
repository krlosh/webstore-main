package org.chenche.webstore.service;

import java.util.List;

import org.chenche.webstore.domain.Customer;

public interface CustomerService {

	public List<Customer> getAllCustomers();
	
	public void saveCustomer(Customer customer);
	
	public Boolean isCustomerExist(String customerId);
	
	public Customer getCustomer(String customerId);
}
