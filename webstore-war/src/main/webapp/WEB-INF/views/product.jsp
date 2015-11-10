<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<section class="container" ng-app="cartApp">
	<div class="row">
		<div class="col-md-5">
			<img
				src="<c:url value="/resource/images/${product.productId}.png"></c:url>"
				alt="image" style="width: 100%" />
			<h3>${product.name}</h3>
			<p>${product.description}</p>
			<p>
				<a
					href="<spring:url value="/resource/pdf/${product.productId}.pdf" />"><spring:message
						code="product.productDetail.manual.label"></spring:message></a>
			</p>
			<p>
				<strong><spring:message code="product.itemCode.label" />:
				</strong><span class="label label-warning">${product.productId}</span>
			</p>
			<p>
				<strong><spring:message code="product.manufacturer.label" /></strong>
				: ${product.manufacturer}
			</p>
			<p>
				<strong><spring:message code="product.category.label" /></strong> :
				${product.category}
			</p>
			<p>
				<strong><spring:message
						code="product.unitsAvailables.label" /></strong> : ${product.unitsInStock}
			</p>
			<h4>${product.unitPrice}USD</h4>
			<p ng-controller="cartCtrl">
				<a href="#" ng-click="addToCart('${product.productId}')"
					class="btn btn-warning btn-large"> <span
					class="glyphicon-shopping-cart glyphicon"></span> <spring:message
						code="product.orderNow.label" />
				</a> <a href="<spring:url value="/cart" />" class="btn btn-default">
					<span class="glyphicon-hand-right glyphicon"></span> View Cart
				</a> <a href="<spring:url value="/products" />" class="btn btn-default">
					<span class="glyphicon-hand-left glyphicon"></span> <spring:message
						code="product.back.label" />
				</a>
			</p>
		</div>
	</div>
</section>
