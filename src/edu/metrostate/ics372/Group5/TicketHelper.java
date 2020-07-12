import java.io.IOException;
import java.util.Random;

public class TicketHelper extends TicketList<Ticket, String> {

    private static TicketHelper ticketHelper;

    /*
     * Private constructor for singleton pattern
     */
    private TicketHelper() {
    }

    /**
     * Supports the singleton pattern
     * 
     * @return the singleton object
     */
    public static TicketHelper instance() {
	if (ticketHelper == null) {
	    return (ticketHelper = new TicketHelper());
	} else {
	    return ticketHelper;
	}
    }

    public String getRandomNumberString() {
	// It will generate 6 digit random Number.
	// from 0 to 999999
	Random rnd = new Random();
	int number = rnd.nextInt(999999);

	// this will convert any number sequence into 6 character.
	return String.format("%06d", number);
    }

    /**
     * Removes a ticket from the catalog
     * 
     * @param loanableItemId book id
     * @return true iff book could be removed
     */
    public boolean removeTicket(String ticketID) {
	Ticket ticketItem = search(ticketID);
	if (ticketItem == null) {
	    return false;
	} else {
	    return super.remove(ticketItem);
	}
    }

    /**
     * Inserts a ticket into the collection
     * 
     * @param book the book to be inserted
     * @return true iff the book could be inserted. Currently always true
     */
    public boolean insertTicket(Ticket ticket) {
	return super.add(ticket);
    }

    /*
     * Supports serialization
     * 
     * @param output the stream to be written to
     */
    private void writeObject(java.io.ObjectOutputStream output) throws IOException {
	output.defaultWriteObject();
	output.writeObject(ticketHelper);
    }

    /*
     * Supports deserialization
     * 
     * @param input the stream to be read from
     */
    private void readObject(java.io.ObjectInputStream input) throws IOException, ClassNotFoundException {
	input.defaultReadObject();
	if (ticketHelper == null) {
	    ticketHelper = (TicketHelper) input.readObject();
	} else {
	    input.readObject();
	}
    }

}
