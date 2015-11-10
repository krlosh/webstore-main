package org.chenche.webstore.service.impl;

import java.util.List;
import java.util.Map;
import java.util.Set;

import org.chenche.webstore.domain.Product;
import org.chenche.webstore.repository.ProductRepository;
import org.chenche.webstore.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
@Service
public class ProductServiceImpl implements ProductService{

	@Autowired	
	private ProductRepository productRepository;
	
	public List<Product> getAllProducts() {
		return this.productRepository.getAllProducts();
	}

	public Product getProductById(String productID) {
		return this.productRepository.getProductById(productID);
	}

	@Override
	public List<Product> getProductsByCategory(String categoryId) {
		return this.productRepository.getProductsByCategory(categoryId);
	}

	@Override
	public Set<Product> getProductsByFilter(Map<String, List<String>> filterParams) {
		return this.productRepository.getProductsByFilter(filterParams);
	}

	@Override
	public List<Product> getProductsByManufacturer(String manufacturer) {
		return this.productRepository.getProductsByManufacturer(manufacturer);
	}

	@Override
	public Set<Product>getProductsByPriceFilter(Map<String, List<String>> filterParams){
		return this.productRepository.getProductsByPriceFilter(filterParams);
	}

	@Override
	public void addProduct(Product p) {
	this.productRepository.addProduct(p);
	}
}
