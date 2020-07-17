package edu.metrostate.ics372.Group5;

import java.util.Date;

/**
 * This is a helper for creating a ticket as there are many different types
 * 
 * @author Anthony Nguyen
 *
 */
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

	/**
	 * Helper for creating a certain type of ticket
	 * 
	 * @param type       ticket type
	 * @param date       show date
	 * @param customerID customer ID
	 * @param showName   name of show
	 * @param price
	 * @return create new ticket
	 */
	public Ticket createTicket(int type, Date date, String customerID, String showName, double price) {
		switch (type) {
		case Theater.GENERALTICKET:
			return new GeneralTicket(date, customerID, showName, price);
		case Theater.ADVANCETICKET:
			return new AdvanceTicket(date, customerID, showName, price);
		case Theater.STUDENTTICKET:
			return new StudentTicket(date, customerID, showName, price);
		default:
			return null;
		}
	}

}
