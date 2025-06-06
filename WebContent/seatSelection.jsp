<%@page import="dao.*"%>
<%@page import="model.*" %>
<%@page import="control.*" %>
<%@page import="java.util.*" %>
<%@page import="java.text.DecimalFormat"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
     <% 
    	User user = (User) request.getSession().getAttribute("user");  //recupero l'attributo dalla sessione dell'utente
    	if(user!=null){  //se l'user appartiene alla sessione
        	request.setAttribute("user", user);   //lo aggiunge agli attributi della richiesta
        }
        
        SeatDao sDao = new SeatDao(DBConnection.getConnection());
        ShowSeatDao showSeatDao = new ShowSeatDao(DBConnection.getConnection());
        ShowDao showDao = new ShowDao(DBConnection.getConnection());
        DecimalFormat df = new DecimalFormat("#0.00");
        
        ArrayList<ShowSeat> seats = showSeatDao.getSeatsForShow(Integer.parseInt(request.getParameter("showId")));
        
        int pId = Integer.parseInt(request.getParameter("venue_id"));
   		int showId = Integer.parseInt(request.getParameter("showId"));
   		
     %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Seleziona posto</title>
<%@include file="includes/head.jsp" %>
<link rel="stylesheet" type="text/css" href="styles/style.css">
<link rel="stylesheet" type="text/css" href="styles/seatSelectionStyle.css">
</head>
<body>
<script src="scripts/seatScript.js"></script>
<%@include file="includes/navbar.jsp" %>

<h1>Scegli il tuo posto!</h1>

<div class="grid-container">
  
  <%
  	for(ShowSeat s : seats){
  %>
  	
  <%
  	if(s.isAvailable()==1) {
  %>
  		<button class="button-1" id="<%=s.getSeatId()%>" role="button" onclick="selectSeat(<%=s.getSeatId()%>)"> <%=df.format(s.getPrice())%>€ </button>
  <% 	
  	} else{
  %>
  	<button disabled class="button-2" role="button">N.A.</button>
  <%
  	}
  }
  %>
  
</div>

<%ArrayList<Show> shows = showDao.getShows(Integer.parseInt(request.getParameter("pId"))); %>
<p>
Seleziona spettacolo
<select id="showSelect" onchange="loadSeats()">

<% for(Show s : shows){%>

<option value=<%=s.getId()%>> <%=s.getDate() %>, <%=s.getTime() %> </option>

<% }%>
</select>
</p>

<!-- link viene aggiunto nel js -->
<a href="#" data-show-id="<%=showId%>" data-venue-id=<%=pId%> id="buy-button" class="btn btn-primary btn-sm"> Prenota e aggiungi al carrello </a>

<%@include file="includes/footer.jsp" %>
</body>
</html>