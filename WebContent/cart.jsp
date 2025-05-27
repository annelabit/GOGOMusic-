<!-- %@page import= %>connessione al DB -->
<%@page import="model.*"%>
<%@page import="control.*"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
	 <% 
    User user = (User) request.getSession().getAttribute("user");
    if(user!=null){
    	response.sendRedirect("index.jsp"); //l'utente ha già fatto log in, non deve farlo nuovamente
    	                                    //la pagina non sarà visibile
    }
    %>
    
<!DOCTYPE html>
<html>
<head>
<title>Shopping Cart</title>
<%@include file="includes/head.jsp"%>
<link rel="stylesheet" type="text/css" href="styles/style.css">
</head>
<body>
	<%@include file="includes/navbar.jsp"%>

	<div class="container">
		<!-- p=padding,  m=margin -->
		<div class="d-flex justify-content-between py-3">
			<h3>Prezzo Totale: 300$</h3>
			<a class="btn btn-primary mx-3" href="#"> Check out </a>
		</div>
		<table class="table table-light">
			<thead>
				<tr>
					<th scope="col">Name</th>
					<th scope="col">Category</th>
					<th scope="col">Price</th>
					<th scope="col">Buy Now</th>
					<th scope="col">Cancel</th>
				</tr>
			</thead>
			<tbody>
				<tr>
					<!--  table row -->
					<td>Ultimo</td>
					<td>Concerto</td>
					<td>100$</td>
					<td>
						<form action="" method="post" class="form-inline">
						<div class="input-group"> 
							<input type="hidden" name="id" value="1" class="form-input">
							<div class="d-flex align-items-center gap-2">
								<a class="btn btn-sm btn-incre" href="#">
								<i class="fas fa-plus-square"></i></a> 
									 <input type="text"name="quantity" class="form-control text-center" value="1" readonly> 
									 <a class="btn btn-sm btn-decre" href="#">
									 <i class="fas fa-minus-square"></i></a>
							</div>
							</div>
						</form>
					</td>
					<td>
						<a class="btn btn-sm btn-danger" href=""> Rimuovi </a>
					</td>
				</tr>
			</tbody>
		</table>
	</div>


	<%@include file="includes/footer.jsp"%>
</body>
</html>