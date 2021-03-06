package bank.rest.controller.handler;

import java.text.DecimalFormat;

import bank.rest.controller.manager.AccountDataManager;
import bank.rest.model.Account;

public class TransactionHandler {

	// Declare an account object to represent the account 
	// that is using in this transaction
	private Account accountInUsed;
	

	public TransactionHandler(Account account) {
		
		this.accountInUsed = account;
		
	}

	/**
	 * This method get the current account in used without password
	 * @param accountNumber
	 * @return account in used that without password
	 */
	public Account getAccountInUsedExcludePassword() {

		// Create a new instance instead of assign directly
		Account account = new Account();
		account.setAccountNumber(accountInUsed.getAccountNumber());
		account.setHolderName(accountInUsed.getHolderName());
		account.setBalanceAmount(accountInUsed.getBalanceAmount());
		account.setBank(accountInUsed.getBank());

		return account;

	}


	/**
	 * This methods update the amount of balance after transaction is completed
	 * @param transactionAccount
	 * @param amountToPay
	 */
	public void updateBankAccountBalance(double amountToPay) {
		
		// Compute new balance
		double newBalance = accountInUsed.getBalanceAmount() - amountToPay;

		// Round off to two decimal places
		newBalance = Double.valueOf(new DecimalFormat("#.##").format(newBalance));

		// Update new balance to account in used
		accountInUsed.setBalanceAmount(newBalance);
		
		// Update account in list
		AccountDataManager accountDataMngr = new AccountDataManager();
		accountDataMngr.updateAccountInList(accountInUsed);
		
	}


	/**
	 * This method validates the sufficiency of account balance to process the transaction
	 * @param amountToPay
	 * @return true if the account balance is sufficient
	 */
	public boolean isBalanceSufficient(double amountToPay) {

		return accountInUsed.getBalanceAmount() >= amountToPay;

	}


	/**
	 * This method validates the password for transaction account
	 * @param password
	 * @return true if password is correct
	 */
	public boolean isPasswardMatch(String password) {

		return accountInUsed.getPassword().equals(password);

	}




}
