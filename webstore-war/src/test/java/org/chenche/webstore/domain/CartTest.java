package org.chenche.webstore.domain;

import static org.junit.Assert.fail;
import static org.junit.Assert.assertEquals;
import java.math.BigDecimal;

import org.junit.Before;
import org.junit.Test;

public class CartTest {

	private Cart cart;
	
	@Before
	public void setup(){
		this.cart=new Cart();
	}
	
	@Test
	public void testGetGrandTotalWithOneItemOneQuantity() {
		
		Product p= new Product("P1234","IPhone 5", new BigDecimal(500));;
		CartItem item = new CartItem(p);
		
		this.cart.addCartItem(item );
		
		BigDecimal grandTotal = this.cart.getGrandTotal();
		
		assertEquals(p.getUnitPrice(),grandTotal);
	}
	
	@Test
	public void testGetGrandTotalWithOneItemTwoQuantity() {
		
		Product p= new Product("P1234","IPhone 5", new BigDecimal(500));;
		CartItem item = new CartItem(p);
		item.setQuantity(2);
		this.cart.addCartItem(item );
		
		BigDecimal grandTotal = this.cart.getGrandTotal();
		
		assertEquals(p.getUnitPrice().multiply(new BigDecimal(2)),grandTotal);
	}

}
