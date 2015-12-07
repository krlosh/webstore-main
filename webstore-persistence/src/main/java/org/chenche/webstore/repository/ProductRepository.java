package org.chenche.webstore.repository;

import java.util.List;
import java.util.Map;
import java.util.Set;

import org.chenche.webstore.domain.ProductVO;


public interface ProductRepository {
	public List<ProductVO> getAllProducts();
	
	public List<ProductVO> getProductsByCategory(String categoryId);
	
	public ProductVO getProductById(String productId);
	
	public Set<ProductVO> getProductsByFilter(Map<String, List<String>> filterParams);
	
	public List<ProductVO> getProductsByManufacturer(String manufacturer);

	public Set<ProductVO> getProductsByPriceFilter(Map<String, List<String>> filterParams);
	
	void addProduct(ProductVO p);
	
	public void updateProduct(ProductVO p);
}
