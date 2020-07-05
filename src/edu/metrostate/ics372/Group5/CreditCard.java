package edu.metrostate.ics372.Group5;

import java.io.Serializable;

/**
 * This class creates the credit card object using the credit card number and
 * expiration date. This class also contains the toString and see if a a credit
 * card is a match
 * 
 * @author Anthony Nguyen
 * @since 2020.06.25
 * @version 1.00.00
 */

public class CreditCard implements Serializable {

    private static final long serialVersionUID = 1L;
    private String creditCardNumber; // credit card information
    private String expiry; // date when card expires

    /**
     * this constructor creates the credit Card object
     * 
     * @param creditCardNumber card information
     * @param expiry           card's expiration date
     */
    public CreditCard(String creditCardNumber, String expiry) {
	this.creditCardNumber = creditCardNumber;
	this.expiry = expiry;
    }

    /**
     * getter for Credit Card number
     * 
     * @return credit card number
     */
    public String getCreditCardNumber() {
	return creditCardNumber;
    }

    /**
     * Check if the credit card number matches with the number
     * 
     * @param Number to check if match with credit card number
     * @return true if it matches
     */
    public boolean match(String Number) {
	return this.creditCardNumber.equals(Number);
    }

    /**
     * convert the credit card object into a string
     * 
     * @return string form of credit card object
     */
    public String toString() {
	String output = "";
	output = "Credit Card Number:" + creditCardNumber + " Expiration Date: " + expiry + "\n";
	return output;
    }

}
