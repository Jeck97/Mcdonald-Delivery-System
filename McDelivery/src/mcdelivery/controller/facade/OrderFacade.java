package mcdelivery.controller.facade;

import mcdelivery.controller.manager.OrderDataManager;
import mcdelivery.model.Order;

public class OrderFacade {
	
	// Declare OrderDataManager object
	private OrderDataManager orderDataMgr;


	/**
	 * The constructor initializes the OrderDataManager object
	 */
	public OrderFacade() {
		orderDataMgr = new OrderDataManager();
	}
	
	
	/**
	 * This method adds a new order into database
	 * @param order
	 * @return order ID
	 */
	public int addOrder(Order order) {
		
		return orderDataMgr.addOrder(order);
		
	}

}
