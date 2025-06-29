package control;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.DBConnection;
import model.Product;
import model.User;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.ArrayList;

import dao.OrderDao;
import dao.ProductDao;

/**
 * Servlet implementation class CancelOrderServlet
 */
@WebServlet("/admin/cancel-order")
public class CancelOrderAdminServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
   
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		try(PrintWriter out = response.getWriter()){
			
			int id = Integer.parseInt(request.getParameter("orderId"));
			
			if(id!=0) {
				OrderDao orderDao = new OrderDao();
				orderDao.cancelOrder(id);
			}
			response.sendRedirect("admin-page");
			
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
