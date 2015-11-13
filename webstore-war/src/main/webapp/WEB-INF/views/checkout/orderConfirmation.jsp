<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<link rel="stylesheet"
	href="//netdna.bootstrapcdn.com/bootstrap/3.0.0/css/bootstrap.min.css">
<title>Order Confirmation</title>
</head>
<body>
	<section>
		<div class="jumbotron">
			<div class="container">
				<h1>Order</h1>
				<p>Order Confirmation</p>
			</div>
		</div>
	</section>
	<div class="container">
		<div class="row">
			<form:form modelAttribute="order" class="form-horizontal">
				<input type="hidden" name="_flowExecutionKey"
					value="${flowExecutionKey}" />
				<div class="well col-xs-10 col-sm-10 col-md-6 col-xs-offset-1 col-sm-offset-1 col-md-offset-3">
					<div class="text-center">
						<h1>Receipt</h1>
					</div>
					<div class="row">
						Dear <strong>${order.customer.name}</strong>
					</div>
					<div class="row">
						<div class="col-xs-6 col-sm-6 col-md-6">
							<address>
								<strong>Shipping Address</strong> <br>
								${order.shippingDetail.name}<br>
								<table>
									<tbody>
										<tr>
											<td>${order.shippingDetail.shippingAddress.streetName}</td>
											<td>${order.shippingDetail.shippingAddress.doorNo}</td>
											<td>${order.shippingDetail.shippingAddress.zipCode}</td>
										</tr>
										<tr>
											<td>${order.shippingDetail.shippingAddress.areaName}</td>
											<td>${order.shippingDetail.shippingAddress.state}</td>
											<td>${order.shippingDetail.shippingAddress.country}</td>
										</tr>
									</tbody>
								</table>
								</address>
								<address>
								<strong>Billing Address</strong> <br>
								<table>
									<tbody>
										<tr>
											<td>${order.customer.billingAddress.streetName}</td>
											<td>${order.customer.billingAddress.doorNo}</td>
											<td>${order.customer.billingAddress.zipCode}</td>
										</tr>
										<tr>
											<td>${order.customer.billingAddress.areaName}</td>
											<td>${order.customer.billingAddress.state}</td>
											<td>${order.customer.billingAddress.country}</td>
										</tr>
									</tbody>
								</table>
								</address>
								<!--  Similarly, furnish every field of the order object within an html table using expression notation "$ { }". I have skipped those tags here -->
								<button id="back" class="btn btn-default"
									name="_eventId_backToCollectShippingDetail">Back</button>
								<button type="submit" class="btn btn-success"
									name="_eventId_orderConfirmed">
									Confirm   <span class="glyphicon glyphicon-chevron-right"></span>
								</button>
								<button id="btnCancel" class="btn btn-default"
									name="_eventId_cancel">Cancel</button>
							
						</div>
					</div>
				</div>
			</form:form>
		</div>
	</div>
</body>
</html>