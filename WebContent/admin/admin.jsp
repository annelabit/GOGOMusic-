<%@page import="java.text.DecimalFormat"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@page import="java.sql.Date" %>	
<%@page import="java.util.Calendar" %>
<%@page import="java.text.SimpleDateFormat" %>
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
	href="<%=request.getContextPath()%>/styles/admin.css">



<title>GOGOMusic!</title>

</head>

<%
boolean isAdmin = (boolean) request.getSession().getAttribute("isAdmin");

if (!isAdmin || isAdmin == false) {
	response.sendRedirect(request.getContextPath() + "/common/login.jsp");
	return;
}
User user = (User) request.getSession().getAttribute("user");
request.setAttribute("user", user);

ArrayList<Product> allEvents = (ArrayList<Product>) request.getAttribute("events");
ArrayList<Location> allLocations = (ArrayList<Location>) request.getAttribute("locations");
ArrayList<User> allUsers = (ArrayList<User>) request.getAttribute("users");
ArrayList<Order> allOrders = (ArrayList<Order>) request.getAttribute("orders");

DecimalFormat df = new DecimalFormat("#0.00");
%>


<body>
	<%@include file="/include/navbar.jsp"%>

	<!-- Admin Dashboard -->
	<div class="small-container admin-dashboard">
		<div class="title-h2">
			<h2>Dashboard Amministratore</h2>
		</div>

		<!-- Admin Navigation -->
		<div class="admin-nav">
			<button class="admin-nav-btn active"
				onclick="showAdminSection('gestione-posti')">
				<i class="fas fa-chair"></i> Gestione Posti
			</button>
			<button class="admin-nav-btn"
				onclick="showAdminSection('gestione-spettacoli')">
				<i class="fas fa-calendar-plus"></i> Spettacoli
			</button>
			<button class="admin-nav-btn"
				onclick="showAdminSection('gestione-eventi')">
				<i class="fas fa-music"></i> Eventi
			</button>
			<button class="admin-nav-btn"
				onclick="showAdminSection('visualizza-utenti')">
				<i class="fas fa-users"></i> Utenti
			</button>
			<button class="admin-nav-btn"
				onclick="showAdminSection('gestione-ordini')">
				<i class="fas fa-shopping-cart"></i> Ordini
			</button>
			<button class="admin-nav-btn" onclick="showAdminSection('catalogo')">
				<i class="fas fa-list"></i> Catalogo
			</button>
		</div>

		<!-- Gestione Posti -->
		<div id="gestione-posti" class="admin-section active">
			<h3>Gestione Posti</h3>
			<div class="admin-card">
				<h4>Modifica Prezzo Posti</h4>
				<form class="admin-form"
					action="<%=request.getContextPath()%>/admin/update-price"
					method="post">
					<div class="form-row">
						<div class="form-group">
							<label for="spettacolo-select">Seleziona Spettacolo:</label> <select
								id="spettacolo-select" name="spettacolo">
								<option value="">-- Seleziona Spettacolo --</option>

								<%
								for (Product e : allEvents) {

									for (Show s : e.getShows()) {
								%>

								<option value="<%=s.getId()%>"><%=e.getName()%> -
									<%=e.getLocation()%>,
									<%=s.getDate()%>
								</option>

								<!-- mettere anche spettacolo preciso?? -->

								<%
								}
								}
								%>
							</select>
						</div>
						<div class="form-group">
							<label for="categoria-select">Categoria Posto:</label> <select
								id="categoria-select" name="categoria">
								<option value="">-- Seleziona Categoria --</option>

								<option value="VIP">VIP</option>
								<option value="Premium">Parterre</option>
								<option value="Settore1">Settore 1</option>
								<option value="Settore2">Settore 2</option>

							</select>
						</div>
					</div>
					<div class="form-row">
						<div class="form-group">
							<label for="nuovo-prezzo">Nuovo Prezzo (€):</label> <input
								type="number" id="nuovo-prezzo" name="prezzo" step="0.01"
								min="0">
						</div>
						<!--  <div class="form-group checkbox-group">
							<label> <input type="checkbox" id="applica-categoria">
								Applica a tutti i posti della categoria
							</label>
						</div>
						-->
					</div>
					<button type="submit" class="btn">Aggiorna Prezzo</button>
				</form>
			</div>
		</div>

		<!-- Gestione Spettacoli -->
		<div id="gestione-spettacoli" class="admin-section">
			<h3>Gestione Spettacoli</h3>
			<div class="admin-card">
				<h4>Aggiungi Nuovo Spettacolo</h4>
				<form class="admin-form"
					action="<%=request.getContextPath()%>/admin/add-show" method="post">
					<div class="form-row">
						<div class="form-group">
							<label for="evento-select">Seleziona Evento:</label> <select
								id="evento-select" name="evento" required>
								<option value="">-- Seleziona Evento --</option>
								<%
								for (Product e : allEvents) {
								%>

								<option value="<%=e.getId()%>"><%=e.getName()%></option>
								<%
								}
								%>
							</select>
						</div>
						<div class="form-group">
							<label for="venue-select">Venue:</label> <select
								id="venue-select" name="venue" required>
								<option value="">-- Seleziona Venue --</option>

								<%
								for (Location l : allLocations) {
								%>

								<option value="<%=l.getId()%>"><%=l.getVenue()%> -
									<%=l.getCity()%></option>

								<%
								}
								%>
							</select>
						</div>
					</div>
					<div class="form-row">
						<div class="form-group">
							<label for="data-spettacolo">Data:</label> <input type="date"
								id="data-spettacolo" name="data" required>
						</div>
						<div class="form-group">
							<label for="ora-spettacolo">Ora:</label> <input type="time"
								id="ora-spettacolo" name="ora" required>
						</div>
					</div>
					<div class="pricing-section">
						<h5>Prezzi per Settore</h5>
						<div class="form-row">
							<div class="form-group">
								<label for="prezzo-vip">VIP (€):</label> <input type="number"
									id="prezzo-vip" name="prezzo_vip" step="0.01" min="0">
							</div>
							<div class="form-group">
								<label for="prezzo-premium">Premium (€):</label> <input
									type="number" id="prezzo_premium" name="prezzo_premium"
									step="0.01" min="0">
							</div>
						</div>
						<div class="form-row">
							<div class="form-group">
								<label for="prezzo-standard">Standard (€):</label> <input
									type="number" id="prezzo_standard" name="prezzo_standard"
									step="0.01" min="0">
							</div>
							<div class="form-group">
								<label for="prezzo-economy">Economy (€):</label> <input
									type="number" id="prezzo_economy" name="prezzo_economy"
									step="0.01" min="0">
							</div>
						</div>
					</div>
					<button type="submit" class="btn">Aggiungi Spettacolo</button>
				</form>
			</div>

			<div class="admin-card">
				<h4>Spettacoli Esistenti</h4>
				<div class="table-controls">
					<select id="filter-evento">
						<option value="">Tutti gli Eventi</option>

						<%
						for (Product p : allEvents) {
						%>
						<option value="<%=p.getId()%>">
							<%=p.getName()%>
						</option>
						<%
						}
						%>
					</select>
					<button class="btn" id="filter-by-event-button">Filtra</button>

				</div>
				<div class="table-container">


					<table class="admin-table" id="show-table">
						<thead>
							<tr>
								<th>ID</th>
								<th>Evento</th>
								<th>Venue</th>
								<th>Data</th>
								<th>Ora</th>
								<th>Azioni</th>
							</tr>
						</thead>


						<tbody>

							<%
							for (Product p : allEvents) {
								for (Show s : p.getShows()) {
							%>
							<tr>
								<td><%=p.getId()%></td>
								<td><%=p.getName()%></td>
								<td><%=p.getLocation()%></td>
								<td><%=s.getDate()%></td>
								<td><%=s.getTime()%></td>
								<td>
									<!-- <button class="btn-small btn-edit" onclick="editShow(1)">Modifica</button> -->
									<form action="<%=request.getContextPath()%>/admin/delete-show"
										method="post" style="display: inline;">
										<input type="hidden" name="showId" value="<%=s.getId()%>">
										<button type="submit" class="btn-small btn-delete">Elimina</button>
									</form>
								</td>
							</tr>
							<%
							}
							}
							%>

						</tbody>



					</table>


				</div>
			</div>
		</div>

		<!-- Gestione Eventi -->
		<div id="gestione-eventi" class="admin-section">
			<h3>Gestione Eventi</h3>
			<div class="admin-card">
				<h4>Aggiungi Nuovo Evento</h4>
				<form class="admin-form"
					action="<%=request.getContextPath()%>/admin/add-event"
					method="post">
					<div class="form-row">
						<div class="form-group">
							<label for="nome-evento">Nome Evento:</label> <input type="text"
								id="nome-evento" name="nome" required>
						</div>
						<div class="form-group">
							<label for="categoria-evento">Categoria:</label> <select
								id="categoria-evento" name="categoria" required>
								<option value="">-- Seleziona Categoria --</option>
								<option value="Pop">Pop</option>
								<option value="Rock">Rock</option>
								<option value="Rap">Rap</option>
								<option value="Latino">Latino</option>
							</select>
						</div>
					</div>
					<div class="form-row">
						<div class="form-group">
							<label for="venue-evento">Venue ID:</label> <input type="number"
								id="venue-evento" name="venue_id" required>
						</div>
						<div class="form-group">
							<label for="immagine-evento">URL Immagine:</label> <input
								type="url" id="immagine-evento" name="immagine">
						</div>
					</div>
					<div class="form-row">
						<div class="form-group full-width">
							<label for="descrizione-evento">Descrizione:</label>
							<textarea id="descrizione-evento" name="descrizione" rows="4"></textarea>
						</div>
					</div>
					<button type="submit" class="btn">Aggiungi Evento</button>
				</form>
			</div>

			<div class="admin-card">
				<h4>Eventi Esistenti</h4>
				<div class="table-controls">
					<select id="filter-categoria-eventi">
						<option value="">Tutte le Categorie</option>
						<option value="Pop">Pop</option>
						<option value="Rock">Rock</option>
						<option value="Rap">Rap</option>
						<option value="Latino">Latino</option>
					</select> <select id="filter-venue-eventi">
						<option value="0">Tutti i Venue</option>
						<option value="2">San Siro - Milano</option>
						<option value="3">Stadio Olimpico - Roma</option>
						<option value="1">Stadio Maradona - Napoli</option>
					</select>
					<button id="filter-by-category-and-venue" class="btn"
						onclick="filterEvents()">Filtra</button>
				</div>
				<div class="table-container">
					<table class="admin-table" id="event-table">
						<thead>
							<tr>
								<th>ID</th>
								<th>Evento</th>
								<th>Venue</th>
								<th>Data</th>
								<th>Ora</th>
								<th>Azioni</th>
							</tr>
						</thead>


						<tbody>

							<%
							for (Product p : allEvents) {
							%>
							<tr>
								<td><%=p.getId()%></td>
								<td><%=p.getName()%></td>
								<td><%=p.getCategory()%></td>
								<td><%=p.getLocation()%></td>
								<td><%=p.getShows().size()%></td>
								<td>
									<!--  <button class="btn-small btn-edit" onclick="editShow(1)">Modifica</button>-->
									<form action="<%=request.getContextPath()%>/admin/delete-event"
										method="post" style="display: inline;">
										<input type="hidden" name="eId" value="<%=p.getId()%>">
										<button type="submit" class="btn-small btn-delete">Elimina</button>
									</form>
								</td>
							</tr>
							<%
							}
							%>

						</tbody>
					</table>
				</div>
			</div>
		</div>

		<!-- Visualizza Utenti -->
		<div id="visualizza-utenti" class="admin-section">
			<h3>Gestione Utenti</h3>
			<div class="admin-card">
				<h4>Tutti gli Utenti</h4>
				<div class="table-controls">
					<input type="text" id="search-users" placeholder="Cerca utente...">
					<button id="filter-by-user" class="btn">Cerca</button>
				</div>
				<div class="table-container">
					<table class="admin-table" id="user-table">
						<thead>
							<tr>
								<th>ID</th>
								<th>Nome</th>
								<th>Email</th>
								<th>Data Registrazione</th>
								<th>Ordini</th>
								<th>Azioni</th>
							</tr>
						</thead>
						<tbody>
							<%
							for (User u : allUsers) {
								
								int tot=0;
								for(Order o : allOrders){
									if(o.getUid()==u.getIdUtente())
										tot++;
								}
								
							%>
							<tr>
								<td><%=u.getIdUtente()%></td>
								<td><%=u.getUsername()%></td>
								<td><%=u.getEmail() %></td>
								<td><%=u.getDate()%></td>
								<td><%=tot %></td>
								<!--   -->
								<td>
									<!-- <button class="btn-small btn-edit">Modifica</button> -->
									<form action="<%=request.getContextPath()%>/admin/delete-user?uId=<%=u.getIdUtente() %>"
										method="post" style="display: inline;">
										<input type="hidden" name="uId" value="<%=u.getIdUtente()%>">
										<button type="submit" class="btn-small btn-delete">Elimina</button>
									</form>
								</td>
							</tr>
							<%
							}
							%>
						</tbody>
					</table>
				</div>
			</div>
		</div>

		<!-- Gestione Ordini -->
		<div id="gestione-ordini" class="admin-section">
			<h3>Gestione Ordini</h3>

			<div class="stats-row">
				<div class="stat-card">
					<h4>Ordini Totali</h4>
					<span class="stat-number"><%=allOrders.size() %> </span>
				</div>
				
				<%
				double total=0;
				for(Order o : allOrders){
					total+=o.getPrice();
				} %>
				
				<div class="stat-card">
					<h4>Fatturato Totale</h4>
					<span class="stat-number"><%=df.format(total)%>$</span>
				</div>
				<%
				
				int ordersToday = 0;

				// Data di oggi in formato stringa "yyyy-MM-dd"
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
				String todayStr = sdf.format(new java.util.Date());

				for (Order o : allOrders) {
				    String orderDateStr = sdf.format(o.getDate());
				    if (orderDateStr.equals(todayStr)) {
				        ordersToday++;
				    }
				}

				
				%>
				
				<div class="stat-card">
					<h4>Ordini Oggi</h4>
					<span class="stat-number"><%=ordersToday %></span>
				</div>
			</div>

			<div class="admin-card">
				<h4>Filtra Ordini</h4>
				<form class="admin-form">
					<div class="form-row">
						<div class="form-group">
							<label for="data-inizio">Data Inizio:</label> <input type="date"
								id="data-inizio" name="data_inizio">
						</div>
						<div class="form-group">
							<label for="data-fine">Data Fine:</label> <input type="date"
								id="data-fine" name="data_fine">
						</div>
					</div>
					<div class="form-row">
						<div class="form-group">
							<label for="cliente-select">Cliente:</label> <select
								id="cliente-select" name="cliente">
								<option value="">-- Tutti i Clienti --</option>
								
								<%
							for (User u : allUsers) {
							%>
								<option value="<%=u.getIdUtente()%>"><%=u.getUsername() %></option>
							<%} %>
							
							</select>
						</div>
						<!--  <div class="form-group">
							<label for="stato-ordine">Stato:</label> <select
								id="stato-ordine" name="stato">
								<option value="">-- Tutti gli Stati --</option>
								<option value="pending">In Attesa</option>
								<option value="confirmed">Confermato</option>
								<option value="cancelled">Annullato</option>
							</select>
						</div> -->
					</div>
					<button type="button" class="btn" id="filter-by-user-and-date">Filtra
						Ordini</button>
				</form>
			</div>

			<div class="admin-card">
				<h4>Ordini</h4>
				<div class="table-container">
					<table class="admin-table" id="order-table">
						<thead>
							<tr>
								<th>ID Ordine</th>
								<th>Cliente</th>
								<th>Evento</th>
								<th>Data Ordine</th>
								<th>Totale</th>
								<th>Azioni</th>
							</tr>
						</thead>
						<tbody>
						
						<%for(Order o : allOrders){ %>
						
							<tr>
								<td>ORD-<%=o.getOrderId() %></td>
								<td><%=o.getNome() %> </td>
								<td><%=o.getName() %></td>
								<td><%=o.getDate() %> </td>
								<td><%=o.getPrice() %> </td>
								<td>
									<!--  <button class="btn-small btn-info"
										>Dettagli</button>-->
									<form action="<%=request.getContextPath()%>/admin/cancel-order?orderId=<%=o.getOrderId()%>"
										method="post" style="display: inline;">
										<input type="hidden" name="orderId" value="<%=o.getOrderId()%>">
										<button class="btn-small btn-delete" >Annulla</button>
									</form>
									
								</td>
							</tr>
						<%} %>

						</tbody>
					</table>
				</div>
			</div>
		</div>

		<!-- Catalogo -->
		<div id="catalogo" class="admin-section">
			<h3>Gestione Catalogo</h3>
			<div class="admin-card">
				<h4>Elementi del Catalogo</h4>
				<div class="table-controls">
					<select id="filter-categoria-catalogo">
						<option value="">Tutte le Categorie</option>
						<option value="Pop">Pop</option>
						<option value="Rock">Rock</option>
						<option value="Hip-Hop">Hip-Hop</option>
					</select>
					<button class="btn" onclick="filterCatalog()">Filtra</button>
				</div>
				<div class="table-container">
					<table class="admin-table">
						<thead>
							<tr>
								<th>ID</th>
								<th>Nome Prodotto</th>
								<th>Categoria</th>
								<th>Prezzo</th>
								<th>Disponibilità</th>
								<th>Azioni</th>
							</tr>
						</thead>
						<tbody>
							<tr>
								<td>1</td>
								<td>Biglietto Taylor Swift VIP</td>
								<td>Pop</td>
								<td>€250.00</td>
								<td><span class="status confirmed">Disponibile</span></td>
								<td>
									<button class="btn-small btn-edit" onclick="editProduct(1)">Modifica</button>
									<button class="btn-small btn-delete" onclick="deleteProduct(1)">Elimina</button>
								</td>
							</tr>
							<tr>
								<td>2</td>
								<td>Biglietto Ed Sheeran Standard</td>
								<td>Pop</td>
								<td>€80.00</td>
								<td><span class="status cancelled">Esaurito</span></td>
								<td>
									<button class="btn-small btn-edit" onclick="editProduct(2)">Modifica</button>
									<button class="btn-small btn-delete" onclick="deleteProduct(2)">Elimina</button>
								</td>
							</tr>
						</tbody>
					</table>
				</div>
			</div>
		</div>
	</div>

	<%@include file="/include/footer.jsp"%>
	<script src="<%=request.getContextPath()%>/scripts/admin.js"></script>
	<script
		src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
	<script
		src="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/4.1.3/js/bootstrap.bundle.min.js"></script>
	<script src="<%=request.getContextPath()%>/scripts/javascript.js"></script>

</body>
</html>