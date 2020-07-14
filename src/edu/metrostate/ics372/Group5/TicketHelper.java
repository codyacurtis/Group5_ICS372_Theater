package edu.metrostate.ics372.Group5;

import org.junit.jupiter.api.MethodOrderer.Random;

public class TicketHelper extends TicketList {

    private static final long serialVersionUID = 1L;
    private static TicketHelper ticketHelper;

    private TicketHelper() {
	super();
    }

    public static TicketHelper instance() {
	if (ticketHelper != null) {
	    ticketHelper = new TicketHelper();
	}
	return ticketHelper;
    }

    public boolean insertTicket(Ticket ticket) {
	return super.insert(ticket);
    }

    public static void listTicket() {
	if (ticketLinkedList.isEmpty()) {
	    System.out.println("Empty List");
	} else {
	    for (Ticket i : ticketLinkedList) {
		System.out.println(i.toString());
	    }
	}

    }

    public static String getRandomNumberString() {
	// It will generate 6 digit random Number.
	// from 0 to 999999
	Random rnd = new Random();
	int number = rnd.nextInt(999999);

	// this will convert any number sequence into 6 character.
	return String.format("%06d", number);
    }

}
