<%@page import="java.text.DecimalFormat"%>
<%@page import="model.*"%>
<%@page import="control.*"%>
<%@page import="java.util.*"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
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
	<link rel="stylesheet"
	href="<%=request.getContextPath()%>/styles/responsive.css">
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/css/bootstrap.min.css"
	rel="stylesheet">
<script
	src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.bundle.min.js"></script>
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0/css/all.min.css">
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/styles/footer.css">
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/styles/stylesheet.css">
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/styles/account.css">

<% User user = (User) request.getSession().getAttribute("user");
if (user != null) { //se l'user appartiene alla sessione
	request.setAttribute("user", user); //lo aggiunge agli attributi della richiesta
	
} else {
	response.sendRedirect("login.jsp");
}

ArrayList<IndirizzoSpedizione> addresses = user.getAddresses();
ArrayList<MetodoPagamento> methods = user.getMethods();

%>

<title>GOGOMusic!</title>
<body>
	<%@include file="/include/navbar.jsp"%>

	<!-- Account Page Content -->
	<div class="account-page">
		<div class="small-container">
			<div class="title-h2">
				<h2>Il Mio Account</h2>
			</div>

			<div class="account-layout">
				<!-- Sidebar -->
				<div class="account-sidebar">
					<div class="user-info">
						<div class="user-avatar">
							<i class="fas fa-user-circle"></i>
						</div>
						<h3>Mario Rossi</h3>
						<p>mario.rossi@email.com</p>
					</div>

					<!-- Desktop Menu -->
					<nav class="account-menu">
						<ul>
							<li><a href="#" class="menu-item active"
								data-section="informazioni-personali"> <i
									class="fas fa-user"></i> <span>Informazioni Personali</span>
							</a></li>
							<li><a href="#" class="menu-item"
								data-section="indirizzo-spedizione"> <i
									class="fas fa-shipping-fast"></i> <span>Indirizzo di
										Spedizione</span>
							</a></li>
							<li><a href="#" class="menu-item"
								data-section="metodo-pagamento"> <i
									class="fas fa-credit-card"></i> <span>Metodo di
										Pagamento</span>
							</a></li>
							<li><a href="#" class="menu-item" data-section="sicurezza">
									<i class="fas fa-shield-alt"></i> <span>Sicurezza</span>
							</a></li>
							<li><a href="logout" class="menu-item logout-item"
								id="logout-link"> <i class="fas fa-sign-out-alt"></i> <span>Log
										Out</span>
							</a></li>
						</ul>
					</nav>

					<!-- Mobile Navigation Buttons -->
					<div class="mobile-nav-buttons">
						<button class="mobile-nav-btn active"
							data-section="informazioni-personali">
							<i class="fas fa-user"></i> <span>Informazioni Personali</span>
						</button>
						<button class="mobile-nav-btn" data-section="indirizzo-spedizione">
							<i class="fas fa-shipping-fast"></i> <span>Indirizzo
								Spedizione</span>
						</button>
						<button class="mobile-nav-btn" data-section="metodo-pagamento">
							<i class="fas fa-credit-card"></i> <span>Metodo Pagamento</span>
						</button>
						<button class="mobile-nav-btn" data-section="sicurezza">
							<i class="fas fa-shield-alt"></i> <span>Sicurezza</span>
						</button>
						<button class="mobile-nav-btn logout-btn" id="mobile-logout">
							<i class="fas fa-sign-out-alt"></i> <span>Log Out</span>
						</button>
					</div>
				</div>

				<!-- Main Content -->
				<div class="account-content">
					<!-- Informazioni Personali -->
					<div id="informazioni-personali" class="content-section active">
						<div class="section-header">
							<h3>Informazioni Personali</h3>
							<p>Gestisci le tue informazioni di base</p>
						</div>

						<div class="content-card">
							<form action="user-info" method="post" class="account-form" id="form-info-personali">
								<div class="form-grid">
									<div class="form-group">
										<label for="username">Nome Utente</label> <input type="text"
											id="username" name="username" value="<%=user.getUsername() %>" required>
									</div>
									<div class="form-group">
										<label for="email">Email</label> <input type="email"
											id="email" name="email" value="<%=user.getEmail() %>"
											required>
									</div>
									<div class="form-group">
										<label for="nome">Nome</label> <input type="text" id="nome"
											name="nome" value="<%=user.getNome() %>" required>
									</div>
									<div class="form-group">
										<label for="cognome">Cognome</label> <input type="text"
											id="cognome" name="cognome" value="<%=user.getCognome() %>" required>
									</div>
								</div>
								<button type="submit" class="btn">Salva Modifiche</button>
							</form>
						</div>
					</div>

					<!-- Indirizzo di Spedizione -->
					<div id="indirizzo-spedizione" class="content-section">
						<div class="section-header">
							<h3>Indirizzi di Spedizione</h3>
							<p>Gestisci i tuoi indirizzi di consegna</p>
						</div>

						<!-- Indirizzo Esistente -->
						
						<%for(IndirizzoSpedizione a : addresses){ %>
						
						<div class="content-card address-card">
							<div class="address-header">
								<div class="address-info">
									
									<%if(a.getMain() == 1){ %>
									<h4>Indirizzo Principale</h4>
									<span class="badge primary">Principale</span>
									<%} else{%>
									<h4>Indirizzo Secondario</h4>
									<%} %>
								</div>
								<div class="address-actions">
									<button class="btn-icon" data-action="edit-address" data-id="1">
										<i class="fas fa-edit"></i>
									</button>
									<button class="btn-icon delete" data-action="delete-address"
										data-id="<%=a.getId() %>">
										<i class="fas fa-trash"></i>
									</button>
								</div>
							</div>
							<div class="address-details">
								<p>
									<strong><%=a.getNomeDestinatario() %> <%=a.getCognomeDestinatario() %></strong>
								</p>
								<p><%=a.getIndirizzo() %></p>
								<p><%=a.getCAP() %> <%=a.getCittà() %></p>
								<p><%=a.getPaese() %></p>
							</div>
						</div>
						<%} %>
						
						<!-- Aggiungi Nuovo Indirizzo -->
						<div class="content-card">
							<button class="btn btn-add" id="btn-show-add-address">
								<i class="fas fa-plus"></i> Aggiungi Nuovo Indirizzo
							</button>

							<div class="add-form-container" id="add-address-form"
								style="display: none;">
								<h4>Nuovo Indirizzo</h4>
								<form action="user-address" method="get" class="account-form" id="form-nuovo-indirizzo">
									<div class="form-grid">
										<div class="form-group">
											<label for="nome-destinatario">Nome Destinatario</label> <input
												type="text" id="nome-destinatario" name="nome_destinatario"
												required>
										</div>
										<div class="form-group">
											<label for="cognome-destinatario">Cognome
												Destinatario</label> <input type="text" id="cognome-destinatario"
												name="cognome_destinatario" required>
										</div>
										<div class="form-group">
											<label for="via">Via/Indirizzo</label> <input type="text"
												id="via" name="via" required>
										</div>
										<!--  
										<div class="form-group">
											<label for="numero-civico">Numero Civico</label> <input
												type="text" id="numero-civico" name="numero_civico" required>
										</div>
										-->
										<div class="form-group">
											<label for="citta">Città</label> <input type="text"
												id="citta" name="citta" required>
										</div>
										<div class="form-group">
											<label for="cap">CAP</label> <input type="text" id="cap"
												name="cap" pattern="[0-9]{5}" required>
										</div>
										<div class="form-group">
											<label for="paese">Paese</label> <input type="text"
												id="paese" name="paese" required>
										</div>
										<div class="form-group checkbox-group">
											<label class="checkbox-label"> <input type="checkbox"
												id="indirizzo-principale" name="principale" value="1"> <span
												class="checkmark"></span> Imposta come indirizzo principale
											</label>
										</div>
									</div>
									<div class="form-actions">
										<button type="submit" class="btn">Salva Indirizzo</button>
										<button type="button" class="btn btn-secondary"
											id="btn-cancel-address">Annulla</button>
									</div>
								</form>
							</div>
						</div>
					</div>

					<!-- Metodo di Pagamento -->
					<div id="metodo-pagamento" class="content-section">
						<div class="section-header">
							<h3>Metodi di Pagamento</h3>
							<p>Gestisci i tuoi metodi di pagamento</p>
						</div>


						<!-- Carta Esistente -->
						
						<%for(MetodoPagamento m : methods){ %>
						
						<div class="content-card payment-card">
							<div class="payment-header">
								<div class="payment-info">
									<div class="card-icon">
										<i class="fab fa-cc-visa"></i>
									</div>
									<div class="card-details">
										<h4><%=m.getTipo() %> •••• •••• •••• <%=m.getLast4Numbers() %></h4>
										<p>Scade: <%=m.getScadenza() %></p>
										<%if(m.getMain() == 1){ %>
										<span class="badge primary">Principale</span>
										<%}else{ %>
										<span class="badge primary">Secondario</span>
										<%} %>
									</div>
								</div>
								<div class="payment-actions">
									<button class="btn-icon" data-action="edit-payment" data-id="1">
										<i class="fas fa-edit"></i>
									</button>
									<button class="btn-icon delete" data-action="delete-payment"
										data-id="1">
										<i class="fas fa-trash"></i>
									</button>
								</div>
							</div>
						</div>
						<%} %>
						<!-- Aggiungi Nuovo Metodo -->
						<div class="content-card">
							<button class="btn btn-add" id="btn-show-add-payment">
								<i class="fas fa-plus"></i> Aggiungi Nuovo Metodo di Pagamento
							</button>

							<div class="add-form-container" id="add-payment-form"
								style="display: none;">
								<h4>Nuovo Metodo di Pagamento</h4>
								<form class="account-form" id="form-nuovo-pagamento" action="user-payment-method" method="post">
									<div class="form-grid">
										<div class="form-group">
											<label for="tipo-carta">Tipo di Carta</label> <select
												id="tipo-carta" name="tipo_carta" required>
												<option value="">-- Seleziona Tipo --</option>
												<option value="Visa">Visa</option>
												<option value="Mastercard">Mastercard</option>
												<option value="American Express">American Express</option>
											</select>
										</div>
										<div class="form-group">
											<label for="numero-carta">Numero Carta</label> <input
												type="text" id="numero-carta" name="numero_carta"
												placeholder="1234 5678 9012 3456" maxlength="19" required>
										</div>
										<div class="form-group">
											<label for="nome-carta">Nome sulla Carta</label> <input
												type="text" id="nome-carta" name="nome_carta" required>
										</div>
										<div class="form-group">
											<label for="scadenza">Scadenza (MM/AA)</label> <input
												type="text" id="scadenza" name="scadenza"
												placeholder="12/26" maxlength="5" required>
										</div>
										<div class="form-group">
											<label for="cvv">CVV</label> <input type="text" id="cvv"
												name="cvv" maxlength="4" required>
										</div>
										<div class="form-group checkbox-group">
											<label class="checkbox-label"> <input type="checkbox"
												id="carta-principale" name="principale"> <span
												class="checkmark"></span> Imposta come metodo principale
											</label>
										</div>
									</div>
									<div class="form-actions">
										<button type="submit" class="btn">Salva Metodo</button>
										<button type="button" class="btn btn-secondary"
											id="btn-cancel-payment">Annulla</button>
									</div>
								</form>
							</div>
						</div>
					</div>

					<!-- Sicurezza -->
					<div id="sicurezza" class="content-section">
						<div class="section-header">
							<h3>Sicurezza Account</h3>
							<p>Modifica la tua password per mantenere l'account sicuro</p>
						</div>

						<div class="content-card">
							<h4>Modifica Password</h4>
							<form class="account-form" id="form-password" action="change-password" method="post">
								<div class="form-grid">
									<div class="form-group full-width">
										<label for="password-attuale">Password Attuale</label> <input
											type="password" id="password-attuale" name="password_attuale"
											required>
									</div>
									<div class="form-group">
										<label for="nuova-password">Nuova Password</label> <input
											type="password" id="nuova-password" name="nuova_password"
											required>
									</div>
									<div class="form-group">
										<label for="conferma-password">Conferma Nuova Password</label>
										<input type="password" id="conferma-password"
											name="conferma_password" required>
									</div>
								</div>
								<div class="password-requirements">
									<h5>Requisiti Password:</h5>
									<ul>
										<li>Almeno 8 caratteri</li>
										<li>Una lettera maiuscola</li>
										<li>Una lettera minuscola</li>
										<li>Un numero</li>
									</ul>
								</div>
								<button type="submit" class="btn">Cambia Password</button>
							</form>
						</div>
					</div>
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


