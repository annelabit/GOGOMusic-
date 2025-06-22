

document.addEventListener("DOMContentLoaded", () => {
    const searchbar = document.getElementById("searchbar");
    const category = document.getElementById("category");
    const container = document.querySelector(".row-products");

    if (!searchbar || !category || !container) {
        console.error("Elemento mancante:", { searchbar, category, container });
        return;
    }

    function loadFilteredProducts() {
        const keyword = searchbar.value.trim();
        const selectedCategory = category.value;

		const xhr = new XMLHttpRequest();
		xhr.open("GET", "loadEvent.jsp?keyword="+keyword+"&category="+selectedCategory, true);

		xhr.onreadystatechange = function () {
		    if (xhr.readyState === 4 && xhr.status === 200) {
		        document.querySelector(".row-products").innerHTML = xhr.responseText;
		    }
		};

		xhr.send();

	}
    searchbar.addEventListener("keyup", loadFilteredProducts);
    category.addEventListener("change", loadFilteredProducts);
});

