/**
 * 
 */

let selectedSeatsIds = [];

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
	
	//passo la stringa con gli id
	buyLink.href = "update-seat?seatIds=" + encodeURIComponent(Ids) + "&pId=" + buyLink.dataset.venueId + "&showId=" + buyLink.dataset.showId + "&quantity="+selectedSeatsIds.length;
	
}
