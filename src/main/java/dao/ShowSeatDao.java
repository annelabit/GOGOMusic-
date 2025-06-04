package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import model.ShowSeat;

public class ShowSeatDao {

	private Connection connection;
	private String query;
	private PreparedStatement pst;
	private ResultSet rs;
	
	
	public ShowSeatDao(Connection connection) {
		this.connection = connection;
	}
	
	public ArrayList<ShowSeat> getAvailableSeats(int showId){
		
		ArrayList<ShowSeat> seatList = new ArrayList<>();
		
		String sql = "SELECT * FROM show_seats WHERE showId = ? AND available = true";
        try (PreparedStatement pst = connection.prepareStatement(sql)) {
            pst.setInt(1, showId);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                ShowSeat seat = new ShowSeat();
                seat.setShowId(showId);
                seat.setSeatId(rs.getInt("seatId"));
                seat.setPrice(rs.getFloat("price"));
                seat.setAvailable(rs.getInt("available"));
                seatList.add(seat);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return seatList;
		
	}
	
public ArrayList<ShowSeat> getSeatsForShow(int showId){
		
		ArrayList<ShowSeat> seatList = new ArrayList<>();
		
		String sql = "SELECT * FROM show_seats WHERE showId = ? ";
        try (PreparedStatement pst = connection.prepareStatement(sql)) {
            pst.setInt(1, showId);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                ShowSeat seat = new ShowSeat();
                seat.setShowId(showId);
                seat.setSeatId(rs.getInt("seatId"));
                seat.setPrice(rs.getFloat("price"));
                seat.setAvailable(rs.getInt("available"));
                seatList.add(seat);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return seatList;
		
	}
	
	public boolean reserveSeats(int showId, ArrayList<Integer> seatIds) {
        
		query = "UPDATE show_seats SET available = 0 WHERE showId = ? AND seatId = ? AND available = 1";
        
		try {
        	PreparedStatement pst = connection.prepareStatement(query);
            for (int seatId : seatIds) {
                pst.setInt(1, showId);
                pst.setInt(2, seatId);
                pst.executeUpdate();
            }
            //pst.executeUpdate();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

	//un solo posto
	public float getSeatPrice(int showId, int seatId) {
        float price = 0;
        query = "SELECT price FROM show_seats WHERE showId = ? AND seatId = ?";
        try  {
        	PreparedStatement pst = connection.prepareStatement(query);
            pst.setInt(1, showId);
            pst.setInt(2, seatId);
            ResultSet rs = pst.executeQuery();
            if (rs.next()) {
                price = rs.getFloat("price");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return price;
    }
	
	//tutti i posti specificati
	public float getTotalPrice(int showId, ArrayList<Integer> seatIds) {
		
		float total = 0;
		for(int seatId : seatIds) {
			total += getSeatPrice(showId, seatId);
		}
		
		return total;
		
	}
	
}
