package mcdelivery.controller.facade;

import mcdelivery.controller.manager.OrderedProductDataManager;
import mcdelivery.model.OrderedProduct;

public class OrderedProductFacade {
	
	// Declare OrderedProductDataManager object
	private OrderedProductDataManager orderedProductDataMgr;


	/**
	 * The constructor initializes the OrderedProductDataManager object
	 */
	public OrderedProductFacade() {
		orderedProductDataMgr = new OrderedProductDataManager();
	}
	
	
	/**
	 * This method adds a new orderedProduct into database
	 * @param orderedProduct
	 * @param orderId
	 */
	public void addOrderedProduct(OrderedProduct orderedProduct, int orderId) {
		
		orderedProductDataMgr.addOrderedProduct(orderedProduct, orderId);
		
	}

}
