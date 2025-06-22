//const searchbar = document.getElementById("searchbar");
//const searchInputCategory = category.value;
//const searchInput = searchbar.value;

/*searchbar.addEventListener("keyup", (event) => {
	
	const xhr = new XMLHttpRequest();
	
	const searchInput = searchbar.value;
	
	xhr.open("get", "loadEvent.jsp?keyword=" + encodeURIComponent(searchInput) + "&category=" + encodeURIComponent(searchInputCategory), true);
	//xhr.setRequestHeader("Connection", "close");//chiude la connessione dopo che è stata inviata la richiesta

	console.log("Searching: " + searchInput);
	
	xhr.onreadystatechange = function() {
	    if (xhr.readyState === 4 && xhr.status === 200) {
	        document.querySelector(".row-products").innerHTML = xhr.responseText;
	    }
 	};

 	xhr.send();
	
});*/

/*category.addEventListener("change", () => {
        
	
		console.log("ok");
		
        const xhr = new XMLHttpRequest();
        xhr.open("GET","loadEvent.jsp?keyword=" + encodeURIComponent(searchInput) + "&category=" + encodeURIComponent(searchInputCategory), true);
        //xhr.setRequestHeader("Connection", "close");

        xhr.onreadystatechange = function () {
            if (xhr.readyState === 4 && xhr.status === 200) {
                document.querySelector(".row-products").innerHTML = xhr.responseText;
            }
        };

		console.log(searchInput);
		
        xhr.send();
    });*/