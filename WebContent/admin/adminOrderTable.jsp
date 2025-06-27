<%@page import="model.*"%>
<%@page import="control.*"%>
<%@page import="java.util.*"%>
<%@page import="java.text.DecimalFormat"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<%
DecimalFormat df = new DecimalFormat("#0.00");

ArrayList<Order> orders = (ArrayList<Order>) request.getAttribute("orders");
%>

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
						
						<%for(Order o : orders){
							
							%>
						
							<tr>
								<td>ORD-<%=o.getOrderId() %></td>
								<td><%=o.getNome() %> </td>
								<td><%=o.getName() %></td>
								<td><%=o.getDate() %> </td>
								<td><%=df.format(o.getPrice()) %> </td>
								<td>
									<!--  <button class="btn-small btn-info"
										onclick="viewOrder('ORD001')">Dettagli</button>-->
									<button class="btn-small btn-delete"
										onclick="cancelOrder('ORD001')">Annulla</button>
								</td>
							</tr>
						<%}
						 %>

						</tbody>
</html>