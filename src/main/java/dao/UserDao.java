package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import model.DBConnection;
import model.Show;
import model.User;

public class UserDao {

	private Connection connection;
	private String query;
	private PreparedStatement pst;
	private ResultSet rs;

	public UserDao() {
	}

	public User userLogin(String username, String password) {

		User user = null;

		try {

			connection = DBConnection.getConnection();

			query = "SELECT * FROM USER WHERE USERNAME=? AND PASSWORD=?";
			pst = this.connection.prepareStatement(query);
			pst.setString(1, username);
			pst.setString(2, password);
			rs = pst.executeQuery();

			if (rs.next()) {
				user = new User();
				user.setIdUtente(rs.getInt("id"));
				user.setNome(rs.getString("nome"));
				user.setCognome(rs.getString("cognome"));
				user.setEmail(rs.getString("email"));
				user.setUsername(rs.getString("username"));
				user.setAdmin(rs.getInt("isAdmin") == 1 ? true : false);
				// per ragioni di sicurezza non metto password
			}

		} catch (Exception e) {
			e.printStackTrace();
			System.out.print(e.getMessage());
		} finally {
			closeConnection(connection);
		}

		return user;
	}

	public String getHashedPassword(int userId) {

		String pw = null;

		try {

			connection = DBConnection.getConnection();

			query = "SELECT * FROM USER WHERE id = ?";
			pst = this.connection.prepareStatement(query);
			pst.setInt(1, userId);
			rs = pst.executeQuery();

			if (rs.next()) {
				pw=rs.getString("password");
			}

		} catch (Exception e) {
			e.printStackTrace();
			System.out.print(e.getMessage());
		} finally {
			closeConnection(connection);
		}

		return pw;

	}
	
	public boolean setNewPassword(String password, int id) {

		query = "UPDATE user SET password = ? WHERE id = ?";

		try {
			connection = DBConnection.getConnection();
			PreparedStatement pst = connection.prepareStatement(query);

			pst.setString(1,password);
			pst.setInt(2, id);
			pst.executeUpdate();

			return true;
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			closeConnection(connection);
		}
		return false;
	}

	// password ha già subito la modifica di funzione hash
	public int userRegister(User user, String password) {
		int id = 0;
		try {

			connection = DBConnection.getConnection();

			query = "INSERT INTO user (nome,cognome,email, username, password) VALUES(?,?,?,?,?)";
			pst = this.connection.prepareStatement(query, PreparedStatement.RETURN_GENERATED_KEYS);
			pst.setString(1, user.getNome());
			pst.setString(2, user.getCognome());
			pst.setString(3, user.getEmail());
			pst.setString(4, user.getUsername());
			pst.setString(5, password);
			pst.executeUpdate();
			ResultSet generatedKeys = pst.getGeneratedKeys();

			if (generatedKeys.next()) {
				id = generatedKeys.getInt(1);
			}
		} catch (Exception e) {
			e.printStackTrace();
			System.out.print(e.getMessage());
		} finally {
			closeConnection(connection);
		}

		return id;
	}

	public ArrayList<User> getAllUsers() {

		ArrayList<User> users = new ArrayList<>();
		User user = null;

		try {
			connection = DBConnection.getConnection();
			query = "SELECT * FROM USER";
			pst = this.connection.prepareStatement(query);
			rs = pst.executeQuery();

			while (rs.next()) {
				user = new User();
				user.setIdUtente(rs.getInt("id"));
				user.setNome(rs.getString("nome"));
				user.setCognome(rs.getString("cognome"));
				user.setEmail(rs.getString("email"));
				user.setUsername(rs.getString("username"));
				user.setAdmin(rs.getInt("isAdmin") == 1 ? true : false);
				// per ragioni di sicurezza non metto password
				users.add(user);
			}

		} catch (Exception e) {
			e.printStackTrace();
			System.out.print(e.getMessage());
		} finally {
			closeConnection(connection);
		}

		return users;
	}

	public boolean updateUserInfo(User user) {

		query = "UPDATE user SET nome = ?, cognome = ?, email = ?, username = ? WHERE id = ?";

		try {
			connection = DBConnection.getConnection();
			PreparedStatement pst = connection.prepareStatement(query);

			pst.setString(1, user.getNome());
			pst.setString(2, user.getCognome());
			pst.setString(3, user.getEmail());
			pst.setString(4, user.getUsername());
			pst.setInt(5, user.getIdUtente());
			pst.executeUpdate();

			return true;
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			closeConnection(connection);
		}
		return false;
	}

	public User getUserFromId(int id) {

		User user = new User();

		try {
			connection = DBConnection.getConnection();
			query = "SELECT * FROM USER WHERE id = ?";
			pst = this.connection.prepareStatement(query);
			pst.setInt(1, id);
			rs = pst.executeQuery();

			if (rs.next()) {
				user = new User();
				user.setIdUtente(rs.getInt("id"));
				user.setNome(rs.getString("nome"));
				user.setCognome(rs.getString("cognome"));
				user.setEmail(rs.getString("email"));
				user.setUsername(rs.getString("username"));
				user.setAdmin(rs.getInt("isAdmin") == 1 ? true : false);
				// per ragioni di sicurezza non metto password
			}

		} catch (Exception e) {
			e.printStackTrace();
			System.out.print(e.getMessage());
		} finally {
			closeConnection(connection);
		}

		return user;
	}

	public void closeConnection(Connection connection) {

		try {
			connection.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	public boolean deleteUser(int uId) {

		boolean result = false;

		try {
			connection = DBConnection.getConnection();

			query = "DELETE FROM user WHERE id = ?";

			pst = this.connection.prepareStatement(query, PreparedStatement.RETURN_GENERATED_KEYS);
			pst.setInt(1, uId);
			pst.executeUpdate();

			result = true; // se è arrivato qui non ci sono eccezioni

		} catch (Exception e) {
			e.printStackTrace();
			System.out.print(e.getMessage());
		} finally {
			closeConnection(connection);
		}

		return result;

	}

}
