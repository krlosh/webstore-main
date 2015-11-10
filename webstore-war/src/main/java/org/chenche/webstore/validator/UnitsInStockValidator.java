package org.chenche.webstore.validator;

import java.math.BigDecimal;

import org.chenche.webstore.domain.Product;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
public class UnitsInStockValidator implements Validator{

	@Override
	public boolean supports(Class<?> arg0) {
		return Product.class.isAssignableFrom(arg0);
	}

	@Override
	public void validate(Object target, Errors errors) {
		Product product=(Product) target;
		if(product.getUnitPrice()!=null && new BigDecimal(1000).compareTo(product.getUnitPrice())<=0 && product.getUnitsInStock()>99){
			errors.rejectValue("unitsInStock", "org.chenche.webstore.validator.UnitsInStockValidator.message");
		}
	}

}
