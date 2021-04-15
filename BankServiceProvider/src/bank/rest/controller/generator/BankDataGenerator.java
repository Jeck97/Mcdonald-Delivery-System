package bank.rest.controller.generator;

import bank.rest.model.Bank;


/**
 * This class generates the bank data
 * @author Tiang King Jeck
 *
 */
public class BankDataGenerator {
	
	
	/**
	 * This class instantiates a new bank object
	 * @param bankName
	 * @return instantiated bank object
	 */
	private Bank getInstantiatedBank(String bankName) {
		
		Bank bank = new Bank();
		bank.setName(bankName);
		
		return bank;
		
	}

	
	/**
	 * This class generates Maybank
	 * @return bank
	 */
	protected Bank getMayBank() {
		
		return this.getInstantiatedBank("Maybank");
		
	}
	
	
	/**
	 * This class generates CIMB Bank
	 * @return bank
	 */
	protected Bank getCimbBank() {
		
		return this.getInstantiatedBank("CIMB Bank");
		
	}
	
	
	/**
	 * This class generates Public Bank
	 * @return bank
	 */
	protected Bank getPublicBank() {
		
		return this.getInstantiatedBank("Public Bank Berhad");
		
	}
	
	
	/**
	 * This class generates RHB Bank
	 * @return bank
	 */
	protected Bank getRhbBank() {
		
		return this.getInstantiatedBank("RHB Bank");
		
	}
	
	
	/**
	 * This class generates Bank Islam
	 * @return bank
	 */
	protected Bank getBankIslam() {
		
		return this.getInstantiatedBank("Bank Islam Malaysia");
		
	}
	
}
