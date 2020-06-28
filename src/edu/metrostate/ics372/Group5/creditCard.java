/**
 * This class creates the credit card object using the credit card number and
 * expiration date. This class also contains the toString method.
 * 
 * @author Anthony Nguyen
 * @since 2020.06.25
 * @version 1.00.00
 */

public class CreditCard implements Serializable {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;
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

    /**
     * getter for Credit Card number
     * 
     * @return credit card number
     */
    public String getCreditCardNumber() {
	return creditCardNumber;
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
