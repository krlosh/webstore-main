package org.chenche.webstore.repository;

import static org.junit.Assert.fail;

import javax.transaction.Transactional;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertFalse;

import org.chenche.webstore.domain.Address;
import org.chenche.webstore.domain.Customer;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
@ContextConfiguration("/spring.xml")
@RunWith(SpringJUnit4ClassRunner.class)
public class CustomerRepositoryTest {

	@Autowired
	private CustomerRepository repository;
	
	@Before	
	public void setup(){
		
	}
	
	private void initializeDB(){
		if(this.repository.getAllCustomers().isEmpty()){
			Customer customer = new Customer("1","Perico de los palotes");
			Customer customer2 = new Customer("2","Armando casitas");
			Customer customer3 = new Customer("3","Jhon foo");
			this.repository.saveCustomer(customer);
			this.repository.saveCustomer(customer2);
			this.repository.saveCustomer(customer3);
		}
	}
	
	@Test
	@Rollback(value=false)
	public void testGetAllCustomers() {
		initializeDB();
		try{
			assertFalse(this.repository.getAllCustomers().isEmpty());
		}
		catch(Throwable t){
			t.printStackTrace();
			fail(t.getLocalizedMessage());
		}
	}

	@Test
	@Rollback(value=false)
	@Transactional
	public void testSaveCustomer() {
		initializeDB();
		try{
		Customer customer3 = new Customer("4","Tom");
		customer3.setPhoneNumber("123456");
		Address ba = new Address();
		ba.setAreaName("esta");
		ba.setCountry("Chiquitistan");
		ba.setDoorNo("1");
		ba.setState("quieto");
		ba.setStreetName("Rue del percebe");
		ba.setZipCode("0001");
		customer3.setBillingAddress(ba);
		this.repository.saveCustomer(customer3);
		}
		catch(Throwable t){
			t.printStackTrace();
			fail(t.getLocalizedMessage());
		}
	}

	@Test
	public void testGetCustomer() {
		initializeDB();
		try{
			assertTrue(this.repository.getCustomer("1")!=null);
		}
		catch(Throwable t){
			t.printStackTrace();
			fail(t.getLocalizedMessage());
		}
	}

	@Test
	public void testIsCustomerExist() {
		initializeDB();
		try{
			assertTrue(this.repository.isCustomerExist("1"));
		}
		catch(Throwable t){
			t.printStackTrace();
			fail(t.getLocalizedMessage());
		}
	}

}
