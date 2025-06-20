package control;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.DBConnection;
import model.User;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

import dao.UserDao;

@WebServlet("/common/login")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.sendRedirect("login.jsp");
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		String username = request.getParameter("login-username");
		String password = request.getParameter("login-password");
		
		UserDao udao = new UserDao();
		User user = udao.userLogin(username, password);
		
		if(user != null) {
			out.println("Login successful");
			request.getSession().setAttribute("user", user);
			response.sendRedirect("index.jsp");
		} else {
			out.println("Login failed");
		}
	}

}
