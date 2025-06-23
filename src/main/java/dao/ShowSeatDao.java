package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import model.DBConnection;
import model.Show;
import model.ShowSeat;
import model.DBConnection;
public class ShowSeatDao {

	private Connection connection;
	private String query;
	private PreparedStatement pst;
	private ResultSet rs;
	
	
	public ShowSeatDao() {}
	
	public ArrayList<ShowSeat> getAvailableSeats(int showId){
		
		ArrayList<ShowSeat> seatList = new ArrayList<>();
		
		String sql = "SELECT * FROM show_seats WHERE showId = ? AND available = true";
        try {
        	connection = DBConnection.getConnection();	
        	PreparedStatement pst = connection.prepareStatement(sql);
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
        }finally {
			closeConnection(connection);
		}
		return seatList;
		
	}
	
public ArrayList<ShowSeat> getSeatsForShow(int showId){
		
		ArrayList<ShowSeat> seatList = new ArrayList<>();
		String sql = "SELECT * FROM show_seats WHERE showId = ? ";
        try{
        	connection = DBConnection.getConnection();	
        	PreparedStatement pst = connection.prepareStatement(sql);
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
        }finally {
			closeConnection(connection);
		}
        return seatList;
		
	}
	
	public boolean reserveSeats(int showId, ArrayList<Integer> seatIds) {
        
		query = "UPDATE show_seats SET available = 0 WHERE showId = ? AND seatId = ? AND available = 1";
        
		try {
			connection = DBConnection.getConnection();	
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
        }finally {
			closeConnection(connection);
		}
        return false;
    }
	
	public boolean setSeatsAvailable(int showId, ArrayList<Integer> seatIds) {
        
		query = "UPDATE show_seats SET available = 1 WHERE showId = ? AND seatId = ? AND available = 0";
        
		try {
			connection = DBConnection.getConnection();	
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
        }finally {
			closeConnection(connection);
		}
        return false;
    }

	//un solo posto
	public float getSeatPrice(int showId, int seatId) {
        float price = 0;
        query = "SELECT price FROM show_seats WHERE showId = ? AND seatId = ?";
        try  {
        	connection = DBConnection.getConnection();	
        	PreparedStatement pst = connection.prepareStatement(query);
            pst.setInt(1, showId);
            pst.setInt(2, seatId);
            ResultSet rs = pst.executeQuery();
            if (rs.next()) {
                price = rs.getFloat("price");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
			closeConnection(connection);
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
	
	public boolean addShowSeat(ShowSeat showSeat) {
		
		//uno spettacolo non può essere reso pubblico se tutti i posti non sono stati assegnati a un prezzo
		
		boolean result = false;
		
		try {
			connection = DBConnection.getConnection();	
			
			query = "INSERT INTO show_seats (showId, seatId, available, price) VALUES(?,?,?,?)";
			
			pst = this.connection.prepareStatement(query, PreparedStatement.RETURN_GENERATED_KEYS);
			pst.setInt(1, showSeat.getShowId());
			pst.setInt(2, showSeat.getSeatId());
			pst.setInt(3, 1);
			pst.setFloat(4, showSeat.getPrice());
			pst.executeUpdate();
			
			result = true; //se è arrivato qui non ci sono eccezioni
			
		}catch(Exception e) {
			e.printStackTrace();
			System.out.print(e.getMessage());	
		}finally {
			closeConnection(connection);
		}
				
		return result;
	}
	

	public boolean addShowSeats(ArrayList<ShowSeat> showSeats) {
		
		boolean result = false;
		
		try {
			connection = DBConnection.getConnection();	
			
			for(ShowSeat s : showSeats) {
			
				query = "INSERT INTO show_seats (showId, seatId, available, price) VALUES(?,?,?,?)";
			
				pst = this.connection.prepareStatement(query, PreparedStatement.RETURN_GENERATED_KEYS);
				pst.setInt(1, s.getShowId());
				pst.setInt(2, s.getSeatId());
				pst.setInt(3, 1);
				pst.setFloat(4, s.getPrice());
				pst.executeUpdate();
			
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
	

	public boolean addShowSeatPriceByCategory(Show show, String category, float price) {
		
		boolean result = false;
		
		SeatDao seatDao = new SeatDao();
		ArrayList<Integer> seats = seatDao.getSeatsByCategory(show.getVenueId(),category);
		
		try {
			connection = DBConnection.getConnection();	
			
			for(Integer s : seats) {
			
				query = "INSERT INTO show_seats (showId, seatId, available, price) VALUES(?,?,?,?)";
			
				pst = this.connection.prepareStatement(query, PreparedStatement.RETURN_GENERATED_KEYS);
				pst.setInt(1, show.getId()); //id spettacolo
				pst.setInt(2, s); //intero id posto
				pst.setInt(3, 1); //disponibile
				pst.setFloat(4, price);
				pst.executeUpdate();
			
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
	

	//modifica il prezzo di tutti i posti di uno specifico spettacolo e di una specifica categoria
	public boolean modifyShowSeatPriceByCategory(Show show, String category, float price) {
		
		boolean result = false;
		
		SeatDao seatDao = new SeatDao();
		ArrayList<Integer> seats = seatDao.getSeatsByCategory(show.getVenueId(),category);
		
		try {
			connection = DBConnection.getConnection();	
			
			for(Integer s : seats) {
			
				query = "UPDATE show_seats SET price = ? WHERE showId = ? AND seatId = ?";
			
				pst = this.connection.prepareStatement(query, PreparedStatement.RETURN_GENERATED_KEYS);
				pst.setFloat(1, price);
				pst.setInt(2, show.getId()); //id spettacolo
				pst.setInt(3, s); //intero id posto
				
				pst.executeUpdate();
			
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
	

	public void closeConnection(Connection connection) {
		
		try {
			connection.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
	
}
