package edu.metrostate.ics372.Group5;

import java.io.Serializable;
import java.text.DecimalFormat;
import java.util.Date;

/**
 * This is a subclass of Ticket and have methods for the price, ticket id, if
 * match and toString. This creates student ticket for 50% discount.
 * 
 * @author Anthony Nguyen
 *
 */
public class StudentTicket extends Ticket implements Serializable, Matchable<String> {

    private static final long serialVersionUID = 1L;
    private static final String GENERAL_TICKET_STRING = "G";
    private static DecimalFormat df2 = new DecimalFormat("#.00"); // price format
    private String ticketID; // ticket ID
    private String ticketType; // type of ticket
    private double price; // price of ticket

    /**
     * constructor
     * @param date show date
     * @param CustomerID customer id
     * @param showName name of show
     * @param price ticket price
     */
    public GeneralTicket(Date date, String CustomerID, String showName, double price) {
	super(date, CustomerID, showName);
	this.ticketType = "General";
	this.price = price;
	this.ticketID = GENERAL_TICKET_STRING + (TicketHelper.instance()).getRandomNumberString();
    }

    /**
     * getter for price
     * 
     * @return ticket price
     */
    public double getPrice() {
	return price;
    }

    /**
     * getter for ticket id
     * 
     * @return ticket id
     */
    public String getTicketID() {
	return ticketID;
    }

    /**
     * checks if equals
     * 
     * @return true if equals
     */
    public boolean matches(String id) {
	return (this.ticketID.equals(id));
    }

    /**
     * String form of student ticket
     * 
     * @return string data
     */
    @Override
    public String toString() {
	return super.toString() + "\nprice:$" + df2.format(price) + " Ticket Number: " + ticketID + " Ticket Type: "
		+ ticketType;
    }
}
