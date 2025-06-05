 <!-- %@page import= %> connessione al DB -->
<%@page import="dao.*"%>
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
    SeatDao sDao = new SeatDao(DBConnection.getConnection());
    ShowDao showDao = new ShowDao(DBConnection.getConnection());
    ArrayList<Product> products = pDao.getProducts();
    LocationDao lDao = new LocationDao(DBConnection.getConnection());
    
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

<!-- <script src="scripts/navbar.js"></script> --><!-- ?? -->

</head>
<body>
<h1>Welcome to GOGOMusic!</h1>
	<%@include file="includes/navbar.jsp" %>

	<div class="container" id="event-container">
		<div class="card-header my-3"> Tutti i prodotti</div>
		<div class="row">
		
		<% 
			if(!products.isEmpty()){ //se c'è almeno un prodotto
				
				for(Product p : products){
					
					//showDao.getShow()
		%>
					
					<div class="col-md-3 my-3">
					<div class="card w-100" style="width: 18rem;">
	  					<img src="images/<%= p.getImage()%>" class="card-img-top" alt="...">
	  						<div class="card-body">
							    <h5 class="card-title"><%= p.getName() %></h5>
							    <h6 class="price"> <%= showDao.getMinimumPrice(p.getId())%>€-<%=showDao.getMaximumPrice(p.getId())%>€ </h6>
							    <h6 class="venue"> <%= lDao.getEventLocation(p.getVenueId()).getVenue()%></h6>
							    <h6 class="category"> <%= p.getCategory() %></h6>
							    <div class="mt-3 d-flex gap-3 align-items-center"><a href="seatSelection.jsp?venue_id=<%=p.getVenueId()%>&pId=<%=p.getId() %>&showId=<%=showDao.getShows(p.getId()).getFirst().getId()%>" class="btn btn-dark">Aggiungi al carrello</a>
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