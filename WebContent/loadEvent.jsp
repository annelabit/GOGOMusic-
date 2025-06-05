<%@page import="dao.*"%>
<%@page import="model.*" %>
<%@page import="control.*" %>
<%@page import="java.util.*" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
		
		<% 
			ProductDao pDao = new ProductDao(DBConnection.getConnection());
		 	ArrayList<Product> products = pDao.getProducts();
		 	SeatDao sDao = new SeatDao(DBConnection.getConnection());
		    ShowDao showDao = new ShowDao(DBConnection.getConnection());
		    LocationDao lDao = new LocationDao(DBConnection.getConnection());
		    
		    String keyword = request.getParameter("keyword");
		    if (keyword == null) keyword = "";
		    
			if(!products.isEmpty()){ //se c'è almeno un prodotto
				
				for(Product p : products){
					if(p.getName().toLowerCase().contains(keyword.toLowerCase()) || 
							lDao.getEventLocation(p.getVenueId()).getVenue().toLowerCase().contains(keyword.toLowerCase()) ||
							lDao.getEventLocation(p.getVenueId()).getCity().toLowerCase().contains(keyword.toLowerCase())
					){
		%>
					
					<div class="col-md-3 my-3">
					<div class="card w-100" style="width: 18rem;">
	  					<img src="images/<%= p.getImage()%>" class="card-img-top" alt="...">
	  						<div class="card-body">
							    <h5 class="card-title"><%= p.getName() %></h5>
							    <h6 class="price"> <%= showDao.getMinimumPrice(p.getId())%>€-
							    	<%=showDao.getMaximumPrice(p.getId())%>€ </h6>
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
			}
		%>