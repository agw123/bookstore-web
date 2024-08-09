<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html"%>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic"%>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean"%>

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
</head>
<body>
	<jsp:include page="/header.jsp" />
	<div class="container store">
		<div class="row justify-content-center">
			<div class="col-12 col-xl-8 my-5">
			<bean:write name="appTitle" scope="application" />
				<h1>
					Hello
					<bean:write name="user" property="username" scope="session" />
				</h1>
				<h2>Welcome to <bean:write name="appTitle" scope="application" /></h2>
				<div class="row">
					<logic:iterate id="product" name="booksList" scope="session">
						<div class="col-sm-4 mb-3">
							<div class="card">
								<div class="card-body">
									<h5 class="card-title"
										id="product-name-'<bean:write name='product' property='id'/>'">
										<bean:write name="product" property="title" />
									</h5>
									<p class="card-text">
										<bean:write name="product" property="author" />
									</p>
									<p class="card-text">
										Category:
										<bean:write name="product" property="category" />
									</p>
									<p class="card-text">
										Price:
										<bean:write name="product" property="price" />
									</p>
									<p class="card-text">
										Availability:
										<bean:write name="product" property="availability" />
									</p>
									<p>
										Id: <small> <bean:write name="product" property="id" /></small>
									</p>


									<html:form action="/add-to-cart.do">
										<input type="hidden" 
										name="bookId"
										value='<bean:write name='product' property='id'/>' />
										<logic:equal name="product" property="availability"
											value="true">
											<html:submit value="Add to cart" styleClass="btn btn-primary" />
										</logic:equal>
										<logic:equal name="product" property="availability"
											value="false">
											<button value="Add to cart" class="btn btn-primary disabled">Add to cart</button>
										</logic:equal>
									</html:form>
								</div>
							</div>
						</div>
					</logic:iterate>
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
