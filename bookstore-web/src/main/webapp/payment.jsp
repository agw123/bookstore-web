<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>

<!DOCTYPE html>
<html:html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Bookstore Web Application</title>
    <!-- Bootstrap CSS -->
    <link rel="stylesheet"
          href="https://cdn.jsdelivr.net/npm/bootstrap@4.3.1/dist/css/bootstrap.min.css"
          integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T"
          crossorigin="anonymous">
</head>
<body>
    <div class="container">
        <div class="row justify-content-center">
            <div class="col-10 my-5">
                <h1>Payment</h1>
                <main>
                    <div class="py-5 text-center">
                        <h2>Checkout form</h2>
                        <p class="lead">Please fill out the form to place the order</p>
                    </div>

                    <div class="row g-5">
                        <div class="col-md-5 col-lg-4 order-md-last">
                            <h4 class="d-flex justify-content-between align-items-center mb-3">
                                <span class="text-primary">Your cart</span> 
                                <span class="badge bg-primary rounded-pill">3</span>
                            </h4>
                            <ul class="list-group mb-3">
                                <li class="list-group-item d-flex justify-content-between lh-sm">
                                    <div>
                                        <h6 class="my-0">Product name</h6>
                                        <small class="text-muted">Brief description</small>
                                    </div> 
                                    <span class="text-muted">$12</span>
                                </li>
                                <li class="list-group-item d-flex justify-content-between lh-sm">
                                    <div>
                                        <h6 class="my-0">Second product</h6>
                                        <small class="text-muted">Brief description</small>
                                    </div> 
                                    <span class="text-muted">$8</span>
                                </li>
                                <li class="list-group-item d-flex justify-content-between lh-sm">
                                    <div>
                                        <h6 class="my-0">Third item</h6>
                                        <small class="text-muted">Brief description</small>
                                    </div> 
                                    <span class="text-muted">$5</span>
                                </li>
                                <li class="list-group-item d-flex justify-content-between">
                                    <span>Total (EUR)</span> <strong>$20</strong>
                                </li>
                            </ul>
                        </div>
                        <div class="col-md-7 col-lg-8">
                            <h4 class="mb-3">Billing address</h4>
                            <html:form action="/payment" styleClass="needs-validation">
                                <div class="row g-3">
                                    <div class="col-sm-6">
                                        <label for="firstName" class="form-label">First name</label>
                                        <html:text property="firstName" styleId="firstName" styleClass="form-control"/>
                                        <div class="invalid-feedback">Valid first name is required.</div>
                                    </div>

                                    <div class="col-sm-6">
                                        <label for="lastName" class="form-label">Last name</label>
                                        <html:text property="lastName" styleId="lastName" styleClass="form-control"/>
                                        <div class="invalid-feedback">Valid last name is required.</div>
                                    </div>

                                    <div class="col-12">
                                        <label for="username" class="form-label">Username</label>
                                        <div class="input-group has-validation">
                                            <span class="input-group-text">@</span>
                                            <html:text property="username" styleId="username" styleClass="form-control"/>
                                            <div class="invalid-feedback">Your username is required.</div>
                                        </div>
                                    </div>

                                    <div class="col-12">
                                        <label for="email" class="form-label">Email <span class="text-muted">(Optional)</span></label>
                                        <html:text property="email" styleId="email" styleClass="form-control" placeholder="you@example.com"/>
                                        <div class="invalid-feedback">Please enter a valid email address for shipping updates.</div>
                                    </div>

                                    <div class="col-12">
                                        <label for="address" class="form-label">Address</label>
                                        <html:text property="address" styleId="address" styleClass="form-control" placeholder="1234 Main St" required="true"/>
                                        <div class="invalid-feedback">Please enter your shipping address.</div>
                                    </div>

                                    <div class="col-12">
                                        <label for="address2" class="form-label">Address 2 <span class="text-muted">(Optional)</span></label>
                                        <html:text property="address2" styleId="address2" styleClass="form-control" placeholder="Apartment or suite"/>
                                    </div>

                                    <div class="col-md-5">
                                        <label for="country" class="form-label">Country</label>
                                        <html:select property="country" styleId="country" styleClass="form-select" required="true">
                                            <html:options value="countryOptions" />
                                        </html:select>
                                        <div class="invalid-feedback">Please select a valid country.</div>
                                    </div>

                                    <div class="col-md-4">
                                        <label for="state" class="form-label">State</label>
                                        <html:select property="state" styleId="state" styleClass="form-select" required="true">
                                            <html:options value="stateOptions" />
                                        </html:select>
                                        <div class="invalid-feedback">Please provide a valid state.</div>
                                    </div>

                                    <div class="col-md-3">
                                        <label for="zip" class="form-label">Zip</label>
                                        <html:text property="zip" styleId="zip" styleClass="form-control" required="true"/>
                                        <div class="invalid-feedback">Zip code required.</div>
                                    </div>
                                </div>

                                <hr class="my-4">

                                <div class="form-check">
                                    <html:checkbox property="sameAddress" styleId="sameAddress" styleClass="form-check-input"/>
                                    <label class="form-check-label" for="sameAddress">Shipping address is the same as my billing address</label>
                                </div>

                                <div class="form-check">
                                    <html:checkbox property="saveInfo" styleId="saveInfo" styleClass="form-check-input"/>
                                    <label class="form-check-label" for="saveInfo">Save this information for next time</label>
                                </div>

                                <hr class="my-4">

                                <h4 class="mb-3">Payment</h4>

                                <div class="my-3">
                                    <div class="form-check">
                                        <html:radio property="paymentMethod" value="credit" styleId="credit" styleClass="form-check-input" checked="true"/>
                                        <label class="form-check-label" for="credit">Credit card</label>
                                    </div>
                                    <div class="form-check">
                                        <html:radio property="paymentMethod" value="debit" styleId="debit" styleClass="form-check-input"/>
                                        <label class="form-check-label" for="debit">Debit card</label>
                                    </div>
                                    <div class="form-check">
                                        <html:radio property="paymentMethod" value="paypal" styleId="paypal" styleClass="form-check-input"/>
                                        <label class="form-check-label" for="paypal">PayPal</label>
                                    </div>
                                </div>

                                <div class="row gy-3">
                                    <div class="col-md-6">
                                        <label for="cc-name" class="form-label">Name on card</label>
                                        <html:text property="ccName" styleId="ccName" styleClass="form-control" required="true"/>
                                        <small class="text-muted">Full name as displayed on card</small>
                                        <div class="invalid-feedback">Name on card is required</div>
                                    </div>

                                    <div class="col-md-6">
                                        <label for="cc-number" class="form-label">Credit card number</label>
                                        <html:text property="ccNumber" styleId="ccNumber" styleClass="form-control" required="true"/>
                                        <div class="invalid-feedback">Credit card number is required</div>
                                    </div>

                                    <div class="col-md-3">
                                        <label for="cc-expiration" class="form-label">Expiration</label>
                                        <html:text property="ccExpiration" styleId="ccExpiration" styleClass="form-control" required="true"/>
                                        <div class="invalid-feedback">Expiration date required</div>
                                    </div>

                                    <div class="col-md-3">
                                        <label for="cc-cvv" class="form-label">CVV</label>
                                        <html:text property="ccCvv" styleId="ccCvv" styleClass="form-control" required="true"/>
                                        <div class="invalid-feedback">Security code required</div>
                                    </div>
                                </div>

                                <hr class="my-4">

                                <html:submit value="Continue to checkout" styleClass="w-100 btn btn-primary btn-lg"/>
                            </html:form>
                        </div>
                    </div>
                </main>
            </div>
        </div>
    </div>

    <script
        src="https://cdn.jsdelivr.net/npm/bootstrap@4.3.1/dist/js/bootstrap.bundle.min.js"
        integrity="sha384-9oCljG5avD8UQ4Z3OmtkAxGfXv8iCkAR4P9HQ9U8a7MCTKfo5TXBF+tpv8E/bhF2Q"
        crossorigin="anonymous"></script>
</body>
</html:html>
