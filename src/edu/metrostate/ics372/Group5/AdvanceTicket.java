package edu.metrostate.ics372.Group5;

/**
 * This is a subclass of the Ticket class, this class implements method such as get price and get ticket ID and a toString method
 * 
 * @author Anthony Nguyen
 * 
 */
import java.io.Serializable;
import java.text.DecimalFormat;
import java.util.Date;

public class AdvanceTicket extends Ticket implements Serializable, Matchable<String> {

    private static final long serialVersionUID = 1L;
    private static final String ADVANCE_TICKET_STRING = "A";
    private static DecimalFormat df2 = new DecimalFormat("#.00"); // format price of ticket
    private String ticketID; // ID number of ticket
    private String ticketType; // what kind of ticket it is
    private double price; // price of ticket

    /**
     * Constructor of Ticket
     * 
     * @param date       show date
     * @param customerID customer ID
     * @param showName   name of show
     * @param price      show price
     */
    public AdvanceTicket(Date date, String customerID, String showName, double price) {
	super(date, customerID, showName);
	this.ticketType = "Advance";
	this.price = price * .7;
	this.ticketID = ADVANCE_TICKET_STRING + (TicketHelper.instance()).getRandomNumberString();
    }

    /**
     * getter for price
     * 
     * @return show price
     */
    public double getPrice() {
	return price;
    }

    /**
     * getter for ticket ID
     * 
     * @return ticket ID
     */
    public String getTicketID() {
	return ticketID;
    }

    /**
     * check if equals
     * 
     * @return true if equals
     */
    public boolean matches(String id) {
	return (this.ticketID.equals(id));
    }

    /**
     * String form of advance ticket object
     * 
     * @return string data
     */
    @Override
    public String toString() {
	return super.toString() + "\nprice:$" + df2.format(price) + " Ticket Number: " + ticketID + " Ticket Type: "
		+ ticketType;
    }

}
