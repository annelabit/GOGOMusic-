package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import model.Order;
import model.Product;

public class OrderDao {

	private Connection connection;
	private String query;
	private PreparedStatement pst;
	private ResultSet rs;
	
	public OrderDao(Connection connection) {
		this.connection = connection;
	}
	
	public boolean insertOrder(Order order, ArrayList<Integer> seatIds) {
		
		boolean result = false;
			
		try {
			
			//devo inserire sia in order sia in order_seats
			query = "INSERT INTO `order` (productId, userId, quantity, date, totalPrice, time, showId) VALUES(?,?,?,?,?,?,?)";
			
			pst = this.connection.prepareStatement(query, PreparedStatement.RETURN_GENERATED_KEYS);
			pst.setInt(1, order.getId());
			pst.setInt(2, order.getUid());
			pst.setInt(3, order.getQuantity());
			pst.setString(4, order.getDate());
			pst.setFloat(5, order.getPrice());
			pst.setString(6, order.getTime());
			pst.setInt(7, order.getShowId());
			pst.executeUpdate();
			
			//order è auto increment quindi devo prendere id
			ResultSet generatedKeys = pst.getGeneratedKeys();
	        int orderId = 0;
	        if (generatedKeys.next()) {
	            orderId = generatedKeys.getInt(1);
	        }
			
			for(int s : seatIds) {
			//devo recuperare showSeatId
				query = "SELECT * FROM show_seats WHERE seatId = ? AND showId = ?";
				pst = this.connection.prepareStatement(query);
				pst.setInt(1, s);
				pst.setInt(2, order.getShowId());
				rs = pst.executeQuery();
				
				int showSeatId=0;
				
				if(rs.next()) {
					showSeatId = rs.getInt("id");
				}
				
				String query = "INSERT INTO order_seats (orderID, showSeatId) VALUES(?,?)";
			
				pst = this.connection.prepareStatement(query);
				pst.setInt(1, orderId );
				pst.setInt(2, showSeatId);
				pst.executeUpdate();
				
			}
			
			result = true; //se è arrivato qui non ci sono eccezioni
			
		}catch(Exception e) {
			e.printStackTrace();
			System.out.print(e.getMessage());	
		}
				
		return result;
		
	}
	
	public ArrayList<Order> userOrders(int id){
		
		ArrayList<Order> orderList = new ArrayList<>();
		
		ProductDao pDao = new ProductDao(this.connection);
		
		try {
			
			query = "SELECT * FROM `order` WHERE userId=? ORDER BY orderId DESC";
			pst = this.connection.prepareStatement(query);
			pst.setInt(1, id);
			rs = pst.executeQuery();
			
			while(rs.next()) {
				Order order = new Order();
				int pId = rs.getInt("productId");
				
				Product product = pDao.getSingleProduct(pId);
				order.setOrderId(rs.getInt("orderId"));
				order.setId(pId);
				order.setName(product.getName());
				order.setCategory(product.getCategory());
				order.setPrice(rs.getFloat("totalPrice"));
				order.setQuantity(rs.getInt("quantity"));
				order.setDate(rs.getString("date"));
				order.setTime(rs.getString("time"));
				orderList.add(order);
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		return orderList;
	}
	
	//order id
	public boolean cancelOrder(int id) {
		
		boolean result = false;
		
		try {
			
			//posti associati all'ordine
			query = "SELECT * FROM order_seats WHERE orderId = ?";
			pst = this.connection.prepareStatement(query);
			pst.setInt(1, id);
			
			rs = pst.executeQuery();
			
			while(rs.next()) {
				
				int showSeatId = rs.getInt("showSeatId");
				
				//i posti sono di nuovo disponibili
				query = "UPDATE show_seats SET available = 1 WHERE id = ?";
				pst = this.connection.prepareStatement(query);
				pst.setInt(1, showSeatId);
				pst.executeUpdate();
				
			}
			
			//cancello entry sia in order sia in order_seats
			
			query = "DELETE FROM order_seats WHERE orderId=?";
			pst = this.connection.prepareStatement(query);
			pst.setInt(1, id);
			pst.execute();
			
			query = "DELETE FROM `order` WHERE orderId=?";
			pst = this.connection.prepareStatement(query);
			pst.setInt(1, id);
			pst.execute();
			
			result = true;
			
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		return result;
		
	}
	
}
