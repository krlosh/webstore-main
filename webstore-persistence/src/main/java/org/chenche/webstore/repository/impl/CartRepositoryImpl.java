package org.chenche.webstore.repository.impl;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.chenche.webstore.domain.CartVO;
import org.chenche.webstore.repository.CartRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class CartRepositoryImpl implements CartRepository {
	
	private static Logger logger = LoggerFactory.getLogger(CartRepositoryImpl.class);

	@PersistenceContext
	private EntityManager entityManager;
	
	public EntityManager getEntityManager() {
		return entityManager;
	}

	public void setEntityManager(EntityManager entityManager) {
		this.entityManager = entityManager;
	}
	
	@Override
	public CartVO create(CartVO cart) {
		this.entityManager.persist(cart);
		return cart;
	}

	@Override
	public CartVO read(String cartId) {
		return this.entityManager.find(CartVO.class,cartId);
	}

	@Override
	public void update(String cartId, CartVO cart) {
		CartVO alreadyCreated = this.read(cartId);
		if(alreadyCreated==null){
			throw new IllegalArgumentException(String.format("Can not update cart. A cart with the give id %s does not exist exists", cartId));
		}
		
		
		CartVO mergedCart  = this.entityManager.merge(cart);
	}

	@Override
	public void delete(String cartId) {
		// TODO Auto-generated method stub

	}

}
