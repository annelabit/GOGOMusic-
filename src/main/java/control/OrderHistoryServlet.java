package control;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import dao.LocationDao;
import dao.OrderDao;
import dao.ProductDao;
import dao.SeatDao;
import dao.ShowDao;
import model.*;
/**
 * Servlet implementation class AddToCartServlet
 */
@WebServlet("/common/order-history")
public class OrderHistoryServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

	    HttpSession session = request.getSession(false);
	    User user = (User) session.getAttribute("user");

	    OrderDao oDao = new OrderDao();
	    SeatDao seatDao = new SeatDao();
	    ProductDao pDao = new ProductDao();
	    ShowDao showDao = new ShowDao();
	    
	    
	    ArrayList<Order> orders = oDao.userOrders(user.getIdUtente());

	    Map<Integer, String> orderAreas = new HashMap<>();
	    for (Order o : orders) {
	        ArrayList<Integer> showSeats = oDao.getOrderShowSeats(o.getOrderId());
	        
	        Product p = pDao.getSingleProduct(o.getId());
	        o.setImage(p.getImage());
	        o.setVenueId(p.getVenueId());
	        
	        o.setShowSeatIds(showSeats);
	        
	        if (showSeats != null && !showSeats.isEmpty()) {
	            // Assumiamo che ogni posto sia legato a uno spettacolo
	        	Show show = showDao.getShowByShowSeatId(showSeats.get(0));

	            if (show != null) {
	                o.setShowDate(show.getDate());  // supponendo che il tuo Order abbia questi setter
	                o.setShowTime(show.getTime());
	            }
	        }
	        
	        
	        if (showSeats != null && !showSeats.isEmpty()) {
	            String area = seatDao.getSeatsByShowSeatId(showSeats.get(0)).getType();
	            orderAreas.put(o.getOrderId(), area);
	        } else {
	            orderAreas.put(o.getOrderId(), "N/A");
	        }
	    }
	    
	    Map<Integer, String> venueNames = new HashMap<>();
	    LocationDao lDao = new LocationDao();

	    for (Order o : orders) {
	        int venueId = o.getVenueId();
	        if (!venueNames.containsKey(venueId)) {
	            venueNames.put(venueId, lDao.getEventLocation(venueId).getVenue());
	        }
	    }
	    
	    request.setAttribute("orders", orders);
	    request.setAttribute("orderAreas", orderAreas);
	    request.setAttribute("venueNames", venueNames);
	    
	    
	    request.getRequestDispatcher("orderHistory.jsp").forward(request, response);

	}
}
