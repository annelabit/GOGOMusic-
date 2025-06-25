<%@page import="java.text.DecimalFormat"%>
<%@page import="dao.*"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@page import="model.*"%>
<%@page import="control.*"%>
<%@page import="java.util.ArrayList"%>
<%
User user = (User) request.getAttribute("user");

ArrayList<Cart> cart = (ArrayList<Cart>) session.getAttribute("cart_list");
if (cart != null) {
	request.setAttribute("cart_list", cart);
}

DecimalFormat df = new DecimalFormat("#0.00");
Product p = (Product) request.getAttribute("product");
Location location = (Location) request.getAttribute("location");
ArrayList<ShowSeat> seats = (ArrayList<ShowSeat>) request.getAttribute("seats");
ArrayList<String> categories = (ArrayList<String>) request.getAttribute("categories");
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
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/styles/seatSelectionStyle.css">

<title>GOGOMusic!</title>
</head>
<body>

	<%@include file="/include/navbar.jsp"%>

	<!-- dettagli del singolo concerto  -->

	<div class="small-container single-product">
		<div class="row">
			<div class="col-2">
				<img
					src="<%=request.getContextPath()%>/images/artisti/<%=p.getImage()%>t.png"
					class="uniform-img" width="100%" id="product-img">

				<div class="small-img-row">
					<div class="small-img-col">
						<img
							src="<%=request.getContextPath()%>/images/artisti/<%=p.getImage()%>t.png"
							width="100%" class="small-img">
					</div>
					<div class="small-img-col">
						<img
							src="<%=request.getContextPath()%>/images/artisti/<%=p.getImage()%>t1.png"
							width="100%" class="small-img">
					</div>
					<div class="small-img-col">
						<img
							src="<%=request.getContextPath()%>/images/artisti/<%=p.getImage()%>t2.png"
							width="100%" class="small-img">
					</div>
				</div>


			</div>
			<div class="col-2">
				<p>Home</p>
				<h1><%=p.getName()%></h1>

				<%
				if (p.getMinPrice() == 0 || p.getMaxPrice() == 0) {
				%>

				<h4 style="color: red">Sold out</h4>


				<%
				} else {
				%>

				<h4><%=df.format(p.getMinPrice())%>-<%=df.format(p.getMaxPrice())%>$
				</h4>

				<%
				}
				%>


				<h3><%=p.getLocation()%></h3>
				<div class="button-group">
					<button id="showImageBtn">Visualizza mappa</button>

					<div id="imageOverlay" class="overlay">
						<span class="close-btn">&times;</span> <img id="fullscreenImage"
							class="overlay-image" src="images/stadi/<%=location.getImage()%>">
					</div>

					<select id="selectbtn" onchange="loadSeats()">
						<option>Seleziona settore</option>

						<%
						for (String c : categories) {
						%>
						<option value=<%=c%>>
							<%=c%>
						</option>
						<%
						}
						%>
					</select> 
					<select id="showSelect" onchange="loadSeats()">

						<%
						for (Show s : p.getShows()) {
						%>

						<option value=<%=s.getId()%>>
							<%=s.getDate()%>,
							<%=s.getTime()%>
						</option>

						<%
						}
						%>
					</select>
				</div>

				<h3>Dettagli concerto</h3>
				<p>Billie Eilish torna in Italia dopo ben 6 anni di assenza per
					il tour del suo terzo album intitolato "Hit me Hard and Soft" che
					conta oltre 7 miliardi di stream.</p>


				<!-- SELEZIONE POSTI -->
				<div class="grid-container"></div>



				<!-- link viene aggiunto nel js -->
				<a href="#" data-show-id="<%=p.getShows().getFirst().getId()%>"
					data-venue-id=<%=p.getVenueId()%> id="buy-button"
					class="btn btn-primary btn-sm"> Prenota e aggiungi al carrello
				</a>





			</div>
		</div>
	</div>


	<%@include file="/include/footer.jsp"%>

	<script
		src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
	<script
		src="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/4.1.3/js/bootstrap.bundle.min.js"></script>
	<script src="<%=request.getContextPath()%>/scripts/javascript.js"></script>
	<script src="<%=request.getContextPath()%>/scripts/seatScript.js"></script>
</body>
</html>