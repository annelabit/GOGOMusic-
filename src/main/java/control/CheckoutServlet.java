package control;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.Cart;
import model.DBConnection;
import model.Order;
import model.User;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Date;

import dao.OrderDao;
import dao.ProductDao;

/**
 * Servlet implementation class CheckoutServlet
 */
@WebServlet("/checkout")
public class CheckoutServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
     
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		try(PrintWriter out = response.getWriter()){
			
			SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
			Date date = new Date();
			
			//tutti i prodotti del carrello
			ArrayList<Cart> cart = (ArrayList<Cart>) request.getSession().getAttribute("cart_list"); 

			//utente deve essere logged in
			User user = (User) request.getSession().getAttribute("user");

			OrderDao oDao = new OrderDao(DBConnection.getConnection());
			ProductDao pDao = new ProductDao(DBConnection.getConnection());
			
			if(user != null && cart != null) {
				
				for(Cart c : cart) {
					
					LocalTime currentTime = LocalTime.now();
					String formattedTime = String.format("%02d:%02d:%02d", currentTime.getHour(), currentTime.getMinute(), currentTime.getSecond());
					
					Order order = new Order();
					order.setId(c.getId());
					order.setUid(user.getIdUtente());
					order.setQuantity(c.getQuantity());
					order.setDate(formatter.format(date));
					order.setTime(formattedTime);
					order.setPrice((float) pDao.getPriceForSelected(c.getSeatIds(), c.getVenueId(), c.getId()));
					
					boolean result = oDao.insertOrder(order);
					if(!result) break;
				}
				
				cart.clear();
				response.sendRedirect("orders.jsp");
				
			} else {
				
				if(user == null) {
					response.sendRedirect("login.jsp");
				}
				
				else response.sendRedirect("cart.jsp");
			}
			
		} catch(Exception e) {
			e.printStackTrace();
		}
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
