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
    private String Id;

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

    public String getId() {
	return Id;
    }

    public String getCreditCardNumber() {
	return creditCardNumber;
    }

    public String toString() {
	String output = "";
	output = "Credit Card Number:" + creditCardNumber + " Expiration Date: " + expiry + "\n";
	return output;
    }

}
