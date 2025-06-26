<%@page import="java.text.DecimalFormat"%>
<%@page import="model.*"%>
<%@page import="control.*"%>
<%@page import="java.util.*"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
User user = (User) request.getSession().getAttribute("user");
if (user != null) {
	//response.sendRedirect("index.jsp"); //l'utente ha già fatto log in, non deve farlo nuovamente
	//la pagina non sarà visibile
}
//questo carrello contiene solo gli id
ArrayList<Cart> cart = (ArrayList<Cart>) session.getAttribute("cart_list");

//questo carrello contiene solo i prodotti scelti
ArrayList<Cart> cartProducts = null;

DecimalFormat df = new DecimalFormat("#0.00");
double totalPrice = (Double) request.getAttribute("totalPrice");

//totale per ogni cart c
ArrayList<Double> total = (ArrayList<Double>) request.getAttribute("total");

ArrayList<String> dates = (ArrayList<String>) request.getAttribute("dates");
ArrayList<String> times = (ArrayList<String>) request.getAttribute("times");
ArrayList<String> categories = (ArrayList<String>) request.getAttribute("categories");

request.setAttribute("cart_list", cart);
request.setAttribute("total", totalPrice);
ArrayList<Product> products = (ArrayList<Product>) request.getAttribute("products");
request.setAttribute("products", products);
%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<link rel="icon" href="images/loghi/simple-logo.png" type="image/png">
<link rel="preconnect" href="https://fonts.googleapis.com">
<link rel="preconnect" href="https://fonts.gstatic.com">
<link rel="stylesheet"
	href="https://fonts.googleapis.com/css2?family=Poppins:ital,wght@0,100;0,200;0,300;0,400;0,500;0,600;0,700;0,800;0,900;1,100;1,200;1,300;1,400;1,500;1,600;1,700;1,800;1,900&display=swap">
<link rel="stylesheet"
	href="https://cdn.jsdelivr.net/npm/@fortawesome/fontawesome-free@6.6.0/css/fontawesome.min.css">
<link rel="stylesheet" href="font-awesome/css/font-awesome.min.css">
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/styles/Poppins.css">
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/styles/slider.css">
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/css/bootstrap.min.css"
	rel="stylesheet">
<script
	src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.bundle.min.js"></script>
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/styles/stylesheet.css">

<title>GOGOMusic!- Carrello</title>

<style>
body {
	background-color: #FFFAFF;
}
</style>
</head>
<body>

	<%@include file="/include/navbar.jsp"%>


	<!-- dettagli prodotti nel carrello -->
	<div class="small-container cart-page">
		<table>
			<tr>
				<th>Prodotto</th>
				<th>Tipologia</th>
				<th>Quantità </th>
				<th>Totale</th>
			</tr>

			<%
			int i = 0;
			if (cart != null && !cart.isEmpty()) {

				for (Cart c : cart) {

					
			%>

			<tr>
				<td>
					<div class="cart-info">
						<img
							src="<%=request.getContextPath()%>/images/artisti/<%=c.getImage()%>.png">
						<div class="text-container">
							<p><%=c.getName()%></p>
							<small>Quantità: <%=c.getQuantity()%></small> <small>Data:
								<%=dates.get(i)%>, <%=times.get(i)%></small>
							<!-- <small>Prezzo: <=pDao.getPriceForSelected(c.getSeatIds(), c.getShowId())  %></small> -->
						</div>
					</div>
				</td>

				<td><%=categories.get(i)%></td>

				<td><a
					href="<%=request.getContextPath()%>/common/remove-from-cart?id=<%=c.getId()%>">Rimuovi</a></td>
				<td><%=df.format(total.get(i))%></td>
			</tr>

			<%
			i++;
			}
			}
			%>
			<%
			
			%>
		</table>


		<div class="total-price">
			<table>
				<tr>
					<td>Subtotale</td>
					<td><%=df.format(totalPrice)%>$</td>
				</tr>
				<tr>
					<td>Tasse</td>
					<td><%=df.format(4.30)%>$</td>
				</tr>
				<tr>
					<td>Totale</td>
					<td><%=df.format(totalPrice + 4.30)%>$</td>
				</tr>
			</table>
		</div>

		<button class="btn" id="checkoutbtn">
			<%
			if (user == null) {
			%>
			<a href="<%=request.getContextPath()%>/common/login.jsp">Checkout</a>
			<%
			} else {
			%>
			<a href="<%=request.getContextPath()%>/common/checkout.jsp">Checkout</a>

			<%
			}
			%>
		</button>
		<button class="btn" id="emptycart">
			<a href="<%=request.getContextPath()%>/common/empty-cart">Svuota
				Carrello</a>
		</button>

	</div>

	<%@include file="/include/footer.jsp"%>

	<script
		src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
	<script
		src="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/4.1.3/js/bootstrap.bundle.min.js"></script>
	<script src="<%=request.getContextPath()%>/scripts/javascript.js"></script>
	<script src="https://kit.fontawesome.com/b53f3cfd48.js"></script>
</body>
</html>