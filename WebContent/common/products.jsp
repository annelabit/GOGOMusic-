<%@page import="java.text.DecimalFormat"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@page import="model.*"%>
<%@page import="control.*"%>
<%@page import="java.util.ArrayList"%>
<%
User user = (User) request.getAttribute("user"); //recupero l'attributo dalla sessione dell'utente
if (user != null) { //se l'user appartiene alla sessione
	request.setAttribute("user", user); //lo aggiunge agli attributi della richiesta
}
DecimalFormat df = new DecimalFormat("#0.00");

ArrayList<Cart> cart = (ArrayList<Cart>) session.getAttribute("cart_list");
if (cart != null) {
	request.setAttribute("cart_list", cart);
}

ArrayList<Product> products = (ArrayList<Product>) request.getAttribute("products");
request.setAttribute("products", products);

String category = (String) request.getParameter("category");
if (category == null)
	category = "";
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



<title>GOGOMusic!</title>
</head>
<body>

	<%@include file="/include/navbar.jsp"%>

	<div class="small-container">
		<div class="row row-2">
			<h2 class="title-products">Concerti</h2>
			<select id="category">
				<option <%if (category == "") {%> selected="selected" <%}%>
					value="default">Default</option>
				<option <%if (category.equals("pop")) {%> selected="selected" <%}%>
					value="pop">Pop</option>
				<option <%if (category.equals("rock")) {%> selected="selected" <%}%>
					value="rock">Rock</option>
				<option <%if (category.equals("rap")) {%> selected="selected" <%}%>
					value="rap">Rap</option>
				<option <%if (category.equals("latino")) {%> selected="selected"
					<%}%> value="latino">Latino</option>
			</select>
		</div>

		<div class="row-products">


			<%
			//int elements = 0;
			for (Product p : products) {

				if (p.getCategory().toLowerCase().equals(category) || category.equals("")) {

					//if (elements++ == 12)
				//break;
			%>



			<div class="col-4-products">
				<a
					href="<%=request.getContextPath()%>/common/product-details?eventId=<%=p.getId()%>&showId=<%=p.getShows().getFirst().getId()%>"><img
					class="card-img-top"
					src="<%=request.getContextPath()%>/images/artisti/<%=p.getImage()%>.png"></a>
				<!-- inserire collegamento pagina singola -->
				<h4><%=p.getName()%></h4>

				<%
				double min = p.getMinPrice();
				double max = p.getMaxPrice();
				if (min == 0 || max == 0) {
				%>

				<p style="color: red">Sold out</p>

				<%
				} else {
				%>

				<p>
					Prezzo:
					<%=df.format(min)%>€-<%=df.format(max)%>€
				</p>
				<p>
					Luogo:
					<%=p.getLocation()%></p>


				<%
				}
				%>
			</div>
			<%
			}
			}
			%>


		</div>




	</div>
	<%@include file="/include/footer.jsp"%>

	<script
		src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
	<script
		src="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/4.1.3/js/bootstrap.bundle.min.js"></script>
	<script src="<%=request.getContextPath()%>/scripts/javascript.js"></script>
	<script src="<%=request.getContextPath()%>/scripts/category.js"></script>
</body>
</html>