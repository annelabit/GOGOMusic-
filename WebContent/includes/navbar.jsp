<nav class="navbar navbar-expand-lg bg-body-tertiary">
  <div class="container">
    <a class="navbar-brand" href="#"><img src="images/GOGOMusic-bw.png" id="logo"></a>
    <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarScroll" aria-controls="navbarScroll" aria-expanded="false" aria-label="Toggle navigation">
      <span class="navbar-toggler-icon"></span>
    </button>
    <div class="collapse navbar-collapse justify-content-between" id="navbarScroll">
      <ul class="navbar-nav me-auto my-2 my-lg-0 navbar-nav-scroll" style="--bs-scroll-height: 100px;">
        <li class="nav-item">
          <a class="nav-link active" aria-current="page" href="index.jsp">Home</a>
        </li>
        <li class="nav-item dropdown">
          <a class="nav-link dropdown-toggle" href="#" role="button" data-bs-toggle="dropdown" aria-expanded="false">
            Categoria
          </a>
          <ul class="dropdown-menu">
            <li><a class="dropdown-item" href="#">Musica</a></li>
            <li><a class="dropdown-item" href="#">Teatro</a></li>
            <li><a class="dropdown-item" href="#">Museo</a></li>
           <!--  <li><hr class="dropdown-divider"></li>
            <li><a class="dropdown-item" href="#">Something else here</a></li> -->
          </ul>
        </li>
        <li class="nav-item">
          <a class="nav-link active" href="cart.jsp">Carrello</a>
        </li>
        <% if(user != null){ %> <!-- Ignora errore -->
        		<li class="nav-item">
                <a class="nav-link active" href="order.jsp">Ordini</a>
              </li>
              <li class="nav-item">
              <a class="nav-link" aria-disabled="false" href="logout">Log out</a>
            </li>
       <%	} else {    %> 
       			<li>
        		<a class="nav-link active" aria-disabled="false" href="login.jsp">Log in</a>
                </li>
        <% 	}   %> 
      </ul>
      <form class="d-flex ms-auto" role="search">
        <input class="form-control me-2" type="search" placeholder="Cerca" aria-label="Search"/>
        <button class="btn btn-outline-success" type="submit">Cerca</button>
    </form>
</div>
    </div>
  </div>
</nav>