<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
	<section>
			<div class="container">
				<h1 class="alert alert-danger">Thank you</h1>
				<p>
					Thanks for the order. your order will be delivered to you on
					<fmt:formatDate type="date"
						value="${order.shippingDetail.shippingDate}" />
					!
				</p>
				<p>Your Order Number is ${order.orderId}</p>
			</div>
	</section>
	<section>
		<div class="container">
			<p>
				<a href="<spring:url value="/products" />" class="btn btn-primary">
					<span class="glyphicon-hand-left glyphicon"></span> Products
				</a>
			</p>
		</div>
	</section>
