package control;

import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.Cart;
import model.DBConnection;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.ArrayList;

import dao.ShowSeatDao;

/**
 * Servlet implementation class RemoveFromCartServlet
 */
@WebServlet("/common/remove-from-cart")
public class RemoveFromCartServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
  
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		response.setContentType("text/html;charset=UTF-8");
		
		try(PrintWriter out = response.getWriter()){
			ShowSeatDao showSeatDao = new ShowSeatDao(DBConnection.getConnection());
			int id = Integer.parseInt(request.getParameter("id"));
			if(id!=0) {//valore di default per interi è 0
				ArrayList<Cart> cart = (ArrayList<Cart>) request.getSession().getAttribute("cart_list"); 
				if(cart!=null) {
					for(Cart c : cart) {
						if(c.getId() == id) {
							showSeatDao.setSeatsAvailable(c.getShowId(), c.getSeatIds());
							cart.remove(cart.indexOf(c));
							response.sendRedirect("cart.jsp");
							break;
						}
					}
					
				}
			}
			else {
				response.sendRedirect("cart.jsp");
			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
		
	

}
