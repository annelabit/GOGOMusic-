
<div class="header">
		<div class="container">
			<div class="navbar">
				<div class="logo">
					<a href="index.html"><img src="images/loghi/logo-no.png" id="logo"
						width="125px"></a>
				</div>
				<nav>
					<ul id="menuItems">
						<li><a href="index.html">Home</a></li>
						<li><a href="Login.html">Log in</a></li>
						<li><a href="">Ordini</a></li>
						<li><a href="">Account</a></li>
					</ul>
				</nav>
				<div class="search-container d-flex align-items-center">
					<form class="d-flex" role="search">
						<input class="form-control me-2" type="search"
							placeholder="Search" aria-label="Search" />
					</form>
				</div>
				<a href="cart.html"><img src="images/loghi/carrello-grande.png"
					width="30px" height="30px"></a> <img src="images/loghi/menu-icon.png"
					class="menu-icon" onclick="menutoggle()">
			</div>
		</div>
	</div>


<!-- IN FONDO perché se non ha caricato searchbar per lui non esiste -->
<script src="<%= request.getContextPath() %>/scripts/navbar.js"></script>