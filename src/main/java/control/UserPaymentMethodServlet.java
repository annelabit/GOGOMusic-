package control;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.*;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

import dao.*;

/**
 * Servlet implementation class UserPaymentMethodServlet
 */
@WebServlet("/common/user-payment-method")
public class UserPaymentMethodServlet extends HttpServlet {
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
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		User user = (User) request.getSession().getAttribute("user");
		if (user == null) {
			response.sendRedirect("login.jsp");
		}
		
		
		
		String tipo = request.getParameter("tipo_carta");
		String numero =  request.getParameter("numero_carta");
		System.out.println(tipo);
		String nomeCarta = request.getParameter("nome_carta");
		//no cognome?
		String scadenza = request.getParameter("scadenza"); 
		
		int cvv = Integer.parseInt(request.getParameter("cvv"));
		
		String numeroPulito = numero.replaceAll("\\D", ""); // rimuove spazi e caratteri non numerici
		int last4 = Integer.parseInt(numeroPulito.substring(numeroPulito.length() - 4));
		
		System.out.println(numeroPulito);
		System.out.println(last4);
		
		int main = 0;
		
		if(request.getParameter("principale")!=null)
			main = 1;
		
		if(user.getMethods() == null || user.getMethods().isEmpty()) main = 1;
		
		MetodoPagamento m = new MetodoPagamento();
		MetodoPagamentoDao aDao = new MetodoPagamentoDao();
		m.setUserId(user.getIdUtente());
		m.setNomeCarta(nomeCarta);
		m.setTipo(tipo);
		//m.setNumeroCarta(toHash(numero));
		m.setNumeroCarta(numero);
		m.setMain(main);
		m.setScadenza(scadenza);
		m.setCvv(cvv);
		m.setLast4Numbers(last4);
		m.setId(aDao.insertMethod(m));
		
		user.setMethods(aDao.getAllPaymentMethods(user.getIdUtente()));
		
		request.setAttribute("user", user);
		request.getSession().setAttribute("user",user);
		request.getRequestDispatcher("account.jsp").forward(request, response);
	}

}
