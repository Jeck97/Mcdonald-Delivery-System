package mcdelivery.controller.facade;

import java.util.List;

import mcdelivery.controller.manager.ProductDataManager;
import mcdelivery.model.Product;

public class ProductFacade {

	// Declare ProductDataManager object
	private ProductDataManager productDataMgr;


	/**
	 * The constructor initializes the ProductDataManager object
	 */
	public ProductFacade() {
		productDataMgr = new ProductDataManager();
	}


	/**
	 * This method selects a list of products from the database
	 * @return List of products
	 */
	public List<Product> selectProducts() {
		
		return productDataMgr.selectProducts();
		
	}
	
	
	/**
	 * This method selects a products by using ID from the database
	 * @param product with ID
	 * @return product with full information
	 */
	public Product selectProduct(Product product) {
		
		return productDataMgr.selectProduct(product);
		
	}

}
