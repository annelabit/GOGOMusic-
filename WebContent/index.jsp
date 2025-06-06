 <!-- %@page import= %> connessione al DB -->
<%@page import="dao.ProductDao"%>
<%@page import="java.util.ArrayList"%>
<%@page import="model.*" %>
<%@page import="control.*" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <% 
    User user = (User) request.getSession().getAttribute("user");  //recupero l'attributo dalla sessione dell'utente
    if(user!=null){  //se l'user appartiene alla sessione
    	request.setAttribute("user", user);   //lo aggiunge agli attributi della richiesta
    }
    
    ProductDao pDao = new ProductDao(DBConnection.getConnection());
    ArrayList<Product> products = pDao.getProducts();
    
    
    %>
<!DOCTYPE html>
<html>
<head>
<title>GOGOMusic!</title>
<%@include file="includes/head.jsp" %>
<link rel="stylesheet" type="text/css" href="styles/style.css">
</head>
<body>
<h1>Welcome to GOGOMusic!</h1>
<%@include file="includes/navbar.jsp" %>

<%@include file="includes/footer.jsp" %>
</body>
</html>