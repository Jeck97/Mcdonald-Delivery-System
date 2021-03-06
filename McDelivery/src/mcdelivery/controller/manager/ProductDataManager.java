package mcdelivery.controller.manager;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import mcdelivery.controller.connector.DbConnector;
import mcdelivery.model.Product;
import mcdelivery.model.Type;

public class ProductDataManager {

	// Declare database connector object
	private DbConnector dbConn;

	// Declare closable SQL objects
	private Connection conn;
	private Statement stmt;
	private ResultSet results;
	

	/**
	 * The constructor initializes the database connector object
	 */
	public ProductDataManager() {
		dbConn = new DbConnector();
	}


	/**
	 * This method selects a list of products from the database
	 * @return List of products
	 */
	public List<Product> selectProducts() {

		// Declare and initialize the product list
		List<Product> products = new ArrayList<Product>();

		// Form a SQL string
		String sql = "SELECT p.ProductId, p.Name, p.Price, p.Type, t.Name "
				+ "FROM product p JOIN type t ON p.type = t.TypeId "
				+ "ORDER BY p.ProductId";

		try {

			// Establish connection
			conn = dbConn.getConnection();
			
			// Execute SQL
			stmt = conn.createStatement();
			results = stmt.executeQuery(sql);

			// Wrap results in the list
			while(results.next()) {
				
				// Wrap result in type object
				Type type = new Type();
				type.setId(results.getInt(4));
				type.setName(results.getString(5));
				
				// Wrap result in product object
				Product product = new Product();
				product.setId(results.getInt(1));
				product.setName(results.getString(2));
				product.setPrice(results.getDouble(3));
				product.setType(type);
				
				// Add into list
				products.add(product);
				
			}

		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		} finally {
			closeSqlObjects();
		}

		return products;

	}
	
	
	/**
	 * This method selects a products by using ID from the database
	 * @param product with ID
	 * @return product with full information
	 */
	public Product selectProduct(Product product) {

		// Declare and initialize the product object
		Product dbProduct = new Product();

		// Form a SQL string
		String sql = "SELECT p.ProductId, p.Name, p.Price, p.Type, t.Name "
				+ "FROM product p JOIN type t ON p.type = t.TypeId "
				+ "WHERE p.ProductId = '" + product.getId() + "'";

		try {

			// Establish connection
			conn = dbConn.getConnection();
			
			// Execute SQL
			stmt = conn.createStatement();
			results = stmt.executeQuery(sql);

			// Wrap results in the list
			while(results.next()) {
				
				// Wrap result in type object
				Type type = new Type();
				type.setId(results.getInt(4));
				type.setName(results.getString(5));
				
				// Wrap result in product object
				dbProduct.setId(results.getInt(1));
				dbProduct.setName(results.getString(2));
				dbProduct.setPrice(results.getDouble(3));
				dbProduct.setType(type);
				
			}

		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		} finally {
			closeSqlObjects();
		}

		return dbProduct;

	}


	/**
	 * This method closes all closable SQL objects
	 */
	private void closeSqlObjects() {

		try {
			
			if(results != null)
				results.close();
			if(stmt != null)
				stmt.close();
			if(conn != null)
				conn.close();

		} catch (SQLException e) {
			e.printStackTrace();
		}

	}


}
