<%@page import="model.*"%>
<%@page import="control.*"%>
<%@page import="java.util.*"%>
<%@page import="java.text.DecimalFormat"%><%@ page language="java"
	contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
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

ArrayList<Product> products = (ArrayList<Product>) request.getAttribute("events");
String categoria = (String) request.getAttribute("categoria");
request.setAttribute("products", products);
int venue = (int) request.getAttribute("venue");
%>

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
	for (Product p : products) {
		if ((p.getCategory().equals(categoria) || categoria.equals("")) && (p.getVenueId() == venue || venue == 0))
			for (Show s : p.getShows()) {
	%>
	<tr>
		<td><%=p.getId()%></td>
		<td><%=p.getName()%></td>
		<td><%=p.getCategory()%></td>
		<td><%=p.getLocation()%></td>
		<td><%=p.getShows().size()%></td>
		<td>
			<button class="btn-small btn-edit" onclick="editShow(1)">Modifica</button>
			<form action="<%=request.getContextPath()%>/admin/delete-event"
				method="post" style="display: inline;">
				<input type="hidden" name="eventId" value="<%=p.getId()%>">
				<button type="submit" class="btn-small btn-delete">Elimina</button>
			</form>
		</td>
	</tr>
	<%
	}
	}
	%>

</tbody>

</html>