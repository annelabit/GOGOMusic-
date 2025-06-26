package control;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.*;

import java.io.IOException;

import dao.*;

/**
 * Servlet implementation class DeletePaymentMethod
 */
@WebServlet("/common/delete-address")
public class DeleteAddress extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request,response);
		}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		IndirizzoSpedizioneDao mDao = new IndirizzoSpedizioneDao();
		User user = (User) request.getSession().getAttribute("user");
		if (user == null) {
			response.sendRedirect("login.jsp");
		}
		int id = Integer.parseInt(request.getParameter("id"));
		mDao.deleteAddress(id);
		
		request.getRequestDispatcher("user-account").forward(request, response);
		
		
	}

}
