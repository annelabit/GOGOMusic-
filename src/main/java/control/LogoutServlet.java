package control;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.Cart;
import model.Product;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import dao.ProductDao;
import dao.ShowSeatDao;

/**
 * Servlet implementation class LogoutServlet
 */
@WebServlet("/common/logout")
public class LogoutServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
   
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try(PrintWriter out= response.getWriter()) {
			
			ShowSeatDao showSeatDao = new ShowSeatDao();
			
			System.out.println("In logout Servlet");
			
			if(request.getSession().getAttribute("user")!= null) {  //attributo user (da settare) nella servlet Login
				ArrayList<Cart> cart = (ArrayList<Cart>) request.getSession().getAttribute("cart_list"); 
				if(cart!=null) {
					for(Cart c : cart) {
							showSeatDao.setSeatsAvailable(c.getShowId(), c.getSeatIds());
					}
					cart.clear();
				}
				
					request.getSession().invalidate();
					System.out.println("Logout successful");
					response.sendRedirect("login.jsp");
					} else { 
				response.sendRedirect("index.jsp");
					}
		}catch(Exception e){
			
		}
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
