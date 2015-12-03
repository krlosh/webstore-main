package org.chenche.webstore.repository.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Expression;
import javax.persistence.criteria.ParameterExpression;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.transaction.Transactional;

import org.chenche.webstore.domain.ProductVO;
import org.chenche.webstore.repository.ProductRepository;
import org.springframework.stereotype.Component;

@Component
public class ProductRepositoryImpl implements ProductRepository {

	@PersistenceContext
	private EntityManager entityManager;
	
	public EntityManager getEntityManager() {
		return entityManager;
	}

	public void setEntityManager(EntityManager entityManager) {
		this.entityManager = entityManager;
	}
	
	@Override
	public List<ProductVO> getAllProducts() {
		return this.entityManager.createQuery("SELECT p from ProductVO p",ProductVO.class).getResultList();
	}

	@Override
	public List<ProductVO> getProductsByCategory(String categoryId) {
//Empleando Criteria builder
		CriteriaBuilder cbuilder = this.entityManager.getCriteriaBuilder();
		CriteriaQuery<ProductVO> query = cbuilder.createQuery(ProductVO.class);
		Root<ProductVO> root = query.from(ProductVO.class);
		ParameterExpression<String> catParameter = cbuilder.parameter(String.class);
		query.select(root).where(cbuilder.equal(root.get("category"),catParameter));
		
		return this.entityManager.createQuery(query).setParameter(catParameter, categoryId).getResultList();
	}

	@Override
	public ProductVO getProductById(String productId) {
		return this.entityManager.find(ProductVO.class, productId);
	}

	@Override
	public Set<ProductVO> getProductsByFilter(Map<String, List<String>> filterParams) {
		CriteriaBuilder cbuilder = this.entityManager.getCriteriaBuilder();
		CriteriaQuery<ProductVO> query = cbuilder.createQuery(ProductVO.class);
		Root<ProductVO> root = query.from(ProductVO.class);
		
		query = query.select(root);
		List<Predicate> andPredicates = new ArrayList<Predicate>();
		for(Entry<String,List<String>> entryFilter: filterParams.entrySet()){
			String fieldName = entryFilter.getKey();
			Expression<String> expressionField = root.get(fieldName);
			List<Predicate> orPredicates = new ArrayList<Predicate>();
			for(String value:entryFilter.getValue()){
				orPredicates.add(cbuilder.equal(expressionField,value));
			}
			andPredicates.add(cbuilder.or(orPredicates.toArray(new Predicate[orPredicates.size()])));
			
		}
		if(!andPredicates.isEmpty()){
			query.where(andPredicates.toArray(new Predicate[]{})).distinct(true);			
		}
		Set<ProductVO>  setPvo = new HashSet<ProductVO>();
		for(ProductVO p: this.entityManager.createQuery(query).getResultList()){
			setPvo.add(p);
		}
		return setPvo;
	}

	@Override
	public List<ProductVO> getProductsByManufacturer(String manufacturer) {
		//JPQL
		return this.entityManager.createQuery("SELECT p from ProductVO p WHERE p.manufacturer=:m",ProductVO.class).setParameter("m", manufacturer).getResultList();
	}

	@Override
	public Set<ProductVO> getProductsByPriceFilter(Map<String, List<String>> filterParams) {
		CriteriaBuilder cbuilder = this.entityManager.getCriteriaBuilder();
		CriteriaQuery<ProductVO> query = cbuilder.createQuery(ProductVO.class);
		Root<ProductVO> root = query.from(ProductVO.class);
		
		query = query.select(root);
		Set<String> criterias = filterParams.keySet();
		Expression<BigDecimal> unitPrice = root.get("unitPrice");
		
		List<Predicate> andPredicates = new ArrayList<Predicate>();
		if(criterias.contains("low")){
				//lowerParameter = cbuilder.parameter(BigDecimal.class);
			for(String low:filterParams.get("low")){
				andPredicates.add(cbuilder.le(unitPrice, new BigDecimal(low)));
			}
		}
		
		if(criterias.contains("high")){
			for(String high:filterParams.get("high")){
				andPredicates.add(cbuilder.gt(unitPrice, new BigDecimal(high)));
			}
		}		
		
		if(!andPredicates.isEmpty()){
			query.where(andPredicates.toArray(new Predicate[]{})).distinct(true);			
		}
		Set<ProductVO>  setPvo = new HashSet<ProductVO>();
		for(ProductVO p: this.entityManager.createQuery(query).getResultList()){
			setPvo.add(p);
		}
		return setPvo;
	}

	@Override
	public void addProduct(ProductVO p) {
		this.entityManager.persist(p);
	}

}
