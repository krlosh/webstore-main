package org.chenche.webstore.repository;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.fail;

import java.math.BigDecimal;
import java.util.UUID;

import org.chenche.webstore.domain.CartItemVO;
import org.chenche.webstore.domain.CartVO;
import org.chenche.webstore.domain.ProductVO;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Propagation;
import javax.transaction.Transactional;
@ContextConfiguration("/spring.xml")
@RunWith(SpringJUnit4ClassRunner.class)
public class CartRepositoryTest {

	private static Logger logger = LoggerFactory.getLogger(CartRepositoryTest.class);
	
	
	
	
	@Autowired
	private ProductRepository productRepository;
	
	@Autowired
	private CartRepository cartRepository;
	
	private String buildCartId(){
		return UUID.randomUUID().toString();
	}
	
	@Transactional()
	private void creadoVacio(String cartid){
		CartVO cart=new CartVO();
		cart.setCartId(cartid);
		this.cartRepository.create(cart);
		logger.info("Creado vacio " +cartid);
	}
	
	@Transactional()
	private void creadoConUnProducto(String cartid){
		CartVO cart=new CartVO();
		cart.setCartId(cartid);
		CartItemVO item = new CartItemVO();
		ProductVO p  = this.productRepository.getProductById("P1234");
		item.setProduct(p);
		item.setQuantity(1);
		item.setTotalPrice(p.getUnitPrice());
		item.setCart(cart);
		cart.getItems().add( item );
		this.cartRepository.create(cart);
		logger.info("Creado un item " +cartid);
	}
	
	@Transactional()
	private void updateConUnproducto(String cartid){
		CartVO cart=new CartVO();
		cart.setCartId(cartid);
		CartItemVO item = new CartItemVO();
		ProductVO p  = this.productRepository.getProductById("P1234");
		item.setProduct(p);
		item.setQuantity(2);
		item.setTotalPrice(p.getUnitPrice());
		item.setCart(cart);
		cart.getItems().add( item );
		this.cartRepository.update("sessionId", cart);
		logger.info("Update cart " +cartid);
	}
	
	@Transactional()
	private void checkNotEmpty(String cartId){
		CartVO readedCart = this.cartRepository.read(cartId);
		assertEquals(1,readedCart.getItems().size());
		assertNotNull(readedCart.getItems().iterator().next().getProduct());
		logger.info(readedCart.getItems().iterator().next().getProduct().toString());
	}
	
	
	@Before
	public void setup(){
		if(this.productRepository.getAllProducts().isEmpty()){
			ProductVO iphone = new ProductVO("P1234", "iPhone 5s", new BigDecimal(500));
			iphone.setDescription("Apple iPhone 5s smartphone with 4.00-inch 640x1136 display and 8-megapixel rear camera");
			iphone.setCategory("Smart Phone");
			iphone.setManufacturer("Apple");
			iphone.setUnitsInStock(1000);
			ProductVO laptop_dell = new ProductVO("P1235", "Dell Inspiron", new BigDecimal(700));
			laptop_dell.setDescription("Dell Inspiron 14-inch Laptop (Black) with 3rd Generation Intel Core processors");
			laptop_dell.setCategory("Laptop");
			laptop_dell.setManufacturer("Dell");
			laptop_dell.setUnitsInStock(1000);
			ProductVO tablet_Nexus = new ProductVO("P1236", "Nexus 7", new BigDecimal(300));
			tablet_Nexus.setDescription(
					"Google Nexus 7 is the lightest 7 inch tablet With a quad-core Qualcomm Snapdragon™ S4 Pro processor");
			tablet_Nexus.setCategory("Tablet");
			tablet_Nexus.setManufacturer("Google");
			tablet_Nexus.setUnitsInStock(1000);
			this.productRepository.addProduct(iphone);
			this.productRepository.addProduct(laptop_dell);
			this.productRepository.addProduct(tablet_Nexus);
		}
	}
	
	
	@Test
	@Transactional
	//@Rollback(false)
	public void testCreateEmpty() {
		logger.debug("testCreateEmpty");
		String cartid= this.buildCartId();
		try {
			CartVO cart=new CartVO();
			cart.setCartId(cartid);
			this.cartRepository.create(cart);
//			CartVO readedCart = this.cartRepository.read("sessionId");
//			assertNotNull(readedCart);
		} catch (Throwable e) {
			e.printStackTrace();
			fail(e.getLocalizedMessage());
		}
	}
	
	@Test
	@Transactional
	public void testCreateOneItem() {
		logger.debug("testCreateOneItem");
		String cartid= this.buildCartId();
		try {
			CartVO cart=new CartVO();
			cart.setCartId(cartid);
			
			CartItemVO item = new CartItemVO();
			ProductVO p  = this.productRepository.getProductById("P1234");
			item.setProduct(p);
			item.setQuantity(1);
			item.setTotalPrice(p.getUnitPrice());
			item.setCart(cart);
			cart.getItems().add( item );
			this.cartRepository.create(cart);
		} catch (Exception e) {
			e.printStackTrace();
			fail(e.getLocalizedMessage());
		}
		
		this.checkNotEmpty(cartid);
	}
	
	
	
	
	
	@Test
	@Transactional
	public void testUpdateOneProductTwice() {
		logger.debug("testUpdateOneProductTwice");
		try {
			String cartid= this.buildCartId();
			this.creadoConUnProducto(cartid);
			logger.info("CREADO VACIO");
			
			this.updateConUnproducto(cartid);
			logger.info("ANADIDO un producto. Update 1");
			
			checkNotEmpty(cartid);
			logger.info("Verificado update 1");
			
			CartVO anotherCart=new CartVO();
			anotherCart.setCartId(cartid);
			CartItemVO anotherItem = new CartItemVO();
			ProductVO p  = this.productRepository.getProductById("P1235");
			anotherItem.setProduct(p);
			anotherItem.setQuantity(2);
			anotherItem.setTotalPrice(p.getUnitPrice());
			anotherItem.setCart(anotherCart);
			anotherCart.getItems().add( anotherItem );
			this.cartRepository.update(cartid, anotherCart);
			logger.info("ANADIDO un producto cantidad 2. UPDATE 2");
			
			//readedCart = this.cartRepository.read("sessionId");
//			System.out.println(readedCart.getItems().size());
//			assertEquals(1,readedCart.getItems().size());
//			assertNotNull(readedCart.getItems().iterator().next().getProduct());
//			System.out.println("Verificado update 2");
		} catch (Exception e) {
			e.printStackTrace();
			fail(e.getLocalizedMessage());
		}
	}
	
	
	@Test
	@Transactional //Importante, el que navegue por las relacciones debe ser transactional.
	public void testRead() {
		logger.info("testRead");
		String cartid= this.buildCartId();
		this.creadoConUnProducto(cartid);
		try {			
			CartVO readedCart = this.cartRepository.read(cartid);
			assertNotNull(readedCart);
			assertFalse(readedCart.getItems().isEmpty());
		} catch (Exception e) {
			e.printStackTrace();
			fail(e.getLocalizedMessage());
		}
	}
	
	
	@Test
	@Rollback(false)
	public void testUpdate() {
		logger.info("testUpdate");
		try {
			String cartid= "02386ef8-0227-4a63-8f3c-b9774da50641";//this.buildCartId();
			//this.creadoConUnProducto(cartid);
			ProductVO p2=this.productRepository.getProductById("P1234");
			ProductVO p=this.productRepository.getProductById("P1235");
			
			logger.info("Cargando valores");
			CartVO cart2=new CartVO();
			cart2.setCartId(cartid);
			CartItemVO c2i1 = new CartItemVO();
			c2i1.setProduct(p2);
			c2i1.setProduct(p2);
			c2i1.setQuantity(2);
			c2i1.setTotalPrice(p2.getUnitPrice().multiply(new BigDecimal(2)));
			c2i1.setCart(cart2);
			cart2.getItems().add(c2i1);
			
			
			CartItemVO c2i2 = new CartItemVO();
			c2i2.setProduct(p);
			c2i2.setProduct(p);
			c2i2.setQuantity(1);
			c2i2.setTotalPrice(p.getUnitPrice());
			c2i2.setCart(cart2);
			cart2.getItems().add(c2i2);
			logger.info("Inicio update");
			this.cartRepository.update(cartid, cart2);
			logger.info("ECOs");
						
			
		} catch (Throwable e) {
			e.printStackTrace();
			fail(e.getLocalizedMessage());
		}
	}
}
