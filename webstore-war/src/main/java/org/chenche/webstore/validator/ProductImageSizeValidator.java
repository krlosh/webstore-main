package org.chenche.webstore.validator;

import org.chenche.webstore.domain.Product;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

public class ProductImageSizeValidator implements Validator{

	private long allowedSize;
	
	@Override
	public boolean supports(Class<?> arg0) {
		return  Product.class.isAssignableFrom(arg0);
	}

	@Override
	public void validate(Object target, Errors errors) {
		Product p=(Product)target;
		if(p.getProductId()!=null){
			if(p.getProductImage().getSize()>allowedSize){
				errors.rejectValue("productImage", "org.chenche.webstore.validator.ProductImageSizeValidator.message");
			}
		}
	}

	public long getAllowedSize() {
		return allowedSize;
	}

	public void setAllowedSize(long allowedSize) {
		this.allowedSize = allowedSize;
	}

}
