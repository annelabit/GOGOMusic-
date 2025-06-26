package control;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.User;

import java.io.IOException;

import dao.UserDao;

/**
 * Servlet implementation class UserInformationServlet
 */
@WebServlet("/common/user-info")
public class UserInformationServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		doPost(request, response);
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		User user = (User) request.getSession().getAttribute("user");
		
		if (user == null) {
			response.sendRedirect("login.jsp");
		}
		
		System.out.println("ok");
		
		String username = request.getParameter("username");
		String email = request.getParameter("email");
		String nome = request.getParameter("nome");
		String cognome = request.getParameter("cognome");
		
		user.setNome(nome);
		user.setCognome(cognome);
		user.setEmail(email);
		user.setUsername(username);
		request.setAttribute("user", user);
		request.getSession().setAttribute("user",user);
		System.out.println(nome);
		UserDao uDao = new UserDao();
		boolean ok = uDao.updateUserInfo(user);
		System.out.println(ok);
		request.getRequestDispatcher("account.jsp").forward(request, response);
		
	}

}
