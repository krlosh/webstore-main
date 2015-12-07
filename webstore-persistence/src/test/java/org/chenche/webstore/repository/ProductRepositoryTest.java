package org.chenche.webstore.repository;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.fail;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.chenche.webstore.domain.ProductVO;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@ContextConfiguration(locations={"/spring.xml",
								"/spring-test.xml"}
					)
@RunWith(SpringJUnit4ClassRunner.class)
public class ProductRepositoryTest {

	@Autowired
	private ProductRepository repository;
	
	@Before	
	public void setup(){
		
	}
	
	private void initializeDB(){
		if(this.repository.getAllProducts().isEmpty()){
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
			this.repository.addProduct(iphone);
			this.repository.addProduct(laptop_dell);
			this.repository.addProduct(tablet_Nexus);
		}
	}
	
	@Test
	public void testGetAllProducts() {
		initializeDB();
		try{
			assertFalse(this.repository.getAllProducts().isEmpty());
		}
		catch(Throwable t){
			t.printStackTrace();
			fail(t.getLocalizedMessage());
		}
	}

	@Test
	public void testGetProductsByCategory() {
		initializeDB();
		try{
			assertFalse(this.repository.getProductsByCategory("Laptop").isEmpty());
		}
		catch(Throwable t){
			t.printStackTrace();
			fail(t.getLocalizedMessage());
		}
	}

	@Test
	public void testGetProductById() {
		initializeDB();
		try{
			assertFalse(this.repository.getProductById("P1234")==null);
		}
		catch(Throwable t){
			t.printStackTrace();
			fail(t.getLocalizedMessage());
		}
	}

	@Test
	public void testGetProductsByFilter() {
		initializeDB();
		try{
			Map<String, List<String>> p =  new HashMap<String, List<String>>();
			
			List<String> la=new ArrayList<String>();
			for(String  l :new String[]{"Laptop","Tablet"}){
				la.add(l);
			}
			p.put("category", la);
			List<String> ha=new ArrayList<String>();
			for(String  l :new String[]{"Dell","Apple"}){
				ha.add(l);
			}
			p.put("manufacturer", ha);
			assertFalse(this.repository.getProductsByFilter(p).isEmpty());
		}
		catch(Throwable t){
			t.printStackTrace();
			fail(t.getLocalizedMessage());
		}
	}

	@Test
	public void testGetProductsByManufacturer() {
		initializeDB();
		try{
			assertFalse(this.repository.getProductsByManufacturer("Apple").isEmpty());
		}
		catch(Throwable t){
			t.printStackTrace();
			fail(t.getLocalizedMessage());
		}
	}

	@Test
	public void testGetProductsByPriceFilter() {
		initializeDB();
		try{
			Map<String, List<String>> p =  new HashMap<String, List<String>>();
			
			List<String> la=new ArrayList<String>();
			for(String  l :new String[]{"10000","500"}){
				la.add(l);
			}
			p.put("low", la);
			List<String> ha=new ArrayList<String>();
			for(String  l :new String[]{"10","20"}){
				ha.add(l);
			}
			p.put("high", ha);
			assertFalse(this.repository.getProductsByPriceFilter(p ).isEmpty());
		}
		catch(Throwable t){
			t.printStackTrace();
			fail(t.getLocalizedMessage());
		}
	}

	@Test
	public void testAddProduct() {
		fail("Not yet implemented");
	}

}
