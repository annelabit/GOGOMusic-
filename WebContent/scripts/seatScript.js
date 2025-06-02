/**
 * 
 */

let selectedSeatId = null;

function selectSeat(seatId){
	
	//se c'è già un posto selezionato
	if(selectedSeatId != null){
		const prevButton = document.getElementById(selectedSeatId);
		if(prevButton){
			prevButton.classList.remove("selected-seat");
		}
	}
	
	//imposto nuovo posto selezionato
	selectedSeatId = seatId;
	
	//aggiungi alla classe del bottone selezionato
	const currentButton = document.getElementById(seatId);
	if(currentButton){
		currentButton.classList.add("selected-seat");
	}
	
	//ora acquista porta all'acquisto corretto
	const buyLink = document.getElementById("buy-button");
	buyLink.href = "update-seat?seatId=" + seatId + "&pId=" + buyLink.dataset.venueId;
	
}

function myFunction(seatId) {
    fetch('update-seat', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/x-www-form-urlencoded'
        },
        body: 'seatId=' + seatId
    })
    .then(response => response.json())
    .then(data => {
        if (data.success) {
            const btn = document.getElementById(seatId);
            btn.innerText = "N.A.";
            btn.disabled = true;
            btn.className = "button-2";
        } else {
            alert("Posto non disponibile o già prenotato.");
        }
    })
    .catch(error => console.error('Errore nella prenotazione:', error));
}