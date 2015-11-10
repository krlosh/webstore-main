package org.chenche.webstore.exception;

public class ProductNotFoundException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8444558756052388564L;
	
	private String productId;

	public String getProductId() {
		return productId;
	}

	public ProductNotFoundException(String productId) {
		super();
		this.productId = productId;
	}
	
	

}
