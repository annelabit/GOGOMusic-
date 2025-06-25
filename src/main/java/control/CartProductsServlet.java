package control;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.Cart;
import model.Product;
import model.User;

import java.io.IOException;
import java.util.ArrayList;

import dao.*;

/**
 * Servlet implementation class CartProductsServlet
 */
@WebServlet("/common/cart-products")
public class CartProductsServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		User user = (User) request.getSession().getAttribute("user");
		ProductDao pDao = new ProductDao();
		SeatDao seatDao = new SeatDao();
		ShowDao showDao = new ShowDao();
		ArrayList<Cart> cart = (ArrayList<Cart>) request.getSession().getAttribute("cart_list");
		ArrayList<String> date = new ArrayList<>();
		ArrayList<String> time = new ArrayList<>();
		ArrayList<String> categories = new ArrayList<>();
		
		double totalPrice = 0;
		ArrayList<Double> total = new ArrayList<>();
		
				if (cart != null && !cart.isEmpty()) {
					for (Cart c : cart) {
						totalPrice += pDao.getPriceForSelected(c.getSeatIds(), c.getShowId());
						total.add(pDao.getPriceForSelected(c.getSeatIds(), c.getShowId()));		
						date.add(showDao.getShow(c.getShowId()).getDate());
						time.add(showDao.getShow(c.getShowId()).getTime());
					
						categories.add(seatDao.getSeatById(c.getSeatIds().getFirst()).getType());
						
					}
				}

		request.setAttribute("totalPrice", totalPrice);
	    request.setAttribute("total", total);
	    request.setAttribute("dates", date);
	    request.setAttribute("times", time);
	    request.setAttribute("categories", categories);
	    request.getRequestDispatcher("cart.jsp").forward(request, response);

		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
