<%@page import="dao.*"%>
<%@page import="model.*" %>
<%@page import="control.*" %>
<%@page import="java.util.*" %>
<%@page import="java.text.DecimalFormat"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
    ShowSeatDao showSeatDao = new ShowSeatDao(DBConnection.getConnection());
    int showId = Integer.parseInt(request.getParameter("showId"));
    ArrayList<ShowSeat> seats = showSeatDao.getSeatsForShow(showId);
    DecimalFormat df = new DecimalFormat("#0.00");
    
	for (ShowSeat s : seats) {
    	if (s.isAvailable() == 1) {
%>
    	<button class="button-1" id="<%=s.getSeatId()%>" role="button" onclick="selectSeat(<%=s.getSeatId()%>)"> <%=df.format(s.getPrice())%>€ </button>
<%
    	} else {
%>
    	<button disabled class="button-2" role="button">N.A.</button>
<%
    	}
	}
%>