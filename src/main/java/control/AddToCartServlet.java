package control;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import model.*;
/**
 * Servlet implementation class AddToCartServlet
 */
@WebServlet("/add-to-cart")
public class AddToCartServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html;charset=UTF-8");
		
		try(PrintWriter out = response.getWriter()) {
			//verrà passato l'id del prodotto aggiunto al carrello a cart
			ArrayList<Cart> cart = new ArrayList<>();
			
			int id = Integer.parseInt(request.getParameter("id"));
			Cart cartItem = new Cart();
			cartItem.setId(id);
			cartItem.setQuantity(1);
			
			//se esiste una sessione restituirà il carrello
			HttpSession session = request.getSession();
			ArrayList<Cart> cartList = (ArrayList<Cart>) session.getAttribute("cart_list");
			
			//se sessione non esiste creo carrello
			if(cartList == null) {
				cart.add(cartItem);
				session.setAttribute("cart_list", cart);
				response.sendRedirect("cart.jsp");
			}else {
				cart = cartList;
				boolean exists = false;
				
				//controlla se il prodotto è già presente nel carrello
				for(Cart c : cartList) {
					if(c.getId() == id) {
						exists = true;
						c.setQuantity(c.getQuantity()+1);
						response.sendRedirect("cart.jsp");
					}
				}	
					if(!exists) {//se non è già presente lo aggiungo al carrello
						cart.add(cartItem);
						response.sendRedirect("cart.jsp");
					}
		
			}
			
			
		}
		
	}

}
