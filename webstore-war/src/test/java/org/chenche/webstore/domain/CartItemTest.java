package org.chenche.webstore.domain;

import static org.junit.Assert.assertEquals;

import java.math.BigDecimal;

import org.junit.Before;
import org.junit.Test;


public class CartItemTest {

	private CartItem cartItem;

	@Before
	public void setup() {
		cartItem = new CartItem();
	}

	@Test
	public void cartItem_total_price_should_be_eaual_to_product_unit_price_in_case_of_single_quantity() {    
		Product iphone = new Product("P1234","IPhone 5", new BigDecimal(500));
		
		this.cartItem.setProduct(iphone);
		assertEquals(iphone.getUnitPrice(), this.cartItem.getTotalPrice());
	}

}
