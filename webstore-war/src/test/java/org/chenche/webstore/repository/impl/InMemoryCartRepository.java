package org.chenche.webstore.repository.impl;

import java.util.HashMap;
import java.util.Map;

import org.chenche.webstore.domain.Cart;
import org.chenche.webstore.repository.CartRepository;
import org.springframework.stereotype.Repository;

@Repository
public class InMemoryCartRepository implements CartRepository{

	private Map<String, Cart> listOfCarts;
	
	public InMemoryCartRepository() {
	    listOfCarts = new HashMap<String,Cart>();
	 }
	
	@Override
	public Cart create(Cart cart) {
		if (listOfCarts.containsKey(cart.getCartId())){
			throw new IllegalArgumentException(String.format("Can not create cart. A cart with the give id (%) already exists", cart.getCartId()));
		}
		listOfCarts.put(cart.getCartId(), cart);
		return cart;
	}

	@Override
	public Cart read(String cartId) {
		return listOfCarts.get(cartId);
	}

	@Override
	public void update(String cartId, Cart cart) {
		if(!listOfCarts.containsKey(cartId)){
			throw new IllegalArgumentException(String.format("Can not update cart. A cart with the give id (%) does not exist exists", cartId));
		}
		
		listOfCarts.put(cartId, cart);
	}

	@Override
	public void delete(String cartId) {
		if(!listOfCarts.containsKey(cartId)){
			throw new IllegalArgumentException(String.format("Can not delete cart. A cart with the give id (%) does not exist exists", cartId));
		}
		
		listOfCarts.remove(cartId);
	}
	
	

}
