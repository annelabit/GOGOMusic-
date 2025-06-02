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
    
    ArrayList<Cart> cart = (ArrayList<Cart>) session.getAttribute("cart_list");
    if(cart != null){
    	request.setAttribute("cart_list", cart);
    }
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

	<div class="container">
		<div class="card-header my-3"> Tutti i prodotti</div>
		<div class="row">
		
		<% 
			if(!products.isEmpty()){ //se c'è almeno un prodotto
				
				for(Product p : products){
		%>
					
					<div class="col-md-3 my-3">
					<div class="card w-100" style="width: 18rem;">
	  					<img src="images/<%= p.getImage()%>" class="card-img-top" alt="...">
	  						<div class="card-body">
							    <h5 class="card-title"><%= p.getName() %></h5>
							    <h6 class="price"> Prezzo: <%= p.getPrice() %>€ </h6>
							    <h6 class="category"> Categoria: <%= p.getCategory() %></h6>
							    <!-- <p class="card-text">Some quick example text to build on the card title and make up the bulk of the card’s content.</p> -->
							    <div class="mt-3 d-flex gap-3 align-items-center">
							    	<a href="add-to-cart?id=<%=p.getId()%>" class="btn btn-dark">Aggiungi al carrello</a>
							    	<a href="buy-now?quantity=1&id=<%=p.getId()%>" class="btn btn-primary">Compra ora</a>
							    </div>
	  						</div>
						</div>
					</div>
					
				<%}
				
				
			}
		%>
		
		
		
					
			</div>
		</div>
		
	<%@include file="includes/footer.jsp" %>
</body>
</html>