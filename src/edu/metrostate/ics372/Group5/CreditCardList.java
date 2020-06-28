package edu.metrostate.ics372.Group5;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class CreditCardList {

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

    public boolean insertNewCustomerCard(CreditCard creditCard, String Id) {
	creditList.add(creditCard);
	customerCards.put(Id, creditList);
	return true;
    }

    public boolean insertExistingCustomerCard(CreditCard creditCard, String Id) {

	if (!checkDuplicate(creditCard.getCreditCardNumber())) { // checks if card is a duplicate
	    customerCards.get(Id).add(creditCard);
	    return true;
	}

	return false; // card wasn't added in to the system
    }

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

    public String getCustomerCreditCards(String Id) {
	String output = "";
	int size = customerCards.get(Id).size();
	for (int i = 0; i < size; i++) {
	    output += customerCards.get(Id).get(i).toString();
	}
	return output;

    }

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

}
