package control;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.ArrayList;

import dao.ProductDao;
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
			
			ProductDao pDao = new ProductDao(DBConnection.getConnection());
		    
			
			int id = Integer.parseInt(request.getParameter("id"));
			int showId = Integer.parseInt(request.getParameter("showId"));
			
			Product p = pDao.getSingleProduct(id);
			Cart cartItem = new Cart(p);
			cartItem.setId(id);
			cartItem.setShowId(showId);
			
			int q = Integer.parseInt(request.getParameter("quantity"));
			cartItem.setQuantity(q);
			
			//voglio che ogni cartItem abbia anche le altre informazioni del prodotto
			//product dao può restituire il prodotto dato un id
			
			
			//stringa con gli id dei posti
			String seatIdsString = request.getParameter("seatIds"); 
			
			//gli id erano separati da virgola
			String[] seatIds = seatIdsString.split(",");
			
			ArrayList<Integer> seatIdList = new ArrayList<>();
			
			//aggiungi le stringhe dalla sessione alla lista da passare al carrello
			if(seatIds != null) {
				
				for(String s : seatIds) {
					try {
						seatIdList.add(Integer.parseInt(s));
					} catch(Exception e) {
						e.printStackTrace();
					}
				}
			}
			
			//nuovo oggetto del carrello che contiene l'array con i posti da inserire
			cartItem.setSeatIds(seatIdList);
			
			//se esiste una sessione restituirà il carrello
			HttpSession session = request.getSession();
			
			//carrello GIà ESISTENTE della sessione
			ArrayList<Cart> cartList = (ArrayList<Cart>) session.getAttribute("cart_list");
			boolean exists = false;
			
			//se sessione non esiste creo carrello
			if(cartList == null) {
				cart.add(cartItem); //aggiungo il primo elemento al carrello
				session.setAttribute("cart_list", cart);
				//session.setAttribute("seat_ids_list", seatIds);
				response.sendRedirect("cart.jsp");
			}else {
				//carrello esiste
				cart = cartList;
				
				//controlla se il prodotto è già presente nel carrello
				for(Cart c : cart) {
					//controllo sia l'id dell'evento sia dello spettacolo
					if(c.getId() == id && c.getShowId() == showId) {//c'è già
						
						exists = true;
						
						//aggiorna quantità
						c.setQuantity(c.getQuantity() + q);

						//aggiorna i posti esistenti
						ArrayList<Integer> existingSeats = c.getSeatIds();
						if (existingSeats == null) {
							existingSeats = new ArrayList<>();
						}
						if (seatIdList != null && !seatIdList.isEmpty()) {
							for (Integer s : seatIdList) {
								if (!existingSeats.contains(s)) {
									existingSeats.add(s);
								}
							}
						}
						existingSeats.sort(null);
						c.setSeatIds(existingSeats);
						//session.setAttribute("cart_list",cart);
						//response.sendRedirect("cart.jsp");
						break;
					} 
				}
				
				if(exists == false) {
					cart.add(cartItem);
				}
				
				session.setAttribute("cart_list",cart);
				response.sendRedirect("cart.jsp");
			
			}
		
		} catch (ClassNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}
}
