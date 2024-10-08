<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean"%>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html"%>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<%@ page import="java.util.Map"%>
<%@ page import="java.util.HashMap"%>
<%@ page import="form.BookForm"%>

<!DOCTYPE html>
<html:html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>Simple Store</title>
<!-- Bootstrap CSS -->
<link rel="stylesheet"
	href="https://cdn.jsdelivr.net/npm/bootstrap@4.3.1/dist/css/bootstrap.min.css"
	integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T"
	crossorigin="anonymous">
<style>
.back-arrow {
	font-weight: bold;
	font-size: 20px;
}
</style>
</head>
<body>
	<div class="container cart">
		<div class="mt-5">
			<a class="back-arrow" href="javascript:history.back()"><span>&#8592;</span>Back</a>
		</div>
		<div class="row justify-content-center">
			<div class="col-7 my-5">
				<div class="card">
					<div class="card-body">
						<h5 class="card-title">Your cart</h5>
						<logic:notEmpty name="productsInCart">
							<table class="table table-hover">
								<thead>
									<tr>
										<th scope="col">Id</th>
										<th scope="col">Title</th>
										<th scope="col">Author</th>
										<th scope="col">Category</th>
										<th scope="col">Price</th>
										<th scope="col">Quantity</th>
									</tr>
								</thead>
								<tbody>
									<logic:iterate id="product" name="productsInCart"
										indexId="index">
										<tr>
											<th scope="row"><bean:write name="product" property="id" /></th>
											<td><bean:write name="product" property="title" /></td>
											<td><bean:write name="product" property="author" /></td>
											<td><bean:write name="product" property="category" /></td>
											<td><bean:write name="product" property="price" /></td>
											<td>
												<%
												BookForm productObj = (BookForm) product;
												Map<Integer, Integer> quantityMap = (Map<Integer, Integer>) request.getAttribute("quantityMap");
												Long productId = productObj.getId();
												Integer quantity = quantityMap != null ? quantityMap.getOrDefault(productId, 0) : 0;
												out.print(quantity);
												%>
											</td>
									</logic:iterate>

								</tbody>
							</table>

							<hr />

							<div class="row justify-content-center">
								<div class="col-6 my-5">
									<div class="d-flex justify-content-between">
										<html:form action="cancel-order">
											<html:submit value="Cancel order"
												styleClass="btn btn-primary" />
										</html:form>
										<%-- 										<html:form action="confirm-order">
											<html:submit value="Confirm order"
												styleClass="btn btn-primary" />
										</html:form> --%>
										<button class="btn btn-primary">
											<a href="payment.jsp">Confirm order</a>
										</button>
									</div>
								</div>
							</div>
						</logic:notEmpty>
						<logic:empty name="productsInCart">
							<p>Your cart is empty</p>
						</logic:empty>
					</div>
				</div>
			</div>
		</div>
	</div>
	<script
		src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"
		integrity="sha384-YvpcrYf0tY3lHB60NNkmXc5s9fDVZLESaAA55NDzOxhy9GkcIdslK1eN7N6jIeHz"
		crossorigin="anonymous"></script>
</body>
</html:html>