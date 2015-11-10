package org.chenche.webstore.domain;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

public class Cart implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 3237303672269635289L;

	private String cartId;
	
	private Map<String,CartItem> cartItems;
	
	private BigDecimal grandTotal;
	
	public Cart(){
		this.cartItems= new HashMap<String,CartItem>();
		this.grandTotal=new BigDecimal(0);
	}
	
	public Cart(String cartId){
		this();
		this.cartId=cartId;
	}

	public String getCartId() {
		return cartId;
	}

	public void setCartId(String cartId) {
		this.cartId = cartId;
	}

	public Map<String, CartItem> getCartItems() {
		return cartItems;
	}

	public void setCartItems(Map<String, CartItem> cartItems) {
		this.cartItems = cartItems;
	}

	public BigDecimal getGrandTotal() {
		return grandTotal;
	}

	public void setGrandTotal(BigDecimal grandTotal) {
		this.grandTotal = grandTotal;
	}
	
	public void addCartItem(CartItem item){
		String productId = item.getProduct().getProductId();
		
		if(this.cartItems.containsKey(productId)){
			CartItem existingItem = cartItems.get(productId);
			existingItem.setQuantity(existingItem.getQuantity()+item.getQuantity());
			cartItems.put(productId, existingItem);
		}
		else{
			cartItems.put(productId, item);
		}
		updateGrandTotal();
	}
	
	public void removeCartItem(CartItem item){
		cartItems.remove(item.getProduct().getProductId());
		updateGrandTotal();
	}
	
	public void updateGrandTotal(){
		this.grandTotal=new BigDecimal(0);
		for(CartItem item : cartItems.values()){
	      grandTotal = grandTotal.add(item.getTotalPrice());
	    }
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((cartId == null) ? 0 : cartId.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Cart other = (Cart) obj;
		if (cartId == null) {
			if (other.cartId != null)
				return false;
		} else if (!cartId.equals(other.cartId))
			return false;
		return true;
	}
	
}
