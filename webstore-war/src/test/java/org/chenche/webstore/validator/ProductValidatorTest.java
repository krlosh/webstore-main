package org.chenche.webstore.validator;

import static org.junit.Assert.assertEquals;

import java.math.BigDecimal;

import org.chenche.webstore.domain.Product;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.validation.BindException;
import org.springframework.validation.ValidationUtils;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("test-DispatcherServlet-context.xml")
@WebAppConfiguration
public class ProductValidatorTest {

	@Autowired
	private ProductValidator productValidator;

	@Test
	public void testProductWithoutPriceShouldbeInvalid() {
		// Arrange
		Product product = new Product();
		product.setCategory("Tablet");
		BindException bindException = new BindException(product, "product");
		// Act
		ValidationUtils.invokeValidator(productValidator, product, bindException);
		// Assert
		assertEquals(1, bindException.getErrorCount());
	}

	@Test
	public void productWithExistingProductIdInvalid() {
		// Arrange
		Product product = new Product("P1234", "iPhone 5s", new BigDecimal(500));
		product.setCategory("Tablet");
		BindException bindException = new BindException(product, "product");
		// Act
		ValidationUtils.invokeValidator(productValidator, product, bindException);
		// Assert
		assertEquals(1, bindException.getErrorCount());
	}

	@Test
	public void aValidProductShouldNotGetAnyErrorDuringValidation() {
		// Arrange
		Product product = new Product("P9876", "iPhone 5s", new BigDecimal(500));
		product.setCategory("Tablet");
		BindException bindException = new BindException(product, "product");
		// Act
		ValidationUtils.invokeValidator(productValidator, product, bindException);
		// Assert
		assertEquals(0, bindException.getErrorCount());
	}
}
