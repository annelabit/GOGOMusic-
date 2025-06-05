package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import model.Cart;
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
				prod.setVenueId(rs.getInt("venueId"));
				products.add(prod);
			}
			
		}	
		catch(Exception e) {
			e.printStackTrace();
		}
		
		return products;
	}
	
	public ArrayList<Cart> getCartProducts(ArrayList<Cart> cart){
		ArrayList<Cart> cartProducts = new ArrayList<Cart>();
		
		try{
			
			if(cart.size()>0) {
				for(Cart cartItem : cart) {
					query = "SELECT * FROM PRODUCT WHERE ID=?";
					pst = this.connection.prepareStatement(query);
					pst.setInt(1, cartItem.getId());
					rs = pst.executeQuery();
					while(rs.next()) {
						
						Cart c = new Cart();
						c.setId(rs.getInt("id"));
						c.setName(rs.getString("name"));
						c.setCategory(rs.getString("category"));
						c.setPrice(rs.getFloat("price")*cartItem.getQuantity());
						c.setQuantity(cartItem.getQuantity());
						c.setVenueId(rs.getInt("venueId"));
						cartProducts.add(c);
					}
					
				}
			}
			
			
		}catch(Exception e){
			e.printStackTrace();
			System.out.print(e.getMessage());		
		}
		
		return cartProducts;
	}
	
	public double getTotalPrice(ArrayList<Cart> cart) {
		
		double total = 0;
		
		try {
			
			for(Cart c : cart) {
					
				if(c.getSeatIds() != null && !c.getSeatIds().isEmpty()) { 
					
					for(Integer seatId : c.getSeatIds()) {
					
						query = "SELECT PRICE FROM SEAT WHERE ID = ?";
						pst = this.connection.prepareStatement(query);
						pst.setInt(1, seatId);
					
						rs = pst.executeQuery();
					
						while(rs.next()) {
							total+=rs.getFloat("price");
						}
					
					}
				}
			
			}	
		} catch(Exception e) {
			e.printStackTrace();
			System.out.print(e.getMessage());	
		}
		
		return total;
		
	}
	
	
	
	public double getPriceForSelected(ArrayList<Integer> seatIds, int venueId, int productId) {
		
		double total = 0;
		
		try {
			if(seatIds.size()!=0) {
				
				for(Integer i : seatIds) {
					query = "SELECT PRICE FROM SEAT WHERE id = ? AND locationId = ?";
					
					pst = this.connection.prepareStatement(query);
					pst.setInt(1, i);
					pst.setInt(2, venueId);
					
					rs = pst.executeQuery();
					
					while(rs.next()) {
						total+=rs.getFloat("price");
					}
					
				}
				
			}
			
		} catch(Exception e) {
			e.printStackTrace();
			System.out.print(e.getMessage());	
		}
		
		return total;
		
	}
	
	public Product getSingleProduct(int id) {
		Product p = null;
		
		try {
			
			query = "SELECT * FROM PRODUCT WHERE ID=?";
			pst = this.connection.prepareStatement(query);
			pst.setInt(1, id);
			rs = pst.executeQuery();
			
			while(rs.next()) {
				p = new Product();
				p.setId(rs.getInt("id"));
				p.setName(rs.getString("name"));
				p.setCategory(rs.getString("category"));
				p.setPrice(rs.getFloat("price"));
				p.setImage(rs.getString("image"));
				p.setVenueId(rs.getInt("venueId"));
			}
			
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		return p;
	}
	
}
