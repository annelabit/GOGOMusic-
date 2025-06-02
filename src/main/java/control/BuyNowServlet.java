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
import java.util.Date;

import dao.OrderDao;

/**
 * Servlet implementation class BuyNowServlet
 */
@WebServlet("/buy-now")
public class BuyNowServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		try(PrintWriter out = response.getWriter()){
			//utente deve essere logged in
			User user = (User) request.getSession().getAttribute("user");
			
			SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
			Date date = new Date();
			
			
			if(user != null) {
				
				int id = Integer.parseInt(request.getParameter("id"));
				int quantity = Integer.parseInt(request.getParameter("quantity"));
				if(quantity<=0) {
					quantity = 1;//quantità minima 1
				}
				
				Order order = new Order();
				order.setId(id);//id prodotto
				order.setUid(user.getIdUtente());
				order.setQuantity(quantity);
				order.setDate(formatter.format(date));
				
				try {
					OrderDao oDao = new OrderDao(DBConnection.getConnection());
					boolean result = oDao.insertOrder(order);
				
					if(result) {
						ArrayList<Cart> cart = (ArrayList<Cart>) request.getSession().getAttribute("cart_list"); 
						if(cart!=null) {
							for(Cart c : cart) {
								if(c.getId() == id) {
									cart.remove(cart.indexOf(c));
									break;
								}
							}
							
						}
						response.sendRedirect("orders.jsp");
					} else {
						out.print("Order failed");
					}
					
				} catch (ClassNotFoundException e) {
					e.printStackTrace();
				} catch (SQLException e) {
					e.printStackTrace();
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
