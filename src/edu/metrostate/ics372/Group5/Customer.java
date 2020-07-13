package edu.metrostate.ics372.Group5;

import java.io.Serializable;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

/**
 * 
 * This class creates the customer object and stores it to a linkedList in
 * CustomerList class. This class contains method to search for customers,
 * prints all customers, print string, add/remove credit cards and remove
 * customers. This class also stores the credit card object in a linked list.
 * 
 * 
 * @author Anthony Nguyen
 * @version 1.00.00
 * @since 2020.06.25
 */

public class Customer extends People implements Serializable {

	private static final long serialVersionUID = 1L;

	private List<CreditCard> creditCardList = new LinkedList<CreditCard>(); // stores customers credit cards

	public Customer(String name, String address, String phone) {
		super(name, address, phone);
		this.id = (CustomerIdServer.instance()).getId();
	}

	/**
	 * This will check if a customer have a matching card in the system
	 * 
	 * @param checkCreditCard the card to find
	 * @return true if card is matching
	 */
	public boolean checkCreditCardDuplicate(String checkCreditCard) {
		for (Iterator<CreditCard> iterator = creditCardList.iterator(); iterator.hasNext();) {
			CreditCard creditCard = (CreditCard) iterator.next();
			if (creditCard.match(checkCreditCard)) {
				return true;
			}
		}
		return false;
	}

	/**
	 * Add credit card to linked list of a customer
	 * 
	 * @param creditCard to add to linked list
	 * @return true if card was added to linked list
	 */
	public boolean addCreditCard(CreditCard creditCard) {
		if (creditCardList.add(creditCard)) {
			return true;
		}
		return false;
	}

	/**
	 * This method removes a card from the customer credit card collection using
	 * only the credit card number
	 * 
	 * @param removeCreditCard the card to remove from Linked List
	 */
	public void removeCreditCardNumber(String removeCreditCard) {
		for (Iterator<CreditCard> iterator = creditCardList.iterator(); iterator.hasNext();) {
			CreditCard creditCard = (CreditCard) iterator.next();
			if (creditCard.match(removeCreditCard)) {
				creditCardList.remove(creditCard); // removes card
			}
		}
	}

	/**
	 * Prints all credit cards from customer
	 * 
	 * @return string data of all credit cards of customer
	 */
	public String printAllCreditCards() {
		String output = "";
		for (Iterator<CreditCard> iterator = creditCardList.iterator(); iterator.hasNext();) {
			CreditCard creditCard = (CreditCard) iterator.next();
			output += creditCard.toString();
		}
		return output;
	}

	/**
	 * This method count how many credit card the customer have
	 * 
	 * @return how many cards found
	 */
	public int howManyCards() {
		return creditCardList.size();
	}

	/**
	 * Check if the id matches with the number
	 * 
	 * @param Number to check if match with object id
	 * @return true if it matches
	 */
	public boolean match(String Number) {
		return this.id.equals(Number);
	}
}
