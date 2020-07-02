package edu.metrostate.ics372.Group5;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.StringTokenizer;

public class UserInterface {

    // TODO Auto-generated method stub

    private static UserInterface userInterface;
    private BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    private static Theater theater;
    private static final int EXIT = 0;
    private static final int ADD_CLIENT = 1;
    private static final int REMOVE_CLIENT = 2;
    private static final int LIST_ALL_CLIENTS = 3;
    private static final int ADD_CUSTOMER = 4;
    private static final int REMOVE_CUSTOMER = 5;
    private static final int LIST_ALL_CUSTOMERS = 6;
    private static final int ADD_CREDIT_CARD = 7;
    private static final int REMOVE_CREDIT_CARD = 8;
    private static final int ADD_SHOW = 9;
    private static final int LIST_ALL_SHOWS = 10;
    private static final int SAVE = 11;
    private static final int RETRIEVE = 12;
    private static final int HELP = 13;

    /**
     * Made private for singleton pattern. Conditionally looks for any saved data.
     * Otherwise, it gets a singleton Library object.
     */
    private UserInterface() {
	if (yesOrNo("Look for saved data and  use it?")) {
	    retrieve();
	} else {
	    theater = Theater.instance();
	}
    }

    /**
     * Supports the singleton pattern
     * 
     * @return the singleton object
     */
    public static UserInterface instance() {
	if (userInterface == null) {
	    return userInterface = new UserInterface();
	} else {
	    return userInterface;
	}
    }

    /**
     * Gets a token after prompting
     * 
     * @param prompt - whatever the user wants as prompt
     * @return - the token from the keyboard
     * 
     */
    public String getToken(String prompt) {
	do {
	    try {
		System.out.println(prompt);
		String line = reader.readLine();
		StringTokenizer tokenizer = new StringTokenizer(line, "\n\r\f");
		if (tokenizer.hasMoreTokens()) {
		    return tokenizer.nextToken();
		}
	    } catch (IOException ioe) {
		System.exit(0);
	    }
	} while (true);
    }

    /**
     * Queries for a yes or no and returns true for yes and false for no
     * 
     * @param prompt The string to be prepended to the yes/no prompt
     * @return true for yes and false for no
     * 
     */
    private boolean yesOrNo(String prompt) {
	String more = getToken(prompt + " (Y|y)[es] or anything else for no");
	if (more.charAt(0) != 'y' && more.charAt(0) != 'Y') {
	    return false;
	}
	return true;
    }

    /**
     * Converts the string to a number
     * 
     * @param prompt the string for prompting
     * @return the integer corresponding to the string
     * 
     */
    public int getNumber(String prompt) {
	do {
	    try {
		String item = getToken(prompt);
		Integer number = Integer.valueOf(item);
		return number.intValue();
	    } catch (NumberFormatException nfe) {
		System.out.println("Please input a number ");
	    }
	} while (true);
    }

    /**
     * Prompts for a date and gets a date object
     * 
     * @param prompt the prompt
     * @return the data as a Calendar object
     */
    public Calendar getDate(String prompt) {
	do {
	    try {
		Calendar date = new GregorianCalendar();
		String item = getToken(prompt);
		DateFormat dateFormat = SimpleDateFormat.getDateInstance(DateFormat.SHORT);
		date.setTime(dateFormat.parse(item));
		return date;
	    } catch (Exception fe) {
		System.out.println("Please input a date as mm/dd/yy");
	    }
	} while (true);
    }

    /**
     * Prompts for a command from the keyboard
     * 
     * @return a valid command
     * 
     */
    public int getCommand() {
	do {
	    try {
		int value = Integer.parseInt(getToken("Enter command:" + HELP + " for help"));
		if (value >= EXIT && value <= HELP) {
		    return value;
		}
	    } catch (NumberFormatException nfe) {
		System.out.println("Enter a number");
	    }
	} while (true);
    }

    /**
     * Displays the help screen
     * 
     */
    public void help() {
	System.out.println("Enter a number between 0 and 12 as explained below:");
	System.out.println(EXIT + " to Exit\n");
	System.out.println(ADD_CLIENT + " to add a client");
	System.out.println(REMOVE_CLIENT + " to  remove a client");
	System.out.println(LIST_ALL_CLIENTS + " to  list all client");
	System.out.println(ADD_CUSTOMER + " to  add a customer");
	System.out.println(REMOVE_CUSTOMER + " to remove a customer");
	System.out.println(LIST_ALL_CUSTOMERS + " to list all customers");
	System.out.println(ADD_CREDIT_CARD + " to add a credit card");
	System.out.println(REMOVE_CREDIT_CARD + " to remove a credit card");
	System.out.println(ADD_SHOW + " to add a show");
	System.out.println(LIST_ALL_SHOWS + " to list all shows");
	System.out.println(SAVE + " to  save data");
	System.out.println(RETRIEVE + " to  retrieve");
	System.out.println(HELP + " for help");
    }

    // checking if the name, address, phone number is valid and if customer exists
    // will be done in the theater class
    public void addClient() {
	String name, address, phone;

	System.out.println("Adding Client");

	do {
	    name = getToken("Enter client name");
	    address = getToken("Enter client address");
	    phone = getToken("Enter client phone");

	    if (yesOrNo("Is this correct: \nName: " + name + "\nAddress: " + address + "\nPhone Number: " + phone)) {
		break;
	    }
	} while (true);
	Client result;
	result = theater.addClient(name, address, phone);

	if (result == null) {
	    System.out.println("Could not add client");
	}
	System.out.println("Added client");
    }

    public void removeClient() {
	String clientID = getToken("Enter id");
	// searches for client and returns the object
	boolean result = false ;
	if (yesOrNo("is data correct?")) {
	    // remove client
		result = ClientList.removeClient(Integer.parseInt(clientID));
	    // result == null if couldnt be removed
	}

	if (!result) {
	    System.out.println("Could not remove client");
	} else {
	    System.out.println("Client removed");
	}

    }

    public void listAllClients() {
    	ClientList.listClients();
    }


    public void addCustomer() {
	Customer result;
	CreditCard creditCard;
	String name, address, phone, creditCardNumber, expiray;

	System.out.println("Adding Customer");

	do {
	    name = getToken("Enter customer name");
	    address = getToken("Enter customer address");
	    phone = getToken("Enter customer phone");
	    creditCardNumber = getToken("Enter credit card number");
	    expiray = getToken("Enter credit card expiratoin date");

	    if (!yesOrNo("Is this correct: \nName: " + name + "\nAddress: " + address + "\nPhone Number: " + phone
		    + "\nCredit Card: " + creditCardNumber + "\nExpiration date: " + expiray)) {
		System.out.println("Try again");

	    }

	    else
		break;

	} while (true);

	result = Theater.addCustomer(name, address, phone, creditCardNumber, expiray);

	if (result == null) {
	    System.out.println("Customer already in system");
	} else
	    System.out.println("Added Customer");

    }

    public void removeCustomer() {
	System.out.println("Remove Customer");
	String customerID = getToken("Enter id");
	Customer customer;

	do {
	    customer = Theater.searchCustomer(customerID);
	    System.out.println(customer.toString());
	    if (yesOrNo("Is this correct?")) {
		break;
	    } else
		customerID = getToken("Enter id again");

	} while (true);

	if (yesOrNo("Are you sure?")) {
	    Theater.removeCustomer(customerID);
	    System.out.println("Customer deleted");
	} else
	    System.out.println("Customer NOT deleted");

    }

    public void addCreditCard() {
	Customer customer;
	String customerID;
	// returns customer data and display

	do {
	    customerID = getToken("Enter id");
	    customer = Theater.searchCustomer(customerID);
	    System.out.println(customer.toString());

	    // returns customer data and display
	    if (yesOrNo("Is this correct")) {
		break;
	    } else
		System.out.println("Try again");

	} while (true);

	Boolean cardDuplicate;
	String creditCardNumber = getToken("Enter credit card number");
	String expiray = getToken("Enter credit card expiration");
	CreditCard creditCard = new CreditCard(creditCardNumber, expiray);
	cardDuplicate = Theater.isCardDuplicate(creditCardNumber);

	if (!cardDuplicate) {
	    System.out.println("adding card failed");
	    System.out.println("already in system");
	}

	else {
	    customer.addCreditCard(creditCard);
	    System.out.println("added card");
	}

    }

    public void removeCreditCard() {
	String creditCardNumber = getToken("Enter credit card number");

	// checks if card is in system
	// shows customer information
	// checks if only one card

	Customer customer = Theater.customerCreditCard(creditCardNumber);
	if (customer == null) {
	    System.out.println("card doesn't exist in system");

	}

	else if (yesOrNo("Is this correct?")) {
	    if (Theater.removeCreditCard(creditCardNumber, customer)) {
		System.out.println("card removed");
	    }
	}

    }

    public void listCustomers() {
	System.out.println(Theater.listCustomers());
    }

    public void addShow() {

	
	String name = getToken("Enter client name");
	String show = getToken("Enter show");
	String clientID = getToken("Enter client ID");
	Calendar startDate = getDate("Enter Start date");
	Calendar endDate = getDate("Enter End date");
	
	//Calendar != Date
	boolean results = TheaterShowList.addShow(clientID, name, startDate, endDate);
	if (results != results) {
	    System.out.println("Client exists");
	} else
	    System.out.println("Show added");
	// add show

    }

    public void listShows() {

    }

    /**
     * Method to be called for saving the Library object. Uses the appropriate
     * Library method for saving.
     * 
     */
    private void save() {
	if (theater.save()) {
	    System.out.println(" The Theater has been successfully saved in the file TheaterData \n");
	} else {
	    System.out.println(" There has been an error in saving \n");
	}
    }

    /**
     * Method to be called for retrieving saved data. Uses the appropriate Library
     * method for retrieval.
     * 
     */
    private void retrieve() {
	try {
	    Theater tempTheater = Theater.retrieve();
	    if (tempTheater != null) {
		System.out.println(" The library has been successfully retrieved from the file LibraryData \n");
		theater = tempTheater;
	    } else {
		System.out.println("File doesnt exist; creating new library");
		theater = Theater.instance();
	    }
	} catch (Exception cnfe) {
	    cnfe.printStackTrace();
	}
    }

    /**
     * Orchestrates the whole process. Calls the appropriate method for the
     * different functionalties.
     * 
     */
    public void process() {
	int command;
	help();
	while ((command = getCommand()) != EXIT) {
	    switch (command) {
	    case ADD_CLIENT:
		addClient();
		break;
	    case REMOVE_CLIENT:
		removeClient();
		break;
	    case LIST_ALL_CLIENTS:
		listAllClients();
		break;
	    case ADD_CUSTOMER:
		addCustomer();
		break;
	    case REMOVE_CUSTOMER:
		removeCustomer();
		break;
	    case ADD_CREDIT_CARD:
		addCreditCard();
		break;
	    case REMOVE_CREDIT_CARD:
		removeCreditCard();
		break;
	    case LIST_ALL_CUSTOMERS:
		listCustomers();
		break;
	    case ADD_SHOW:
		addShow();
		break;
	    case LIST_ALL_SHOWS:
		listShows();
		break;
	    case SAVE:
		save();
		break;
	    case RETRIEVE:
		retrieve();
		break;
	    case HELP:
		help();
		break;
	    }
	}
    }

    /**
     * The method to start the application. Simply calls process().
     * 
     * @param args not used
     */
    public static void main(String[] args) {
	UserInterface.instance().process();
    }
}
