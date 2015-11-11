<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<li>
	<div>
	<a href="?language=en">English</a>|<a href="?language=es">Español</a><!-- Esto no funciona pq en products hay que añadir el productId -->
	</div>
</li>
<li><a href="<spring:url value="/"/>">Home</a></li>
<li><a href="<spring:url value= "/customers/"/>">Customers</a></li>
<li><a href="<spring:url value= "/products/"/>">Products</a></li>
<li><a href="<spring:url value="/products/add"/>">Add Product</a></li> <li><a href="<spring:url value="/cart/"/>">Cart</a></li>