package org.chenche.webstore.repository;

import java.util.List;

import org.chenche.webstore.domain.Customer;

public interface CustomerRepository {
	
	public List<Customer> getAllCustomers();
	
	public void saveCustomer(Customer customer);
	
	public Customer getCustomer(String customerId);
	
	public Boolean isCustomerExist(String customerId); 
}
