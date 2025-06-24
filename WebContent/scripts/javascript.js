//menu itmes
var menuItems = document.getElementById("menuItems");
menuItems.style.maxHeight = "0px";

function menutoggle() {
	if (menuItems.style.maxHeight == "0px") {
		menuItems.style.maxHeight = "260px";
	}
	else {
		menuItems.style.maxHeight = "0px";
	}
}



/* pagina login e register*/
var loginForm = document.getElementById("loginForm");
var regForm = document.getElementById("regForm");
var indicator = document.getElementById("indicator");

function register() {
	// Sposta il form di login a sinistra (fuori dallo schermo)
	loginForm.style.transform = "translateX(-100%)";
	// Porta il form di registrazione al centro
	regForm.style.transform = "translateX(0)";
	// Sposta l'indicatore sotto "Registrati"
	indicator.style.transform = "translateX(100px)";
}

function login() {
	// Porta il form di login al centro
	loginForm.style.transform = "translateX(0)";
	// Sposta il form di registrazione a destra (fuori dallo schermo)
	regForm.style.transform = "translateX(100%)";
	// Riporta l'indicatore sotto "Login"
	indicator.style.transform = "translateX(0)";
}




/*  galleria prodotti */
var productImg = document.getElementById("product-img");
var smallImg = document.getElementsByClassName("small-img");
//small img è un array (sono 3 foto)

smallImg[0].onclick = function() {
	productImg.src = smallImg[0].src;
}
smallImg[1].onclick = function() {
	productImg.src = smallImg[1].src;
}
smallImg[2].onclick = function() {
	productImg.src = smallImg[2].src;
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


/*pagina ordini*/
function toggleDetails(orderId) {
	const detailsElement = document.getElementById(orderId);
	const button = detailsElement.previousElementSibling.querySelector('.details-btn');
	const btnText = button.querySelector('.btn-text');
	const arrow = button.querySelector('.arrow');

	if (detailsElement.style.display === 'block' || detailsElement.classList.contains('show')) {
		// Nascondi i dettagli
		detailsElement.style.display = 'none';
		detailsElement.classList.remove('show');
		btnText.textContent = 'Vedi Dettagli';
		arrow.style.transform = 'rotate(0deg)';
	} else {
		// Mostra i dettagli
		detailsElement.style.display = 'block';
		detailsElement.classList.add('show');
		btnText.textContent = 'Nascondi Dettagli';
		arrow.style.transform = 'rotate(180deg)';
	}
}



// Admin Dashboard 
	// Show/Hide admin sections
	function showAdminSection(sectionId) {
	    // Hide all sections
	    const sections = document.querySelectorAll('.admin-section');
	    sections.forEach(section => {
	        section.classList.remove('active');
	    });
	    
	    // Show selected section
	    document.getElementById(sectionId).classList.add('active');
	    
	    // Update navigation active state
	    const navButtons = document.querySelectorAll('.admin-nav-btn');
	    navButtons.forEach(btn => {
	        btn.classList.remove('active');
	    });
	    
	    // Find and activate the corresponding nav button
	    const activeButton = Array.from(navButtons).find(btn => 
	        btn.getAttribute('onclick').includes(sectionId)
	    );
	    if (activeButton) {
	        activeButton.classList.add('active');
	    }
	}

	// Seat Management Functions
	function updateSeatPrices() {
	    const spettacolo = document.getElementById('spettacolo-select').value;
	    const categoria = document.getElementById('categoria-select').value;
	    const prezzo = document.getElementById('nuovo-prezzo').value;
	    const applicaCategoria = document.getElementById('applica-categoria').checked;
	    
	    if (!spettacolo || !categoria || !prezzo) {
	        alert('Compila tutti i campi obbligatori');
	        return;
	    }
	    
	    console.log('Aggiornamento prezzi:', {
	        spettacolo,
	        categoria,
	        prezzo,
	        applicaCategoria
	    });
	    
	    alert('Prezzi aggiornati con successo!');
	    
	    // Reset form
	    document.getElementById('spettacolo-select').value = '';
	    document.getElementById('categoria-select').value = '';
	    document.getElementById('nuovo-prezzo').value = '';
	    document.getElementById('applica-categoria').checked = false;
	}

	// Show Management Functions
	function addShow() {
	    const form = document.querySelector('#gestione-spettacoli .admin-form');
	    const formData = new FormData(form);
	    
	    // Validate required fields
	    const evento = formData.get('evento');
	    const venue = formData.get('venue');
	    const data = formData.get('data');
	    const ora = formData.get('ora');
	    
	    if (!evento || !venue || !data || !ora) {
	        alert('Compila tutti i campi obbligatori');
	        return;
	    }
	    
	    // Simulate API call
	    console.log('Nuovo spettacolo:', Object.fromEntries(formData));
	    alert('Spettacolo aggiunto con successo!');
	    form.reset();
	}

	function editShow(id) {
	    alert(`Modifica spettacolo ID: ${id}`);
	    // Here you would typically open a modal or redirect to edit page
	}

	function deleteShow(id) {
	    if (confirm('Sei sicuro di voler eliminare questo spettacolo?')) {
	        console.log(`Eliminazione spettacolo ID: ${id}`);
	        alert('Spettacolo eliminato con successo!');
	        // Here you would make API call and remove row from table
	    }
	}

	/*function filterShows() {
	    const eventoFilter = document.getElementById('filter-evento').value;
	    console.log('Filtra spettacoli per evento:', eventoFilter);
	    // Implement filtering logic here
	    //alert('Filtro applicato per evento: ' + (eventoFilter || 'Tutti'));
		//non ritengo necessario un alert anche per questo	
	}*/

	// Event Management Functions
	function addEvent() {
	    const form = document.querySelector('#gestione-eventi .admin-form');
	    const formData = new FormData(form);
	    
	    const nome = formData.get('nome');
	    const categoria = formData.get('categoria');
	    const venueId = formData.get('venue_id');
	    
	    if (!nome || !categoria || !venueId) {
	        alert('Compila tutti i campi obbligatori');
	        return;
	    }
	    
	    console.log('Nuovo evento:', Object.fromEntries(formData));
	    alert('Evento aggiunto con successo!');
	    form.reset();
	}

	function editEvent(id) {
	    alert(`Modifica evento ID: ${id}`);

	}

	function deleteEvent(id) {
	    if (confirm('Sei sicuro di voler eliminare questo evento e tutti i suoi spettacoli?')) {
	        console.log(`Eliminazione evento ID: ${id}`);
	        alert('Evento eliminato con successo!');
	    }
	}

	function viewShows(eventId) {
	    alert(`Visualizza spettacoli per evento ID: ${eventId}`);
	    // Implement show viewing functionality
	}

	function filterEvents() {
	    const categoriaFilter = document.getElementById('filter-categoria-eventi').value;
	    const venueFilter = document.getElementById('filter-venue-eventi').value;
	    console.log('Filtra eventi:', { categoriaFilter, venueFilter });
	    //alert('Filtri applicati per categoria: ' + (categoriaFilter || 'Tutte') + 
	    //      ' e venue: ' + (venueFilter || 'Tutti'));
	}

	// User Management Functions
	function searchUsers() {
	    const searchTerm = document.getElementById('search-users').value;
	    console.log('Cerca utenti:', searchTerm);
	    alert('Ricerca utenti per: ' + (searchTerm || 'tutti gli utenti'));
	}

	function editUser(id) {
	    alert(`Modifica utente ID: ${id}`);
	    // Implement edit functionality
	}

	function deleteUser(id) {
	    if (confirm('Sei sicuro di voler eliminare questo utente?')) {
	        console.log(`Eliminazione utente ID: ${id}`);
	        alert('Utente eliminato con successo!');
	    }
	}

	function viewUserOrders(userId) {
	    alert(`Visualizza ordini per utente ID: ${userId}`);
	    // Implement order viewing functionality
	}

	// Order Management Functions
	function filterOrders() {
	    const dataInizio = document.getElementById('data-inizio').value;
	    const dataFine = document.getElementById('data-fine').value;
	    const cliente = document.getElementById('cliente-select').value;
	    const stato = document.getElementById('stato-ordine').value;
	    
	    console.log('Filtra ordini:', {
	        dataInizio,
	        dataFine,
	        cliente,
	        stato
	    });
	    
	    let filterMessage = 'Filtri applicati:';
	    if (dataInizio) filterMessage += ` Da: ${dataInizio}`;
	    if (dataFine) filterMessage += ` A: ${dataFine}`;
	    if (cliente) filterMessage += ` Cliente: ${cliente}`;
	    if (stato) filterMessage += ` Stato: ${stato}`;
	    
	    alert(filterMessage);
	}

	function viewOrder(orderId) {
	    alert(`Visualizza dettagli ordine: ${orderId}`);
	    // Implement order details functionality
	}

	function cancelOrder(orderId) {
	    if (confirm('Sei sicuro di voler annullare questo ordine?')) {
	        console.log(`Annullamento ordine: ${orderId}`);
	        alert('Ordine annullato con successo!');
	    }
	}

	// Catalog Management Functions
	function filterCatalog() {
	    const categoriaFilter = document.getElementById('filter-categoria-catalogo').value;
	    console.log('Filtra catalogo per categoria:', categoriaFilter);
	    // alert('Filtro catalogo applicato per categoria: ' + (categoriaFilter || 'Tutte'));
	}

	function editProduct(id) {
	    alert(`Modifica prodotto ID: ${id}`);
	    // Implement edit functionality
	}

	function deleteProduct(id) {
	    if (confirm('Sei sicuro di voler eliminare questo prodotto?')) {
	        console.log(`Eliminazione prodotto ID: ${id}`);
	        alert('Prodotto eliminato con successo!');
	    }
	}

	// Form submission handlers
	document.addEventListener('DOMContentLoaded', function() {
	    // Handle form submissions
	    const forms = document.querySelectorAll('.admin-form');
	    forms.forEach(form => {
	        form.addEventListener('submit', function(e) {
	            e.preventDefault();
	            
	            // Determine which form was submitted
	            const section = form.closest('.admin-section');
	            const sectionId = section.id;
	            
	            switch(sectionId) {
	                case 'gestione-posti':
	                    updateSeatPrices();
	                    break;
	                case 'gestione-spettacoli':
	                    addShow();
	                    break;
	                case 'gestione-eventi':
	                    addEvent();
	                    break;
	                default:
	                    console.log('Form submitted for section:', sectionId);
	            }
	        });
	    });
	    
	    // Initialize first section as active
	    showAdminSection('gestione-posti');
	});

	// Menu toggle function for mobile (from original theme)
	function menutoggle() {
	    const menuItems = document.getElementById('menuItems');
	    if (menuItems) {
	        menuItems.classList.toggle('show');
	    }
	}

	// Utility functions
	function formatCurrency(amount) {
	    return new Intl.NumberFormat('it-IT', {
	        style: 'currency',
	        currency: 'EUR'
	    }).format(amount);
	}

	function formatDate(date) {
	    return new Intl.DateTimeFormat('it-IT').format(new Date(date));
	}

	// Handle window scroll for mobile menu (from original theme)
	let lastScrollTop = 0;
	window.addEventListener('scroll', function() {
	    let scrollTop = window.pageYOffset || document.documentElement.scrollTop;
	    const nav = document.querySelector('nav');
	    
	    if (scrollTop > lastScrollTop && nav) {
	        // Scrolling down - close mobile menu
	        nav.classList.add('scrolling');
	        const menuItems = document.getElementById('menuItems');
	        if (menuItems && menuItems.classList.contains('show')) {
	            menuItems.classList.remove('show');
	        }
	    } else {
	        // Scrolling up
	        if (nav) nav.classList.remove('scrolling');
	    }
	    lastScrollTop = scrollTop;
	});

	// Export functions for external use
	window.adminFunctions = {
	    showAdminSection,
	    updateSeatPrices,
	    addShow,
	    editShow,
	    deleteShow,
	    filterShows,
	    addEvent,
	    editEvent,
	    deleteEvent,
	    viewShows,
	    filterEvents,
	    searchUsers,
	    editUser,
	    deleteUser,
	    viewUserOrders,
	    filterOrders,
	    viewOrder,
	    cancelOrder,
	    filterCatalog,
	    editProduct,
	    deleteProduct
	};