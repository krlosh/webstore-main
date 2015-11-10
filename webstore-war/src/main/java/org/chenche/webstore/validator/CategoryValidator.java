package org.chenche.webstore.validator;

import java.util.ArrayList;
import java.util.List;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class CategoryValidator implements ConstraintValidator<Category, String>{

	private  List<String> allowedCategories;
	@Override
	public void initialize(Category arg0) {
		this.allowedCategories=new ArrayList<>();
		this.allowedCategories.add("Smart Phone");
		this.allowedCategories.add("Tablet");
		this.allowedCategories.add("Laptop");
	}

	@Override
	public boolean isValid(String category, ConstraintValidatorContext arg1) {
		if(category==null){
			return false;
		}
		else{
			return this.allowedCategories.contains(category);
		}
	}

}
