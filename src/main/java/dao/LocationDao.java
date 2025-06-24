package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import model.Location;
import model.Order;
import model.Product;
import model.DBConnection;

public class LocationDao {

	private Connection connection;
	private String query;
	private PreparedStatement pst;
	private ResultSet rs;

	public LocationDao() {
	}

	public Location getEventLocation(int id) {
		Location l = new Location();

		try {
			connection = DBConnection.getConnection();
			query = "SELECT * FROM LOCATION WHERE ID=?";
			pst = this.connection.prepareStatement(query);
			pst.setInt(1, id);
			rs = pst.executeQuery();

			while (rs.next()) {
				l = new Location();
				l.setId(rs.getInt("id"));
				l.setVenue(rs.getString("venue"));
				l.setCity(rs.getString("city"));
				l.setAddress(rs.getString("address"));
				l.setImage(rs.getString("image"));
				l.setRows(rs.getInt("rows"));
				l.setColumns(rs.getInt("columns"));
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			closeConnection(connection);
		}

		return l;
	}

	public ArrayList<Location> getAllLocations() {

		Location l = null;
		ArrayList<Location> locations = new ArrayList<>();
		
		try {
			connection = DBConnection.getConnection();
			query = "SELECT * FROM LOCATION";
			pst = this.connection.prepareStatement(query);
			rs = pst.executeQuery();

			while (rs.next()) {
				l = new Location();
				l.setId(rs.getInt("id"));
				l.setVenue(rs.getString("venue"));
				l.setCity(rs.getString("city"));
				l.setAddress(rs.getString("address"));
				l.setImage(rs.getString("image"));
				l.setRows(rs.getInt("rows"));
				l.setColumns(rs.getInt("columns"));
				locations.add(l);
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			closeConnection(connection);
		}

		return locations;
	}

	public void closeConnection(Connection connection) {

		try {
			connection.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}
}
