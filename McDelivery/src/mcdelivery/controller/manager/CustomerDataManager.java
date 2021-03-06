package mcdelivery.controller.manager;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import mcdelivery.controller.connector.DbConnector;
import mcdelivery.model.Customer;

public class CustomerDataManager {

	// Declare database connector object
	private DbConnector dbConn;

	// Declare closable SQL objects
	private Connection conn;
	private Statement stmt;
	private PreparedStatement preStmt;
	private ResultSet results;

	/**
	 * The constructor initializes the database connector object
	 */
	public CustomerDataManager() {
		dbConn = new DbConnector();
	}


	/**
	 * This method adds a new customer into database
	 * @param customer
	 * @return customer ID
	 */
	public int addCustomer(Customer customer) {

		// Define customer ID with 0
		int customerId = 0;

		// Form a SQL string
		String sql = "INSERT INTO customer(Name, Address1, Address2, Postcode, "
				+ "Area, State, Username, Password) "
				+ "VALUES(?, ?, ?, ?, ?, ?, ?, ?)";

		try {

			// Establish connection
			conn = dbConn.getConnection();

			// Set parameters and execute the SQL
			preStmt = conn.prepareStatement(sql, 
					PreparedStatement.RETURN_GENERATED_KEYS);
			preStmt.setString(1, customer.getName());
			preStmt.setString(2, customer.getAddress1());
			preStmt.setString(3, customer.getAddress2());
			preStmt.setInt(4, customer.getPostcode());
			preStmt.setString(5, customer.getArea());
			preStmt.setString(6, customer.getState());
			preStmt.setString(7, customer.getUsername());
			preStmt.setString(8, customer.getPassword());
			preStmt.executeUpdate();

			// Get auto generated key
			results = preStmt.getGeneratedKeys();

			// Set the key as the customer ID
			while(results.next())
				customerId = results.getInt(1);

		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		} finally {
			closeSqlObjects();
		}

		return customerId;

	}


	/**
	 * This method selects a customer by username
	 * and password from the database
	 * @param customer
	 * @return customer
	 */
	public Customer selectCustomer(Customer customer) {
		
		// Declare and initialize the customer object
		Customer dbCustomer = new Customer();

		// Form a SQL string
		String sql = "SELECT CustomerId, Name, Address1, Address2, Postcode, "
				+ "Area, State, McWalletBalance, Username, Password FROM customer "
				+ "WHERE BINARY Username = '" + customer.getUsername() + "' "
				+ "AND BINARY Password = '" + customer.getPassword() + "'";

		try {

			// Establish connection
			conn = dbConn.getConnection();

			// Execute SQL
			stmt = conn.createStatement();
			results = stmt.executeQuery(sql);

			// Wrap results in the customer object
			while(results.next()) {

				dbCustomer.setId(results.getInt(1));
				dbCustomer.setName(results.getString(2));
				dbCustomer.setAddress1(results.getString(3));
				dbCustomer.setAddress2(results.getString(4));
				dbCustomer.setPostcode(results.getInt(5));
				dbCustomer.setArea(results.getString(6));
				dbCustomer.setState(results.getString(7));
				dbCustomer.setMcWalletBalance(results.getDouble(8));
				dbCustomer.setUsername(results.getString(9));
				dbCustomer.setPassword(results.getString(10));
				
			}

		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		} finally {
			closeSqlObjects();
		}

		return dbCustomer;

	}
	
	
	/**
	 * This method count the customer by username from the database
	 * @param customer
	 * @return count of customer
	 */
	public int countCustomer(Customer customer) {
		
		// Declare and initialize the count of customer
		int count = 0;

		// Form a SQL string
		String sql = "SELECT COUNT(*) FROM customer WHERE Username = '" 
				+ customer.getUsername() + "'";

		try {

			// Establish connection
			conn = dbConn.getConnection();

			// Execute SQL
			stmt = conn.createStatement();
			results = stmt.executeQuery(sql);

			// Get count from result
			while(results.next()) 
				count = results.getInt(1);

		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		} finally {
			closeSqlObjects();
		}

		return count;

	}
	
	
	/**
	 * This method update the McWallet balance of a customer
	 * @param customer
	 */
	public void updateMcWalletBalance(Customer customer) {

		// Form a SQL string
		String sql = "UPDATE customer SET McWalletBalance = ? "
				+ "WHERE CustomerId = ?";
		
		try {

			// Establish connection
			conn = dbConn.getConnection();

			// Set parameters and execute the SQL
			preStmt = conn.prepareStatement(sql);
			preStmt.setDouble(1, customer.getMcWalletBalance());
			preStmt.setInt(2, customer.getId());
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
			if(stmt != null)
				stmt.close();
			if(conn != null)
				conn.close();

		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

}
