package edu.metrostate.ics372.Group5;

/**
 * This class is a sub class to Ticket and this generalTicket object represents ticket purchase of the day of show. This class have methods for the price, ticket id and if equals and a toString
 * @author Anthony Nguyen
 * @since 
 * @version
 */
import java.io.Serializable;
import java.text.DecimalFormat;
import java.util.Date;

public class GeneralTicket extends Ticket implements Serializable, Matchable<String> {

    private static final long serialVersionUID = 1L;
    private static final String GENERAL_TICKET_STRING = "G";
    private static DecimalFormat df2 = new DecimalFormat("#.00"); // price format
    private String ticketID; // ID of ticket
    private String ticketType; // type of ticket
    private double price; // price of ticket

    /**
     * Constructor
     * 
     * @param date       show date
     * @param CustomerID customer ID
     * @param showName   name of show
     * @param price      ticket price
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
     * String form of general ticket
     * 
     * @return string data
     */
    @Override
    public String toString() {
	return super.toString() + "\nprice:$" + df2.format(price) + " Ticket Number: " + ticketID + " Ticket Type: "
		+ ticketType;
    }

}
