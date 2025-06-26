<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@page import="model.*" %>
    <%@page import="control.*" %>
    <%@page import="java.util.ArrayList"%>
        

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
<link rel="stylesheet" href="<%= request.getContextPath() %>/styles/Poppins.css">
<link rel="stylesheet" href="<%= request.getContextPath() %>/styles/slider.css">
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/css/bootstrap.min.css"
	rel="stylesheet">
<script
	src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.bundle.min.js"></script>
<link rel="stylesheet" href="<%= request.getContextPath() %>/styles/stylesheet.css">
<% 
    User user = (User) request.getSession().getAttribute("user");
    if(user!=null){
    	response.sendRedirect("index.jsp"); //l'utente ha già fatto log in, non deve farlo nuovamente
    	                                    //la pagina non sarà visibile
    }
    
    ArrayList<Cart> cart = (ArrayList<Cart>) session.getAttribute("cart_list");
    if(cart != null){
    	request.setAttribute("cart_list", cart);
    }
    ArrayList<Product> products = (ArrayList<Product>) request.getAttribute("products");
    request.setAttribute("products", products);
%>
<title>GOGOMusic!</title>
</head>
<body>

	<%@include file="/include/navbar.jsp"%>


	<!-- account page -->
	<div class="account-page">
		<div class="container">
			<div class="row">
				<div class="col-2">
					<img src="images/foto-artiste/artists-3.png" id="artists" width="100%" style="max-width: 350px;">
				</div>
				<div class="col-2" id="login">
					<div class="form-container">
						<div class="form-btn">
						<span onclick="login()">Login</span>
						<span onclick="register()">Registrati</span>
						<hr id="indicator">
						</div>
						
						<form action="<%=request.getContextPath()%>/common/loginServlet" id="loginForm" method="post">
							<input name="login-username" type="text" placeholder="Username">
							<input name="login-password" type="password" placeholder="Password">
							<button type="submit" class="btn">Login</button>  <!-- Aggiungere eventuali collegamenti -->
						</form>
						<form action="<%=request.getContextPath()%>/common/register" id="regForm" method="post">
							<input name="register-email" type="email" placeholder="Email">
							<input name="register-username" type="text" placeholder="Username">
							<input name="register-nome" type="text" placeholder="Nome">
							<input name="register-cognome" type="text" placeholder="Cognome">
							<input name="register-password" type="password" placeholder="Password">
							<button type="submit" class="btn">Registrati</button>   <!-- Aggiungere eventuali collegamenti -->
						</form>
					</div>
				</div>
			</div>
		</div>
	</div>


	<%@include file= "/include/footer.jsp"%>
	
	<script
		src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
	<script
		src="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/4.1.3/js/bootstrap.bundle.min.js"></script>
	<script src="<%= request.getContextPath() %>/scripts/javascript.js"></script>
</body>
</html>