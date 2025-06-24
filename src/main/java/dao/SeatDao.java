package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import model.Product;
import model.Seat;
import model.ShowSeat;
import model.DBConnection;

public class SeatDao {

	private Connection connection;
	private String query;
	private PreparedStatement pst;
	private ResultSet rs;

	public SeatDao() {
	}

	public ArrayList<Seat> getAllSeats(int venueId) {

		ArrayList<Seat> seats = new ArrayList<>();

		try {
			connection = DBConnection.getConnection();
			query = "SELECT * FROM SEAT WHERE locationId=?";
			pst = this.connection.prepareStatement(query);
			pst.setInt(1, venueId);
			rs = pst.executeQuery();

			while (rs.next()) {
				Seat seat = new Seat();
				seat.setId(rs.getInt("id"));
				seat.setAvailable(rs.getInt("available"));
				seat.setType(rs.getString("type"));
				seats.add(seat);
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			closeConnection(connection);
		}
		return seats;

	}

	public ArrayList<Integer> getAllSeatIds(int venueId) {

		ArrayList<Integer> seatIds = new ArrayList<>();

		try {
			connection = DBConnection.getConnection();
			query = "SELECT id FROM SEAT WHERE locationId=?";
			pst = this.connection.prepareStatement(query);
			pst.setInt(1, venueId);
			rs = pst.executeQuery();

			while (rs.next()) {
				seatIds.add(rs.getInt("id"));
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			closeConnection(connection);
		}
		return seatIds;

	}
	
	public Seat getSeatsByShowSeat(ShowSeat showSeat) {

		Seat seat = new Seat();

		try {
			connection = DBConnection.getConnection();
			query = "SELECT * FROM SEAT WHERE id = ?";
			pst = this.connection.prepareStatement(query);
			pst.setInt(1, showSeat.getSeatId());
			rs = pst.executeQuery();

			while (rs.next()) {
				seat.setId(rs.getInt("id"));
				seat.setType(rs.getString("type"));
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			closeConnection(connection);
		}
		return seat;
	}
	
	public Seat getSeatsByShowSeatId(int showSeat) {

		Seat seat = new Seat();

		try {
			connection = DBConnection.getConnection();
			query = "SELECT * FROM SEAT INNER JOIN show_seats ON show_seats.seatId = seat.id WHERE show_seats.id = ?";
			pst = this.connection.prepareStatement(query);
			pst.setInt(1, showSeat);
			rs = pst.executeQuery();

			while (rs.next()) {
				seat.setId(rs.getInt("id"));
				seat.setType(rs.getString("type"));
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			closeConnection(connection);
		}
		return seat;
	}

	public boolean reserveSeat(int seatId) {

		boolean result = false;

		try {
			connection = DBConnection.getConnection();
			query = "UPDATE SEAT SET AVAILABLE = 0 WHERE ID = ? AND AVAILABLE = 1";
			pst = this.connection.prepareStatement(query);
			pst.setInt(1, seatId);

			pst.executeUpdate();

			result = true;

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			closeConnection(connection);
		}

		return result;

	}

	public ArrayList<Integer> getSeatsByCategory(int venueId, String category) {

		ArrayList<Integer> seatIds = new ArrayList<>();

		try {
			connection = DBConnection.getConnection();
			query = "SELECT id FROM SEAT WHERE locationId=? AND type = ?";
			pst = this.connection.prepareStatement(query);
			pst.setInt(1, venueId);
			pst.setString(2, category);
			rs = pst.executeQuery();

			while (rs.next()) {
				seatIds.add(rs.getInt("id"));
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			closeConnection(connection);
		}
		return seatIds;
	}

	public ArrayList<String> getAllCategories(int venueId) {

		ArrayList<String> categories = new ArrayList<>();

		try {
			connection = DBConnection.getConnection();
			query = "SELECT type FROM SEAT WHERE locationId = ?";
			pst = this.connection.prepareStatement(query);
			pst.setInt(1, venueId);
			rs = pst.executeQuery();

			while (rs.next()) {
				
				if(!categories.contains(rs.getString("type")))
						categories.add(rs.getString("type"));
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			closeConnection(connection);
		}
		return categories;
	}

	public Seat getSeatById(int id) {

		Seat seat = new Seat();

		try {
			connection = DBConnection.getConnection();
			query = "SELECT * FROM SEAT WHERE id = ?";
			pst = this.connection.prepareStatement(query);
			pst.setInt(1, id);
			rs = pst.executeQuery();

			while (rs.next()) {
				seat.setId(rs.getInt("id"));
				seat.setType(rs.getString("type"));
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			closeConnection(connection);
		}
		return seat;
	}
	
	public void closeConnection(Connection connection) {

		try {
			connection.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

}
