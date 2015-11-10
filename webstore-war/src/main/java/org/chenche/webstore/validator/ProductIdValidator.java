package org.chenche.webstore.validator;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.chenche.webstore.domain.Product;
import org.chenche.webstore.exception.ProductNotFoundException;
import org.chenche.webstore.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;

public class ProductIdValidator implements ConstraintValidator<ProductId, String>{

	@Autowired
	private ProductService productService;

	@Override
	public void initialize(ProductId arg0) {
		// Intentionally left blank
		
	}

	@Override
	public boolean isValid(String value, ConstraintValidatorContext arg1) {
		Product product;
	    try {
	      product = productService.getProductById(value);

	    } catch (ProductNotFoundException e) {
	    	return true;
	    }
	    
	    if(product!= null) {
	        return false;
	     }
	    
	    return true;
	    
	}

}
