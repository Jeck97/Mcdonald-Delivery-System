package mcdelivery.controller.handler;

import java.text.DecimalFormat;

import mcdelivery.controller.facade.CustomerFacade;
import mcdelivery.model.Customer;

/**
 * This class handles the reload McWallet process 
 * after transaction had been done
 * @author Tiang King Jeck
 *
 */
public class ReloadHandler {

	/**
	 * This method processes the reload and update the McWallet balance
	 * @param customer
	 */
	public void processReload(Customer customer, double amountReload) {
		
			
		// Get new balance of McWallet
		double newMcWalletBalance = this.getNewMcWalletBalance
				(customer.getMcWalletBalance(), amountReload);
			
		// Set the new balance to customer
		customer.setMcWalletBalance(newMcWalletBalance);
			
		// Update the new balance to database
		this.updateMcWalletBalance(customer);

	}
	
	
	/**
	 * This method computes the new balance of McWallet
	 * after reloading McWallet
	 * @param currentBalance
	 * @param amountReload
	 * @return new balance of McWallet
	 */
	private double getNewMcWalletBalance(double currentBalance, double amountReload) {
		
		// Compute new balance
		double newBalance = currentBalance + amountReload;
		
		// Round off to two decimal places
		newBalance = Double.valueOf(new DecimalFormat("#.##").format(newBalance));

		return newBalance;
		
	}
	
	
	/**
	 * This method update the McWallet balance in 
	 * database after reloading McWallet
	 * @param customer
	 */
	private void updateMcWalletBalance(Customer customer) {
		
		CustomerFacade customerFacade = new CustomerFacade();
		customerFacade.updateMcWalletBalance(customer);
		
	}
	
}
