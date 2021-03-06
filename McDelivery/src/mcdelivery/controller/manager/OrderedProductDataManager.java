package mcdelivery.controller.manager;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import mcdelivery.controller.connector.DbConnector;
import mcdelivery.model.OrderedProduct;

public class OrderedProductDataManager {

	// Declare database connector object
	private DbConnector dbConn;

	// Declare closable SQL objects
	private Connection conn;
	private PreparedStatement preStmt;
	private ResultSet results;

	/**
	 * The constructor initializes the database connector object
	 */
	public OrderedProductDataManager() {
		dbConn = new DbConnector();
	}


	/**
	 * This method adds a new orderedProduct into database
	 * @param orderedProduct
	 * @param orderId
	 */
	public void addOrderedProduct(OrderedProduct orderedProduct, int orderId) {

		// Form a SQL string
		String sql = "INSERT INTO orderedproduct(`Order`, Product, "
				+ "Quantity, Subtotal) VALUES(?, ?, ?, ?)";

		try {

			// Establish connection
			conn = dbConn.getConnection();

			// Set parameters and execute the SQL
			preStmt = conn.prepareStatement(sql);
			preStmt.setInt(1, orderId);
			preStmt.setInt(2, orderedProduct.getProduct().getId());
			preStmt.setInt(3, orderedProduct.getQuantity());
			preStmt.setDouble(4, orderedProduct.getSubtotal());
			preStmt.executeUpdate();

		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		} finally {
			closeSqlObjects();
		}

	}


	/**
	 * This method closes all closable SQL objects
	 */
	private void closeSqlObjects() {

		try {

			if(results != null)
				results.close();
			if(preStmt != null)
				preStmt.close();
			if(conn != null)
				conn.close();

		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

}
