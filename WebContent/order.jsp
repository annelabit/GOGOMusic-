 <!-- %@page import= %>connessione al DB -->
<%@page import="model.*" %>
<%@page import="control.*" %>
<%@page import="java.util.*" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <% 
    User user = (User) request.getSession().getAttribute("user");  //recupero l'attributo dalla sessione dell'utente
    if(user!=null){  //se l'user appartiene alla sessione
    	request.setAttribute("user", user);   //lo aggiunge agli attributi della richiesta
    }
    ArrayList<Cart> cart = (ArrayList<Cart>) session.getAttribute("cart_list");
    if(cart != null){
    	request.setAttribute("cart_list", cart);
    }
    
    %>
<!DOCTYPE html>
<html>
<head>
<title>Order</title>
<%@include file="includes/head.jsp" %>
<link rel="stylesheet" type="text/css" href="styles/style.css">
</head>
<body>
<%@include file="includes/navbar.jsp" %>

<%@include file="includes/footer.jsp" %>
</body>
</html>