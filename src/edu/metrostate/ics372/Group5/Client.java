package edu.metrostate.ics372.Group5;

/**
 * 
 * This is a sub class of the people class, this represents the people hosting the show and implements features for the balance and a toString method
 * 
 */
import java.io.Serializable;
import java.text.DecimalFormat;

public class Client extends People implements Serializable {
	protected double balance;
	private static final long serialVersionUID = 1L;
	private static DecimalFormat df2 = new DecimalFormat("#.00"); // balance format

	// Constructor for the client object
	public Client(String name, String address, String phoneNumber) {
		super(name, address, phoneNumber);
		balance = 0;
		// this is to make sure that there are not duplicates
		this.id = (IdServer.instance()).getId();
		System.out.println("New Client: " + toString());
	}

	/**
	 * get balance
	 * 
	 * @return balance
	 */
	public double getBalance() {
		return balance;
	}

	/**
	 * set balance
	 * 
	 * @param balance how much money to set as balance
	 */
	public void setBalance(double balance) {
		this.balance = balance;
	}

	/**
	 * add to balance
	 * 
	 * @param addBalance how much money to add to balance
	 */
	public void addBalance(double addBalance) {
		balance += addBalance;
	}

	/**
	 * subtract from balance
	 * 
	 * @param subBalance how much money to reduce
	 */
	public void subBalance(double subBalance) {
		this.balance += subBalance;
	}

	/**
	 * String version of client
	 */
	public String toString() {
		return super.toString() + "Balance:$" + df2.format(balance);
	}

}
