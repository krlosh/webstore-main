package org.chenche.webstore.service.impl;

import org.chenche.webstore.domain.Product;
import org.chenche.webstore.domain.ProductVO;

public class ProductTranslator {

	//Translate to Product
	static Product translateToProduct(ProductVO pVo) {
		if(pVo!=null){
			Product product = new Product(pVo.getProductId(),pVo.getName(),pVo.getUnitPrice());
			product.setCategory(pVo.getCategory());
			product.setCondition(pVo.getCondition());
			product.setDescription(pVo.getDescription());
			product.setDiscontinued(pVo.isDiscontinued());
			product.setManufacturer(pVo.getManufacturer());
			product.setUnitsInOrder(pVo.getUnitsInOrder());
			product.setUnitsInStock(pVo.getUnitsInStock());
			return product;
		}
		else {
			return null;
		}
	}
	
	static ProductVO translateToProductVO(Product p){
		ProductVO vo = new ProductVO(p.getProductId(),p.getName(),p.getUnitPrice());
		vo.setCategory(p.getCategory());
		vo.setCondition(p.getCondition());
		vo.setDescription(p.getDescription());
		vo.setDiscontinued(p.isDiscontinued());
		vo.setManufacturer(p.getManufacturer());
		vo.setUnitsInOrder(p.getUnitsInOrder());
		vo.setUnitsInStock(p.getUnitsInStock());
		return vo;
	}

}
