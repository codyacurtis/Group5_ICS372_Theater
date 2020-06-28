package edu.metrostate.ics372.Group5;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class CreditCardList implements Serializable {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;
    private static CreditCardList creditCardList;

    static Map<String, List<CreditCard>> customerCards = new HashMap<>();
    List<CreditCard> creditList = new ArrayList<>();

    /*
     * Private constructor for singleton pattern
     * 
     */
    private CreditCardList() {
    }

    /**
     * Supports the singleton pattern
     * 
     * @return the singleton object
     */
    public static CreditCardList instance() {
	if (creditCardList == null) {
	    return (creditCardList = new CreditCardList());
	} else {
	    return creditCardList;
	}
    }

    /**
     * inserts a new customer's credit card to the collection
     * 
     * @param creditCard the numbers to be added to the collection
     * @param Id         the key for the Hash Table
     * @return true if credit card was added to the collection
     */
    public boolean insertNewCustomerCard(CreditCard creditCard, String Id) {
	creditList.add(creditCard);
	customerCards.put(Id, creditList);
	return true;
    }

    /**
     * Insert a new card to an existing customer
     * 
     * @param creditCard the card to be added
     * @param Id         the key where the card will be added
     * @return true if added to collection
     */
    public boolean insertExistingCustomerCard(CreditCard creditCard, String Id) {

	if (!checkDuplicate(creditCard.getCreditCardNumber())) { // checks if card is a duplicate
	    customerCards.get(Id).add(creditCard);
	    return true;
	}
	return false; // card wasn't added in to the system
    }

    /**
     * 
     * removes a credit card in the collection
     * 
     * @param creditCardNumber the card to be removed
     * @return true if card was removed
     */
    public boolean removeCreditCard(String creditCardNumber) {

	int size = 0; // size of arrayList in a Hash table cell
	java.util.Set<String> keys = customerCards.keySet();

	for (String key : keys) { // loop through all the customers
	    size = customerCards.get(key).size();
	    for (int i = 0; i < size; i++) { // loop through the arrayList of cards
		if (customerCards.get(key).get(i).getCreditCardNumber().equals(creditCardNumber) && size > 1) {
		    customerCards.get(key).remove(i); // removes credit card
		    return true;
		} else if (customerCards.get(key).get(i).getCreditCardNumber().equals(creditCardNumber) && size == 1
			|| size == 0) {
		    System.out.println("This is the only card on file for customer");
		    return false;
		}
	    }
	}

	System.out.print("Card wasn't found"); // can't find the card
	return false;
    }

    /**
     * This method removes all credit cards from the Customer
     * 
     * @param Id the key for the collection
     * @return true if the cards are deleted
     */
    public boolean deleteCustomerCards(String Id) {
	if (customerCards.remove(Id) != null)
	    return true;
	return false;
    }

    /**
     * This methods get all credit cards connected to the customer
     * 
     * @param Id the key for the customer's cards
     * @return string form of customer's card
     */
    public String getCustomerCreditCards(String Id) {
	String output = "";
	int size = customerCards.get(Id).size();
	for (int i = 0; i < size; i++) {
	    output += customerCards.get(Id).get(i).toString();
	}
	return output;

    }

    /**
     * This searches for the same card in the collection
     * 
     * @param creditCard the target
     * @return true if card isn't found in the collection
     */
    public boolean checkDuplicate(String creditCard) {
	int size = 0;
	Set<String> keys = customerCards.keySet();
	for (String key : keys) { // loops through the hash table keys
	    size = customerCards.get(key).size();
	    for (int i = 0; i < size; i++) { // loops through the ArrayList
		if (customerCards.get(key).get(i).getCreditCardNumber().equals(creditCard)) {
		    return true; // card is found in arrayList
		}
	    }
	}
	return false;
    }

    /*
     * Supports serialization
     * 
     * @param output the stream to be written to
     */
    private void writeObject(java.io.ObjectOutputStream output) {
	try {
	    output.defaultWriteObject();
	    output.writeObject(customerCards);
	} catch (IOException ioe) {
	    ioe.printStackTrace();
	}
    }

    /*
     * Supports serialization
     * 
     * @param input the stream to be read from
     */
    @SuppressWarnings("unchecked")
    private void readObject(java.io.ObjectInputStream input) {
	try {
	    if (customerCards != null) {
		return;
	    } else {
		input.defaultReadObject();
		if (customerCards == null) {
		    customerCards = (Map<String, List<CreditCard>>) input.readObject();
		} else {
		    input.readObject();
		}
	    }
	} catch (IOException ioe) {
	    ioe.printStackTrace();
	} catch (ClassNotFoundException cnfe) {
	    cnfe.printStackTrace();
	}
    }

}
