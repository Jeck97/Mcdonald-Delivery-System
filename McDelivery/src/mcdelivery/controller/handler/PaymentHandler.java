package mcdelivery.controller.handler;

import java.text.DecimalFormat;
import java.util.List;

import mcdelivery.controller.facade.CustomerFacade;
import mcdelivery.controller.facade.OrderFacade;
import mcdelivery.controller.facade.OrderedProductFacade;
import mcdelivery.model.Customer;
import mcdelivery.model.Order;
import mcdelivery.model.OrderedProduct;

/**
 * This class handles the payment process from customer
 * @author Tiang King Jeck
 *
 */
public class PaymentHandler {
	
	public static final int MCWALLET = 1;
	public static final int INTERBANK = 2;
	
	
	/**
	 * This method processes the payment and get the order number
	 * @param customer
	 * @param order
	 * @return order number
	 */
	public Order processPayment(Customer customer, Order order) {
		
		// Update the McWallet if customer pay by McWallet
		if(order.getPaymentMethod() == MCWALLET) {
			
			// Get new balance of McWallet
			double newMcWalletBalance = this.getNewMcWalletBalance
					(customer.getMcWalletBalance(), order.getAmountAfterTax());
			
			// Set the new balance to customer
			customer.setMcWalletBalance(newMcWalletBalance);
			
			// Update the new balance to database
			this.updateMcWalletBalance(customer);

		}
		
		// Set customer into order
		order.setCustomer(customer);
		
		// Get order number
		int orderNumber = this.getOrderNumber(order);
		order.setId(orderNumber);
		
		return order;
		
	}
	
	
	/**
	 * This method computes the new balance of McWallet
	 * after purchasing the order
	 * @param currentBalance
	 * @param amountAftertax
	 * @return new balance of McWallet
	 */
	private double getNewMcWalletBalance(double currentBalance, double amountAftertax) {
		
		// Compute new balance
		double newBalance = currentBalance - amountAftertax;
		
		// Round off to two decimal places
		newBalance = Double.valueOf(new DecimalFormat("#.##").format(newBalance));

		return newBalance;
		
	}
	
	
	/**
	 * This method update the McWallet balance in 
	 * database after purchasing order
	 * @param customer
	 */
	private void updateMcWalletBalance(Customer customer) {
		
		CustomerFacade customerFacade = new CustomerFacade();
		customerFacade.updateMcWalletBalance(customer);
		
	}
	
	
	/**
	 * This method gets the order number returned after 
	 * adding the order into database by facade
	 * @param order
	 * @return order number
	 */
	private int getOrderNumber(Order order) {
		
		// Add order into database
		OrderFacade orderFacade = new OrderFacade();
		int orderNumber = orderFacade.addOrder(order);
		
		// Add the corresponding products into database
		List<OrderedProduct> orderedProducts = order.getOrderedProducts();
		
		for(OrderedProduct orderedProduct:orderedProducts) 
			this.addOrderedProduct(orderedProduct, orderNumber);
		
		return orderNumber;
		
	}
	
	
	/**
	 * This method adds ordered product into database 
	 * that purchased by customer
	 * @param orderedProduct
	 * @param orderId
	 */
	private void addOrderedProduct(OrderedProduct orderedProduct, int orderId) {
		
		OrderedProductFacade orderedProductFacade = new OrderedProductFacade();
		orderedProductFacade.addOrderedProduct(orderedProduct, orderId);
		
	}

}
