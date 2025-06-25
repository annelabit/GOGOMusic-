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
 * Servlet implementation class ProductDetailsServlet
 */
@WebServlet("/common/product-details")
public class ProductDetailsServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		int pId = Integer.parseInt(request.getParameter("eventId"));
		
		
		User user = (User) request.getSession().getAttribute("user");
		if (user != null) {
			request.setAttribute("user", user);
		}
		
		SeatDao sDao = new SeatDao();
		ShowSeatDao showSeatDao = new ShowSeatDao();
		ShowDao showDao = new ShowDao();
		ProductDao pDao = new ProductDao();
		LocationDao lDao = new LocationDao();
		
		System.out.println(pId);
		Product p = pDao.getSingleProduct(pId);
		
		p.setShows(showDao.getShows(pId));
		
		ArrayList<ShowSeat> seats = showSeatDao.getSeatsForShow(p.getShows().getFirst().getId());
		ArrayList<String> categories = sDao.getAllCategories(p.getVenueId());
		
		p.setMinPrice(showDao.getMinimumPrice(p.getId()));
		p.setMaxPrice(showDao.getMaximumPrice(p.getId()));
		p.setLocation(lDao.getEventLocation(p.getVenueId()).getVenue());
		p.setShows(showDao.getShows(p.getId()));
		Location location = lDao.getEventLocation(p.getVenueId());
		
		request.setAttribute("product", p);
		request.setAttribute("location", location);
		request.setAttribute("seats", seats);
		request.setAttribute("categories", categories);
		request.getRequestDispatcher("productDetails.jsp").forward(request, response);
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
