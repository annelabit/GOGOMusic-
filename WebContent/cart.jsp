<!-- %@page import= %>connessione al DB -->
<%@page import="java.text.DecimalFormat"%>
<%@page import="model.*"%>
<%@page import="control.*"%>
<%@page import="dao.*"%>
<%@page import="java.util.*"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
	 <% 
	User user = (User) request.getSession().getAttribute("user");
    if(user!=null){
    	//response.sendRedirect("index.jsp"); //l'utente ha già fatto log in, non deve farlo nuovamente
    	                                    //la pagina non sarà visibile
    }
    
    //questo carrello contiene solo gli id
    ArrayList<Cart> cart = (ArrayList<Cart>) session.getAttribute("cart_list");
    
    //questo carrello contiene solo i prodotti scelti
    ArrayList<Cart> cartProducts = null;
    ProductDao pDao = new ProductDao(DBConnection.getConnection());
    
    if(cart != null){
    	
    	//cartProducts = pDao.getCartProducts(cart);
    	double totalPrice = pDao.getTotalPrice(cart);
    	request.setAttribute("cart_list", cart);
    	request.setAttribute("total", totalPrice);
    }
    
    %>
    
<!DOCTYPE html>
<html>
<head>
<title>Shopping Cart</title>
<%@include file="includes/head.jsp"%>
<link rel="stylesheet" type="text/css" href="styles/style.css">
</head>
<body>
	<%@include file="includes/navbar.jsp"%>

	<div class="container">
		<!-- p=padding,  m=margin -->
		<div class="d-flex justify-content-between py-3">
	
	<%
    		DecimalFormat df = new DecimalFormat("#0.00");
			//pDao somma i prezzi per tutti i posti assegnati
    		double totalPrice = (cart != null) ? pDao.getTotalPrice(cart) : 0.0;
	%>
		<h3>Prezzo Totale: €<%= df.format(totalPrice) %></h3>
			<a class="btn btn-primary mx-3" href="checkout"> Check out </a>
		</div>
		<table class="table table-light">
			<thead>
				<tr>
					<th scope="col">Name</th>
					<th scope="col">Category</th>
					<th scope="col">Price</th>
					<th scope="col">Seats</th>
					<th scope="col">Buy Now</th>
					<th scope="col">Cancel</th>
				</tr>
			</thead>
			<tbody>
			
			<%
			//ArrayList<Cart> cartList = (ArrayList<Cart>) session.getAttribute("cart_list");
				if (cart != null && !cart.isEmpty()) {
				
					//for(Cart c : cartProducts){
						for(Cart c : cart){
			%>
						<tr>
						<!--  table row -->
						<td><%= c.getName() %></td>
						<td><%= c.getCategory() %></td>
						<td><%= pDao.getPriceForSelected(c.getSeatIds(),c.getVenueId(),c.getId()) %>€</td>
					
						<td>
					
				<%
					
						ArrayList<Integer> seatList = c.getSeatIds();
				
						if(seatList != null && !seatList.isEmpty()){
							out.print(seatList.toString());
						} else{ 
							out.print("Nessun posto selezionato");
						}
					
				%>
					
						</td>
				
					<td>
						<form action="buy-now" method="post" class="form-inline">
						<div class="input-group"> 
							<input type="hidden" name="id" value="<%= c.getId() %>" class="form-input">
							<div class="d-flex align-items-center gap-2">
								<a class="btn btn-sm btn-incre" href="quantity?action=inc&id=<%=c.getId()%>">
								<i class="fas fa-plus-square"></i></a> 
									 <input type="text"name="quantity" class="form-control text-center" value="<%=c.getQuantity()%>" readonly> 
									 <a class="btn btn-sm btn-decre" href="quantity?action=dec&id=<%=c.getId()%>">
									 <i class="fas fa-minus-square"></i></a>
							</div>
							</div>
							<button type="submit" class="btn btn-primary btn-sm"> Acquista </button>
						</form>
					</td>
					<td>
						<a class="btn btn-sm btn-danger" href="remove-from-cart?id=<%=c.getId()%>"> Rimuovi </a>
					</td>
				</tr>
					
			<%
				}
				
				
			} %>
			
				
			</tbody>
		</table>
	</div>


	<%@include file="includes/footer.jsp"%>
</body>
</html>