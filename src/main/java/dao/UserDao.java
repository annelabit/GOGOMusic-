package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import model.User;

public class UserDao {

	private Connection connection;
	private String query;
	private PreparedStatement pst;
	private ResultSet rs;
	
	
	public UserDao(Connection connection) {
		this.connection = connection;
	}
	
	public User userLogin(String username, String password) {
		User user = null;
		
		
		try {
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
			
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.print(e.getMessage());		
		}
		
		return user;
	}
	
}
