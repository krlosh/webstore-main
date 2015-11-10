package org.chenche.webstore.repository;

import java.util.List;
import java.util.Map;
import java.util.Set;

import org.chenche.webstore.domain.Product;

public interface ProductRepository {
	public List<Product> getAllProducts();
	
	public List<Product> getProductsByCategory(String categoryId);
	
	public Product getProductById(String productId);
	
	public Set<Product> getProductsByFilter(Map<String, List<String>> filterParams);
	
	public List<Product> getProductsByManufacturer(String manufacturer);

	public Set<Product> getProductsByPriceFilter(Map<String, List<String>> filterParams);
	
	void addProduct(Product p);
}
