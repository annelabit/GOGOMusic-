

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