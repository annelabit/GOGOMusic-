package control;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.*;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

import dao.*;
import model.*;
import java.util.*;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
/**
 * Servlet implementation class SearchDataServlet
 */
@WebServlet("/common/search-data")
public class SearchDataServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
 
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		        ProductDao dao = new ProductDao();
		        ShowDao sDao = new ShowDao();
		        
		        ArrayList<Product> products = dao.getProducts();

		        
		        
		        JSONArray json = new JSONArray();
		        for (Product p : products) {
		        	
		        	if(sDao.getShows(p.getId()).isEmpty()) {
		        		continue;
		        	}
		        	
		            JSONObject obj = new JSONObject();
		            try {
						obj.put("name", p.getName());
						obj.put("eventId", p.getId());
			            if (p.getShows() != null && !p.getShows().isEmpty()) {
			                obj.put("showId", p.getShows().get(0).getId());
			            } else {
			                obj.put("showId", JSONObject.NULL);
			            }
			            json.put(obj);
					} catch (JSONException e) {
						e.printStackTrace();
					}
		            
		        }
		        	
		        request.setAttribute("products", products);
		        response.setContentType("application/json");
		        response.getWriter().write(json.toString());
	}
		


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
