package org.chenche.webstore.domain;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.validation.constraints.Digits;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import org.chenche.webstore.validator.Category;
import org.hibernate.validator.constraints.NotEmpty;

@Entity
public class ProductVO {


	@Column
	@Id
	@Pattern(regexp="P[0-9]+", message="{Pattern.Product.productId.validation}")
	private String productId;
	
	@Column
	@Size(min=4, max=50, message="{Size.Product.name.validation}")
	private String name;
	
	@Column
	@Min(value=0, message="{Min.Product.unitPrice.validation}")
	@Digits(integer=8, fraction=2, message="{Digits.Product.unitPrice.validation}")
	@NotNull(message= "{NotNull.Product.unitPrice.validation}")
	private BigDecimal unitPrice;
	
	@Column
	private String description;
	
	@Column
	private String manufacturer;
	
	@Column
	@Category
	@NotEmpty(message="{NotEmpty.Product.category.validation}")
	private String category;
	
	@Column
	@Min(value=0, message="{Min.Product.unitsInStock.validation}")
	private long unitsInStock;
	
	@Column
	private long unitsInOrder;
	
	@Column
	private boolean discontinued;
	
	@Column(name="condicion")
	private String condition;
	
	public ProductVO(){
		super();
	}

	public ProductVO(String productId, String name, BigDecimal unitPrice) {
		super();
		this.productId = productId;
		this.name = name;
		this.unitPrice = unitPrice;
	}
	
	public String getProductId() {
		return productId;
	}
	public void setProductId(String productId) {
		this.productId = productId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public BigDecimal getUnitPrice() {
		return unitPrice;
	}
	public void setUnitPrice(BigDecimal unitPrice) {
		this.unitPrice = unitPrice;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getManufacturer() {
		return manufacturer;
	}
	public void setManufacturer(String manufacturer) {
		this.manufacturer = manufacturer;
	}
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	public long getUnitsInStock() {
		return unitsInStock;
	}
	public void setUnitsInStock(long unitsInStock) {
		this.unitsInStock = unitsInStock;
	}
	public long getUnitsInOrder() {
		return unitsInOrder;
	}
	public void setUnitsInOrder(long unitsInOrder) {
		this.unitsInOrder = unitsInOrder;
	}
	public boolean isDiscontinued() {
		return discontinued;
	}
	public void setDiscontinued(boolean discontinued) {
		this.discontinued = discontinued;
	}
	public String getCondition() {
		return condition;
	}
	public void setCondition(String condition) {
		this.condition = condition;
	}
	
}
