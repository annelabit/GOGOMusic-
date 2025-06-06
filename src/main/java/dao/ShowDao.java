package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import model.Show;

public class ShowDao {

	private Connection connection;
	private String query;
	private PreparedStatement pst;
	private ResultSet rs;
	
	
	public ShowDao(Connection connection) {
		this.connection = connection;
	}
	
	public ArrayList<Show> getShows(int eventId){
		
		ArrayList<Show> shows = new ArrayList<>();
		
		query = "SELECT * FROM `show` WHERE eventId = ?";
		
		try {
			pst = connection.prepareStatement(query);
			pst.setInt(1, eventId);
			rs = pst.executeQuery();
			
			while(rs.next()) {
				Show show = new Show();
				show.setId(rs.getInt("id"));
				show.setProductId(eventId);
				show.setVenueId(rs.getInt("locationId"));
				show.setDate(rs.getString("date"));
				show.setTime(rs.getString("time"));
				shows.add(show);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	return shows;	
		
	}
	
	public Show getShow(int id) {
		
		Show show = new Show();
		
		query = "SELECT * FROM `show` WHERE id = ?";
		
		try {
			pst = connection.prepareStatement(query);
			pst.setInt(1, id);
			rs = pst.executeQuery();
			
			if(rs.next()) {
				show.setId(rs.getInt("id"));
				show.setProductId(rs.getInt("eventId"));
				show.setVenueId(rs.getInt("locationId"));
				show.setDate(rs.getString("date"));
				show.setTime(rs.getString("time"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return show;
	}
	
public double getMinimumPrice(int eventId) {
		
	double max=0;
	
	try {
		
		//Unisci showId di show_seats a id di show. Show contiene eventId
		
		query = "SELECT Min(PRICE) FROM show_seats INNER JOIN `show` ON show_seats.showId = `show`.id WHERE eventId = ? AND available = 1";
				
				pst = this.connection.prepareStatement(query);
				pst.setInt(1, eventId);
				rs = pst.executeQuery();
				
				if(rs.next()) {
					max=rs.getFloat(1);
				}
				
		
	} catch(Exception e) {
		e.printStackTrace();
		System.out.print(e.getMessage());	
	}
	
	return max;
}

	
	public double getMaximumPrice(int eventId) {
		
		double max=0;
		
		try {
			
			//Unisci showId di show_seats a id di show. Show contiene eventId
			
					query = "SELECT Max(PRICE) FROM show_seats INNER JOIN `show` ON show_seats.showId = `show`.id WHERE eventId = ? AND available = 1";
					
					pst = this.connection.prepareStatement(query);
					pst.setInt(1, eventId);
					rs = pst.executeQuery();
					
					if(rs.next()) {
						max=rs.getFloat(1);
					}
					
			
		} catch(Exception e) {
			e.printStackTrace();
			System.out.print(e.getMessage());	
		}
		
		return max;
	}
	
	
}
