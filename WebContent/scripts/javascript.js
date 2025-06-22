//menu itmes
var menuItems= document.getElementById("menuItems");
menuItems.style.maxHeight= "0px";

function menutoggle(){
	if(menuItems.style.maxHeight== "0px")
		{
			menuItems.style.maxHeight= "260px";
		}
	else {
		menuItems.style.maxHeight= "0px";
	}
}



/* pagina login e register*/
var loginForm= document.getElementById("loginForm");
var regForm= document.getElementById("regForm");
var indicator= document.getElementById("indicator");

function register(){
		// Sposta il form di login a sinistra (fuori dallo schermo)
	    loginForm.style.transform = "translateX(-100%)";
	    // Porta il form di registrazione al centro
	    regForm.style.transform = "translateX(0)";
	    // Sposta l'indicatore sotto "Registrati"
	    indicator.style.transform = "translateX(100px)";
}

function login(){
		// Porta il form di login al centro
	    loginForm.style.transform = "translateX(0)";
	    // Sposta il form di registrazione a destra (fuori dallo schermo)
	    regForm.style.transform = "translateX(100%)";
	    // Riporta l'indicatore sotto "Login"
	    indicator.style.transform = "translateX(0)";
}




/*  galleria prodotti */
var productImg= document.getElementById("product-img");
var smallImg= document.getElementsByClassName("small-img");
//small img è un array (sono 3 foto)

smallImg[0].onclick= function(){
	productImg.src= smallImg[0].src;
}
smallImg[1].onclick= function(){
	productImg.src= smallImg[1].src;
}
smallImg[2].onclick= function(){
	productImg.src= smallImg[2].src;
}




/* immagine mappa */
document.addEventListener("DOMContentLoaded", function() {
    const btn = document.getElementById("showImageBtn");
    const overlay = document.getElementById("imageOverlay");
    const closeBtn = document.querySelector(".close-btn");
    const fullscreenImage = document.getElementById("fullscreenImage");

    // Apre l'overlay al click
    btn.addEventListener("click", function() {
        overlay.style.display = "block";
    });

    // Chiude l'overlay
    closeBtn.addEventListener("click", function() {
        overlay.style.display = "none";
    });

    // Chiude l'overlay se si clicca fuori dall'immagine
    overlay.addEventListener("click", function(e) {
        if (e.target === overlay) {
            overlay.style.display = "none";
        }
    });
});




