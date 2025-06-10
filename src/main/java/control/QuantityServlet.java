package control;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.Cart;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

/**
 * Servlet implementation class QuantityServlet
 */
@WebServlet("/common/quantity")
public class QuantityServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
		response.setContentType("text/html;charset=UTF-8");
		
		try(PrintWriter out = response.getWriter();){
			String action = request.getParameter("action");//action=int o dec
			int id = Integer.parseInt(request.getParameter("id"));
			request.getSession().getAttribute("cart_list");
			
			ArrayList<Cart> cart = (ArrayList<Cart>) request.getSession().getAttribute("cart_list");
			
			if(action != null && id>=1) {
				 
					for(Cart c : cart) {
						
						if(c.getId()==id) {
							
							int quantity = c.getQuantity();
							if(action.equals("inc")) {
								c.setQuantity(quantity+1);
								response.sendRedirect("cart.jsp");
								break;
							} else if(action.equals("dec") && c.getQuantity()>1) {//quantità non può essere 0
								c.setQuantity(quantity-1);
								response.sendRedirect("cart.jsp");
								break;
							} else response.sendRedirect("cart.jsp");
							
							
						}
						
					}
				
			} else response.sendRedirect("cart.jsp");
		}
	}

}
