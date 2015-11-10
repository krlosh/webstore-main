package org.chenche.webstore.service;

import org.chenche.webstore.domain.Cart;

public interface CartService {
	Cart create(Cart cart);
	  
	Cart read(String cartId);
  
	void update(String cartId, Cart cart);
  
	void delete(String cartId);
	
	Cart validate(String cartId);
}
