package org.chenche.webstore.service.impl;

import java.util.List;

import javax.transaction.Transactional;

import org.chenche.webstore.domain.Customer;
import org.chenche.webstore.repository.CustomerRepository;
import org.chenche.webstore.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CustomerServiceImpl implements CustomerService {

	@Autowired
	private CustomerRepository customerRepository;
	
	public List<Customer> getAllCustomers() {
		return this.customerRepository.getAllCustomers();
	}

	@Override
	@Transactional
	public void saveCustomer(Customer customer) {
		this.customerRepository.saveCustomer(customer);
	}

	@Override
	public Boolean isCustomerExist(String customerId) {
		return this.customerRepository.isCustomerExist(customerId);
	}

	@Override
	@Transactional
	public Customer getCustomer(String customerId) {
		return this.customerRepository.getCustomer(customerId);
	}

}
