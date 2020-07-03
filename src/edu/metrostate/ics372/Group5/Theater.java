package edu.metrostate.ics372.Group5;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

/**
 * @author Anthony
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

    public static Customer

	    addCustomer(String name, String address, String phone, String creditCardNumber, String expiray) {
	Customer customer = new Customer(name, address, phone);
	CreditCard creditCard = new CreditCard(creditCardNumber, expiray);

	if (!CustomerList.checkNewCustomer(name, address, phone)) {
	    CustomerList.insertCustomer(customer);
	    customer.addCreditCard(creditCard);
	    return customer;

	}

	return null;

    }

    @SuppressWarnings("static-access")
    public static void removeCustomer(String ID) {
	Customer customer;
	customer = CustomerList.searchCustomer(ID);
	CustomerList.removeCustomer(customer);
    }

    public static Customer searchCustomer(String ID) {
	Customer customer;
	customer = CustomerList.searchCustomer(ID);
	return customer;

    }

    public static Customer customerCreditCard(String creditCard) {
	Customer customer = CustomerList.customerCreditCard(creditCard);
	return customer;
    }

    public static boolean isCardDuplicate(String creditCard) {
	if (!CustomerList.searchCreditCard(creditCard)) {
	    return false;
	}
	return true;
    }

    public static boolean onlyCreditCard(String creditCard) {
	Customer customer;
	int cards = 0;
	customer = CustomerList.customerCreditCard(creditCard);
	cards = customer.howManyCards();
	if (cards > 1) {
	    customer.removeCreditCardNumber(creditCard);
	    return true;
	}

	return false;
    }

    public static boolean removeCreditCard(String creditCard, Customer customer) {
	int cards = 0;

	cards = customer.howManyCards();

	if (cards > 1) {
	    customer.removeCreditCardNumber(creditCard);
	    return true;
	}

	System.out.println("Customer only has one card");
	return false;
    }

    public static String listCustomers() {
	String output = "";
	return CustomerList.printAll();

    }

    /**
     * Retrieves a deserialized version of the library from disk
     * 
     * @return a Library object
     */
    public static Theater retrieve() {
	try {
	    FileInputStream file = new FileInputStream("TheaterData");
	    ObjectInputStream input = new ObjectInputStream(file);
	    input.readObject();
	    CustomerIdServer.retrieve(input);
	    CustomerList.readTest(input); // read customerList object to file work around
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
     * Serializes the Library object
     * 
     * @return true iff the data could be saved
     */
    public static boolean save() {
	try {
	    FileOutputStream file = new FileOutputStream("TheaterData");
	    ObjectOutputStream output = new ObjectOutputStream(file);
	    output.writeObject(theater);
	    output.writeObject(CustomerIdServer.instance());
	    CustomerList.writeTest(output); // write customerList object to file work around
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

}
