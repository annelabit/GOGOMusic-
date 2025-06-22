<%@page import="dao.*"%>
<%@page import="model.*" %>
<%@page import="control.*" %>
<%@page import="java.util.*" %>
<%@page import="java.text.DecimalFormat"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<link rel="icon" href="images/loghi/simple-logo.png" type="image/png">
<link rel="preconnect" href="https://fonts.googleapis.com">
<link rel="preconnect" href="https://fonts.gstatic.com">
<link rel="stylesheet"
	href="https://fonts.googleapis.com/css2?family=Poppins:ital,wght@0,100;0,200;0,300;0,400;0,500;0,600;0,700;0,800;0,900;1,100;1,200;1,300;1,400;1,500;1,600;1,700;1,800;1,900&display=swap">
<link rel="stylesheet"
	href="https://cdn.jsdelivr.net/npm/@fortawesome/fontawesome-free@6.6.0/css/fontawesome.min.css">
<link rel="stylesheet" href="font-awesome/css/font-awesome.min.css">
<link rel="stylesheet" href="<%= request.getContextPath() %>/styles/Poppins.css">
<link rel="stylesheet" href="<%= request.getContextPath() %>/styles/slider.css">
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/css/bootstrap.min.css"
	rel="stylesheet">
<script
	src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.bundle.min.js"></script>
<link rel="stylesheet" href="<%= request.getContextPath() %>/styles/stylesheet.css">
		<% 
			ProductDao pDao = new ProductDao();
		 	ArrayList<Product> products = pDao.getProducts();
		 	SeatDao sDao = new SeatDao();
		    ShowDao showDao = new ShowDao();
		    LocationDao lDao = new LocationDao();
		    DecimalFormat df = new DecimalFormat("#0.00");
		    
		    String category = request.getParameter("category");
		    String keyword = request.getParameter("keyword");
		    System.out.print(category);
		    
		    if (category == null) category = "";
		    if (keyword == null) keyword = "";
		    
		    System.out.println(keyword);
		    
			if(!products.isEmpty()){ //se c'è almeno un prodotto
				
				for(Product p : products){
					if((p.getName().toLowerCase().contains(keyword.toLowerCase()) || 
							lDao.getEventLocation(p.getVenueId()).getVenue().toLowerCase().contains(keyword.toLowerCase()) ||
							lDao.getEventLocation(p.getVenueId()).getCity().toLowerCase().contains(keyword.toLowerCase())) && 
							(p.getCategory().toLowerCase().equals(category) || category.equals("default") || category.equals(""))){
		%>
					
				<div class="col-4-products">
				<a href="productDetails.jsp?eventId=<%=p.getId()%>&showId=<%=showDao.getShows(p.getId()).getFirst().getId()%>"><img class="card-img-top" src="<%= request.getContextPath()%>/images/artisti/<%= p.getImage()%>.png"></a> <!-- inserire collegamento pagina singola -->
				<h4><%= p.getName() %></h4>
				
				<%
									
				double min = showDao.getMinimumPrice(p.getId());
				double max = showDao.getMaximumPrice(p.getId());
				if (min==0||max==0){
				
				%>
				
				<p style = "color:red">Sold out</p>
				
				<%
					}
					else{
				%>
				
				<p>Prezzo: <%= df.format(min) %>€-<%= df.format(max)%>€</p>
				<p>Luogo: <%= lDao.getEventLocation(p.getVenueId()).getVenue()%></p>
			
		
			<%
			}
				
			%>
			</div>
					
				<%}
				}
			}
		%>