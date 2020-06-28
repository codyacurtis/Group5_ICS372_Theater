package edu.metrostate.ics372.Group5;

import java.io.IOException;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

public class CustomerList implements Serializable {

    private static final long serialVersionUID = 1L;
    private CreditCardList creditCardList;
    private static CustomerList customerList;
    private List<Customer> customers = new LinkedList<Customer>();

    /**
     * Private for the singleton pattern Creates the creditCard collection
     */
    @SuppressWarnings("static-access")
    private CustomerList() {
	creditCardList = CreditCardList.instance();
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
    public boolean insertCustomer(Customer customer) {
	customers.add(customer);
	return true;
    }

    /**
     * Removes a customer from the collection
     * 
     * @param Id the target to remove
     * @return true if customer is removed
     */
    public boolean removeCustomer(String Id) {
	int index = search(Id);
	if (index != -99) { // checks if customer is found
	    customers.remove(index);
	    creditCardList.deleteCustomerCards(Id);
	    return true;
	}
	return false;
    }

    /**
     * search for a customer in the collection and returns the index
     * 
     * @param Id customer's ID
     * @return index of customer found
     * 
     */
    public Integer search(String Id) {
	int index = 0;
	for (Iterator<Customer> iterator = customers.iterator(); iterator.hasNext(); index++) {
	    Customer Customer = (Customer) iterator.next();
	    if (Customer.getId().equals(Id)) {
		return index;
	    }
	}
	return -99; // customer not found
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

    /**
     * 
     * returns all customers into string form
     * 
     * @return customers in string form
     */
    public String printAllCustomers() {
	String output = "";
	String Id;
	for (Iterator<Customer> iterator = customers.iterator(); iterator.hasNext();) {
	    Customer Customer = (Customer) iterator.next();
	    output += Customer.toString();
	    Id = Customer.getId();
	    output += creditCardList.getCustomerCreditCards(Id); // gets the credit card of customer
	}

	return output;
    }

}
