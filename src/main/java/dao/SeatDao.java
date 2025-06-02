package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import model.Product;
import model.Seat;

public class SeatDao {
	
	private Connection connection;
	private String query;
	private PreparedStatement pst;
	private ResultSet rs;
	
	public SeatDao(Connection connection) {
		super();
		this.connection = connection;
	}
	
	public ArrayList<Seat> getAllSeats(int venueId){
		
		ArrayList<Seat> seats = new ArrayList<>();
		
		try {
			query = "SELECT * FROM SEAT WHERE locationId=?";
			pst = this.connection.prepareStatement(query);
			pst.setInt(1, venueId);
			rs = pst.executeQuery();
			
			while(rs.next()) {
				Seat seat = new Seat();
				seat.setId(rs.getInt("id"));
				seat.setPrice(rs.getFloat("price"));
				seat.setAvailable(rs.getInt("available"));
				seat.setType(rs.getString("type"));
				seats.add(seat);
			}
			
		}	
		catch(Exception e) {
			e.printStackTrace();
		}
		return seats;
		
	}
	
	public boolean reserveSeat(int seatId, int userId) {
		
		boolean result = false;
		
		try {
			
			query = "UPDATE SEAT SET AVAILABLE = 0 WHERE ID = ? AND AVAILABLE = 1";
			pst = this.connection.prepareStatement(query);
			pst.setInt(1, seatId);
			
			pst.executeUpdate();
			
			result = true;
			
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		return result;
		
	}
	
}
