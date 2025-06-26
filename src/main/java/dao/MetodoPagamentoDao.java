package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import model.*;

public class MetodoPagamentoDao {

	private Connection connection;
	private String query;
	private PreparedStatement pst;
	private ResultSet rs;

	public MetodoPagamentoDao() {
	}

	public ArrayList<MetodoPagamento> getAllPaymentMethods(int uId) {

		ArrayList<MetodoPagamento> addresses = new ArrayList<>();

		String sql = "SELECT * FROM metodo_pagamento WHERE userId = ? ORDER BY main DESC";
		try {
			connection = DBConnection.getConnection();
			PreparedStatement pst = connection.prepareStatement(sql);
			pst.setInt(1, uId);
			ResultSet rs = pst.executeQuery();
			while (rs.next()) {
				MetodoPagamento method = new MetodoPagamento();
				method.setId(rs.getInt("id"));
				method.setNomeCarta(rs.getString("nomeCarta"));
				method.setTipo(rs.getString("tipo"));
				method.setScadenza(rs.getString("scadenza"));
				method.setNumeroCarta(rs.getString("numero"));
				method.setCvv(rs.getInt("cvv"));
				method.setMain(rs.getInt("main"));
				method.setUserId(uId);
				method.setLast4Numbers(rs.getInt("lastDigits"));
				addresses.add(method);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			closeConnection(connection);
		}
		return addresses;

	}

	public int insertMethod(MetodoPagamento method) {

		int id = 0;
		try {

			connection = DBConnection.getConnection();

			query = "INSERT INTO metodo_pagamento (userId,tipo,numero,nomeCarta,"
					+ "scadenza,cvv,main,lastDigits) VALUES(?,?,?,?,?,?,?,?)";
			pst = this.connection.prepareStatement(query, PreparedStatement.RETURN_GENERATED_KEYS);
			pst.setInt(1, method.getUserId());
			pst.setString(2, method.getTipo());
			pst.setString(3, method.getNumeroCarta());
			pst.setString(4, method.getNomeCarta());
			pst.setString(5, method.getScadenza());
			pst.setInt(6, method.getCvv());
			pst.setInt(7, method.getMain());
			pst.setInt(8, method.getLast4Numbers());
			pst.executeUpdate();

			

			ResultSet generatedKeys = pst.getGeneratedKeys();

			if (generatedKeys.next()) {
				id = generatedKeys.getInt(1);
				if (method.getMain() == 1) {
					setAsMain(id,method.getUserId());
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
	
	public boolean deletePaymentMethod(int id) {

		boolean result = false;

		try {
			
			System.out.println("Setting " + id +" as Main");
			
			connection = DBConnection.getConnection();

			query = "DELETE FROM metodo_pagamento WHERE id = ?";

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

	public boolean setAsMain(int id, int uId) {

		boolean result = false;

		try {
			
			System.out.println("Setting " + id +" as Main");
			
			connection = DBConnection.getConnection();

			query = "UPDATE metodo_pagamento SET main = 0 WHERE userId = ?";

			pst = this.connection.prepareStatement(query, PreparedStatement.RETURN_GENERATED_KEYS);
			pst.setInt(1, uId);

			pst.executeUpdate();

			query = "UPDATE metodo_pagamento SET main = 1 WHERE id = ? AND userId = ?";

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
