

document.addEventListener("DOMContentLoaded", () => {
	const filterEvent = document.getElementById("filter-evento");

	if (!filterEvent) {
		console.error("Elemento mancante:", { filterEvent });
		return;
}

	function filterShows() {
		const selectedEvent = filterEvent.value;

		const xhr = new XMLHttpRequest();
		xhr.open("GET", "admin-page?eventId=" + encodeURIComponent(selectedEvent), true);

		xhr.onreadystatechange = function() {
			if (xhr.readyState === 4 && xhr.status === 200) {
				document.querySelector("#show-table").innerHTML = xhr.responseText;
			}
		};

		xhr.send();

	}
	document.getElementById("filter-by-event-button").addEventListener("click", filterShows);

});

document.addEventListener("DOMContentLoaded", () => {
	const filterEvent = document.getElementById("filter-categoria-eventi");
	const filterVenue = document.getElementById("filter-venue-eventi");
	
	if (!filterEvent || !filterVenue) {
		console.error("Elemento mancante");
		return;
}

	function filterShows() {
		const selectedEvent = filterEvent.value;
		const selectedVenue = filterVenue.value;
		const xhr = new XMLHttpRequest();
		xhr.open("GET", "admin-page?categoria=" + encodeURIComponent(selectedEvent)+"&venue=" + encodeURIComponent(selectedVenue), true);

		xhr.onreadystatechange = function() {
			if (xhr.readyState === 4 && xhr.status === 200) {
				document.querySelector("#event-table").innerHTML = xhr.responseText;
			}
		};

		xhr.send();

	}
	document.getElementById("filter-by-category-and-venue").addEventListener("click", filterShows);

});

document.addEventListener("DOMContentLoaded", () => {
	const filterUser = document.getElementById("search-users");
	
	if (!filterUser) {
		console.error("Elemento mancante");
		return;
}

	function filterShows() {
		const selectedUser = filterUser.value;
		const xhr = new XMLHttpRequest();
		xhr.open("GET", "admin-page?keyword=" + encodeURIComponent(selectedUser), true);

		xhr.onreadystatechange = function() {
			if (xhr.readyState === 4 && xhr.status === 200) {
				document.querySelector("#user-table").innerHTML = xhr.responseText;
			}
		};

		xhr.send();

	}
	document.getElementById("filter-by-user").addEventListener("click", filterShows);

});