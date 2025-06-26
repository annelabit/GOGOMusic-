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
<link rel="stylesheet" href="Poppins.css">
<link rel="stylesheet" href="slider.css">
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/css/bootstrap.min.css"
	rel="stylesheet">
<script
	src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.bundle.min.js"></script>
<link rel="stylesheet" href="style.css">
<link rel="stylesheet" href="footer.css">

<title>GOGOMusic!</title>
</head>
<body>

	<%@include file="/include/navbar.jsp"%>

	<div class="container-checkout">
		<form a22ction="">
			<div class="row-checkout">
				<div class="col-checkout">
					<h3 class="title-checkout">Indirizzo di fattura</h3>
					<div class="input-box">
						<span>Nome e Cognome:</span> <input type="text"
							placeholder="Nome e Cognome">
					</div>
					<div class="input-box">
						<span>Email:</span> <input type="email"
							placeholder="esempio@gmail.com">
					</div>
					<div class="input-box">
						<span>Indirizzo:</span> <input type="text" placeholder="Indirizzo">
					</div>
					<div class="input-box">
						<span>Città:</span> <input type="text" placeholder="Città">
					</div>

					<div class="flex">
						<div class="input-box">
							<span>Paese:</span> <input type="text" placeholder="Paese">
						</div>
						<div class="input-box">
							<span>Codice postale:</span> <input type="number" min="0"
								placeholder="12345">
						</div>
					</div>
				</div>

				<div class="col-checkout">
					<h3 class="title-checkout">Metodo di pagamento</h3>
					<div class="input-box">
						<span>Carte accettate:</span> <img
							src="images/loghi/carte-credito.png">
					</div>
					<div class="input-box">
						<span>Nome e Cognome titolare:</span> <input type="text"
							placeholder="Nome e Cognome titolare della carta">
					</div>
					<div class="input-box">
						<span>Numero di carta:</span> <input type="number" min="0"
							placeholder="1234 5678 9012 3456">
					</div>

					<div class="flex">
						<div class="input-box">
							<span>Mese Scadenza:</span> <input type="number" min="0"
								placeholder="MM">
						</div>
						<div class="input-box">
							<span>Anno Scadenza:</span> <input type="number" min="0"
								placeholder="AAAA">
						</div>
						<div class="input-box">
							<span>CVV:</span> <input type="number" min="0" placeholder="123">
						</div>
					</div>
				</div>
			</div>

			<button type="submit" class="btn-checkout">Acquista</button>
		</form>
	</div>



	<!-- footer -->
	<div class="footer-clean">
		<footer>
			<div class="container">
				<div class="row justify-content-center">
					<div class="col-sm-4 col-md-3 item">
						<h3>Servizi</h3>
						<ul>
							<li>Web design</li>
							<li>Sviluppo</li>
							<li>Hosting</li>
						</ul>
					</div>
					<div class="col-sm-4 col-md-3 item">
						<h3>Chi siamo</h3>
						<ul>
							<li>Azienda</li>
							<li>Team</li>
							<li>Storia</li>
						</ul>
					</div>
					<div class="col-sm-4 col-md-3 item">
						<h3>Info</h3>
						<ul>
							<li>Biglietteria</li>
							<li>Metodo di pagamento</li>
							<li>Metodo di spedizione</li>
						</ul>
					</div>
					<div class="col-lg-3 item social">
						<a href="https://www.facebook.com/?locale=it_IT"><i
							class="fa fa-facebook-square" aria-hidden="true"></i></a> <a
							href="https://x.com"><i class="fa fa-twitter"
							aria-hidden="true"></i></a> <a href="https://www.youtube.com"><i
							class="fa fa-youtube-play" aria-hidden="true"></i></a> <a
							href="https://www.instagram.com"><i class="fa fa-instagram"
							aria-hidden="true"></i></a>
						<p class="copyright">GOGOMusic! © 2025</p>
					</div>
				</div>
			</div>
		</footer>
	</div>
	<script
		src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
	<script
		src="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/4.1.3/js/bootstrap.bundle.min.js"></script>
	<script src="javascript.js"></script>
</body>
</html>