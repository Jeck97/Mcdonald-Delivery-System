package bank.rest.controller.manager;

import java.util.ArrayList;
import java.util.List;

import bank.rest.controller.generator.AccountDataGenerator;
import bank.rest.model.Account;

/**
 * This class manages the accounts in banks.
 * @author Tiang King Jeck
 *
 */
public class AccountDataManager {

	// Apply static to avoid data redundancy out of sync
	private static final List<Account> accounts = generateBankAccounts();

	
	/**
	 * This method updates specific account in list
	 * @param account
	 */
	public void updateAccountInList(Account account) {
		
		accounts.set(accounts.indexOf(account), account);
		
	}
	
	
	/**
	 * This method get the list of accounts
	 * @return copy of accounts list
	 */
	public List<Account> getAccounts() {
		
		// Return the copy of accounts list
		return new ArrayList<Account>(accounts);
		
	}
	
	
	/**
	 * This method gets an account from list by account number
	 * @param accountNumber
	 * @return account with this account number, otherwise null
	 */
	public Account getAccount(long accountNumber) {
		
		// Loop to find account by comparing account number
		for(Account account : accounts) 
			if (accountNumber == account.getAccountNumber()) 
				return account;

		return null;

	}
	

	/**
	 * This method generates a list of accounts with different bank
	 * @return a list of accounts
	 */
	private static List<Account> generateBankAccounts() {

		List<Account> accounts = new ArrayList<Account>();

		// Add each generated account into a list
		AccountDataGenerator accountGenerator = new AccountDataGenerator();
		accounts.add(accountGenerator.getArthurAccount());
		accounts.add(accountGenerator.getHatsuneAccount());
		accounts.add(accountGenerator.getTiangAccount());
		accounts.add(accountGenerator.getYamamotoAccount());

		return accounts;

	}

}
