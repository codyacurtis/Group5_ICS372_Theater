package edu.metrostate.ics372.Group5;

import java.io.IOException;
import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;

public class TicketList implements Serializable {

    private static final long serialVersionUID = 1L;
    private static TicketList ticketList;
    protected static List<Ticket> ticketLinkedList = new LinkedList<Ticket>();

    public static TicketList instance() {
	if (ticketList != null) {
	    ticketList = new TicketList();
	}
	return ticketList;
    }

    public static boolean insert(Ticket ticket) {
	ticketLinkedList.add(ticket);
	return true;
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

    public static void writeObject(java.io.ObjectOutputStream output) {
	try {
	    output.writeObject(ticketLinkedList);
	} catch (IOException e) {
	    // TODO Auto-generated catch block
	    e.printStackTrace();
	}
    }

    @SuppressWarnings("unchecked")
    public static void readObject(java.io.ObjectInputStream input) {
	try {
	    ticketLinkedList = (List<Ticket>) input.readObject();
	} catch (ClassNotFoundException e) {
	    // TODO Auto-generated catch block
	    e.printStackTrace();
	} catch (IOException e) {
	    // TODO Auto-generated catch block
	    e.printStackTrace();
	}

    }

}
