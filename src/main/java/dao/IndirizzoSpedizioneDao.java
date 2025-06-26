package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import model.DBConnection;
import model.IndirizzoSpedizione;
import model.Show;
import model.ShowSeat;
import model.User;

public class IndirizzoSpedizioneDao {
	private Connection connection;
	private String query;
	private PreparedStatement pst;
	private ResultSet rs;

	public IndirizzoSpedizioneDao() {
	}

	public ArrayList<IndirizzoSpedizione> getAllAddresses(int uId) {

		ArrayList<IndirizzoSpedizione> addresses = new ArrayList<>();

		String sql = "SELECT * FROM indirizzo_spedizione WHERE userId = ? ORDER BY main DESC";
		try {
			connection = DBConnection.getConnection();
			PreparedStatement pst = connection.prepareStatement(sql);
			pst.setInt(1, uId);
			ResultSet rs = pst.executeQuery();
			while (rs.next()) {
				IndirizzoSpedizione address = new IndirizzoSpedizione();
				address.setId(rs.getInt("id"));
				address.setNomeDestinatario(rs.getString("nomeDestinatario"));
				address.setCognomeDestinatario(rs.getString("cognomeDestinatario"));
				address.setIndirizzo(rs.getString("indirizzo"));
				address.setCAP(rs.getInt("CAP"));
				address.setCittà(rs.getString("città"));
				address.setPaese(rs.getString("paese"));
				address.setMain(rs.getInt("main"));
				address.setUserId(uId);
				
				addresses.add(address);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			closeConnection(connection);
		}
		return addresses;

	}

	public boolean deleteAddress(int id) {

		boolean result = false;

		try {
			
			System.out.println("Setting " + id +" as Main");
			
			connection = DBConnection.getConnection();

			query = "DELETE FROM indirizzo_spedizione WHERE id = ?";

			pst = this.connection.prepareStatement(query, PreparedStatement.RETURN_GENERATED_KEYS);
			pst.setInt(1, id);

			pst.executeUpdate();

			result = true; // se è arrivato qui non ci sono eccezioni

		} catch (Exception e) {
			e.printStackTrace();
			System.out.print(e.getMessage());
		}

		return result;
	}
	
	public int insertAddress(IndirizzoSpedizione address) {

		int id = 0;
		try {

			connection = DBConnection.getConnection();

			query = "INSERT INTO indirizzo_spedizione (userId,nomeDestinatario,"
					+ "cognomeDestinatario,indirizzo,CAP,città,paese,main) VALUES(?,?,?,?,?,?,?,?)";
			pst = this.connection.prepareStatement(query, PreparedStatement.RETURN_GENERATED_KEYS);
			pst.setInt(1, address.getUserId());
			pst.setString(2, address.getNomeDestinatario());
			pst.setString(3, address.getCognomeDestinatario());
			pst.setString(4, address.getIndirizzo());
			pst.setInt(5, address.getCAP());
			pst.setString(6, address.getCittà());
			pst.setString(7, address.getPaese());
			pst.setInt(8, 0);
			pst.executeUpdate();

			

			ResultSet generatedKeys = pst.getGeneratedKeys();

			if (generatedKeys.next()) {
				id = generatedKeys.getInt(1);
				if (address.getMain() == 1) {
					setAsMain(id,address.getUserId());
				}
			}
			
			
		} catch (Exception e) {
			e.printStackTrace();
			System.out.print(e.getMessage());
		} finally {
			closeConnection(connection);
		}

		return id;
	}

	public boolean setAsMain(int id, int uId) {

		boolean result = false;

		try {
			
			System.out.println("Setting " + id +" as Main");
			
			connection = DBConnection.getConnection();

			query = "UPDATE indirizzo_spedizione SET main = 0 WHERE userId = ?";

			pst = this.connection.prepareStatement(query, PreparedStatement.RETURN_GENERATED_KEYS);
			pst.setInt(1, uId);

			pst.executeUpdate();

			query = "UPDATE indirizzo_spedizione SET main = 1 WHERE id = ? AND userId = ?";

			pst = this.connection.prepareStatement(query, PreparedStatement.RETURN_GENERATED_KEYS);
			pst.setInt(1, id);
			pst.setInt(2, uId);
			pst.executeUpdate();

			result = true; // se è arrivato qui non ci sono eccezioni

		} catch (Exception e) {
			e.printStackTrace();
			System.out.print(e.getMessage());
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
