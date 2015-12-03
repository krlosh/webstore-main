package org.chenche.webstore.service;

import static org.junit.Assert.*;
import static org.hamcrest.core.IsNot.*;
import static org.hamcrest.core.Is.*;
import static org.hamcrest.core.IsNull.*;

import java.math.BigDecimal;
import java.util.UUID;

import org.chenche.webstore.domain.Cart;
import org.chenche.webstore.domain.CartItem;
import org.chenche.webstore.domain.Product;
import org.hamcrest.core.IsNull;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"test-DispatcherServlet-context.xml",
								"classpath:spring.xml"})
public class CartServiceTest {

	private static Logger logger = LoggerFactory.getLogger(CartServiceTest.class);
	
	@Autowired
	private ProductService productSvc;
	
	@Autowired
	private CartService cartService;
	
	@Before
	public void setup(){
		if(this.productSvc.getAllProducts().isEmpty()){
			Product iphone = new Product("P1234", "iPhone 5s", new BigDecimal(500));
			iphone.setDescription("Apple iPhone 5s smartphone with 4.00-inch 640x1136 display and 8-megapixel rear camera");
			iphone.setCategory("Smart Phone");
			iphone.setManufacturer("Apple");
			iphone.setUnitsInStock(1000);
			Product laptop_dell = new Product("P1235", "Dell Inspiron", new BigDecimal(700));
			laptop_dell.setDescription("Dell Inspiron 14-inch Laptop (Black) with 3rd Generation Intel Core processors");
			laptop_dell.setCategory("Laptop");
			laptop_dell.setManufacturer("Dell");
			laptop_dell.setUnitsInStock(1000);
			Product tablet_Nexus = new Product("P1236", "Nexus 7", new BigDecimal(300));
			tablet_Nexus.setDescription(
					"Google Nexus 7 is the lightest 7 inch tablet With a quad-core Qualcomm Snapdragon™ S4 Pro processor");
			tablet_Nexus.setCategory("Tablet");
			tablet_Nexus.setManufacturer("Google");
			tablet_Nexus.setUnitsInStock(1000);
			this.productSvc.addProduct(iphone);
			this.productSvc.addProduct(laptop_dell);
			this.productSvc.addProduct(tablet_Nexus);
		}
	}
	
	
	private String buildCartId(){
		return UUID.randomUUID().toString();
	}
	
	
	@Test
	public void testUpdateAlreadyCreated() {
		String cartId="02386ef8-0227-4a63-8f3c-b9774da50641";
		Cart c = new Cart(cartId);
		
//		Product p  = this.productSvc.getProductById("P1234");
//		CartItem item = new CartItem(p);
//		item.setQuantity(2);
//		c.addCartItem(item );
		Product p2  = this.productSvc.getProductById("P1235");
		CartItem item2 = new CartItem(p2);
		item2.setQuantity(1);
		c.addCartItem(item2);
		this.cartService.update(cartId, c);
		logger.info("UPDATED");
		Cart cartToCheck = this.cartService.read(cartId);
		assertThat(cartToCheck.getCartItems().size(),is(1));
	}
	
	@Test
	public void testUpdate() {
		try {
			String cartId=this.buildCartId();
			
			Product p  = this.productSvc.getProductById("P1234");
			Cart c = new Cart(cartId);
			CartItem item = new CartItem(p);
			item.setQuantity(2);
			c.addCartItem(item );
			this.cartService.create(c);
			logger.info("CREADO ....");
			Product p2  = this.productSvc.getProductById("P1235");
			CartItem item2 = new CartItem(p2);
			item2.setQuantity(3);
			c.addCartItem(item2);
			this.cartService.update(cartId, c);
			logger.info("UPDATE 1 ...."+cartId);
			Cart updatedCart = this.cartService.read(cartId);
			assertThat(updatedCart.getCartItems().values().iterator().next().getProduct(),notNullValue());
		} catch (Throwable e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//assertEquals(1,this.cartService.read(cartId).getCartItems().size());
		
	}

	@Test
	public void testCreate(){
		String cartId=this.buildCartId();
		Product p  = this.productSvc.getProductById("P1234");
		Cart c = new Cart(cartId);
		CartItem item = new CartItem(p);
		item.setQuantity(1);
		c.addCartItem(item);
		this.cartService.create(c);
		logger.info("Items:"+this.cartService.read(cartId).getCartItems().size());
		logger.info("Producto:"+this.cartService.read(cartId).getCartItems().values().iterator().next().getProduct());
		assertThat(c.getCartItems().values().iterator().next().getProduct(),notNullValue());
	}

	@Test
	public void testRead(){
		Cart c = this.cartService.read("981f735a-4f8a-4180-9659-d6b66c744e63");
		assertThat(c,notNullValue());
		assertThat(c.getCartItems().values().iterator().next().getProduct(),notNullValue());
	}
}
