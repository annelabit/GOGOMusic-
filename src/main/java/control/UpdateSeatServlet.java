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
import java.util.ArrayList;

import dao.SeatDao;
import dao.ShowSeatDao;

/**
 * Servlet implementation class UpdateSeatServlet
 */
@WebServlet("/common/update-seat")
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

		//stringa con gli id dei posti
		String seatIdsString = request.getParameter("seatIds"); 
		
		//gli id erano separati da virgola
		String[] seatIds = seatIdsString.split(",");
		
		int pId = Integer.parseInt(request.getParameter("pId"));
		int showId = Integer.parseInt(request.getParameter("showId"));
		
		User user = (User) request.getSession().getAttribute("user");
		//int uId = user.getIdUtente();
		
		//converto array di stringhe in arrayList di interi
		ArrayList<Integer> seatIdList = new ArrayList<>();
		for (String id : seatIds) {
		    try {
		        seatIdList.add(Integer.parseInt(id));
		    } catch (Exception e) {
		        e.printStackTrace();
		    }
		}
		
		try (PrintWriter out = response.getWriter()){
		
			//SeatDao seatDao = new SeatDao(DBConnection.getConnection());
			ShowSeatDao showSeatDao = new ShowSeatDao();
			SeatDao seatDao = new SeatDao();
			String type = seatDao.getSeatsByShowSeatId(seatIdList.getFirst()).getType();
			showSeatDao.reserveSeats(showId,seatIdList);
			
			response.sendRedirect("add-to-cart?id="+pId+"&quantity="+request.getParameter("quantity")+"&seatIds="+seatIdsString+"&showId="+showId
					+"&type="+type);
			
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
