package edu.metrostate.ics372.Group5;
import java.util.Date;

public class TicketFactory {

    private static TicketFactory factory;

    /**
     * Private for singleton
     */
    private TicketFactory() {
    }

    /**
     * Returns the only instance of the class.
     * 
     * @return the instance
     */
    public static TicketFactory instance() {
	if (factory == null) {
	    factory = new TicketFactory();
	}
	return factory;
    }

    public Ticket createTicket(int type, String show, Date date, String customerID, String showID, String showName,
	    double price) {
	switch (type) {
	case Theater.GENERALTICKET:
	    return new GeneralTicket(date, customerID, showID, showName, price);
	case Theater.ADVANCETICKET:
	    return new AdvanceTicket(date, customerID, showID, showName, price);
	case Theater.STUDENTTICKET:
	    return new AdvanceTicket(date, customerID, showID, showName, price);
	default:
	    return null;
	}
    }

}
