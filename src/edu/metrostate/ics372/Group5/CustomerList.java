package edu.metrostate.ics372.Group5;

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;

/**
 * This class is the collection class for the customers. The class stores the
 * customers into a linked list and is able to add, remove, search for customers
 * and return their information.
 * 
 * @author Anthony Nguyen
 * @since 2020.04.07
 * @version 1.0.0
 */

public class CustomerList implements Serializable {

    private static final long serialVersionUID = 1L;
    private static CustomerList customerList;
    private static ArrayList<Customer> customers = new ArrayList<Customer>();

    /**
     * Private for the singleton pattern Creates the Customer List collection
     */
    @SuppressWarnings("static-access")
    private CustomerList() {
    };

    /**
     * Supports the singleton pattern
     * 
     * @return the singleton object
     */
    public static CustomerList instance() {
	if (customerList == null) {
	    return (customerList = new CustomerList());
	} else {
	    return customerList;
	}
    }

    /**
     * Inserts a customer into the collection
     * 
     * @param customer the customer to be inserted
     * @return true if the member could be inserted. Currently always true
     */
    public static boolean insertCustomer(Customer customer) {
	customers.add(customer);
	return true;
    }

    /**
     * Removes a customer from the collection
     * 
     * @param Id the target to remove
     * @return true if customer is removed
     */
    public static boolean removeCustomer(Customer customer) {
	customers.remove(customer);
	return true;

    }

    /**
     * search for a customer in the collection and returns the customer object if
     * found
     * 
     * @param Id customer's ID
     * @return customer object
     * 
     */
    public static Customer searchCustomer(String Id) {
	for (Iterator<Customer> iterator = customers.iterator(); iterator.hasNext();) {
	    Customer customer = (Customer) iterator.next();
	    if (customer.match(Id)) {
		return customer;
	    }
	}
	return null; // customer not found
    }

    /**
     * Checks if a customer already exists in the system
     * 
     * @param name    Customer's name
     * @param address customer's address
     * @param phone   customer's phone
     * @return true if customer exists
     */
    public static boolean checkNewCustomer(String name, String address, String phone) {
	for (Iterator<Customer> iterator = customers.iterator(); iterator.hasNext();) {
	    Customer customer = (Customer) iterator.next();
	    if (customer.getName().equalsIgnoreCase(name) && customer.getAddress().equalsIgnoreCase(address)
		    && customer.getPhoneNumber().equals(phone)) {
		return true;
	    }
	}
	return false; // customer not found
    }

    /**
     * This method returns the customer with the matching credit card number
     * 
     * @param creditCard card to find in system
     * @return customer object
     */

    public static Customer customerCreditCard(String creditCard) {
	for (Iterator<Customer> iterator = customers.iterator(); iterator.hasNext();) {
	    Customer customer = (Customer) iterator.next();
	    if (customer.checkCreditCardDuplicate(creditCard)) {
		return customer;
	    }
	}
	return null;
    }

    /**
     * prints all customer and credit card in system
     * 
     * @return string data of customers and credit cards
     */
    public static String printAll() {
	String output = "\n";

	if (customers.isEmpty())
	    return output = "List Empty";

	for (Iterator<Customer> iterator = customers.iterator(); iterator.hasNext();) {
	    Customer customer = (Customer) iterator.next();
	    output += customer.toString();
	    output += customer.printAllCreditCards() + "\n";
	}

	return output;
    }

    /*
     * Supports serialization
     * 
     * @param output the stream to be written to
     */
    public static void writeObject(java.io.ObjectOutputStream output) {
	try {
	    output.writeObject(customers);
	} catch (IOException e) {
	    // TODO Auto-generated catch block
	    e.printStackTrace();
	}

    }

    /*
     * Supports serialization
     * 
     * @param input the stream to be read from
     */
    @SuppressWarnings("unchecked")
    public static void readObject(java.io.ObjectInputStream input) {
	try {
	    customers = (ArrayList<Customer>) input.readObject();
	} catch (ClassNotFoundException e) {
	    // TODO Auto-generated catch block
	    e.printStackTrace();
	} catch (IOException e) {
	    // TODO Auto-generated catch block
	    e.printStackTrace();
	}
    }

}
