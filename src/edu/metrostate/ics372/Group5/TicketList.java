package edu.metrostate.ics372.Group5;

import java.io.IOException;
import java.io.Serializable;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

/**
 * maintains a list of tickets subclasses by general, advance and student
 * tickets
 * 
 * @author Anthony Nguyen
 *
 */

public class TicketList implements Serializable {

	private static final long serialVersionUID = 1L;
	private static TicketList ticketList;
	protected static List<Ticket> ticketLinkedList = new LinkedList<Ticket>();

	/**
	 * Supports the singleton pattern
	 * 
	 * @return the singleton object
	 */
	public static TicketList instance() {
		if (ticketList != null) {
			ticketList = new TicketList();
		}
		return ticketList;
	}

	/**
	 * insert ticket to collections
	 * 
	 * @param ticket object to insert
	 * @return true if inserted
	 */
	public static boolean insert(Ticket ticket) {
		ticketLinkedList.add(ticket);
		return true;
	}

	/**
	 * list all tickets
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
	 * print tickets of a certain date
	 * 
	 * @param date the date
	 */
	public static void dateTicket(Date date) {
		if (ticketLinkedList.isEmpty()) {
			System.out.println("Empty List");
		} else {
			for (Ticket i : ticketLinkedList) {
				// System.out.println(i.toString());
				if (i.getShowDate().compareTo(date) == 0) {
					System.out.println(i.toString());
				}
			}
		}

	}

	/**
	 * Save objects to file
	 * 
	 * @param output file to save in
	 */
	public static void writeObject(java.io.ObjectOutputStream output) {
		try {
			output.writeObject(ticketLinkedList);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * read object from file
	 * 
	 * @param input file to be read from
	 */
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
