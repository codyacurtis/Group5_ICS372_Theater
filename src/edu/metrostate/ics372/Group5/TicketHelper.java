package edu.metrostate.ics372.Group5;

import java.util.Random;

/**
 * This the collection class for the tickets
 * 
 * @author Anthony Nguyen
 *
 */
public class TicketHelper extends TicketList {

	private static final long serialVersionUID = 1L;
	private static TicketHelper ticketHelper;

	/*
	 * Private constructor for singleton pattern
	 */
	private TicketHelper() {
		super();
	}

	/**
	 * Supports the singleton pattern
	 *
	 * @return the singleton object
	 */
	public static TicketHelper instance() {
		if (ticketHelper != null) {
			ticketHelper = new TicketHelper();
		}
		return ticketHelper;
	}

	/**
	 * insert ticket to the collection
	 * 
	 * @param ticket show ticket
	 * @return insert ticket to collection
	 */
	public boolean insertTicket(Ticket ticket) {
		return super.insert(ticket);
	}

	/**
	 * list all tickets in the collection
	 */
	public static void listTicket() {
		if (ticketLinkedList.isEmpty()) {
			System.out.println("Empty List");
		} else {
			for (Ticket i : ticketLinkedList) {
				System.out.println(i.toString());
			}
		}
	}

	/**
	 * generate numbers for ticket ID
	 * 
	 * @return random numbers
	 */
	public static String getRandomNumberString() {
		// It will generate 6 digit random Number.
		// from 0 to 999999
		Random rnd = new Random();
		int number = rnd.nextInt(999999);

		// this will convert any number sequence into 6 character.
		return String.format("%06d", number);
	}

}
