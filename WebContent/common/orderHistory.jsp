<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@page import="java.text.DecimalFormat"%>
<%@page import="java.util.*"%>
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
	href="<%=request.getContextPath()%>/styles/footer.css">
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/styles/stylesheet.css">
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/styles/order.css">

<%

//NUOVO
User user = (User) request.getSession().getAttribute("user");
if (user != null) { //se l'user appartiene alla sessione
	request.setAttribute("user", user); //lo aggiunge agli attributi della richiesta
	
} else {
	response.sendRedirect("login.jsp");
}
ArrayList<Order> orders = (ArrayList<Order>) request.getAttribute("orders");
Map<Integer, String> orderAreas = (Map<Integer, String>) request.getAttribute("orderAreas");
Map<Integer, String> venueNames = (Map<Integer, String>) request.getAttribute("venueNames");
DecimalFormat df = new DecimalFormat("#0.00");
%>

<title>GOGOMusic!</title>
</head>
<body>
	<%@include file="/include/navbar.jsp"%>

	<!-- Ordini -->
	<div class="order-history-container">
		<div class="header-section">
			<h1>I Tuoi Ordini</h1>
			<p class="subtitle">Visualizza i tuoi concerti passati e futuri</p>
		</div>


		<%
		int i = 0;

		//System.out.println(orders.toString());

		if (orders != null)
			for (Order o : orders) {
				i++;
		%>
		<div class="orders-grid">
			<!-- Order 1 -->
			<div class="order-card">
				<div class="order-header">
					<div class="concert-image">
						<img
							src="<%=request.getContextPath()%>/images/artisti/<%=o.getImage()%>t.png" />
					</div>

					<div class="concert-info">
						<h3 class="concert-name"><%=o.getName()%></h3>
						<p class="artist-name"></p>
						<p class="venue-info"><%=venueNames.get(o.getVenueId())%></p>
						<p class="date-time"><%=o.getShowDate()%>
							alle
							<%=o.getShowTime()%></p>
					</div>

					<div class="ticket-summary">
						<div class="ticket-detail">
							<span class="label">Biglietti:</span> <span class="value"><%=o.getQuantity()%></span>
						</div>
						<div class="ticket-detail">
							<span class="label">Area:</span> <span class="value"><%=orderAreas.get(o.getOrderId())%></span>
						</div>
						<div class="ticket-detail">
							<span class="label">Sezione:</span> <span class="value">A1</span>
						</div>
						<div class="ticket-detail total-cost">
							<span class="label">Totale:</span> <span class="value"><%=df.format(o.getPrice())%></span>
						</div>
					</div>

					<div class="actions">
						<button class="details-btn" onclick="toggleDetails('order<%=i%>')">
							<span class="btn-text">Vedi Dettagli</span> <i class="arrow">▼</i>
						</button>
					</div>
				</div>

				<div class="order-details" id="order<%=i%>">
					<div class="details-content">
						<div class="detail-section">
							<h4>Indirizzo di Spedizione</h4>
							<div class="address-info">
								<p>
									<strong><%=o.getNome()%></strong>
								</p>
								<p><%=o.getIndirizzo()%></p>
								<p><%=o.getCittà()%>,
									<%=o.getCap()%></p>
								<p><%=o.getPaese()%></p>
							</div>
						</div>

						<div class="detail-section">
							<h4>Metodo di Pagamento</h4>
							<div class="payment-info">
								<p>
									<strong>Carta di Credito</strong>
								</p>
								<p>Mastercard</p>
								<p>
									Scadenza:
									<%=o.getMeseScadenza()%>/<%=o.getAnnoScadenza()%></p>
							</div>
						</div>

						<div class="detail-section">
							<h4>Informazioni Ordine</h4>
							<div class="order-info">
								<p>
									<strong>ID Ordine:</strong> #ORD-<%=o.getOrderId()%>
								</p>
								<p>
									<strong>Data Ordine:</strong>
									<%=o.getDate()%>
								</p>
								<p>
									<strong>ID posti:</strong>
									<%for(Integer s : o.getShowSeatIds()){ %>
									#SEAT-<%=s.toString()%>   
									<%}%>
								</p>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
		<%
		}
		%>
	</div>



	<%@include file="/include/footer.jsp"%>

	<script
		src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
	<script
		src="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/4.1.3/js/bootstrap.bundle.min.js"></script>
	<script src="<%=request.getContextPath()%>/scripts/javascript.js"></script>

</body>
</html>