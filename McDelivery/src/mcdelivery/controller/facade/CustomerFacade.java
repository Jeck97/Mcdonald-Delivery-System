package mcdelivery.controller.facade;

import mcdelivery.controller.manager.CustomerDataManager;
import mcdelivery.model.Customer;

public class CustomerFacade {

	// Declare CustomerDataManager object
	private CustomerDataManager customerDataMgr;


	/**
	 * The constructor initializes the CustomerDataManager object
	 */
	public CustomerFacade() {
		customerDataMgr = new CustomerDataManager();
	}


	/**
	 * This method adds a new customer into database
	 * @param customer
	 * @return customer ID
	 */
	public int addCustomer(Customer customer) {

		return customerDataMgr.addCustomer(customer);

	}


	/**
	 * This method selects a customer by username from the database
	 * @param customer
	 * @return customer
	 */
	public Customer selectCustomer(Customer customer) {

		return customerDataMgr.selectCustomer(customer);

	}
	
	
	/**
	 * This method count the customer by username from the database
	 * @param customer
	 * @return count of customer
	 */
	public int countCustomer(Customer customer) {
		
		return customerDataMgr.countCustomer(customer);
		
	}
	
	
	/**
	 * This method update the McWallet balance of a customer
	 * @param customer
	 */
	public void updateMcWalletBalance(Customer customer) {
		
		customerDataMgr.updateMcWalletBalance(customer);
		
	}

}
