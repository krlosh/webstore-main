<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>


<section>
	<div class="container">
		<h1 class="alert alert-danger">There is no product found with the
			Product id ${invalidProductId}</h1>
	</div>
</section>

<section>
	<div class="container">
		<p>${url}</p>
		<p>${exception}</p>
	</div>

	<div class="container">
		<p>
			<a href="<spring:url value="/products" />" class="btn btn-primary">
				<span class="glyphicon-hand-left glyphicon"></span> Products
			</a>
		</p>
	</div>

</section>
