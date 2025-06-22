 <!-- %@page import= %>connessione al DB -->
<%@page import="dao.OrderDao"%>
<%@page import="model.*" %>
<%@page import="control.*" %>
<%@page import="java.util.*" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <% 
    User user = (User) request.getSession().getAttribute("user");  //recupero l'attributo dalla sessione dell'utente
    ArrayList<Order> orders = null;
    
    if(user!=null){  //se l'user appartiene alla sessione
    	request.setAttribute("user", user);   //lo aggiunge agli attributi della richiesta
    	OrderDao oDao = new OrderDao();
    	orders = oDao.userOrders(user.getIdUtente());

    } else{
    	response.sendRedirect("login.jsp");
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
<link rel="stylesheet" type="text/css" href="<%= request.getContextPath() %>/styles/style.css">
</head>
<body>
<%@include file="includes/navbar.jsp" %>

<div class="container">
	<div class="card-header my-3">Tutti gli ordini</div>
	<table class="table table-light">
		<thead>
			<tr>
				<th scope="col">Date</th>
				<th scope="col">Time</th>
				<th scope="col">Name</th>
				<th scope="col">Category</th>
				<th scope="col">Quantity</th>
				<th scope="col">Price</th>
				<th scope="col">Cancel</th>
			</tr>
		</thead>
		<tbody>
		
		<%
		if(orders!=null){
			for(Order o : orders){
			%>
			<tr>
			<!-- data cells -->
				<td><%=o.getDate()%></td>
				<td><%=o.getTime()%></td>
				<td><%=o.getName()%></td>
				<td><%=o.getCategory()%></td>
				<td><%=o.getQuantity()%></td>
				<td><%=o.getPrice()%></td>
				<td><a class="btn btn-sm btn-danger" href="<%= request.getContextPath() %>/common/cancel-order?id=<%=o.getOrderId()%>">Cancel</a></td>
			</tr>
			<%
			}
			
		}
		%>
		
		
		</tbody>
	</table>

</div>

<%@include file="includes/footer.jsp" %>
</body>
</html>