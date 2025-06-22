<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!-- navbar  -->
<div class="header">
	<div class="container">
		<div class="navbar">
			<div class="logo">
				<a href="<%= request.getContextPath() %>/common/index.jsp"><img src="<%= request.getContextPath() %>/images/loghi/logo-no.png" id="logo"
					width="125px"></a>
			</div>
			<nav>
				<ul id="menuItems">
					<li><a href="<%= request.getContextPath() %>/common/index.jsp">Home</a></li>
					<li><a href="<%= request.getContextPath() %>/common/login.jsp">Log in</a></li>
					<li><a href="">Ordini</a></li>
					<li><a href="">Account</a></li>
				</ul>
			</nav>
			<div class="search-container d-flex align-items-center">
				<form class="d-flex" role="search" >
					<input id="searchbar" class="form-control me-2" type="search"
						placeholder="Search" aria-label="Search" />
				</form>
			</div>
			<a href="<%= request.getContextPath() %>/common/cart.jsp"><img src="<%= request.getContextPath() %>/images/loghi/carrello-grande.png"
				width="30px" height="30px"></a> <img src="<%= request.getContextPath() %>/images/loghi/menu-icon.png"
				class="menu-icon" onclick="menutoggle()">
		</div>
	</div>
</div>
	
<script src="<%= request.getContextPath() %>/scripts/navbar.js"></script>