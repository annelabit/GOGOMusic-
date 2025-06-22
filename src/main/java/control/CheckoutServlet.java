package control;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.Cart;
import model.DBConnection;
import model.Order;
import model.User;

import java.io.IOException;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;
import java.text.SimpleDateFormat;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import dao.OrderDao;
import dao.ProductDao;

/**
 * Servlet implementation class CheckoutServlet
 */
@WebServlet("/common/checkout")
public class CheckoutServlet extends HttpServlet {
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
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		doPost(request, response);

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		try (PrintWriter out = response.getWriter()) {

			SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
			Date date = new Date();
			java.sql.Date dateSql = new java.sql.Date(Calendar.getInstance().getTime().getTime());

			// tutti i prodotti del carrello
			ArrayList<Cart> cart = (ArrayList<Cart>) request.getSession().getAttribute("cart_list");

			// utente deve essere logged in
			User user = (User) request.getSession().getAttribute("user");

			OrderDao oDao = new OrderDao();
			ProductDao pDao = new ProductDao();

			if (user != null && cart != null) {

				for (Cart c : cart) {

					LocalTime currentTime = LocalTime.now();
					String formattedTime = String.format("%02d:%02d:%02d", currentTime.getHour(),
							currentTime.getMinute(), currentTime.getSecond());

					Order order = new Order();
					order.setId(c.getId());// product
					order.setUid(user.getIdUtente());
					order.setQuantity(c.getQuantity());
					// order.setDate(formatter.format(date));
					order.setDate(dateSql);
					order.setTime(formattedTime);
					order.setPrice((float) pDao.getPriceForSelected(c.getSeatIds(), c.getShowId()));
					order.setShowId(c.getShowId());
					
					order.setNome(request.getParameter("name"));
					order.setEmail(request.getParameter("email"));
					order.setIndirizzo(request.getParameter("indirizzo"));
					order.setCittà(request.getParameter("città"));
					order.setPaese(request.getParameter("paese"));
					order.setCap(Integer.parseInt(request.getParameter("cap")));
					order.setNomeTitolare(request.getParameter("nomeTitolare"));
					order.setNumeroCarta(toHash(request.getParameter("cardNumber")));
					order.setMeseScadenza(Integer.parseInt(request.getParameter("mese")));
					order.setAnnoScadenza(Integer.parseInt(request.getParameter("anno")));
					order.setCvv(Integer.parseInt(request.getParameter("cvv")));
					
					boolean result = oDao.insertOrder(order, c.getSeatIds());
					
					System.out.println("Ok " + result);
					
					if (!result)
						break;
				}

				cart.clear();
				response.sendRedirect("orders.jsp");

			} else {

				if (user == null) {
					System.out.println("User null");
					response.sendRedirect("login.jsp");
				}

				else
					response.sendRedirect("cart.jsp");
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	

}
