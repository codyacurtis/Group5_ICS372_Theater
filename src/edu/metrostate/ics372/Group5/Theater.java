package edu.metrostate.ics372.Group5;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.Date;

/**
 * This class is the helper for the CustomerList, Customer, and ClienteList This
 * class also saves and writes the theater Object
 * 
 * @author Anthony Nguyen
 * @author Cody Curtis
 * @author Prophet Isiah Taylor
 * @since 2020.04.07
 * @version 1.00.00
 *
 */
public class Theater implements Serializable {

    private static final long serialVersionUID = 1L;
    public static final int GENERALTICKET = 1;
    public static final int ADVANCETICKET = 2;
    public static final int STUDENTTICKET = 3;
    private static Theater theater;
    private CustomerList customerList;
    private ClientList clientList;
    private TheaterShowList theaterShowList;
    private TicketHelper ticketHelper;

    /**
     * Private for the singleton pattern Creates the catalog and member collection
     * objects
     */
    private Theater() {
	customerList = CustomerList.instance();
	clientList = ClientList.instance();
	theaterShowList = TheaterShowList.instance();
    }

    /**
     * instantiate all singletons-
     * 
     * @return Theater object
     */
    public static Theater instance() {
	if (theater == null) {
	    CustomerIdServer.instance();
	    return (theater = new Theater());
	} else {
	    return theater;
	}
    }

    /**
     * Check if string is all numbers
     * 
     * @param string to check if only have numbers
     * @return true if have only numbers
     */
    public static boolean isNumeric(String string) {
	if (string == null) {
	    return false;
	}
	try {
	    long number = Long.parseLong(string);
	} catch (NumberFormatException nfe) {
	    return false;
	}
	return true;
    }

    /**
     * This method will check if credit card creditCardNumber is valid, this only
     * accept American Express, MasterCard, Visa, Discover card and JCB this method
     * uses the Luhn algorithim to verify the card
     * 
     * @param creditCardNumber credit card to check
     * @return true if card is valid
     */
    public static boolean isValidCard(long creditCardNumber) {
	return (getSize(creditCardNumber) >= 13 && getSize(creditCardNumber) <= 16)
		&& (prefixMatched(creditCardNumber, 4) || prefixMatched(creditCardNumber, 5)
			|| prefixMatched(creditCardNumber, 37) || prefixMatched(creditCardNumber, 6))
		&& ((sumOfDoubleEvenPlace(creditCardNumber) + sumOfOddPlace(creditCardNumber)) % 10 == 0);
    }

    // return sum of credit card numbers
    public static int sumOfDoubleEvenPlace(long creditCardNumber) {
	int sum = 0;
	String num = creditCardNumber + "";
	for (int i = getSize(creditCardNumber) - 2; i >= 0; i -= 2)
	    sum += getDigit(Integer.parseInt(num.charAt(i) + "") * 2);

	return sum;
    }

    // Return this creditCardNumber if it is a single digit, otherwise,
    // return the sum of the two digits
    public static int getDigit(int creditCardNumber) {
	if (creditCardNumber < 9)
	    return creditCardNumber;
	return creditCardNumber / 10 + creditCardNumber % 10;
    }

    // Return sum of odd-place digits in creditCardNumber
    public static int sumOfOddPlace(long creditCardNumber) {
	int sum = 0;
	String num = creditCardNumber + "";
	for (int i = getSize(creditCardNumber) - 1; i >= 0; i -= 2)
	    sum += Integer.parseInt(num.charAt(i) + "");
	return sum;
    }

    // Return true if the digit d is a prefix for creditCardNumber
    public static boolean prefixMatched(long creditCardNumber, int d) {
	return getPrefix(creditCardNumber, getSize(d)) == d;
    }

    // Return the creditCardNumber of digits in d
    public static int getSize(long d) {
	String num = d + "";
	return num.length();
    }

    // Return the first k creditCardNumber of digits from
    // creditCardNumber. If the creditCardNumber of digits in creditCardNumber
    // is less than k, return creditCardNumber.
    public static long getPrefix(long creditCardNumber, int k) {
	if (getSize(creditCardNumber) > k) {
	    String num = creditCardNumber + "";
	    return Long.parseLong(num.substring(0, k));
	}
	return creditCardNumber;
    }

    /**
     * Check if credit card expiration date is valid
     * 
     * @param date
     * @return
     */
    public static boolean isDate(String date) {
	if ((date.length() == 5) && Character.isDigit(date.charAt(0)) && Character.isDigit(date.charAt(1))
		&& Character.isDigit(date.charAt(3)) && Character.isDigit(date.charAt(4))
		&& containsSpecialCharacter(date) && isMonthYearValid(date)) {
	    return true;
	}
	return false;
    }

    /**
     * Checks if month and year is a valid
     * 
     * @param date the month and year
     * @return true if valid
     */
    public static boolean isMonthYearValid(String date) {
	String[] parts = date.split("/");
	int month = Integer.parseInt(parts[0]);
	int year = Integer.parseInt(parts[1]);
	if (month <= 12 && month >= 1 && year <= 35 && year >= 20) {
	    return true;
	}
	return false;
    }

    /**
     * Checks if string contains /
     * 
     * @param date checks if have /
     * @return true if have /
     */
    public static boolean containsSpecialCharacter(String date) {
	if (date.substring(2, 3).matches("/")) {
	    return true;
	}
	return false;
    }

    /**
     * Checks if phone is a valid number
     * 
     * @param phone check phone if valid
     * @return true if valid
     */
    public static boolean validPhone(String phone) {
	String regex = "\\d{3}-\\d{3}-\\d{4}"; // XXX-XXX-XXXX
	return phone.matches(regex);
    }

    /**
     * This method will add a customer to the CustomerList collection and checks if
     * the customer exists in the system
     * 
     * @param name             customer's name
     * @param address          customers's address
     * @param phone            customer's phone
     * @param creditCardNumber customers' credit card number
     * @param expiray          credit card number expiration date
     * @return customer if the customer was added successfully
     */
    public static Customer addCustomer(String name, String address, String phone, String creditCardNumber,
	    String expiray) {
	Customer customer = new Customer(name, address, phone);
	CreditCard creditCard = new CreditCard(creditCardNumber, expiray);

	if (!CustomerList.checkNewCustomer(name, address, phone)) {
	    CustomerList.insert(customer);
	    customer.addCreditCard(creditCard);
	    return customer;
	}
	return null;
    }

    /**
     * This method removes the customer from the customer list and also their credit
     * cards
     * 
     * @param ID Customer's ID number
     */
    @SuppressWarnings("static-access")
    public static void removeCustomer(String ID) {
	CustomerList.removeFromList(ID);
    }

    /**
     * This finds customer with an ID
     * 
     * @return customer object
     */
    public static Customer searchCustomer(String ID) {
	return (Customer) CustomerList.search(ID);
    }

    /**
     * Finds a customer with only using a credit card number
     * 
     * @param creditCard the card to find in the system
     * @return customer object
     */
    public static Customer customerCreditCard(String creditCard) {
	Customer customer = CustomerList.customerCreditCard(creditCard);
	return customer;
    }

    /**
     * This will create a new credit card
     * 
     * @param creditCardNumber card number
     * @param expiray          card expiration date
     * @return creditCard object
     */
    public static CreditCard newCreditCard(String creditCardNumber, String expiray) {
	CreditCard creditCard = new CreditCard(creditCardNumber, expiray);
	return creditCard;
    }

    /**
     * This removes a credit card from a user, but it will check if customer have
     * more than 1 card
     * 
     * @param creditCard the card to be removed
     * @param customer   where the card will be removed from
     * @return true if card was removed
     */
    public static boolean removeCreditCard(String creditCard, Customer customer) { // used
	int cards = customer.howManyCards();

	if (cards > 1) {
	    customer.removeCreditCardNumber(creditCard);
	    return true;
	}
	System.out.println("Customer only has one card");
	return false;
    }

    /**
     * Prints all customers and credit cards
     * 
     * @return string data of customers and cards
     */
    public static void listCustomers() {
	CustomerList.listPeople();
    }

    /**
     * Retrieves a deserialized version of the Theater from disk
     * 
     * @return a Library object
     */
    public static Theater retrieve() {
	try {
	    FileInputStream file = new FileInputStream("TheaterData");
	    ObjectInputStream input = new ObjectInputStream(file);
	    input.readObject();
	    CustomerIdServer.readObject(input);
	    CustomerList.readObject(input);

	    // Shows and clients
	    TheaterShowList.readObject(input);
	    ClientIdServer.readObject(input);
	    ClientList.readObject(input);
	    TicketList.readObject(input);

	    return theater;
	} catch (IOException ioe) {
	    ioe.printStackTrace();
	    return null;
	} catch (ClassNotFoundException cnfe) {
	    cnfe.printStackTrace();
	    return null;
	}
    }

    /**
     * Serializes the Theater object
     * 
     * @return true iff the data could be saved
     */
    public static boolean save() {
	try {
	    FileOutputStream file = new FileOutputStream("TheaterData");
	    ObjectOutputStream output = new ObjectOutputStream(file);
	    output.writeObject(theater);
	    CustomerIdServer.writeObject(output);
	    CustomerList.writeObject(output);
	    TheaterShowList.writeObject(output);
	    ClientIdServer.writeObject(output);
	    ClientList.writeObject(output);
	    TicketList.writeObject(output);

	    return true;
	} catch (IOException ioe) {
	    ioe.printStackTrace();
	    return false;
	}
    }

    /**
     * Writes the object to the output stream
     * 
     * @param output the stream to be written to
     */
    private void writeObject(java.io.ObjectOutputStream output) {
	try {
	    output.defaultWriteObject();
	    output.writeObject(theater);
	} catch (IOException ioe) {
	    System.out.println(ioe);
	}
    }

    /**
     * Reads the object from a given stream
     * 
     * @param input the stream to be read
     */
    private void readObject(java.io.ObjectInputStream input) {
	try {
	    input.defaultReadObject();
	    if (theater == null) {
		theater = (Theater) input.readObject();
	    } else {
		input.readObject();
	    }
	} catch (IOException ioe) {
	    ioe.printStackTrace();
	} catch (Exception e) {
	    e.printStackTrace();
	}
    }

    /**
     * creates a client and add it the client collection
     * 
     * @param name    client name
     * @param address client address
     * @param phone   client phone number
     * @return client if created
     */
    public Client addClient(String name, String address, String phone) {
	Client insert = new Client(name, address, phone);
	ClientList.insert(insert);
	return insert;
    }

    /**
     * Search for client in the client list
     * 
     * @param clientID ID of client to search for
     * @return client object if found
     */
    public static Client searchClient(String clientID) {
	Client client;
	client = (Client) ClientList.search(clientID);
	return client;
    }

    public static void listClient() {
	ClientList.listPeople();

    }

    public Ticket buyTicket(int type, Date date, String customerID, String showName, double price, String clientID) {

	Ticket ticket = TicketFactory.instance().createTicket(type, showName, date, customerID, showName, price);

	Client client = (Client) PeopleList.search(clientID);
	client.addBalance(ticket.getPrice());

	if (TicketHelper.insert(ticket)) {
	    System.out.println("Ticket Added");
	    return (ticket);
	}
	return null;
    }

    public static void listTickets() {
	TicketHelper.listTicket();
    }

    public static TheaterShow searchShow(String ID) {
	TheaterShow show;
	show = TheaterShowList.searchShow(ID);
	return show;
    }

    public static void dateTickets(Date date) {
	TicketHelper.dateTicket(date);
    }

}
