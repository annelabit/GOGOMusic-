package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import model.DBConnection;
import model.User;

public class UserDao {

	private Connection connection;
	private String query;
	private PreparedStatement pst;
	private ResultSet rs;
	
	public UserDao() {}
	
	public User userLogin(String username, String password) {
		User user = null;
		
		
		try {
			
			connection = DBConnection.getConnection();
			
			query = "SELECT * FROM USER WHERE USERNAME=? AND PASSWORD=?";
			pst = this.connection.prepareStatement(query);
			pst.setString(1, username);
			pst.setString(2, password);
			rs = pst.executeQuery();
			
			if(rs.next()) {
				user = new User();
				user.setIdUtente(rs.getInt("id"));
				user.setNome(rs.getString("nome"));
				user.setCognome(rs.getString("cognome"));
				user.setEmail(rs.getString("email"));
				user.setUsername(rs.getString("username"));
				//per ragioni di sicurezza non metto password
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			System.out.print(e.getMessage());		
		}finally{
			closeConnection(connection);
		}
		
		return user;
	}
	
	public ArrayList<User> getAllUsers() {

		ArrayList<User> users = null;
		User user = null;
		
		try {
			connection = DBConnection.getConnection();
			query = "SELECT * FROM USER";
			pst = this.connection.prepareStatement(query);
			rs = pst.executeQuery();
			
			if(rs.next()) {
				user = new User();
				user.setIdUtente(rs.getInt("id"));
				user.setNome(rs.getString("nome"));
				user.setCognome(rs.getString("cognome"));
				user.setEmail(rs.getString("email"));
				user.setUsername(rs.getString("username"));
				//per ragioni di sicurezza non metto password
				users.add(user);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			System.out.print(e.getMessage());		
		}finally{
			closeConnection(connection);
		}
		
		return users;
	}
	

	public void closeConnection(Connection connection) {
		
		try {
			connection.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
	
}
