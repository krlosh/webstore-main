package org.chenche.webstore.repository.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.chenche.webstore.domain.Customer;
import org.chenche.webstore.repository.CustomerRepository;
import org.springframework.stereotype.Component;

@Component
public class CustomerRepositoryImpl implements CustomerRepository {
	
	@PersistenceContext
	private EntityManager entityManager;

	
	public EntityManager getEntityManager() {
		return entityManager;
	}

	public void setEntityManager(EntityManager entityManager) {
		this.entityManager = entityManager;
	}

	public List<Customer> getAllCustomers() {
		return this.entityManager.createQuery("SELECT c from Customer c",Customer.class).getResultList();
	}

	@Transactional
	public void saveCustomer(Customer customer) {
		this.entityManager.persist(customer);

	}

	public Customer getCustomer(String customerId) {
		return this.entityManager.find(Customer.class, customerId);
	}

	public Boolean isCustomerExist(String customerId) {
		return this.getCustomer(customerId)!=null;
	}

}
