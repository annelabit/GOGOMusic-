package control;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.*;
import dao.*;

import java.io.IOException;
import java.util.ArrayList;

/**
 * Servlet implementation class UpdatePriceServlet
 */
@WebServlet("/admin/update-price")
public class UpdatePriceServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
  
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		doPost(request, response);
		
		
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//controllo se utente è admin
				boolean isAdmin = (boolean) request.getSession().getAttribute("isAdmin");

				
				
				if(!isAdmin|| isAdmin == false){
					response.sendRedirect(request.getContextPath()+"/common/login.jsp");
					return;
				}
				
				User user = (User) request.getSession().getAttribute("user");
				request.setAttribute("user", user);
				
				String categoria = request.getParameter("categoria");
				float prezzo = Float.parseFloat(request.getParameter("prezzo"));
				int showId = Integer.parseInt(request.getParameter("spettacolo"));

				
				ProductDao pDao = new ProductDao();
				ShowDao showDao = new ShowDao();
				ShowSeatDao showSeatDao = new ShowSeatDao();
				SeatDao seatDao = new SeatDao();
				LocationDao locationDao = new LocationDao();
				
				ArrayList<Product> events = new ArrayList<>();
				events = pDao.getProducts();
				
				showSeatDao.modifyShowSeatPriceByCategory(showDao.getShow(showId), categoria, prezzo);
				
				System.out.println("Ok");
				
				request.getRequestDispatcher("admin-page").include(request, response);
				
	}

}
