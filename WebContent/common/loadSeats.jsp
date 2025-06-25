<%@page import="dao.*"%>
<%@page import="model.*"%>
<%@page import="control.*"%>
<%@page import="java.util.*"%>
<%@page import="java.text.DecimalFormat"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
User user = (User) request.getAttribute("user");

ArrayList<Cart> cart = (ArrayList<Cart>) session.getAttribute("cart_list");
if (cart != null) {
	request.setAttribute("cart_list", cart);
}

String categoria = (String) request.getAttribute("categoria");

DecimalFormat df = new DecimalFormat("#0.00");
Location location = (Location) request.getAttribute("location");
ArrayList<Seat> seats = (ArrayList<Seat>) request.getAttribute("seats");
ArrayList<ShowSeat> showSeats = (ArrayList<ShowSeat>) request.getAttribute("showSeats");
ArrayList<String> categories = (ArrayList<String>) request.getAttribute("categories");
ArrayList<Product> products = (ArrayList<Product>) request.getAttribute("products");
request.setAttribute("products", products);
int i=0;

for (ShowSeat s : showSeats) {
	if (seats.get(i).getType().equalsIgnoreCase(categoria)) {

		if (s.isAvailable() == 1) {
%>
<button class="button-1" id="<%=s.getSeatId()%>" role="button"
	onclick="selectSeat(<%=s.getSeatId()%>)">
	<%=df.format(s.getPrice())%>€
</button>
<%
} else {
%>
<button disabled class="button-2" role="button">N.A.</button>
<%
}
}
	i++;
}
%>