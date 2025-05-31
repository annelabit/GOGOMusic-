<!-- %@page import= %> connessione al DB -->
<%@page import="model.*" %>
<%@page import="control.*" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <% 
    User user = (User) request.getSession().getAttribute("user");  //recupero l'attributo dalla sessione dell'utente
    if(user!=null){  //se l'user appartiene alla sessione
    	request.setAttribute("user", user);   //lo aggiunge agli attributi della richiesta
    }
    %>
<!DOCTYPE html>
<html>
<head>
<title>Shopping Cart</title>
<%@include file="includes/head.jsp" %>
<link rel="stylesheet" type="text/css" href="styles/style.css">
</head>
<body>
<%@include file="includes/navbar.jsp" %>

<%@include file="includes/footer.jsp" %>
</body>
</html>