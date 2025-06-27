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
ArrayList<User> users = (ArrayList<User>) request.getAttribute("users");
ArrayList<Order> orders = (ArrayList<Order>) request.getAttribute("orders");
String username = (String) request.getAttribute("keyword");
%>

<thead>
	<tr>
		<th>ID</th>
		<th>Nome</th>
		<th>Email</th>
		<th>Ordini</th>
		<th>Azioni</th>
	</tr>
</thead>


<tbody>

	<%
	System.out.println(username);
	for (User u : users) {
		if (u.getUsername().contains(username) || username == ""){
			
			int tot=0;
			for(Order o : orders){
				if(o.getUid()==u.getIdUtente())
					tot++;
			}
			
	%>
	<tr>
		<td><%=u.getIdUtente()%></td>
		<td><%=u.getUsername()%></td>
		<td><%=u.getEmail()%></td>
		<td><%=tot %></td>
		<td>
			<!--  <button class="btn-small btn-edit" onclick="editShow(1)">Modifica</button>-->
			<form action="<%=request.getContextPath()%>/admin/delete-user?uId=<%=u.getIdUtente() %>"
				method="post" style="display: inline;">
				<input type="hidden" name="eventId" value="<%=u.getIdUtente()%>">
				<button type="submit" class="btn-small btn-delete">Elimina</button>
			</form>
		</td>
	</tr>
	<%}
	}
	%>

</tbody>

</html>