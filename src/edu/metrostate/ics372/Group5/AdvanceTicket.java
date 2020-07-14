package edu.metrostate.ics372.Group5;

import java.io.Serializable;
import java.text.DecimalFormat;
import java.util.Date;

public class AdvanceTicket extends Ticket implements Serializable, Matchable<String> {

    private static final long serialVersionUID = 1L;
    private static final String ADVANCE_TICKET_STRING = "A";
    private static DecimalFormat df2 = new DecimalFormat("#.00");
    private String ticketID;
    private String ticketType;
    private double price;

    public AdvanceTicket(Date date, String customerID, String showName, double price) {
	super(date, customerID, showName);
	this.ticketType = "Advance";
	this.price = price * .7;
	this.ticketID = ADVANCE_TICKET_STRING + (TicketHelper.instance()).getRandomNumberString();

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
