<%@page import="dao.*"%>
<%@page import="model.*" %>
<%@page import="control.*" %>
<%@page import="java.util.*" %>
<%@page import="java.text.DecimalFormat"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
		
		<% 
			ProductDao pDao = new ProductDao(DBConnection.getConnection());
		 	ArrayList<Product> products = pDao.getProducts();
		 	SeatDao sDao = new SeatDao(DBConnection.getConnection());
		    ShowDao showDao = new ShowDao(DBConnection.getConnection());
		    LocationDao lDao = new LocationDao(DBConnection.getConnection());
		    DecimalFormat df = new DecimalFormat("#0.00");
		    
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
	  					<img src="<%= request.getContextPath() %>/images/<%= p.getImage()%>" class="card-img-top" alt="...">
	  						<div class="card-body">
							    <h5 class="card-title"><%= p.getName() %></h5>
							     <%
									double min = showDao.getMinimumPrice(p.getId());
							    	double max = showDao.getMaximumPrice(p.getId());
							    	if (min==0||max==0){
							   	%>
							    	<h6 class="price" style = "color:red"> Sold out </h6>
							    <%
							    	}
							    	else{
							    %>
							    
							    	<h6 class="price"> <%= df.format(min) %>€-<%= df.format(max)%>€ </h6>
							    <% 
							    	} 
							    %>
							    <h6 class="venue"> <%= lDao.getEventLocation(p.getVenueId()).getVenue()%></h6>
							    <h6 class="category"> <%= p.getCategory() %></h6>
							    <div class="mt-3 d-flex gap-3 align-items-center"><a href="<%= request.getContextPath() %>/common/seatSelection.jsp?venue_id=<%=p.getVenueId()%>&pId=<%=p.getId() %>&showId=<%=showDao.getShows(p.getId()).getFirst().getId()%>" class="btn btn-dark">Aggiungi al carrello</a>
							    	<a href="<%= request.getContextPath() %>/common/buy-now?quantity=1&id=<%=p.getId()%>" class="btn btn-primary">Compra ora</a>
							    </div>
	  						</div>
						</div>
					</div>
					
				<%}
				}
			}
		%>