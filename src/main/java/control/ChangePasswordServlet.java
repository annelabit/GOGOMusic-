package control;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

import dao.*;
import model.*;

/**
 * Servlet implementation class ChangePasswordServlet
 */
@WebServlet("/common/change-password")
public class ChangePasswordServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
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
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request,response);
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		
		
		User user = (User) request.getSession().getAttribute("user");
		if (user == null) {
			response.sendRedirect("login.jsp");
		}
		
		UserDao uDao = new UserDao();
		
		String passwordAttuale = request.getParameter("password_attuale");
		String nuovaPassword = request.getParameter("nuova_password");
		String confermaPassword = request.getParameter("conferma_password");
		
		String password = uDao.getHashedPassword(user.getIdUtente());
		
		if(password.compareTo(toHash(passwordAttuale))==0 && nuovaPassword.compareTo(confermaPassword)==0) {
			uDao.setNewPassword(toHash(nuovaPassword), user.getIdUtente());
		}
		request.setAttribute("user", user);
		request.getSession().setAttribute("user",user);
		request.getRequestDispatcher("account.jsp").forward(request, response);
		
		
		
	}

}
