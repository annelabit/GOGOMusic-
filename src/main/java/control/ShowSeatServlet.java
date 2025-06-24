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
@WebServlet("/common/show-seat")
public class ShowSeatServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String categoria =(String)request.getParameter("categoria");
		int showId = Integer.parseInt(request.getParameter("showId"));
		
		
		User user = (User) request.getSession().getAttribute("user");
		if (user != null) {
			request.setAttribute("user", user);
		}
		
		SeatDao sDao = new SeatDao();
		ShowSeatDao showSeatDao = new ShowSeatDao();
		ShowDao showDao = new ShowDao();
		ProductDao pDao = new ProductDao();
		LocationDao lDao = new LocationDao();
		
		
		
		//Product p = pDao.getSingleProduct(pId);
		ArrayList<ShowSeat> showSeats = showSeatDao.getSeatsForShow(Integer.parseInt(request.getParameter("showId")));
		
		ArrayList<Seat> seats = new ArrayList<>();
		
		for(ShowSeat s : showSeats) {
		
			seats.add(sDao.getSeatsByShowSeat(s));
		}
		
		request.setAttribute("seats", seats);
		request.setAttribute("showSeats", showSeats);
		request.setAttribute("categoria", categoria);
		request.getRequestDispatcher("loadSeats.jsp").forward(request, response);
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
