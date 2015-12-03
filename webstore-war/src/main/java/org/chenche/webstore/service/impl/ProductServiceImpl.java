package org.chenche.webstore.service.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.transaction.Transactional;

import org.chenche.webstore.domain.Product;
import org.chenche.webstore.domain.ProductVO;
import org.chenche.webstore.repository.ProductRepository;
import org.chenche.webstore.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
@Service
public class ProductServiceImpl implements ProductService{

	@Autowired	
	private ProductRepository productRepository;
	
	public List<Product> getAllProducts() {
		List<Product> productList = new ArrayList<Product>();
		for(ProductVO vo: this.productRepository.getAllProducts()){
			productList.add(ProductTranslator.translateToProduct(vo));
		}
		return productList;
	}

	

	public Product getProductById(String productID) {
		return ProductTranslator.translateToProduct(this.productRepository.getProductById(productID));
	}

	@Override
	public List<Product> getProductsByCategory(String categoryId) {
		List<Product> productList = new ArrayList<Product>();
		for(ProductVO vo:this.productRepository.getProductsByCategory(categoryId)){
			productList.add(ProductTranslator.translateToProduct(vo));
		}
		return productList;
	}

	@Override
	public Set<Product> getProductsByFilter(Map<String, List<String>> filterParams) {
		Set<Product> productSet = new HashSet<Product>();
		for (ProductVO vo : this.productRepository.getProductsByFilter(filterParams)) {
			productSet.add(ProductTranslator.translateToProduct(vo));
		}
		return productSet;		
	}

	@Override
	public List<Product> getProductsByManufacturer(String manufacturer) {
		List<Product> productList = new ArrayList<Product>();
		for (ProductVO vo : this.productRepository.getProductsByManufacturer(manufacturer)) {
			productList.add(ProductTranslator.translateToProduct(vo));
		}
		return productList;
	}

	@Override
	public Set<Product>getProductsByPriceFilter(Map<String, List<String>> filterParams){
		Set<Product> productSet = new HashSet<Product>();
		for (ProductVO vo : this.productRepository.getProductsByPriceFilter(filterParams)) {
			productSet.add(ProductTranslator.translateToProduct(vo));
		}
		return productSet;
	}

	@Override
	@Transactional
	public void addProduct(Product p) {
		ProductVO vo = ProductTranslator.translateToProductVO(p);
		this.productRepository.addProduct(vo);
	}
	
	@javax.annotation.PostConstruct
	public void init(){
		System.out.println("Inicializando productos");
		
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
		this.addProduct(iphone);
		this.addProduct(laptop_dell);
		this.addProduct(tablet_Nexus);
	}
}
