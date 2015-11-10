package org.chenche.webstore.service;

import java.util.List;
import java.util.Map;
import java.util.Set;

import org.chenche.webstore.domain.Product;

public interface ProductService {
	public List<Product> getAllProducts();
	
	public List<Product> getProductsByCategory(String categoryId);
	
	public Product getProductById(String productID);
	
	public Set<Product> getProductsByFilter(Map<String, List<String>> filterParams);
	
	public List<Product> getProductsByManufacturer(String manufacturer);

	public Set<Product> getProductsByPriceFilter(Map<String, List<String>> filterParams);
	
	public void addProduct(Product p);
}
