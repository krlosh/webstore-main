package org.chenche.webstore.service.impl;

import org.chenche.webstore.domain.Cart;
import org.chenche.webstore.exception.InvalidCartException;
import org.chenche.webstore.repository.CartRepository;
import org.chenche.webstore.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CartServiceImpl implements CartService {

	@Autowired
	private CartRepository cartRepository;
	
	 public Cart create(Cart cart) {
	    return cartRepository.create(cart);
	  }

	  public Cart read(String cartId) {
	    return cartRepository.read(cartId);
	  }

	  public void update(String cartId, Cart cart) {
	    cartRepository.update(cartId, cart);
	  }

	  public void delete(String cartId) {
	    cartRepository.delete(cartId);
	    
	  }

	@Override
	public Cart validate(String cartId) {
		Cart cart = cartRepository.read(cartId);
		if(cart==null||cart.getCartItems().size()==0){
			throw new InvalidCartException(cartId);
		}
		return cart;
	}
}
