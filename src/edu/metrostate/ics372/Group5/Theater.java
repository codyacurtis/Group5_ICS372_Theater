package edu.metrostate.ics372.Group5;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

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
    private static CustomerList customerList;
    private static Theater theater;

    /**
     * Private for the singleton pattern Creates the catalog and member collection
     * objects
     */
    private Theater() {
	customerList = CustomerList.instance();
    }

    public static Theater instance() {
	if (theater == null) {
	    CustomerIdServer.instance(); // instantiate all singletons
	    return (theater = new Theater());
	} else {
	    return theater;
	}
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
	    CustomerList.insertCustomer(customer);
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
	Customer customer;
	customer = CustomerList.searchCustomer(ID);
	CustomerList.removeCustomer(customer);
    }

    /**
     * This finds customer with an ID
     * 
     * @return customer object
     */
    public static Customer searchCustomer(String ID) {
	Customer customer;
	customer = CustomerList.searchCustomer(ID);
	return customer;
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
    public static String listCustomers() {
	String output = "";
	return CustomerList.printAll();
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
	    CustomerIdServer.retrieve(input);
	    CustomerList.readObject(input);

	    // Shows and clients
	    TheaterShowList.readObject(input);
	    ClientIdServer.readObject(input);
	    ClientList.readObject(input);

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
	    output.writeObject(CustomerIdServer.instance());
	    CustomerList.writeObject(output);
	    TheaterShowList.writeObject(output);
	    ClientIdServer.writeObject(output);
	    ClientList.writeObject(output);

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

    public Client addClient(String name, String address, String phone) {
	Client insert = new Client(name, address, phone);
	ClientList.insertClient(insert);
	return insert;
    }

	public static Client searchClient(String clientID) {
		Client client;
		client = ClientList.search(clientID);
		return client;
	}

}
