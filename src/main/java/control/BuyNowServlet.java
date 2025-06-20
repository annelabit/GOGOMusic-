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
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import dao.OrderDao;
import dao.ProductDao;

import java.time.LocalTime;
/**
 * Servlet implementation class BuyNowServlet
 */
@WebServlet("/common/buy-now")
public class BuyNowServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		try(PrintWriter out = response.getWriter()){
			//utente deve essere logged in
			User user = (User) request.getSession().getAttribute("user");
			
			SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
			Date date = new Date();
			
			java.sql.Date dateSql = new java.sql.Date(Calendar.getInstance().getTime().getTime());
			
			ProductDao pDao = new ProductDao();
			
			if(user != null) {
				
				int id = Integer.parseInt(request.getParameter("id"));
				int quantity = Integer.parseInt(request.getParameter("quantity"));
				if(quantity<=0) {
					quantity = 1;//quantità minima 1
				}
				
				LocalTime currentTime = LocalTime.now();
				String formattedTime = String.format("%02d:%02d:%02d", currentTime.getHour(), currentTime.getMinute(), currentTime.getSecond());
				
				
				Order order = new Order();
				order.setId(id);//id prodotto
				order.setUid(user.getIdUtente());
				order.setQuantity(quantity);
				
				order.setDate(dateSql);
				order.setTime(formattedTime);
				
				ArrayList<Cart> cart = (ArrayList<Cart>) request.getSession().getAttribute("cart_list"); 
				
				//trovo l'oggetto nel carrello per sommare tutti i prezzi dei posti
				//non elegantissimo
				if(cart!=null) {
					for(Cart c : cart) {
						if(c.getId() == id) {
							order.setPrice((float) pDao.getPriceForSelected(c.getSeatIds(), c.getShowId()));
							order.setShowId(c.getShowId());
							break;
						}
					}
				}
				
				
				OrderDao oDao = new OrderDao();

					if(cart!=null) {
						for(Cart c : cart) {
							if(c.getId() == id) {
								oDao.insertOrder(order, c.getSeatIds());
								cart.remove(cart.indexOf(c));
								break;
							}
						}
					response.sendRedirect("orders.jsp");
				} else {
					out.print("Order failed");
				}
				
				
				
			} else {
				response.sendRedirect("login.jsp");
			}
		}
	
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
