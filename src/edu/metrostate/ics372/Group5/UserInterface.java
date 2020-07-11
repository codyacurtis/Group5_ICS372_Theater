package edu.metrostate.ics372.Group5;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.StringTokenizer;

/**
 * This Program creates a file to store clients, customers, show times, and
 * credit card information for a theater. The program can add and remove
 * clients, customers, shows, and credit card information, also it can display
 * all clients and customers in the file.
 * 
 * 
 * @author Anthony Nguyen
 * @author Cody Curtis
 * @author Prophet Isiah Taylor
 * @since 2020.07.04
 * @version 2.10.04
 */

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
     * Otherwise, it gets a singleton Theater object.
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
     * Checks input for 'C' or 'c' if the user wants to cancel the operation
     * 
     * @param token created during the method
     * @return true if cancel and false for anything else
     * 
     */
    private boolean cancel(String token) {
	if ((token.charAt(0) == 'C' || token.charAt(0) == 'c') && token.length() == 1)
	    return true;
	else
	    return false;
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
    public Date getDate(String prompt) {
	do {
	    try {
		String item = getToken(prompt);
		SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yy");
		Date date = sdf.parse(item);
		return date;
	    } catch (Exception fe) {
		System.out.println("Please input a date as MM/dd/yy");
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
	System.out.println(EXIT + "  to Exit\n");
	System.out.println(ADD_CLIENT + "  to add a client");
	System.out.println(REMOVE_CLIENT + "  to remove a client");
	System.out.println(LIST_ALL_CLIENTS + "  to list all client");
	System.out.println(ADD_CUSTOMER + "  to add a customer");
	System.out.println(REMOVE_CUSTOMER + "  to remove a customer");
	System.out.println(LIST_ALL_CUSTOMERS + "  to list all customers");
	System.out.println(ADD_CREDIT_CARD + "  to add a credit card");
	System.out.println(REMOVE_CREDIT_CARD + "  to remove a credit card");
	System.out.println(ADD_SHOW + "  to add a show");
	System.out.println(LIST_ALL_SHOWS + " to list all shows");
	System.out.println(SAVE + " to save data");
	System.out.println(RETRIEVE + " to retrieve");
	System.out.println(HELP + " for help");
    }

    // checking if the name, address, phone number is valid and if customer exists
    // will be done in the theater class
    public void addClient() {
	String name, address, phone;

	System.out.println("Adding Client");
	try {
	    do {
		name = getToken("Enter client name or (C/c) to cancel");
		if (cancel(name))
		    return;
		address = getToken("Enter client address");
		phone = getToken("Enter client phone XXX-XXX-XXXX");

		while (!Theater.validPhone(phone)) {
		    phone = getToken("Enter a valid phone number XXX-XXX-XXXX or Enter (C/c) to return to main menu");
		    if (cancel(phone)) // return to menu
			return;
		}

		if (yesOrNo(
			"Is this correct: \nName: " + name + "\nAddress: " + address + "\nPhone Number: " + phone)) {
		    break;
		}
	    } while (true);
	    Client result;
	    result = theater.addClient(name, address, phone);

	    if (result == null) {
		System.out.println("Could not add client");
		return;
	    }
	    System.out.println("Added client");
	    System.out.println("CLient Id: " + result.getId());
	} catch (NullPointerException e) {
	    System.out.println("Could not add client");
	}
    }

    public void removeClient() {
	Client client;
	String clientID = getToken("Enter id or (C/c) to return to main menu");
	// checks if user wants to return to main menu
	if (cancel(clientID))
	    return;
	// searches for client and returns the object
	boolean result = false;
	client = Theater.searchClient(clientID); // searches for customer
	System.out.println(client);
	if (yesOrNo("is data correct?")) {
	    // remove client
	    result = ClientList.removeClientFromList(clientID);
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

    /**
     * This method takes a name,address,phone number and credit card information and
     * check is a credit card and phone number is valid and checks if the user and
     * credit card already exists in the system
     */
    public void addCustomer() {
	Customer result = null;
	Customer resultCreditCard;
	String name, address, phone, expiray, creditCard;
	long creditCardNumber;
	System.out.println("Adding Customer");

	do { // loops until the user is happy with the information
	    name = getToken("Enter customer name or Enter (C/c) to return to main menu");
	    if (cancel(name)) // return to menu
		return;

	    address = getToken("Enter customer address");
	    phone = getToken("Enter customer phone XXX-XXX-XXXX");

	    while (!Theater.validPhone(phone)) {
		phone = getToken("Enter a valid phone number XXX-XXX-XXXX or Enter (C/c) to return to main menu");
		if (cancel(phone)) // return to menu
		    return;
	    }

	    if (!yesOrNo("Is this correct: \nName: " + name + "\nAddress: " + address + "\nPhone Number: " + phone)) {
		System.out.println("Try again");
	    } else
		break;

	} while (true);

	do {
	    // loops until the user is happy with the information
	    System.out.println("Enter (C/c) to return to main menu");
	    creditCard = getToken(
		    "Enter a credit card number (Mastercard, Visa, American Express, Discover Card, JCB)");
	    if (cancel(creditCard)) // return to menu
		return;

	    if (!Theater.isNumeric(creditCard)) {
		continue;
	    }

	    creditCardNumber = Long.parseLong(creditCard);
	    expiray = getToken("Enter credit card expiration date MM/YY");
	    if (!Theater.isValidCard(creditCardNumber) || !Theater.isDate(expiray)) {
		System.out.println("Enter a VALID credit card");
		System.out.println("We DO NOT accept RuPay, Union Pay");

	    } else {
		break;
	    }
	} while (true);

	resultCreditCard = Theater.customerCreditCard(creditCard); // checks if a customer already have the same
	// card

	if (resultCreditCard != null) {
	    System.out.println("Credit card already in system");
	    System.out.println("Customer not added");
	    return;
	}

	else // card isn't found in the system
	    result = Theater.addCustomer(name, address, phone, creditCard, expiray);

	if (result == null) {
	    System.out.println("Customer already in system");
	    System.out.println("Customer not added");
	    return;
	} else {
	    System.out.println("Added Customer");
	    System.out.println(result.toString());
	}

    }

    /**
     * This method takes a customer's ID and removes that customer and their credit
     * card from the system
     * 
     */
    public void removeCustomer() {
	String customerID;
	Customer customer;
	System.out.println("Remove Customer");
	do { // displays the customer data and see if it is the correct customer
	    customerID = getToken("Enter Cusomter's ID Number or (C/c) to return to main menu");
	    // checks if user wants to return to main menu
	    if (cancel(customerID))
		return;
	    if (!Theater.isNumeric(customerID)) {
		System.out.println("Enter a valid ID");
		continue;
	    }
	    customer = Theater.searchCustomer(customerID); // searches for customer

	    if (customer == null) {
		System.out.println("Customer not found");
		continue;
	    }

	    System.out.println(customer);
	    if (yesOrNo("Is this correct?")) {
		break;
	    }
	} while (true);

	if (yesOrNo("Delete customer?")) {
	    Theater.removeCustomer(customerID); // delete customer
	    System.out.println("Customer deleted");
	} else
	    System.out.println("Customer NOT deleted");

    }

    /**
     * This method adds a credit card to a customer with a given ID and credit Card
     * information This method will check if the customer exists and if the card is
     * already in the system before adding it to the customer also it will check if
     * card is valid and if it is American Express, Visa, Discover Card or
     * Mastercard
     * 
     */
    public void addCreditCard() {
	Customer customer, result;
	String customerID, creditCard, expiray;
	long creditCardNumber;
	do { // checks if the information is correct
	    customerID = getToken("Enter ID or Enter (C/c) to return to main menu");
	    if (cancel(customerID))
		return;

	    if (!Theater.isNumeric(customerID)) {
		System.out.println("Enter a valid ID");
		continue;
	    }

	    customer = Theater.searchCustomer(customerID); // search for customer with the same card
	    if (customer == null) { // customer isn't found
		System.out.println("Customer not found");
		continue;
	    }
	    System.out.println(customer.toString());

	    if (yesOrNo("Is this correct")) {
		break;
	    } else
		System.out.println("Try again");

	} while (true);

	do {
	    // checks if card is valid
	    creditCard = getToken("Enter credit card number or Enter (C/c) to return to main menu");
	    if (cancel(creditCard)) // return to menu
		return;

	    if (!Theater.isNumeric(creditCard)) { // check if only has numbers
		continue;
	    }

	    creditCardNumber = Long.parseLong(creditCard);
	    expiray = getToken("Enter credit card expiration date MM/YY");
	    if (!Theater.isValidCard(creditCardNumber) || !Theater.isDate(expiray)) {
		System.out.println("Enter a VALID credit card");
		System.out.println("We accept Mastercard, Visa, American Express, Discover Card and JCB");
		System.out.println("We DO NOT accept RuPay, Union Pay");
	    } else {
		break;
	    }
	} while (true);

	CreditCard newCreditCard = new CreditCard(creditCard, expiray);
	result = Theater.customerCreditCard(creditCard); // search for customer with the same card

	if (result != null) {
	    System.out.println("Adding card failed");
	    System.out.println("Already in system");
	} else {
	    customer.addCreditCard(newCreditCard);
	    System.out.println("added card");
	}
    }

    /**
     * This method removes a credit card from the system with a given card number it
     * will also check if the customer has more than one credit card in the system
     * before deleting and will refuse to delete if customer only has one credit
     * card.
     */
    public void removeCreditCard() {
	String creditCardString;

	do {
	    // checks if card is valid
	    creditCardString = getToken("Enter credit card number or Enter (C/c) to return to main menu");
	    if (cancel(creditCardString)) // return to menu
		return;

	    if (Theater.isNumeric(creditCardString)) { // check if only has numbers
		break;
	    }
	} while (true);

	Customer customer = Theater.customerCreditCard(creditCardString); // searches for customer with the card in the
	// system
	if (customer == null) {
	    System.out.println("card doesn't exist in system");
	    // checks if it is the correct customer
	} else if (yesOrNo(customer.toString() + "\nIs this correct?")) {
	    if (Theater.removeCreditCard(creditCardString, customer)) {
		System.out.println("card removed");
	    }
	}
    }

    /**
     * This method shows all customers that was saved and that have been created
     */
    public void listCustomers() {
	System.out.println(Theater.listCustomers());
    }

    public void addShow() {
	try {
	    String show = getToken("Enter show name or (C/c) to cancel");
	    if (cancel(show))
		return;
	    String clientID = getToken("Enter client ID");
	    Date startDate = getDate("Enter Start date");
	    Date endDate = getDate("Enter End date");

	    if (startDate.compareTo(endDate) < 0) {

		boolean results = TheaterShowList.addShow(clientID, show, startDate, endDate);
		if (!results) {
		    System.out.println("Show not added");
		} else
		    System.out.println("Show added");
		// add show
	    } else {
		System.out.println("Show not added");
	    }
	} catch (NullPointerException e) {
	    System.out.println("Client not found");
	}
    }

    public void listShows() {
	TheaterShowList.listShows();
    }

    /**
     * Method to be called for saving the Library object. Uses the appropriate
     * Library method for saving.
     * 
     */
    @SuppressWarnings("static-access")
    private void save() {
	if (theater.save()) {
	    System.out.println("The Theater has been successfully saved in the file TheaterData \n");
	} else {
	    System.out.println("There has been an error in saving \n");
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
		System.out.println("The theater has been successfully retrieved from the file TheaterData \n");
		theater = tempTheater;
	    } else {
		System.out.println("File doesnt exist; creating new theater");
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
	if (yesOrNo("Exiting... Save current data"))
	    save();
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
