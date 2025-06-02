package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import model.Order;

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
	
	
	
}
