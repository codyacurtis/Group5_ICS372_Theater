package edu.metrostate.ics372.Group5;

import java.io.IOException;
import java.io.Serializable;
import java.util.Iterator;
import java.util.LinkedList;

public class CustomerList implements Serializable {

    /**
     * fdx
     */
    private static final long serialVersionUID = 1L;
    private static CustomerList customerList;
    private static LinkedList<Customer> customers = new LinkedList<Customer>();

    /**
     * Private for the singleton pattern Creates the creditCard collection
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
     * search for a customer in the collection and returns the index
     * 
     * @param Id customer's ID
     * @return index of customer found
     * 
     */
    public Integer searchCustomerIndex(String Id) {
	int index = 0;
	for (Iterator<Customer> iterator = customers.iterator(); iterator.hasNext(); index++) {
	    Customer Customer = (Customer) iterator.next();
	    if (Customer.getId().equals(Id)) {
		return index;
	    }
	}
	return -99; // customer not found
    }

    /**
     * search for a customer in the collection and returns the index
     * 
     * @param Id customer's ID
     * @return index of customer found
     * 
     */
    public static Customer searchCustomer(String Id) {

	for (Iterator<Customer> iterator = customers.iterator(); iterator.hasNext();) {
	    Customer customer = (Customer) iterator.next();
	    if (customer.getId().equals(Id)) {
		return customer;
	    }
	}
	return null; // customer not found
    }

    public static boolean checkNewCustomer(String name, String address, String phone) {
	int index = 0;
	for (Iterator<Customer> iterator = customers.iterator(); iterator.hasNext(); index++) {
	    Customer customer = (Customer) iterator.next();
	    if (customer.getName().equalsIgnoreCase(name) && customer.getAddresss().equalsIgnoreCase(address)
		    && customer.getPhone().equals(phone)) {
		return true;
	    }
	}
	return false; // customer not found
    }

    public static boolean searchCreditCard(String creditCard) {
	for (Iterator<Customer> iterator = customers.iterator(); iterator.hasNext();) {
	    Customer customer = (Customer) iterator.next();
	    if (customer.checkCreditCardDuplicate(creditCard)) {
		return false;
	    }

	}
	return true;
    }

    public static Customer customerCreditCard(String creditCard) {
	for (Iterator<Customer> iterator = customers.iterator(); iterator.hasNext();) {
	    Customer customer = (Customer) iterator.next();
	    if (customer.checkCreditCardDuplicate(creditCard)) {
		return customer;
	    }

	}
	return null;
    }

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
    private void writeObject(java.io.ObjectOutputStream output) {
	try {
	    output.defaultWriteObject();
	    output.writeObject(customerList);
	} catch (IOException ioe) {
	    ioe.printStackTrace();
	}
    }

    /*
     * Supports serialization
     * 
     * @param input the stream to be read from
     */
    private void readObject(java.io.ObjectInputStream input) {
	try {
	    if (customerList != null) {
		return;
	    } else {
		input.defaultReadObject();
		if (customerList == null) {
		    customerList = (CustomerList) input.readObject();
		} else {
		    input.readObject();
		}
	    }
	} catch (IOException ioe) {
	    ioe.printStackTrace();
	} catch (ClassNotFoundException cnfe) {
	    cnfe.printStackTrace();
	}
    }

}
