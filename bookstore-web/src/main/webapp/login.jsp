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
            <div class="col-6 my-5">
                <h1>Login</h1>
                <html:form action="/login">
              
                    <div class="form-group">
                        <label for="username">Username</label>
                        <html:text property="username" styleId="username" styleClass="form-control"/>
                    </div>
                    <div class="form-group">
                        <label for="password">Password</label>
                        <html:password property="password" styleId="password" styleClass="form-control"/>
                  
                    </div>
                    <html:submit value="Login" styleClass="btn btn-primary"/>
                </html:form>
            </div>
        </div>
    </div>
    <script
        src="https://cdn.jsdelivr.net/npm/bootstrap@4.3.1/dist/js/bootstrap.bundle.min.js"
        integrity="sha384-9oCljG5avD8UQ4Z3OmtkAxGfXv8iCkAR4P9HQ9U8a7MCTKfo5TXBF+tpv8E/bhF2Q"
        crossorigin="anonymous"></script>
</body>
</html:html>
