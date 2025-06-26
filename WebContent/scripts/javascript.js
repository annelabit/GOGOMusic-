//menu itmes
// Menu toggle function
function menutoggle() {
	const menuItems = document.getElementById('menuItems');

	if (menuItems) {
		menuItems.classList.toggle('show');
		console.log('Menu toggled, classes:', menuItems.className);
	} else {
		console.error('Element menuItems not found');
	}
}

// Gestione scroll e click 
document.addEventListener('DOMContentLoaded', function() {
	let scrollTimeout;
	let lastScrollPosition = 0;

	// Gestione scroll
	window.addEventListener('scroll', function() {
		const currentScroll = window.pageYOffset || document.documentElement.scrollTop;
		const navbar = document.querySelector('.navbar');
		const menuItems = document.getElementById('menuItems');

		if (currentScroll > lastScrollPosition && navbar && menuItems) {
			// Scrolling down
			navbar.classList.add('scrolling');
			if (menuItems.classList.contains('show')) {
				menuItems.classList.remove('show');
			}
		} else {
			// Scrolling up
			if (navbar) navbar.classList.remove('scrolling');
		}
		lastScrollPosition = currentScroll;
	});

	// Chiudi menu quando si clicca fuori
	document.addEventListener('click', function(event) {
		const menuItems = document.getElementById('menuItems');
		const menuIcon = document.querySelector('.menu-icon');
		const navbar = document.querySelector('.navbar');

		if (menuItems && menuIcon && navbar) {
			const isClickInsideNav = navbar.contains(event.target);
			const isMenuOpen = menuItems.classList.contains('show');

			if (!isClickInsideNav && isMenuOpen) {
				menuItems.classList.remove('show');
			}
		}
	});

	// Chiudi menu quando si clicca su un link
	const menuLinks = document.querySelectorAll('#menuItems a');
	menuLinks.forEach(link => {
		link.addEventListener('click', function() {
			const menuItems = document.getElementById('menuItems');
			if (window.innerWidth <= 800 && menuItems) {
				setTimeout(() => {
					menuItems.classList.remove('show');
				}, 100);
			}
		});
	});
});



window.addEventListener("DOMContentLoaded", () => {
	const loginForm = document.getElementById("loginForm");
	const regForm = document.getElementById("regForm");
	const indicator = document.getElementById("indicator");

	if (!loginForm || !regForm || !indicator) {
		console.error("Uno degli elementi non esiste!");
		return;
	}

	window.register = function () {
		loginForm.style.transform = "translateX(-100%)";
		regForm.style.transform = "translateX(0)";
		indicator.style.transform = "translateX(100px)";
	};

	window.login = function () {
		loginForm.style.transform = "translateX(0)";
		regForm.style.transform = "translateX(100%)";
		indicator.style.transform = "translateX(0)";
	};
});




document.addEventListener("DOMContentLoaded", function () {
	// Galleria prodotti
	const productImg = document.getElementById("product-img");
	const smallImgs = document.getElementsByClassName("small-img");

	for (let i = 0; i < smallImgs.length; i++) {
		smallImgs[i].addEventListener("click", function () {
			productImg.src = smallImgs[i].src;
		});
	}

	// Overlay mappa
	const btn = document.getElementById("showImageBtn");
	const overlay = document.getElementById("imageOverlay");
	const closeBtn = document.querySelector(".close-btn");

	btn?.addEventListener("click", function () {
		overlay.style.display = "block";
	});

	closeBtn?.addEventListener("click", function () {
		overlay.style.display = "none";
	});

	overlay?.addEventListener("click", function (e) {
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


// Account Page 

// Show/Hide account sections
function showAccountSection(sectionId) {
	// Hide all sections
	const sections = document.querySelectorAll('.content-section');
	sections.forEach(section => {
		section.classList.remove('active');
	});

	// Show selected section
	const targetSection = document.getElementById(sectionId);
	if (targetSection) {
		targetSection.classList.add('active');
	}

	// Update menu active state (desktop)
	const menuItems = document.querySelectorAll('.menu-item');
	menuItems.forEach(item => {
		item.classList.remove('active');
	});

	const activeMenuItem = document.querySelector(`.menu-item[data-section="${sectionId}"]`);
	if (activeMenuItem) {
		activeMenuItem.classList.add('active');
	}

	// Update mobile buttons active state
	const mobileButtons = document.querySelectorAll('.mobile-nav-btn');
	mobileButtons.forEach(btn => {
		btn.classList.remove('active');
	});

	const activeMobileBtn = document.querySelector(`.mobile-nav-btn[data-section="${sectionId}"]`);
	if (activeMobileBtn) {
		activeMobileBtn.classList.add('active');
	}
}

// Form handlers

function updatePersonalInfo() {
	const form = document.getElementById('form-info-personali');
	const formData = new FormData(form);

	console.log('Aggiornamento informazioni personali:', Object.fromEntries(formData));
	alert('Informazioni personali aggiornate con successo!');
}

function addNewAddress() {
	const form = document.getElementById('form-nuovo-indirizzo');
	const formData = new FormData(form);

	if (!form.checkValidity()) {
		alert('Compila tutti i campi obbligatori');
		return;
	}

	console.log('Nuovo indirizzo:', Object.fromEntries(formData));
	alert('Indirizzo aggiunto con successo!');

	// Hide form and reset
	document.getElementById('add-address-form').style.display = 'none';
	document.getElementById('btn-show-add-address').style.display = 'block';
	form.reset();
}

function addNewPayment() {
	const form = document.getElementById('form-nuovo-pagamento');
	const formData = new FormData(form);

	if (!form.checkValidity()) {
		alert('Compila tutti i campi obbligatori');
		return;
	}

	console.log('Nuovo metodo di pagamento:', Object.fromEntries(formData));
	alert('Metodo di pagamento aggiunto con successo!');

	// Hide form and reset
	document.getElementById('add-payment-form').style.display = 'none';
	document.getElementById('btn-show-add-payment').style.display = 'block';
	form.reset();
}

function changePassword() {
	const form = document.getElementById('form-password');
	const passwordAttuale = document.getElementById('password-attuale').value;
	const nuovaPassword = document.getElementById('nuova-password').value;
	const confermaPassword = document.getElementById('conferma-password').value;

	if (!passwordAttuale || !nuovaPassword || !confermaPassword) {
		alert('Compila tutti i campi');
		return;
	}

	if (nuovaPassword !== confermaPassword) {
		alert('Le password non coincidono');
		return;
	}

	if (nuovaPassword.length < 8) {
		alert('La password deve essere di almeno 8 caratteri');
		return;
	}

	console.log('Cambio password richiesto');
	alert('Password cambiata con successo!');
	form.reset();
}

/*function logout() {
	if (confirm('Sei sicuro di voler effettuare il log out?')) {
		console.log('Logout richiesto');
		alert('Log out in corso...');
		// Redirect to login page
		window.location.href = 'Login.html';
	}
}*/

// Generic action handler
function handleAction(action, id) {
	switch (action) {
		case 'edit-address':
			alert(`Modifica indirizzo ID: ${id}`);
			break;
		case 'delete-address':
			if (confirm('Sei sicuro di voler eliminare questo indirizzo?')) {
				alert('Indirizzo eliminato con successo!');
			}
			break;
		case 'edit-payment':
			alert(`Modifica metodo di pagamento ID: ${id}`);
			break;
		case 'delete-payment':
			if (confirm('Sei sicuro di voler rimuovere questo metodo di pagamento?')) {
				alert('Metodo di pagamento rimosso con successo!');
			}
			break;
		default:
			console.log('Azione non riconosciuta:', action, id);
	}
}

// Format card number input
function formatCardNumber(input) {
	let value = input.value.replace(/\s+/g, '').replace(/[^0-9]/gi, '');
	let formattedValue = value.match(/.{1,4}/g)?.join(' ') || value;
	input.value = formattedValue;
}

// Format expiry date input
function formatExpiryDate(input) {
	let value = input.value.replace(/\D/g, '');
	if (value.length >= 2) {
		value = value.substring(0, 2) + '/' + value.substring(2, 4);
	}
	input.value = value;
}

// Menu toggle function for mobile (navbar)
function menutoggle() {
	const menuItems = document.getElementById('menuItems');
	if (menuItems) {
		menuItems.classList.toggle('show');
	}
}

// Initialize when DOM is loaded
document.addEventListener('DOMContentLoaded', function() {
	// Desktop menu items
	const menuItems = document.querySelectorAll('.menu-item');
	menuItems.forEach(item => {
		item.addEventListener('click', function(e) {
			// Qui `this` è l'elemento cliccato
			if (this.classList.contains('logout-item')) return; // lascia fare il link logout
			
			e.preventDefault();
			const sectionId = this.getAttribute('data-section');
			if (sectionId) {
				showAccountSection(sectionId);
			}
		});
	});

	// Mobile navigation buttons
	const mobileButtons = document.querySelectorAll('.mobile-nav-btn');
	mobileButtons.forEach(button => {
		
		button.addEventListener('click', function() {
			
			
			const sectionId = this.getAttribute('data-section');
			if (sectionId) {
				showAccountSection(sectionId);
			}
		});
	});

	// Action buttons
	const actionButtons = document.querySelectorAll('[data-action]');
	actionButtons.forEach(button => {
		button.addEventListener('click', function() {
			const action = this.getAttribute('data-action');
			const id = this.getAttribute('data-id');
			handleAction(action, id);
		});
	});

	// Form submissions
	/*document.getElementById('form-info-personali')?.addEventListener('submit', function(e) {
		e.preventDefault();
		updatePersonalInfo();
	});

	document.getElementById('form-nuovo-indirizzo')?.addEventListener('submit', function(e) {
		e.preventDefault();
		addNewAddress();
	});

	document.getElementById('form-nuovo-pagamento')?.addEventListener('submit', function(e) {
		e.preventDefault();
		addNewPayment();
	});

	document.getElementById('form-password')?.addEventListener('submit', function(e) {
		e.preventDefault();
		changePassword();
	});*/

	// Show/Hide forms
	document.getElementById('btn-show-add-address')?.addEventListener('click', function() {
		document.getElementById('add-address-form').style.display = 'block';
		this.style.display = 'none';
	});

	document.getElementById('btn-cancel-address')?.addEventListener('click', function() {
		document.getElementById('add-address-form').style.display = 'none';
		document.getElementById('btn-show-add-address').style.display = 'block';
		document.getElementById('form-nuovo-indirizzo').reset();
	});

	document.getElementById('btn-show-add-payment')?.addEventListener('click', function() {
		document.getElementById('add-payment-form').style.display = 'block';
		this.style.display = 'none';
	});

	document.getElementById('btn-cancel-payment')?.addEventListener('click', function() {
		document.getElementById('add-payment-form').style.display = 'none';
		document.getElementById('btn-show-add-payment').style.display = 'block';
		document.getElementById('form-nuovo-pagamento').reset();
	});

	// Logout (both desktop and mobile)
	/*document.getElementById('logout-link')?.addEventListener('click', function(e) {
		e.preventDefault();
		logout();
	});

	document.getElementById('mobile-logout')?.addEventListener('click', function(e) {
		e.preventDefault();
		logout();
	});*/

	// Format card number and expiry date
	document.getElementById('numero-carta')?.addEventListener('input', function() {
		formatCardNumber(this);
	});

	document.getElementById('scadenza')?.addEventListener('input', function() {
		formatExpiryDate(this);
	});

	// Initialize first section
	showAccountSection('informazioni-personali');
});
