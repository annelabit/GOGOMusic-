package control;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.Product;
import model.Show;
import model.User;

import java.io.IOException;
import java.util.ArrayList;

import dao.LocationDao;
import dao.ProductDao;
import dao.SeatDao;
import dao.ShowDao;
import dao.ShowSeatDao;

/**
 * Servlet implementation class AddShowServlet
 */
@WebServlet("/admin/add-show")
public class AddShowServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//controllo se utente è admin
		boolean isAdmin = (boolean) request.getSession().getAttribute("isAdmin");

		User user = (User) request.getSession().getAttribute("user");
		request.setAttribute("user", user);
		
		if(!isAdmin|| isAdmin == false){
			response.sendRedirect(request.getContextPath()+"/common/login.jsp");
			return;
		}
		int pId = Integer.parseInt(request.getParameter("evento"));
		int venueId = Integer.parseInt(request.getParameter("venue"));
		String data = request.getParameter("data");
		String ora = request.getParameter("ora");
		
		float prezzoVip = Float.parseFloat(request.getParameter("prezzo_vip"));
		float prezzoPremium = Float.parseFloat(request.getParameter("prezzo_premium"));
		float prezzoStandard = Float.parseFloat(request.getParameter("prezzo_standard"));
		float prezzoEconomy = Float.parseFloat(request.getParameter("prezzo_economy"));
		
		ProductDao pDao = new ProductDao();
		ShowDao showDao = new ShowDao();
		ShowSeatDao showSeatDao = new ShowSeatDao();
		SeatDao seatDao = new SeatDao();
		LocationDao locationDao = new LocationDao();
		
		
		
		Show s = new Show();
		s.setProductId(pId);
		s.setVenueId(venueId);
		s.setDate(data);
		s.setTime(ora);
		
		s.setId(showDao.insertShow(s));
		
		showSeatDao.addShowSeatPriceByCategory(s, "Vip", prezzoVip);
		showSeatDao.addShowSeatPriceByCategory(s, "Settore1", prezzoPremium);
		showSeatDao.addShowSeatPriceByCategory(s, "Settore2", prezzoStandard);
		//showSeatDao.addShowSeatPriceByCategory(s, "Vip", venueId);
		
		ArrayList<Product> events = new ArrayList<>();
		events = pDao.getProducts();
		
		//showSeatDao.modifyShowSeatPriceByCategory(showDao.getShow(showId), categoria, prezzo);
		
		System.out.println("Ok");
		
		request.getRequestDispatcher("admin-page").include(request, response);
	}

}
