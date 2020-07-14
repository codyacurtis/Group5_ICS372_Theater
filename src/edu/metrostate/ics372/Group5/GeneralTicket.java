package edu.metrostate.ics372.Group5;

import java.io.Serializable;
import java.text.DecimalFormat;
import java.util.Date;

public class GeneralTicket extends Ticket implements Serializable, Matchable<String> {

    private static final long serialVersionUID = 1L;
    private static final String GENERAL_TICKET_STRING = "G";
    private static DecimalFormat df2 = new DecimalFormat("#.00");
    private String ticketID;
    private String ticketType;
    private double price;

    public GeneralTicket(Date date, String CustomerID, String showName, double price) {
	super(date, CustomerID, showName);
	this.ticketType = "General";
	this.price = price;
	this.ticketID = GENERAL_TICKET_STRING + (TicketHelper.instance()).getRandomNumberString();
    }

    public double getPrice() {
	return price;
    }

    public String getTicketID() {
	return ticketID;
    }

    public boolean matches(String id) {
	return (this.ticketID.equals(id));
    }

    /**
     * String form
     * 
     */
    @Override
    public String toString() {
	return super.toString() + "\nprice:$" + df2.format(price) + " Ticket Number: " + ticketID + " Ticket Type: "
		+ ticketType;
    }

}
