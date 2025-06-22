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
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/css/bootstrap.min.css"
	rel="stylesheet">
<script
	src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.bundle.min.js"></script>
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/styles/stylesheet.css">
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/styles/footer.css">

<title>GOGOMusic!</title>
</head>


<body>

	<%@include file="/include/navbar.jsp"%>

	<div class="container-checkout">
		<form action="<%=request.getContextPath()%>/common/checkout" method="post">
			<div class="row-checkout">
				<div class="col-checkout">
					<h3 class="title-checkout">Indirizzo di fattura</h3>
					<div class="input-box">
						<span>Nome e Cognome:</span> <input type="text"
							placeholder="Nome e Cognome" name="name">
					</div>
					<div class="input-box">
						<span>Email:</span> <input type="email"
							placeholder="esempio@gmail.com" name="email">
					</div>
					<div class="input-box">
						<span>Indirizzo:</span> <input type="text" placeholder="Indirizzo" name="indirizzo">
					</div>
					<div class="input-box">
						<span>Città:</span> <input type="text" placeholder="Città" name="città">
					</div>

					<div class="flex">
						<div class="input-box">
							<span>Paese:</span> <input type="text" placeholder="Paese" name="paese">
						</div>
						<div class="input-box">
							<span>Codice postale:</span> <input type="number" min="0"
								placeholder="12345" name="cap">
						</div>
					</div>
				</div>

				<div class="col-checkout">
					<h3 class="title-checkout">Metodo di pagamento</h3>
					<div class="input-box">
						<span>Carte accettate:</span> <img
							src="<%=request.getContextPath() %>/images/loghi/carte-credito.jpg">
					</div>
					<div class="input-box">
						<span>Nome e Cognome titolare:</span> <input type="text"
							placeholder="Nome e Cognome titolare della carta" name="nomeTitolare">
					</div>
					<div class="input-box">
						<span>Numero di carta:</span> <input type="number" min="0"
							placeholder="1234 5678 9012 3456" name="cardNumber">
					</div>

					<div class="flex">
						<div class="input-box">
							<span>Mese Scadenza:</span> <input type="number" min="0"
								placeholder="MM" name="mese">
						</div>
						<div class="input-box">
							<span>Anno Scadenza:</span> <input type="number" min="0"
								placeholder="AAAA" name="anno">
						</div>
						<div class="input-box">
							<span>CVV:</span> <input type="number" min="0" placeholder="123" name="cvv">
						</div>
					</div>
				</div>
			</div>

			<button type="submit" class="btn-checkout">Acquista</button>
		</form>
	</div>

	<%@include file="/include/footer.jsp"%>

	<script
		src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
	<script
		src="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/4.1.3/js/bootstrap.bundle.min.js"></script>
	<script src="<%=request.getContextPath()%>/scripts/javascript.js"></script>
</body>
</html>