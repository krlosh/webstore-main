package org.chenche.webstore.exception;

public class InvalidCartException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1273641415606420937L;

	private String cartId;
	
	public InvalidCartException(String cartId){
		this.cartId=cartId;
	}
	
	public String getCartId(){
		return this.cartId;
	}
}
