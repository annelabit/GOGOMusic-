<%@page import="java.text.DecimalFormat"%>
<%@page import="model.*"%>
<%@page import="control.*"%>
<%@page import="java.util.*"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
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

	<!-- carosello -->
	<div id="hero-carousel" class="carousel slide" data-bs-ride="carousel">
		<div class="carousel-indicators">
			<button type="button" data-bs-target="#hero-carousel"
				data-bs-slide-to="0" class="active" aria-current="true"
				aria-label="Slide 1"></button>
			<button type="button" data-bs-target="#hero-carousel"
				data-bs-slide-to="1" aria-label="Slide 2"></button>
			<button type="button" data-bs-target="#hero-carousel"
				data-bs-slide-to="2" aria-label="Slide 3"></button>
		</div>

		<div class="carousel-inner">
			<div class="carousel-item active c-item">
				<img src="images/foto-carosello/crc.png" class="d-block w-100 c-img"
					alt="Slide 1">
				<div class="carousel-caption top-0 mt-4">
					<p class="text-uppercase fs-3 mt-5">Stadio Olimpico</p>
					<p class="display-1 fw-bolder text-capitalize">Midwest Princess
						Tour</p>
					<button class="btn px-4 py-2 fs-5 mt-5">
						<a
							href="<%=request.getContextPath()%>/common/product-details?eventId=3&showId=3">Compra
							ora
					</button>
					</a>
				</div>
			</div>
			<div class="carousel-item c-item">
				<img src="images/foto-carosello/lgc.png" class="d-block w-100 c-img"
					alt="Slide 2">
				<div class="carousel-caption top-0 mt-4">
					<p class="text-uppercase fs-3 mt-5">Unipol Arena</p>
					<p class="display-1 fw-bolder text-capitalize">The Mayhem Ball</p>
					<button class="btn px-4 py-2 fs-5 mt-5" data-bs-toggle="modal"
						data-bs-target="#booking-modal">
						<a
							href="<%=request.getContextPath()%>/product-details?eventId=2&showId=2">Compra
							ora
					</button>
					</a>
				</div>
			</div>
			<div class="carousel-item c-item">
				<img src="images/foto-carosello/tsc1.png"
					class="d-block w-100 c-img" alt="Slide 3">
				<div class="carousel-caption top-0 mt-4">
					<p class="text-uppercase fs-3 mt-5">Stadio San Siro</p>
					<p class="display-1 fw-bolder text-capitalize">The Eras Tour</p>
					<button class="btn px-4 py-2 fs-5 mt-5" data-bs-toggle="modal"
						data-bs-target="#booking-modal">
						<a
							href="<%=request.getContextPath()%>/common/product-details?eventId=6&showId=22">Compra
							ora
					</button>
					</a>
				</div>
			</div>
		</div>
		<button class="carousel-control-prev" type="button"
			data-bs-target="#hero-carousel" data-bs-slide="prev">
			<span class="carousel-control-prev-icon" aria-hidden="true"></span> <span
				class="visually-hidden">Previous</span>
		</button>
		<button class="carousel-control-next" type="button"
			data-bs-target="#hero-carousel" data-bs-slide="next">
			<span class="carousel-control-next-icon" aria-hidden="true"></span> <span
				class="visually-hidden">Next</span>
		</button>
	</div>

	<div class="small-container">
		<h2 class="title">Eventi proposti</h2>

		<!-- Prodotti -->
		<div class="row">

			<%
			int elements = 0;
			int i=0;
			for (Product p : products) {
				
				
				
				if (elements++ == 6)
					break;
			%>



			<div class="col-3">
				<a
					href="<%=request.getContextPath()%>/common/product-details?eventId=<%=p.getId()%>&showId=<%=p.getShows().getFirst().getId() %>"><img
					src="<%=request.getContextPath()%>/images/artisti/<%=p.getImage()%>.png"></a>
				<!-- inserire collegamento pagina singola -->
				<h4><%=p.getName()%></h4>

				<%
				if (p.getMinPrice() == 0 || p.getMaxPrice() == 0) {
				%>

				<p style="color: red">Sold out</p>

				<%
				} else {
				%>

				<p>
					Prezzo:
					<%=df.format(p.getMinPrice())%>€-<%=df.format(p.getMaxPrice())%>€
				</p>
				<p>
					Luogo:
					<%=p.getLocation()%></p>


				<%
				}
				%>
			</div>
			<%
			
			i++;
			}
			%>

		</div>


		<a href="<%=request.getContextPath()%>/common/index?products=yes"><button
				class="btn" id="productsbtn">Vedi tutti</button></a>
	</div>


	<!-- Offerte -->
	<div class="offer">
		<div class="small-container">
			<div class="row">
				<div class="col-2">
					<img src="images/billie-eilish/be.png" class="offer-img">
				</div>
				<div class="col-2" id="offerta">
					<p>Offerta esclusiva</p>
					<h1>Hit me Hard and Soft</h1>
					<small>Offerta esclusiva per uno dei concerti più venduti
						al mondo.</small> <br> <a
						href="<%=request.getContextPath()%>/common/product-details?eventId=1&showId=1"
						class="btn">Compra Ora &#8594;</a>
					<!-- inserire collegamento pagina singola -->
				</div>
			</div>
		</div>
	</div>


	<!-- categorie  -->
	<div class="small-container">
		<div class="row">
			<div class="col-4">
				<a
					href="<%=request.getContextPath()%>/common/index?category=pop&all=yes"><img
					src="<%=request.getContextPath()%>/images/generi/pop.png"> </a>
				<!-- inserire collegamento pagina con quel genere -->
				<h3>Pop</h3>
			</div>
			<div class="col-4">
				<a
					href="<%=request.getContextPath()%>/common/index?category=rock&all=yes"><img
					src="<%=request.getContextPath()%>/images/generi/rock.png"></a>
				<!-- inserire collegamento pagina con quel genere -->
				<h3>Rock</h3>
			</div>
			<div class="col-4">
				<a
					href="<%=request.getContextPath()%>/common/index?category=rap&all=yes"><img
					src="<%=request.getContextPath()%>/images/generi/rap.png"></a>
				<!-- inserire collegamento pagina con quel genere -->
				<h3>Rap</h3>
			</div>
			<div class="col-4">
				<a
					href="<%=request.getContextPath()%>/common/index?category=latino&all=yes"><img
					src="<%=request.getContextPath()%>/images/generi/latino.png"></a>
				<!-- inserire collegamento pagina con quel genere -->
				<h3>Latino</h3>
			</div>
		</div>
	</div>


	<!-- brand -->
	<div class="brands">
		<div class="small-container">
			<div class="row">
				<div class="col-5">
					<img src="<%=request.getContextPath()%>/images/loghi/coca-cola.png">
				</div>
				<div class="col-5">
					<img src="<%=request.getContextPath()%>/images/loghi/amazon.png">
				</div>
				<div class="col-5">
					<img src="<%=request.getContextPath()%>/images/loghi/mcdonald.png">
				</div>
				<div class="col-5">
					<img src="<%=request.getContextPath()%>/images/loghi/paypal.png">
				</div>
			</div>
		</div>
	</div>

	<%@include file="/include/footer.jsp"%>

	<script
		src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
	<script
		src="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/4.1.3/js/bootstrap.bundle.min.js"></script>
	<script src="<%=request.getContextPath()%>/scripts/javascript.js"></script>
</body>
</html>