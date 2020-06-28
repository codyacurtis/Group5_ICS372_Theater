package edu.metrostate.ics372.Group5;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

public class CustomerList {

    private CreditCardList creditCardList;
    private static CustomerList customerList;
    private List<Customer> customers = new LinkedList();

    private CustomerList() {
	creditCardList = creditCardList.instance();
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
     * @param member the member to be inserted
     * @return true iff the member could be inserted. Currently always true
     */
    public boolean insertCustomer(Customer customer) {
	customers.add(customer);
	return true;
    }

    public boolean removeCustomer(String Id) {
	int index = search(Id);
	if (index != -99) { // checks if customer is found
	    customers.remove(index);
	    return true;
	}
	return false;
    }

    /**
     * Checks whether a customer with a given member id exists.
     * 
     * @param memberId the id of the member
     * @return true iff member exists
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

    public String printAllCustomers() {
	String output = "";
	String Id;
	for (Iterator<Customer> iterator = customers.iterator(); iterator.hasNext();) {
	    Customer Customer = (Customer) iterator.next();
	    output += Customer.toString();
	    Id = Customer.getId();
	    output += creditCardList.getCustomerCreditCards(Id);
	}

	return output;
    }

}
