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

import dao.OrderDao;

/**
 * Servlet implementation class CancelOrderServlet
 */
@WebServlet("/cancel-order")
public class CancelOrderServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
   
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		try(PrintWriter out = response.getWriter()){
			
			int id = Integer.parseInt(request.getParameter("id"));
			
			if(id!=0) {
				OrderDao orderDao = new OrderDao(DBConnection.getConnection());
				orderDao.cancelOrder(id);
			}
			response.sendRedirect("orders.jsp");
			
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
