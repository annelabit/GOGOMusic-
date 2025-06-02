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

import dao.SeatDao;

/**
 * Servlet implementation class UpdateSeatServlet
 */
@WebServlet("/update-seat")
public class UpdateSeatServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UpdateSeatServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		int seatId = Integer.parseInt(request.getParameter("seatId"));
		int pId = Integer.parseInt(request.getParameter("pId"));
		User user = (User) request.getSession().getAttribute("user");
		int uId = user.getIdUtente();
		
		try (PrintWriter out = response.getWriter()){
			
			SeatDao seatDao = new SeatDao(DBConnection.getConnection());
			boolean result = seatDao.reserveSeat(seatId, uId);
			
			response.setContentType("application/json");
			response.getWriter().print("{\"success\": true}");
			//response.getWriter().write("{\"success\": " + result + "}");
			
			response.sendRedirect("add-to-cart?id="+pId);
			
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
