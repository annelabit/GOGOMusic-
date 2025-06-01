<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@page import="model.*" %>
    <%@page import="control.*" %>
    <%@page import="java.util.*" %>
        <% 
    User user = (User) request.getSession().getAttribute("user");
    if(user!=null){
    	response.sendRedirect("index.jsp"); //l'utente ha già fatto log in, non deve farlo nuovamente
    	                                    //la pagina non sarà visibile
    }
    
    ArrayList<Cart> cart = (ArrayList<Cart>) session.getAttribute("cart_list");
    if(cart != null){
    	request.setAttribute("cart_list", cart);
    }
    
    %>
     
<!DOCTYPE html>
<html>
<head>
<title>Login</title>
<%@include file="includes/head.jsp" %>
<link rel="stylesheet" type="text/css" href="styles/style.css">
</head>
<body>
<%@include file="includes/navbar.jsp" %>

<div class="container">
<div class="card w-50 mx-auto my-4"> <!-- mx-auto== centro, my-5 == allieamento -->
<div class="card-header text-center"> User Login</div>
<div class="card-body">
<form action="LoginServlet" method="post">
  <div class="mb-3">
    <label for="InputUsername" class="form-label">Username</label>
    <input type="text" class="form-control" name="login-username" placeholder="Enter Your Username" required>
  </div>
  <div class="mb-3">
    <label for="InputPassword1" class="form-label">Password</label>
    <input type="password" class="form-control" name="login-password" placeholder="*******" required>
  </div>
  <button type="submit" class="btn btn-primary">Submit</button>
</form>

<%@include file="includes/footer.jsp" %>
</body>
</html>