<!-- %@page import= %>  connessione al DB -->
<%@page import="model.*" %>
<%@page import="control.*" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
   <!--   
    User user = (User) request.getSession().getAttribute("user");  //recupero l'attributo dalla sessione dell'utente
    if(user!=null){  //se l'user appartiene alla sessione
    	request.setAttribute("user", user);   //lo aggiunge agli attributi della richiesta
    }
    	AGGIUNGERE CODICE DAO PER VEDERE TUTTI I PRODOTTI
    %>-->
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
			if(!products.isEmpty()){  //se ci sono prodotti
				for(Product p: products){ //stampa per tutti i prodotti %>
					<div class="col-md-3 my-3">
					<div class="card w-100" style="width: 18rem;">
	  					<img src="images<%= p.getImage() %>" class="card-img-top" alt="...">
	  						<div class="card-body">
							    <h5 class="card-title"> <%= p.getName() %></h5>
							    <h6 class="price"> Prezzo: <%= p.getPrice() %></h6>
							    <h6 class="category"> Categoria: <%= p.getCategory() %></h6>
							    <!-- <p class="card-text">Some quick example text to build on the card title and make up the bulk of the card’s content.</p> -->
							    <div class="mt-3 d-flex gap-3 align-items-center">>
							    	<a href="#" class="btn btn-primary ">Aggiungi al carrello</a>
							    	<a href="#" class="btn btn-primary ">Compra ora</a>
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