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

		System.out.println("Admin servlet");
		
		// controllo se utente è admin
		//boolean isAdmin = (boolean) request.getSession().getAttribute("isAdmin");

		if (request.getSession().getAttribute("isAdmin")==null || (boolean) request.getSession().getAttribute("isAdmin") == false) {
			response.sendRedirect(request.getContextPath() + "/common/login.jsp");
			return;
		}
		User user = (User) request.getSession().getAttribute("user");
		if (user != null) { //se l'user appartiene alla sessione
			request.setAttribute("user", user); //lo aggiunge agli attributi della richiesta
			
		} else {
			response.sendRedirect("login.jsp");
		}

		UserDao uDao = new UserDao();
		ProductDao pDao = new ProductDao();
		ShowDao showDao = new ShowDao();
		ShowSeatDao showSeatDao = new ShowSeatDao();
		SeatDao seatDao = new SeatDao();
		LocationDao locationDao = new LocationDao();
		OrderDao orderDao = new OrderDao();
		
		ArrayList<Order> orders = orderDao.getAllOrders();
		request.setAttribute("orders", orders);
		
		ArrayList<Product> events = new ArrayList<>();
		events = pDao.getProducts();
		
		ArrayList<Location> locations = locationDao.getAllLocations();
		
		//per ogni evento e per ogni spettacolo imposta tutti i posti
		
		ArrayList<User> users = uDao.getAllUsers();
		
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
		
		
		
		request.setAttribute("products", events);
		request.setAttribute("events", events);
		request.setAttribute("locations", locations);
		request.setAttribute("users", users);
		//viene da filtra eventi
		if(request.getParameter("eventId") != null) {
			request.setAttribute("eventId", request.getParameter("eventId"));
			request.getRequestDispatcher("adminShowTable.jsp").forward(request, response);

		}else if(request.getParameter("categoria") != null && request.getParameter("venue") != null) {
			request.setAttribute("categoria", request.getParameter("categoria"));
			request.setAttribute("venue", Integer.parseInt(request.getParameter("venue")));
			request.getRequestDispatcher("adminEventTable.jsp").forward(request, response);
			
		} else if(request.getParameter("keyword") != null && request.getParameter("keyword") != null) {
			request.setAttribute("orders",orders);
			request.setAttribute("keyword", request.getParameter("keyword"));
			request.getRequestDispatcher("adminUserTable.jsp").forward(request, response);
		} else if(request.getParameter("userOrder") != null || request.getParameter("startDate")!= null || request.getParameter("endDate")!=null) {
			
			if(request.getParameter("startDate")==null || request.getParameter("endDate")==null) {
				
				request.setAttribute("orders", orderDao.userOrders(Integer.parseInt(request.getParameter("userOrder"))));
				
			}else{
				
				
				String startParam = request.getParameter("startDate");
				String endParam = request.getParameter("endDate");

				System.out.println("Raw startParam: " + startParam + ", endParam: " + endParam);

				java.sql.Date start = null;
				java.sql.Date end = null;

				try {
				    if (startParam != null && endParam != null) {
				        start = java.sql.Date.valueOf(startParam);
				        end = java.sql.Date.valueOf(endParam);
				    }
				} catch (IllegalArgumentException e) {
				    e.printStackTrace();
				}
				request.setAttribute("orders", orderDao.getOrdersByDateAndUser(start, end, Integer.parseInt(request.getParameter("userOrder"))) );
			}
			request.getRequestDispatcher("adminOrderTable.jsp").forward(request, response);
		
		}else
		
		request.getRequestDispatcher("admin.jsp").forward(request, response);

	}

}
