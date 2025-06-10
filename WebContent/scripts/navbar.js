const searchbar = document.getElementById("searchbar");

searchbar.addEventListener("keyup", (event) => {
	
	const xhr = new XMLHttpRequest();
	
	const searchInput = searchbar.value;
	
	xhr.open("get", "loadEvent.jsp?keyword=" + encodeURIComponent(searchInput), true);
	xhr.setRequestHeader("Connection", "close");//chiude la connessione dopo che è stata inviata la richiesta

	console.log("Searching: " + searchInput);
	
	xhr.onreadystatechange = function() {
	    if (xhr.readyState === 4 && xhr.status === 200) {
	        document.querySelector("#event-container .row").innerHTML = xhr.responseText;
	    }
 	};

 	xhr.send();
	
});