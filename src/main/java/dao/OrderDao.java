package dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import model.DBConnection;
import model.Order;
import model.Product;

public class OrderDao {

	private Connection connection;
	private String query;
	private PreparedStatement pst;
	private ResultSet rs;

	public OrderDao() {
	}

	public boolean insertOrder(Order order, ArrayList<Integer> seatIds) {

		boolean result = false;

		try {
			connection = DBConnection.getConnection();
			// devo inserire sia in order sia in order_seats
			query = "INSERT INTO `order` (productId, userId, quantity, date, totalPrice, time, showId,"
					+ " nome, indirizzo, città, paese, cap, nomeTitolare, "
					+ "numeroCarta, scadenza, cvv, email) VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";

			pst = this.connection.prepareStatement(query, PreparedStatement.RETURN_GENERATED_KEYS);
			pst.setInt(1, order.getId());
			pst.setInt(2, order.getUid());
			pst.setInt(3, order.getQuantity());
			pst.setDate(4, order.getDate());
			pst.setFloat(5, order.getPrice());
			pst.setString(6, order.getTime());
			pst.setInt(7, order.getShowId());
			pst.setString(8, order.getNome());
			pst.setString(9, order.getIndirizzo());
			pst.setString(10, order.getCittà());
			pst.setString(11, order.getPaese());
			pst.setInt(12, order.getCap());
			pst.setString(13, order.getNomeTitolare());
			pst.setString(14, order.getNumeroCarta());
			pst.setString(15, order.getScadenza());
			pst.setInt(16, order.getCvv());
			pst.setString(17, order.getEmail());

			pst.executeUpdate();

			// order è auto increment quindi devo prendere id
			ResultSet generatedKeys = pst.getGeneratedKeys();
			int orderId = 0;
			if (generatedKeys.next()) {
				orderId = generatedKeys.getInt(1);
			}

			for (int s : seatIds) {
				// devo recuperare showSeatId
				query = "SELECT * FROM show_seats WHERE seatId = ? AND showId = ?";
				pst = this.connection.prepareStatement(query);
				pst.setInt(1, s);
				pst.setInt(2, order.getShowId());
				rs = pst.executeQuery();

				int showSeatId = 0;

				if (rs.next()) {
					showSeatId = rs.getInt("id");
				}

				String query = "INSERT INTO order_seats (orderID, showSeatId) VALUES(?,?)";

				pst = this.connection.prepareStatement(query);
				pst.setInt(1, orderId);
				pst.setInt(2, showSeatId);
				pst.executeUpdate();

			}

			result = true; // se è arrivato qui non ci sono eccezioni

		} catch (Exception e) {
			e.printStackTrace();
			System.out.print(e.getMessage());
		} finally {
			closeConnection(connection);
		}

		return result;

	}

	public ArrayList<Integer> getOrderShowSeats(int id) {

		ArrayList<Integer> seatList = new ArrayList<>();

		try {
			connection = DBConnection.getConnection();
			query = "SELECT * FROM `order_seats` WHERE orderId = ?";
			pst = this.connection.prepareStatement(query);
			pst.setInt(1, id);
			rs = pst.executeQuery();

			while (rs.next()) {
				int showSeatId = rs.getInt("showSeatId");

				seatList.add(showSeatId);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			closeConnection(connection);
		}

		return seatList;

	}

	public ArrayList<Order> userOrders(int id) {

		ArrayList<Order> orderList = new ArrayList<>();

		ProductDao pDao = new ProductDao();

		try {
			connection = DBConnection.getConnection();
			query = "SELECT * FROM `order` WHERE userId=? ORDER BY orderId DESC";
			pst = this.connection.prepareStatement(query);
			pst.setInt(1, id);
			rs = pst.executeQuery();

			while (rs.next()) {
				Order order = new Order();
				int pId = rs.getInt("productId");

				Product product = pDao.getSingleProduct(pId);
				order.setOrderId(rs.getInt("orderId"));
				order.setId(pId);
				order.setUid(rs.getInt("userId"));
				order.setName(product.getName());
				order.setCategory(product.getCategory());
				order.setPrice(rs.getFloat("totalPrice"));
				order.setQuantity(rs.getInt("quantity"));
				order.setDate(rs.getDate("date"));
				order.setTime(rs.getString("time"));
				order.setNome(rs.getString(("nome")));
				order.setIndirizzo(rs.getString(("indirizzo")));
				order.setCittà(rs.getString(("città")));
				order.setPaese(rs.getString(("paese")));
				order.setCap(rs.getInt(("cap")));
				order.setNomeTitolare(rs.getString(("nomeTitolare")));
				// order.setNome(rs.getString(("numeroCarta")));
				order.setScadenza(rs.getString("scadenza"));
				// order.setCvv(rs.getInt(("cvv")));
				order.setEmail(rs.getString(("email")));
				orderList.add(order);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			closeConnection(connection);
		}

		return orderList;
	}

	// order id
	public boolean cancelOrder(int id) {

		boolean result = false;

		try {
			connection = DBConnection.getConnection();
			// posti associati all'ordine
			query = "SELECT * FROM order_seats WHERE orderId = ?";
			pst = this.connection.prepareStatement(query);
			pst.setInt(1, id);

			rs = pst.executeQuery();

			while (rs.next()) {

				int showSeatId = rs.getInt("showSeatId");

				// i posti sono di nuovo disponibili
				query = "UPDATE show_seats SET available = 1 WHERE id = ?";
				pst = this.connection.prepareStatement(query);
				pst.setInt(1, showSeatId);
				pst.executeUpdate();

			}

			// cancello entry sia in order sia in order_seats

			query = "DELETE FROM order_seats WHERE orderId=?";
			pst = this.connection.prepareStatement(query);
			pst.setInt(1, id);
			pst.execute();

			query = "DELETE FROM `order` WHERE orderId=?";
			pst = this.connection.prepareStatement(query);
			pst.setInt(1, id);
			pst.execute();

			result = true;

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			closeConnection(connection);
		}

		return result;

	}

	public ArrayList<Order> getOrdersByDate(Date start, Date end) {

		ArrayList<Order> orderList = new ArrayList<>();

		ProductDao pDao = new ProductDao();

		try {
			connection = DBConnection.getConnection();
			query = "SELECT * FROM `order` WHERE date BETWEEN ? AND ? ORDER BY orderId DESC";

			pst = this.connection.prepareStatement(query);
			pst.setDate(1, start);
			pst.setDate(2, end);
			rs = pst.executeQuery();

			while (rs.next()) {
				Order order = new Order();
				int pId = rs.getInt("productId");

				Product product = pDao.getSingleProduct(pId);
				order.setOrderId(rs.getInt("orderId"));
				order.setId(pId);
				order.setUid(rs.getInt("userId"));
				order.setName(product.getName());
				order.setCategory(product.getCategory());
				order.setPrice(rs.getFloat("totalPrice"));
				order.setQuantity(rs.getInt("quantity"));
				order.setDate(rs.getDate("date"));
				order.setTime(rs.getString("time"));
				order.setNome(rs.getString(("nome")));
				order.setIndirizzo(rs.getString(("indirizzo")));
				order.setCittà(rs.getString(("città")));
				order.setPaese(rs.getString(("paese")));
				order.setCap(rs.getInt(("cap")));
				order.setNomeTitolare(rs.getString(("nomeTitolare")));
				// order.setNome(rs.getString(("numeroCarta")));
				order.setScadenza(rs.getString("scadenza"));
				// order.setCvv(rs.getInt(("cvv")));
				order.setEmail(rs.getString(("email")));
				orderList.add(order);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			closeConnection(connection);
		}

		return orderList;
	}
	
	public ArrayList<Order> getOrdersByDateAndUser(Date start, Date end, int id) {

		ArrayList<Order> orderList = new ArrayList<>();

		ProductDao pDao = new ProductDao();

		try {
			connection = DBConnection.getConnection();
			query = "SELECT * FROM `order` WHERE userId = ? AND date BETWEEN ? AND ? ORDER BY orderId DESC";

			pst = this.connection.prepareStatement(query);
			pst.setInt(1, id);
			pst.setDate(2, start);
			pst.setDate(3, end);
			rs = pst.executeQuery();

			while (rs.next()) {
				Order order = new Order();
				int pId = rs.getInt("productId");

				Product product = pDao.getSingleProduct(pId);
				order.setOrderId(rs.getInt("orderId"));
				order.setId(pId);
				order.setUid(rs.getInt("userId"));
				order.setName(product.getName());
				order.setCategory(product.getCategory());
				order.setPrice(rs.getFloat("totalPrice"));
				order.setQuantity(rs.getInt("quantity"));
				order.setDate(rs.getDate("date"));
				order.setTime(rs.getString("time"));
				order.setNome(rs.getString(("nome")));
				order.setIndirizzo(rs.getString(("indirizzo")));
				order.setCittà(rs.getString(("città")));
				order.setPaese(rs.getString(("paese")));
				order.setCap(rs.getInt(("cap")));
				order.setNomeTitolare(rs.getString(("nomeTitolare")));
				// order.setNome(rs.getString(("numeroCarta")));
				order.setScadenza(rs.getString("scadenza"));
				// order.setCvv(rs.getInt(("cvv")));
				order.setEmail(rs.getString(("email")));
				orderList.add(order);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			closeConnection(connection);
		}

		return orderList;
	}

	public ArrayList<Order> getAllOrders() {

		ArrayList<Order> orderList = new ArrayList<>();

		ProductDao pDao = new ProductDao();

		try {
			connection = DBConnection.getConnection();
			query = "SELECT * FROM `order` ORDER BY orderId DESC";

			pst = this.connection.prepareStatement(query);
			rs = pst.executeQuery();

			while (rs.next()) {
				Order order = new Order();
				int pId = rs.getInt("productId");

				Product product = pDao.getSingleProduct(pId);
				order.setOrderId(rs.getInt("orderId"));
				order.setId(pId);
				order.setUid(rs.getInt("userId"));
				order.setName(product.getName());
				order.setCategory(product.getCategory());
				order.setPrice(rs.getFloat("totalPrice"));
				order.setQuantity(rs.getInt("quantity"));
				order.setDate(rs.getDate("date"));
				order.setTime(rs.getString("time"));

				order.setNome(rs.getString(("nome")));
				order.setIndirizzo(rs.getString(("indirizzo")));
				order.setCittà(rs.getString(("città")));
				order.setPaese(rs.getString(("paese")));
				order.setCap(rs.getInt(("cap")));
				order.setNomeTitolare(rs.getString(("nomeTitolare")));
				// order.setNome(rs.getString(("numeroCarta")));
				order.setScadenza(rs.getString("scadenza"));
				// order.setCvv(rs.getInt(("cvv")));
				order.setEmail(rs.getString(("email")));

				orderList.add(order);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			closeConnection(connection);
		}

		return orderList;
	}

	public void closeConnection(Connection connection) {

		try {
			connection.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

}
