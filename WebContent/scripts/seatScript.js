/**
 * 
 */

let selectedSeatsIds = [];

function loadSeats(){
	
	const xhr = new XMLHttpRequest();
	const select = document.getElementById("showSelect");
	const showId = select.value;
	const venueId = document.getElementById("buy-button").getAttribute("data-venue-id");

	xhr.open("get", "loadSeats.jsp?showId=" + showId, true);
	xhr.setRequestHeader("Connection", "close");

	xhr.onreadystatechange = function() {
	    if (xhr.readyState === 4 && xhr.status === 200) {
	        // sostituisci posti
	        document.querySelector(".grid-container").innerHTML = xhr.responseText;

			//svuoto array di posti selezionati altrimenti li conta ancora come selected			
			selectedSeatsIds = [];

			//cambia il link del bottone
			const buyLink = document.getElementById("buy-button");
			buyLink.href = "#";
			document.getElementById("buy-button").setAttribute("data-show-id", showId);
			//document.getElementById("buy-button").setAttribute("seatIds", null);
	    }
 	};

 	xhr.send();
	
}

function selectSeat(seatId){
	
	const seatButton = document.getElementById(seatId);
	
	//se è già selezionato
	if(selectedSeatsIds.includes(seatId)){

		selectedSeatsIds = selectedSeatsIds.filter(id => id !== seatId);
		seatButton.classList.remove("selected-seat");
	
	} else {
		
		selectedSeatsIds.push(seatId);
		seatButton.classList.add("selected-seat");
		
	}
	
	//ora acquista porta all'acquisto corretto
	const buyLink = document.getElementById("buy-button");
	
	//concatena e separa con virgola
	const Ids = selectedSeatsIds.join(",");
	
	const contextPath = window.location.pathname.split("/")[1];
	const baseUrl = "/" + contextPath;
	//const url = baseUrl + "/common/MyServlet";
	
	//passo la stringa con gli id
	buyLink.href = baseUrl +"/common/update-seat?seatIds=" + encodeURIComponent(Ids) + "&pId=" + buyLink.dataset.venueId + "&showId=" + buyLink.dataset.showId + "&quantity="+selectedSeatsIds.length;
	
}
