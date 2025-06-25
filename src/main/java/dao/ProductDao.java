package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import model.Cart;
import model.Product;
import model.Show;
import model.DBConnection;

public class ProductDao {

	private Connection connection;
	private String query;
	private PreparedStatement pst;
	private ResultSet rs;
	
	public ProductDao() {}
	
	public ArrayList<Product> getProducts(){
		
		ArrayList<Product> products = new ArrayList<Product>();
		
		try {
			connection = DBConnection.getConnection();
			query = "SELECT * FROM PRODUCT";
			pst = this.connection.prepareStatement(query);
			rs = pst.executeQuery();
			
			while(rs.next()) {
				Product prod = new Product();
				prod.setId(rs.getInt("id"));
				prod.setName(rs.getString("name"));
				prod.setCategory(rs.getString("category"));
				prod.setImage(rs.getString("image"));
				prod.setVenueId(rs.getInt("venueId"));
				prod.setDescrizione(rs.getString("descrizione"));
				products.add(prod);
			}
			
		}	
		catch(Exception e) {
			e.printStackTrace();
		}finally {
			closeConnection(connection);
		}
		
		return products;
	}
	
	public ArrayList<Cart> getCartProducts(ArrayList<Cart> cart){
		ArrayList<Cart> cartProducts = new ArrayList<Cart>();
		
		try{
			connection = DBConnection.getConnection();
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
						c.setDescrizione(rs.getString("descrizione"));
						cartProducts.add(c);
					}
					
				}
			}
			
			
		}catch(Exception e){
			e.printStackTrace();
			System.out.print(e.getMessage());		
		}finally {
			closeConnection(connection);
		}
		
		return cartProducts;
	}
	
	public double getTotalPrice(ArrayList<Cart> cart, int showId) {
		
		double total = 0;
		
		try {
			connection = DBConnection.getConnection();
			for(Cart c : cart) {
					
				if(c.getSeatIds() != null && !c.getSeatIds().isEmpty()) { 
					
					for(Integer seatId : c.getSeatIds()) {
					
						query = "SELECT PRICE FROM show_seats WHERE seatId = ? AND showId = ?";
						pst = this.connection.prepareStatement(query);
						pst.setInt(1, seatId);
						pst.setInt(2, showId);
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
		}finally {
			closeConnection(connection);
		}
		
		return total;
		
	}
	
	
	
	public double getPriceForSelected(ArrayList<Integer> seatIds, int showId) {
		
		double total = 0;
		
		try {
			connection = DBConnection.getConnection();
			if(seatIds.size()!=0) {
				
				for(Integer i : seatIds) {
					query = "SELECT PRICE FROM show_seats WHERE seatId = ? AND showId = ?";
					
					pst = this.connection.prepareStatement(query);
					pst.setInt(1, i);
					pst.setInt(2, showId);
					
					rs = pst.executeQuery();
					
					while(rs.next()) {
						total+=rs.getFloat("price");
					}
					
				}
				
			}
			
		} catch(Exception e) {
			e.printStackTrace();
			System.out.print(e.getMessage());	
		}finally {
			closeConnection(connection);
		}
		
		return total;
		
	}
	
	public Product getSingleProduct(int id) {
		Product p = null;
		
		try {
			connection = DBConnection.getConnection();
			query = "SELECT * FROM PRODUCT WHERE ID=?";
			pst = this.connection.prepareStatement(query);
			pst.setInt(1, id);
			rs = pst.executeQuery();
			
			while(rs.next()) {
				p = new Product();
				p.setId(rs.getInt("id"));
				p.setName(rs.getString("name"));
				p.setCategory(rs.getString("category"));
				p.setImage(rs.getString("image"));
				p.setVenueId(rs.getInt("venueId"));
				p.setDescrizione(rs.getString("descrizione"));
			}
			
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			closeConnection(connection);
		}
		
		return p;
	}
	
	public ArrayList<Product> getEventByVenueId(int venueId){
		
		ArrayList<Product> products = new ArrayList<Product>();
		
		try {
			connection = DBConnection.getConnection();
			query = "SELECT * FROM PRODUCT WHERE venueId = ?";
			pst = this.connection.prepareStatement(query);
			pst.setInt(1, venueId);
			rs = pst.executeQuery();
			
			while(rs.next()) {
				Product prod = new Product();
				prod.setId(rs.getInt("id"));
				prod.setName(rs.getString("name"));
				prod.setCategory(rs.getString("category"));
				prod.setImage(rs.getString("image"));
				prod.setVenueId(rs.getInt("venueId"));
				prod.setDescrizione(rs.getString("descrizione"));
				products.add(prod);
			}
			
		}	
		catch(Exception e) {
			e.printStackTrace();
		}finally {
			closeConnection(connection);
		}
		
		return products;
		
	}
	
	public int insertEvent(Product p) {
		
		int id = 0;
		
		try {
			connection = DBConnection.getConnection();
			
			query = "INSERT INTO product (name, category, image, venueId) VALUES(?,?,?,?)";
			
			pst = this.connection.prepareStatement(query, PreparedStatement.RETURN_GENERATED_KEYS);
			pst.setString(1, p.getName());
			pst.setString(2, p.getCategory());
			pst.setString(3, p.getImage());
			pst.setInt(4, p.getVenueId());
			pst.executeUpdate();
			
			ResultSet keys = pst.getGeneratedKeys();
			if(keys.next())
				id = keys.getInt(1);
			
		}catch(Exception e) {
			e.printStackTrace();
			System.out.print(e.getMessage());	
		}finally {
			closeConnection(connection);
		}
				
		return id;
	}

	public ArrayList<Product> getEventsByCategory(String category) {
		
		ArrayList<Product> events = new ArrayList<>();
		
		try {
			connection = DBConnection.getConnection();
			query = "SELECT * FROM product WHERE category = ?";
			pst = this.connection.prepareStatement(query);
			pst.setString(1, category);
			rs = pst.executeQuery();
			
			while(rs.next()) {

				Product p = new Product();
				p.setId(rs.getInt("id"));
				p.setName(rs.getString("name"));
				p.setImage(rs.getString("image"));
				p.setCategory(rs.getString("category"));
				p.setDescrizione(rs.getString("descrizione"));
				p.setVenueId(0);
				
				events.add(p);
			}
			
		}	
		catch(Exception e) {
			e.printStackTrace();
		}finally {
			closeConnection(connection);
		}
		return events;
	}
	
	//modifica il prezzo di tutti i posti di uno specifico spettacolo e di una specifica categoria
		public boolean modifyEventInformation(Show show, String name, String category, String image, int venueId, String descrizione) {
			
			boolean result = false;
			
			SeatDao seatDao = new SeatDao();
			ArrayList<Integer> seats = seatDao.getSeatsByCategory(show.getVenueId(),category);
			
			try {
				connection = DBConnection.getConnection();
				for(Integer s : seats) {
				
					query = "UPDATE product SET name = ?, category = ?, image = ?, venueId = ? WHERE id = ?";
				
					pst = this.connection.prepareStatement(query, PreparedStatement.RETURN_GENERATED_KEYS);
					pst.setString(1, name);
					pst.setString(2, category);
					pst.setString(3, image);
					pst.setInt(4, venueId); 
					pst.setInt(5, show.getId());
					pst.setString(6, descrizione);
					pst.executeUpdate();
				
					//show.setName ...?
					
				}
				result = true; //se è arrivato qui non ci sono eccezioni
				
			}catch(Exception e) {
				e.printStackTrace();
				System.out.print(e.getMessage());	
			}finally {
				closeConnection(connection);
			}
					
			return result;
		}
	
		public boolean deleteEvent(int pId) {
			
			boolean result = false;
			ShowDao showDao = new ShowDao();
			
			try {
				connection = DBConnection.getConnection();
				ArrayList<Show> shows= showDao.getShows(pId);
				
				for(Show s : shows) {
					showDao.deleteShow(s.getId());
				}
				
				query = "DELETE FROM product WHERE id = ?";
				
				pst = this.connection.prepareStatement(query, PreparedStatement.RETURN_GENERATED_KEYS);
				pst.setInt(1, pId);
				pst.executeUpdate();
				
				//lo spettacolo non scompare dagli ordini di nessun utente
				
				result = true; //se è arrivato qui non ci sono eccezioni
				
			}catch(Exception e) {
				e.printStackTrace();
				System.out.print(e.getMessage());	
			}finally {
				closeConnection(connection);
			}
					
			return result;
		}
		
		public void closeConnection(Connection connection) {
			
			try {
				connection.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			
		}
}
