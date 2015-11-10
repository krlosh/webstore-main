package org.chenche.webstore.repository.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.chenche.webstore.domain.Product;
import org.chenche.webstore.exception.ProductNotFoundException;
import org.chenche.webstore.repository.ProductRepository;
import org.springframework.stereotype.Repository;

@Repository
public class InMemoryProductRepository implements ProductRepository {

	private List<Product> listOfProducts = new ArrayList<Product>();

	public InMemoryProductRepository() {
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
		listOfProducts.add(iphone);
		listOfProducts.add(laptop_dell);
		listOfProducts.add(tablet_Nexus);
	}

	public List<Product> getAllProducts() {
		return listOfProducts;
	}

	public Product getProductById(String productId) {

		Product productById = null;
		for (Product product : listOfProducts) {
			if (product != null && product.getProductId() != null && product.getProductId().equals(productId)) {
				productById = product;
				break;
			}
		}

		if (productById == null) {
			throw new ProductNotFoundException("No products found with the product id: " + productId);
		}
		return productById;

	}

	@Override
	public List<Product> getProductsByCategory(String categoryId) {
		List<Product> lista = new ArrayList<>();
		for(Product p:this.listOfProducts){
			if(categoryId.equalsIgnoreCase(p.getCategory())){
				lista.add(p);
			}
		}
		return lista;
	}

	@Override
	public Set<Product> getProductsByFilter(Map<String, List<String>> filterParams) {
		
		Set<Product> productsByBrand = new HashSet<Product>();
	    Set<Product> productsByCategory = new HashSet<Product>();

	    Set<String> criterias = filterParams.keySet();
	    
	    if(criterias.contains("brand")) {
	      for(String brandName: filterParams.get("brand")) {
	        for(Product product: listOfProducts) {
	          if(brandName.equalsIgnoreCase(product.getManufacturer())){
	            productsByBrand.add(product);
	          }
	        }
	      }
	    }
	    
	    if(criterias.contains("category")) {
	      for(String category: filterParams.get("category")) {
	    	  productsByCategory.addAll(this.getProductsByCategory(category));
	      }
	    }
		
	    productsByCategory.retainAll(productsByBrand);
		
		return productsByCategory;
	}
	
	@Override
	public Set<Product> getProductsByPriceFilter(Map<String, List<String>> filterParams){
		Set<Product> resultadosLow = new HashSet<Product>();
		Set<Product> resultadosHigh = new HashSet<Product>();
		
		Set<String> criterias = filterParams.keySet();
		
		if(criterias.contains("low")){
			for(String low:filterParams.get("low")){
				for(Product p:this.listOfProducts){
					if (p.getUnitPrice().compareTo(new BigDecimal(low))>=0){
						resultadosLow.add(p);
					}
				}
			}
		}
		if(criterias.contains("high")){
			for(String high:filterParams.get("high")){
				for(Product p:this.listOfProducts){
					if (p.getUnitPrice().compareTo(new BigDecimal(high))<0){
						resultadosHigh.add(p);
					}
				}
			}
		}
		resultadosLow.retainAll(resultadosHigh);
		return resultadosLow;
	}

	@Override
	public List<Product> getProductsByManufacturer(String manufacturer) {
		List<Product> lista = new ArrayList<>();
		for(Product p:this.listOfProducts){
			if(manufacturer.equalsIgnoreCase(p.getManufacturer())){
				lista.add(p);
			}
		}
		return lista;
	}

	@Override
	public void addProduct(Product p) {
		this.listOfProducts.add(p);
	}
	
}
