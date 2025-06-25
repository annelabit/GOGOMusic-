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
@WebServlet("/admin/add-event")
public class AddProductServlet extends HttpServlet {
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
		
		String data = request.getParameter("data");
		String ora = request.getParameter("ora");
		
		String nome = request.getParameter("nome");
		int venueId = Integer.parseInt(request.getParameter("venue_id"));
		String img = request.getParameter("immagine");
		String descrizione = request.getParameter("descrizione");
		String categoria = request.getParameter("categoria");
		
		ProductDao pDao = new ProductDao();
		
		Product p = new Product();
		p.setVenueId(venueId);
		p.setName(nome);
		p.setImage(img);
		p.setDescrizione(descrizione);
		p.setCategory(categoria);
		
		p.setId(pDao.insertEvent(p));

		ArrayList<Product> events = new ArrayList<>();
		events = pDao.getProducts();
		
		System.out.println("Ok");
		
		request.getRequestDispatcher("admin-page").include(request, response);
	}

}
