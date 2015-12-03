package org.chenche.webstore.domain;

import java.util.ArrayList;
import java.util.Collection;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;


@Entity
public class CartVO {

	@Id
	private String cartId;
	
	@OneToMany(/*mappedBy="cart",*/cascade=CascadeType.ALL,orphanRemoval=true)
	@JoinColumn(name="cartId",nullable=false)
	private Collection<CartItemVO> items=new ArrayList<CartItemVO>();

	public String getCartId() {
		return cartId;
	}

	public void setCartId(String cartId) {
		this.cartId = cartId;
	}

	public Collection<CartItemVO> getItems() {
		return items;
	}

	public void setItems(Collection<CartItemVO> items) {
		this.items = items;
	}
	
	
}
