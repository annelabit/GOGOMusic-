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
import java.nio.charset.StandardCharsets;
import java.sql.SQLException;

import dao.UserDao;

@WebServlet("/common/loginServlet")
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
		User user = udao.userLogin(username, toHash(password));
		
		if(user != null) {
			
			System.out.println("Login successful");
			request.getSession().setAttribute("user", user);
			
			if(user.isAdmin()) {
				request.getSession().setAttribute("isAdmin", Boolean.TRUE);//inserisco il token nella sessione
			} else {
				request.getSession().setAttribute("isAdmin", Boolean.FALSE);//inserisco il token nella sessione
			}
			
			response.sendRedirect("index.jsp");
		} else {
			System.out.println("Login failed");
			response.sendRedirect("login.jsp");
			//login dovrà mostrare un messaggio di errore
		}
	}
	
	private String toHash(String password) {
		String hashString = null;
		try {
			java.security.MessageDigest digest = java.security.MessageDigest.getInstance("SHA-512");
			byte[] hash = digest.digest(password.getBytes(StandardCharsets.UTF_8));
			hashString = "";
			for (int i = 0; i < hash.length; i++) {
				hashString += Integer.toHexString((hash[i] & 0xFF) | 0x100).substring(1, 3);
			}
		} catch (java.security.NoSuchAlgorithmException e) {
			System.out.println(e);
		}
		return hashString;
	}

}
