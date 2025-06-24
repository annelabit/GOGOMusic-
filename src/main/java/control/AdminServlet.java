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
 * Servlet implementation class AdminServlet
 */
@WebServlet(name = "AdminPageServlet", urlPatterns = { "/admin/admin-page" })
public class AdminServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		// controllo se utente è admin
		boolean isAdmin = (boolean) request.getSession().getAttribute("isAdmin");

		if (!isAdmin || isAdmin == false) {
			response.sendRedirect(request.getContextPath() + "/common/login.jsp");
			return;
		}
		User user = (User) request.getSession().getAttribute("user");
			request.setAttribute("user", user);

		ProductDao pDao = new ProductDao();
		ShowDao showDao = new ShowDao();
		ShowSeatDao showSeatDao = new ShowSeatDao();
		SeatDao seatDao = new SeatDao();
		LocationDao locationDao = new LocationDao();
		
		ArrayList<Product> events = new ArrayList<>();
		events = pDao.getProducts();
		
		ArrayList<Location> locations = locationDao.getAllLocations();
		
		//per ogni evento e per ogni spettacolo imposta tutti i posti
		
		for(Product p : events) {
			p.setMinPrice(showDao.getMinimumPrice(p.getId()));
			p.setMaxPrice(showDao.getMaximumPrice(p.getId()));
			p.setLocation(locationDao.getEventLocation(p.getVenueId()).getVenue());
			
			for(Show s : showDao.getShows(p.getId())) {
			
				ArrayList<ShowSeat> seats = showSeatDao.getSeatsForShow(s.getId());
				s.setSeats(seats);
				s.setCategories(seatDao.getAllCategories(s.getVenueId()));
			}
			
			p.setShows(showDao.getShows(p.getId()));
		}
		
		request.setAttribute("events", events);
		request.setAttribute("locations", locations);
		request.getRequestDispatcher("admin.jsp").forward(request, response);

	}

}
