package control;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.IndirizzoSpedizione;
import model.User;
import dao.*;
import java.io.IOException;
import java.util.ArrayList;

import dao.IndirizzoSpedizioneDao;

/**
 * Servlet implementation class UserAddressServlet
 */
@WebServlet("/common/user-address")
public class UserAddressServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
   
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		User user = (User) request.getSession().getAttribute("user");
		if (user == null) {
			response.sendRedirect("login.jsp");
		}
		
		String nomeDestinatario = request.getParameter("nome_destinatario");
		String cognomeDestinatario = request.getParameter("cognome_destinatario");
		String indirizzo = request.getParameter("via");
		String città = request.getParameter("citta");
		String paese = request.getParameter("paese");
		int cap = Integer.parseInt(request.getParameter("cap"));
		
		int main = 0;
		
		if(request.getParameter("principale")!=null)
			main = 1;
		
		if(user.getAddresses() == null || user.getAddresses().isEmpty()) main = 1;
		
		IndirizzoSpedizione a = new IndirizzoSpedizione();
		IndirizzoSpedizioneDao aDao = new IndirizzoSpedizioneDao();
		a.setNomeDestinatario(nomeDestinatario);
		a.setCognomeDestinatario(cognomeDestinatario);
		a.setIndirizzo(indirizzo);
		a.setCittà(città);
		a.setPaese(paese);
		a.setCAP(cap);
		a.setUserId(user.getIdUtente());
		a.setMain(main);
		a.setId(aDao.insertAddress(a));
		
		user.setAddresses(aDao.getAllAddresses(user.getIdUtente()));
		
		request.setAttribute("user", user);
		request.getSession().setAttribute("user",user);
		request.getRequestDispatcher("account.jsp").forward(request, response);
	}

}
