package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import model.Product;


public class ProductDao {

	private Connection connection;
	private String query;
	private PreparedStatement pst;
	private ResultSet rs;
	
	public ProductDao(Connection connection) {
		super();
		this.connection = connection;
	}
	
	public ArrayList<Product> getProducts(){
		
		ArrayList<Product> products = new ArrayList<Product>();
		
		try {
			
			query = "SELECT * FROM PRODUCT";
			pst = this.connection.prepareStatement(query);
			rs = pst.executeQuery();
			
			while(rs.next()) {
				Product prod = new Product();
				prod.setId(rs.getInt("id"));
				prod.setName(rs.getString("name"));
				prod.setCategory(rs.getString("category"));
				prod.setPrice(rs.getFloat("price"));
				prod.setImage(rs.getString("image"));
				
				products.add(prod);
			}
			
		}	
		catch(Exception e) {
			e.printStackTrace();
		}
		
		return products;
	}
	
	
}
