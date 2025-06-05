package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import model.Location;
import model.Order;
import model.Product;

public class LocationDao {

	private Connection connection;
	private String query;
	private PreparedStatement pst;
	private ResultSet rs;
	
	public LocationDao(Connection connection) {
		this.connection = connection;
	}
	
	public Location getEventLocation(int id) {
		Location l = null;
		
		try {
			
			query = "SELECT * FROM LOCATION WHERE ID=?";
			pst = this.connection.prepareStatement(query);
			pst.setInt(1, id);
			rs = pst.executeQuery();
			
			while(rs.next()) {
				l = new Location();
				l.setId(rs.getInt("id"));
				l.setVenue(rs.getString("venue"));
				l.setCity(rs.getString("city"));
				l.setAddress(rs.getString("address"));
				l.setImage(rs.getString("image"));
				l.setRows(rs.getInt("rows"));
				l.setColumns(rs.getInt("columns"));
			}
			
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		return l;
	}
	
}
