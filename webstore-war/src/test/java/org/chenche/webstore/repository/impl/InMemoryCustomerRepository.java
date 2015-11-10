package org.chenche.webstore.repository.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.chenche.webstore.domain.Customer;
import org.chenche.webstore.repository.CustomerRepository;
import org.springframework.stereotype.Repository;

@Repository
public class InMemoryCustomerRepository implements CustomerRepository {

	@Override
	public void saveCustomer(Customer customer) {
		this.listOfCustomers.put(customer.getCustomerId(), customer);
	}

	@Override
	public Customer getCustomer(String customerId) {
		if(this.isCustomerExist(customerId)){
			return this.listOfCustomers.get(customerId);
		}
		return null;
	}

	@Override
	public Boolean isCustomerExist(String customerId) {
		return this.listOfCustomers.containsKey(customerId);
	}

	private Map<String,Customer> listOfCustomers = new HashMap<String,Customer>();
	
	public InMemoryCustomerRepository(){
		Customer customer1 = new Customer("1","Carlos Henche");
		Customer customer2 = new Customer("2", "Elena Serna");
		this.listOfCustomers.put(customer1.getCustomerId(),customer1);
		this.listOfCustomers.put(customer2.getCustomerId(),customer2);
	}
	
	public List<Customer> getAllCustomers() {
		return new ArrayList<Customer>(this.listOfCustomers.values());
	}

}
