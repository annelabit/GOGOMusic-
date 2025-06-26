package control;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.*;

import java.io.IOException;
import java.util.ArrayList;

import dao.*;

/**
 * Servlet implementation class IndexServlet
 */
@WebServlet("/common/index")
public class IndexServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		// controllo se viene da selezione categoria
		String category = request.getParameter("category");
		
		//controllo se viene da tutti i prodotti
		String all = request.getParameter("all");

		User user = (User) request.getSession().getAttribute("user");
		if (user != null) {
			request.setAttribute("user", user);
		}

		// Inizializza DAO
		ProductDao productDao = new ProductDao();
		ShowDao showDao = new ShowDao();
		LocationDao locationDao = new LocationDao();

		// Carica prodotti
		ArrayList<Product> products = productDao.getProducts();
		request.setAttribute("products", products);
		//request.getSession().setAttribute("products",products);
		for (Product p : products) {
			
			if(showDao.getShows(p.getId())!=null || showDao.getShows(p.getId()).isEmpty()) {
	        	
			
			p.setMinPrice(showDao.getMinimumPrice(p.getId()));
			p.setMaxPrice(showDao.getMaximumPrice(p.getId()));
			p.setLocation(locationDao.getEventLocation(p.getVenueId()).getVenue());
			p.setShows(showDao.getShows(p.getId()));
			}
		}

		// Carrello
		ArrayList<Cart> cart = (ArrayList<Cart>) request.getSession().getAttribute("cart_list");
		if (cart != null) {
			request.setAttribute("cart_list", cart);
		}

		if(all != null) {
			// Reindirizza alla JSP
			request.getRequestDispatcher("products.jsp").forward(request, response);
		}
		
		if (category == null) {
			
			/*if(all != null) {
				// Reindirizza alla JSP
				request.getRequestDispatcher("products.jsp").forward(request, response);
			}*/
			
			// Reindirizza alla JSP
			request.getRequestDispatcher("index.jsp").forward(request, response);
		} else {
			request.setAttribute("category", category);
			request.getRequestDispatcher("loadEvent.jsp").include(request, response);
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
