package control;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.User;

import java.io.IOException;

import dao.*;

/**
 * Servlet implementation class UserAccountServlet
 */
@WebServlet("/common/user-account")
public class UserAccountServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UserAccountServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		User user = (User) request.getSession().getAttribute("user");
		if (user == null) {
			response.sendRedirect("login.jsp");
		}
		
		IndirizzoSpedizioneDao addressDao = new IndirizzoSpedizioneDao();
		MetodoPagamentoDao mDao = new MetodoPagamentoDao();
		user.setAddresses(addressDao.getAllAddresses(user.getIdUtente()));
		user.setMethods(mDao.getAllPaymentMethods(user.getIdUtente()));
		//stessa cosa per metodo pagamento
		
		request.setAttribute("user", user);
		
		request.getRequestDispatcher("account.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		doGet(request, response);
	}

}
