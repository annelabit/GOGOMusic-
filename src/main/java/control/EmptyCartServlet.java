package control;

import jakarta.servlet.ServletException;
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
 * Servlet implementation class EmptyCartServlet
 */
@WebServlet("/common/empty-cart")
public class EmptyCartServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
   
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		response.setContentType("text/html;charset=UTF-8");
		
		try(PrintWriter out = response.getWriter()){
			
			ShowSeatDao showSeatDao = new ShowSeatDao();

				ArrayList<Cart> cart = (ArrayList<Cart>) request.getSession().getAttribute("cart_list"); 
				if(cart!=null) {
					for(Cart c : cart) {
							showSeatDao.setSeatsAvailable(c.getShowId(), c.getSeatIds());
					}
				}
				cart.clear();
				response.sendRedirect("cart.jsp");
				
		}
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
