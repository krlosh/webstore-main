package org.chenche.webstore.validator;

import java.util.HashSet;
import java.util.Set;

import javax.validation.ConstraintViolation;

import org.chenche.webstore.domain.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

//Al emplear Spring validation,  no se tienen en cuenta los jsr-303 validators excepto que lo implementemos de este modo

public class ProductValidator implements Validator{

	@Autowired   
	private javax.validation.Validator beanValidator;
	
	private Set<Validator> springValidators;
	
	public ProductValidator(){
		springValidators=new HashSet<Validator>();
	}
	
	public void setSpringValidators(Set<Validator> springValidators) {
		this.springValidators = springValidators;
	}
	
	@Override
	public boolean supports(Class<?> arg0) {
		return Product.class.isAssignableFrom(arg0);
	}

	@Override
	public void validate(Object target, Errors errors) {
		Set<ConstraintViolation<Object>> constraintViolations =beanValidator.validate(target);
		for (ConstraintViolation<Object> constraintViolation : constraintViolations) {
			String propertyPath=constraintViolation.getPropertyPath().toString();
			String message = constraintViolation.getMessageTemplate().replace("{", "").replace("}", "");
			errors.rejectValue(propertyPath, message);
		}
		
		for (Validator validator : springValidators) {
			validator.validate(target, errors);
		}
	}
	
	

}
