package control;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

import dao.*;
import model.*;

/**
 * Servlet implementation class DeleteShowServlet
 */
@WebServlet("/admin/delete-user")
public class DeleteUserServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		if (request.getSession().getAttribute("isAdmin")==null || (boolean) request.getSession().getAttribute("isAdmin") == false) {
			response.sendRedirect(request.getContextPath() + "/common/login.jsp");
			return;
		}
		User user = (User) request.getSession().getAttribute("user");
		request.setAttribute("user", user);
		System.out.println("Delete user");
		int uId = Integer.parseInt(request.getParameter("uId"));
		
		UserDao userDao = new UserDao();
		
		userDao.deleteUser(uId);
		
		request.getRequestDispatcher("admin-page").forward(request, response);
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
