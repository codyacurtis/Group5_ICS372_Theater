package edu.metrostate.ics372.Group5;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Set;

/**
 * 
 * This class creates the customer object and stores it to a linkedlist. This
 * class contains method to search for customers, prints all customers, print
 * string, add/remove credit cards and remove customers. This class also stores
 * the credit card information to a Hashmap where the user ID is the key.
 * 
 * 
 * @author Anthony Nguyen
 * @version 1.00.00
 * @since 2020.06.25
 */


public class Customer {

    private String name; // name of customer
    private String address; // address of customer
    private String phone; // customer's phone number
    private String creditCard; // customer's credit information
    private String expiry; // credit card expiration date
    private String id; // customer's id number

    // stores customers data into Linkedlist
    public static LinkedList<Customer> customerList = new LinkedList<Customer>();
    // use customer's id as a key an use arraylist to store multiple cards
    static Map<String, List<creditCard>> customerCards = new HashMap<>();
    List<creditCard> cardsList = new ArrayList<>();

    /**
     * 
     * @param name       of customer
     * @param address    customer address
     * @param phone      customer phone number
     * @param creditCard customer credit card
     * @param expiry     creditcard expieration date
     * @param id         customer ID // testing purposes
     */
    @SuppressWarnings("unchecked")
    public Customer(String name, String address, String phone, String creditCard, String expiry) {
	this.name = name;
	this.address = address;
	this.phone = phone;
	this.creditCard = creditCard;
	this.expiry = expiry;
	id = generateID();
	creditCard newCard = new creditCard(creditCard, expiry); // create a credit card object
	// card.put(id, card2);
	customerList.add(this);
	// customer.put(id, (List<creditCard>) card2);
	cardsList.add(newCard);
	customerCards.put(id, cardsList);
    }

    /**
     * getter to get customer ID
     * 
     * @return customer id
     */
    public String getId() {
	return id;
    }

    /**
     * Setter for name
     * 
     * @param newName member's new name
     */
    public void setName(String newName) {
	name = newName;
    }

    /**
     * Setter for address
     * 
     * @param newName member's new address
     */
    public void setAddress(String newAddress) {
	address = newAddress;
    }

    /**
     * Setter for phone
     * 
     * @param newName member's new phone
     */
    public void setPhone(String newPhone) {
	phone = newPhone;
    }

    /**
     * 
     * This method returns all of the customers information that has been stored in
     * the linkedList and credit card information
     * 
     * @return all the customers data
     */
    public String getAllCustomers() {
	String list = Arrays.toString(customerList.toArray()).replace("[", "").replace("]", "").replace("\n, ", "")
		.replace(", \n", "\n");
	return list;
    }

    /**
     * 
     * This method search for a customer with the given id and looks for the index
     * of the ID, if not found will return -99 as an error
     * 
     * @param id the target to be found
     */
    public int searchCustomer(String id) {
	String stringOutput = "";

	// loops through customers to find the proper id
	for (int i = 0; i < customerList.size(); i++) {
	    if (customerList.get(i).id.equals(id)) {
		stringOutput += customerList.get(i).toString();
		System.out.println(stringOutput);
		return i;
	    }
	}
	// returns -99 if customer can't be found
	return -99;

    }

    public void customerInformation(String id) {
	int index = searchCustomer(id);
	String stringOutput = "";
	if (index != -99) {
	    stringOutput += customerList.get(index).toString();
	    System.out.println(stringOutput);
	} else
	    System.out.println("User Not found");

    }

    /**
     * This method removes the customer from linked list and also removes their
     * credit card
     * 
     * @param id the customer to be deleted
     */
    public void removeCustomer(String id) {
	for (int i = 0; i < customerList.size(); i++) {
	    if (customerList.get(i).id.equals(id)) {
		customerList.remove(i); // remove customer
		customerCards.remove(id); // remove credit card
		return;
	    }
	}

	System.out.println("not found"); // prints error if customer isn't found
    }

    /**
     * 
     * This method adds a new credit card to a customer, however it can't be a card
     * in use already
     * 
     * @param id         the credit card information to be add for this customer
     * @param creditCard the credit card to be added
     * @param expiry     expiration date for credit card
     */
    public void addCreditCard(String id, String creditCard, String expiry) {

	// checks if the card is valid
	if (!creditCard.matches("[0-9]+") || !expiry.matches("[0-9,/]+")) {
	    System.out.println("\nInvalid Card"); // prints error message card is invalid
	    return;
	}

	else if (searchCustomer(id) == -99) {
	    System.out.println("\nCustomer not found"); // prints error if customer doens't exist
	}

	else if (!checkDuplicate(creditCard)) { // checks if card is a duplicate
	    creditCard addCard = new creditCard(creditCard, expiry); // create a credit card object
	    customerCards.get(id).add(addCard);
	    return;
	}

	else
	    System.out.println("\nCard already in system"); // prints error message if in system
    }

    /**
     * 
     * This method removes a credit card from the system using only the card
     * information
     * 
     * @param creditCard the card to be deleted
     */
    @SuppressWarnings("unlikely-arg-type")
    public void removeCreditCard(String creditCard) {
	int size = 0; // size of arrayList in a Hashmap cell
	Set<String> keys = customerCards.keySet();

	for (String key : keys) { // loop through all the customers
	    size = customerCards.get(key).size();

	    for (int i = 0; i < size; i++) { // loop through the arrayList of cards

		if (customerCards.get(key).get(i).toStringCredit().contains(creditCard) && size > 1) {
		    System.out.println("Card has been removed");
		    customerCards.get(key).remove(i); // removes credit card
		    return;
		    // checks if only card on file for user
		} else if (customerCards.get(key).get(i).toStringCredit().contains(creditCard) && size == 1) {
		    System.out.println("This is the only card on file for customer");
		    return;
		}
	    }
	}
	System.out.print("Card wasn't found"); // can't find the card
    }

    /**
     * 
     * This methods check if a card is already in the system
     * 
     * @param creditCard to card to search for in system
     * @return true if card is found
     */
    public boolean checkDuplicate(String creditCard) {
	int size = 0;
	Set<String> keys = customerCards.keySet();
	for (String key : keys) { // loops through the hash table keys
	    size = customerCards.get(key).size();
	    for (int i = 0; i < size; i++) { // loops through the ArrayList
		if (customerCards.get(key).get(i).toStringCredit().contains(creditCard)) {
		    return true; // card is found in arrayList
		}
	    }
	}
	return false;
    }

    /**
     * 
     * This method creates a string version out of the customerList and the
     * customerCards
     * 
     */
    public String toString() {

	int size = customerCards.get(id).size();
	String StrOutput = "";
	StrOutput = "\n\nName: " + name + "\nAddress: " + address + "\nPhone Number: " + phone + "\nID:" + id;

	// gathers the credit card information
	for (int i = 0; i < size; i++) {
	    StrOutput += customerCards.get(id).get(i).toStringCredit();
	}

	return StrOutput;
    }

    /**
     * This method generates a random 6 digits number to assign a new customer
     * 
     * @return random ID number
     */
    public String generateID() {
	Random rnd = new Random();
	char[] digits = new char[6];
	digits[0] = (char) (rnd.nextInt(9) + '1');
	for (int i = 1; i < digits.length; i++) {
	    digits[i] = (char) (rnd.nextInt(10) + '0');
	}
	return new String(digits);
    }

}
