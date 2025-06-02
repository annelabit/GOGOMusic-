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
	
	public boolean insertOrder(Order order) {
		
		boolean result = false;
			
		try {
			
			query = "INSERT INTO `order` (productId, userId, quantity, date) VALUES(?,?,?,?)";
			
			pst = this.connection.prepareStatement(query);
			pst.setInt(1, order.getId());
			pst.setInt(2, order.getUid());
			pst.setInt(3, order.getQuantity());
			pst.setString(4, order.getDate());
			pst.executeUpdate();
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
				order.setPrice(product.getPrice()*rs.getInt("quantity"));
				order.setQuantity(rs.getInt("quantity"));
				order.setDate(rs.getString("date"));
				orderList.add(order);
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		
		
		
		
		return orderList;
	}
	
	public void cancelOrder(int id) {
		
		try {
			query = "DELETE FROM `order` WHERE orderId=?";
			pst = this.connection.prepareStatement(query);
			pst.setInt(1, id);
			pst.execute();
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		
	}
	
}
