package org.chenche.webstore.repository;

import static org.hamcrest.core.IsNull.notNullValue;

import static org.junit.Assert.*;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;

import javax.transaction.Transactional;

import org.chenche.webstore.domain.Address;
import org.chenche.webstore.domain.Customer;
import org.chenche.webstore.domain.OrderItemVO;
import org.chenche.webstore.domain.OrderVO;
import org.chenche.webstore.domain.ProductVO;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@ContextConfiguration("/spring.xml")
@RunWith(SpringJUnit4ClassRunner.class)
public class OrderRepositoryTest  {

	@Autowired
	private OrderRepository repository;
	
	@Autowired
	private ProductRepository productRepository;
	
	
	@Autowired
	private CustomerRepository customerRepository;
	
	@Test
	@Transactional
	@Rollback(value=false)
	public void testSaveAnOrder() {
		OrderVO order = new OrderVO();
		Customer customer = this.customerRepository.getCustomer("c123");
		order.setCustomer(customer);
		Address shippingAddress = new Address();
		shippingAddress.setAreaName("area");
		shippingAddress.setCountry("country");
		shippingAddress.setDoorNo("1");
		shippingAddress.setState("state");
		shippingAddress.setStreetName("streetName");
		shippingAddress.setZipCode("zip");
		order.setShippingAddress(shippingAddress );
		order.setShippingDate(Calendar.getInstance().getTime());
		ProductVO product = this.productRepository.getProductById("P1234");
		Collection<OrderItemVO> items = new ArrayList<OrderItemVO>();
		OrderItemVO anItem = new OrderItemVO();
		anItem.setProduct(product);
		anItem.setQuantity(1);
		anItem.setTotalPrice(product.getUnitPrice());
		items.add(anItem);
		order.setItems(items );
		assertThat(this.repository.saveOrder(order ),notNullValue());
	}

}
