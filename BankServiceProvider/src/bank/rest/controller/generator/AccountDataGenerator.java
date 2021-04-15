package bank.rest.controller.generator;

import bank.rest.model.Account;


/**
 * This class generates the account data
 * @author Tiang King Jeck
 *
 */
public class AccountDataGenerator {
	
	private BankDataGenerator bankGenerator;
	
	
	/**
	 * This constructor initializes the bank generator
	 */
	public AccountDataGenerator() {
		
		bankGenerator = new BankDataGenerator();
		
	}
	
	
	/**
	 * This method generate bank account of Tiang
	 * This account represents a rich account
	 * @return Tiang's account
	 */
	public Account getTiangAccount() {
		
		Account account = new Account();
		account.setAccountNumber(80803123);
		account.setHolderName("Tiang King Jeck");
		account.setPassword("789789");
		account.setBalanceAmount(9999999.99);
		account.setBank(bankGenerator.getBankIslam());
		
		return account;
		
	}
	
	
	/**
	 * This method generate bank account of Yamamoto
	 * This account represents a poor account
	 * @return Yamamoto's account
	 */
	public Account getYamamotoAccount() {
		
		Account account = new Account();
		account.setAccountNumber(30881234);
		account.setHolderName("Yamamoto Takeshi");
		account.setPassword("yamayama");
		account.setBalanceAmount(100.00);
		account.setBank(bankGenerator.getCimbBank());
		
		return account;
		
	}
	
	
	/**
	 * This method generate bank account of King Arthur
	 * This account represents a salary man account
	 * @return King Arthur's account
	 */
	public Account getArthurAccount() {
		
		Account account = new Account();
		account.setAccountNumber(12271469);
		account.setHolderName("Arthur Pendragon");
		account.setPassword("1314520");
		account.setBalanceAmount(50000.00);
		account.setBank(bankGenerator.getPublicBank());
		
		return account;
		
	}
	
	
	/**
	 * This method generate bank account of Hatsune
	 * This account represents a student account
	 * @return Hatsune's account
	 */
	public Account getHatsuneAccount() {
		
		Account account = new Account();
		account.setAccountNumber(39010831);
		account.setHolderName("Hatsune Miku");
		account.setPassword("39music");
		account.setBalanceAmount(3939.39);
		account.setBank(bankGenerator.getMayBank());
		
		return account;
		
	}
	
}
