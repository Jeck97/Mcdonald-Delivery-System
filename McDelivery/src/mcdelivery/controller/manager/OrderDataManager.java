package mcdelivery.controller.manager;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import mcdelivery.controller.connector.DbConnector;
import mcdelivery.model.Order;

public class OrderDataManager {

	// Declare database connector object
	private DbConnector dbConn;

	// Declare closable SQL objects
	private Connection conn;
	private PreparedStatement preStmt;
	private ResultSet results;

	/**
	 * The constructor initializes the database connector object
	 */
	public OrderDataManager() {
		dbConn = new DbConnector();
	}


	/**
	 * This method adds a new order into database
	 * @param order
	 * @return order ID
	 */
	public int addOrder(Order order) {

		// Define order ID with 0
		int orderId = 0;

		// Form a SQL string
		String sql = "INSERT INTO `order`(OrderBy, AmountBeforeTax, Tax, "
				+ "AmountAfterTax, PaymentMethod) VALUES(?, ?, ?, ?, ?)";

		try {

			// Establish connection
			conn = dbConn.getConnection();

			// Set parameters and execute the SQL
			preStmt = conn.prepareStatement(sql, 
					PreparedStatement.RETURN_GENERATED_KEYS);
			preStmt.setInt(1, order.getCustomer().getId());
			preStmt.setDouble(2, order.getAmountBeforeTax());
			preStmt.setDouble(3, order.getServiceTax());
			preStmt.setDouble(4, order.getAmountAfterTax());
			preStmt.setInt(5, order.getPaymentMethod());
			preStmt.executeUpdate();

			// Get auto generated key
			results = preStmt.getGeneratedKeys();

			// Set the key as the order ID
			while(results.next())
				orderId = results.getInt(1);

		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		} finally {
			closeSqlObjects();
		}

		return orderId;

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
