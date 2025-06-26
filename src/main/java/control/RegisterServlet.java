package control;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.Product;
import model.User;

import java.io.IOException;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Calendar;

import dao.ProductDao;
import dao.UserDao;

/**
 * Servlet implementation class RegisterServlet
 */
@WebServlet("/common/register")
public class RegisterServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RegisterServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.sendRedirect("login.jsp");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		String username = request.getParameter("register-username");
		String email = request.getParameter("register-email");
		String password = request.getParameter("register-password");
		String nome = request.getParameter("register-nome");
		String cognome = request.getParameter("register-cognome");

		
		UserDao udao = new UserDao();
		User user = new User();
		user.setUsername(username);
		user.setEmail(email);
		user.setNome(nome);
		user.setCognome(cognome);
		user.setAdmin(false);//se si sta iscrivendo adesso non è un admin
		java.sql.Date dateSql = new java.sql.Date(Calendar.getInstance().getTime().getTime());
		user.setDate(dateSql);
		int id = udao.userRegister(user, toHash(password));
		user.setIdUtente(id);
		System.out.println(user.toString());
		if(id==0) {
			//DOVREBBE MOSTRARE MESSAGGIO DI ERRORE EMAIL GIà ESISTENTE ECC
			response.sendRedirect("login.jsp");
		}else {
			
			request.getSession().setAttribute("user", user);
			
			request.getSession().setAttribute("isAdmin", Boolean.FALSE);//inserisco il token nella sessione
			
			response.sendRedirect("index");
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
