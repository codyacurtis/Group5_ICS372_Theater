/**
 * This class creates the credit card object using the credit card number and
 * expiration date. This class also contains the toString method.
 * 
 * @author Anthony Nguyen
 * @since 2020.06.25
 * @version 1.00.00
 */

public class creditCard {
    private String creditCardNumber; // credit card information
    private String expiry; // date when card expires

    /**
     * this constructor creates the credit Card object
     * 
     * @param creditCardNumber card information
     * @param expiry           card's expiration date
     */
    public creditCard(String creditCardNumber, String expiry) {
	this.creditCardNumber = creditCardNumber;
	this.expiry = expiry;
    }

    /**
     * 
     * This method converts the creditCard object into a string data
     * 
     * @return string output
     */
    public String toStringCredit() {
	String StrOutput = "";
	StrOutput = "\nCredit Card Number: " + creditCardNumber + " Expirition Date: " + expiry + "";
	return StrOutput;
    }

}