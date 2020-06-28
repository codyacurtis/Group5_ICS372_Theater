package edu.metrostate.ics372.Group5;

import java.util.LinkedList;

/**
 * 
 * This class creates the customer object and stores it to a linkedList. This
 * class contains method to search for customers, prints all customers, print
 * string, add/remove credit cards and remove customers. This class also stores
 * the credit card information to a hash map where the user ID is the key.
 * 
 * 
 * @author Anthony Nguyen
 * @version 1.00.00
 * @since 2020.06.25
 */

public class Customer {

    private String name; // name of customer
    private String address; // address of customer
    private String phone; // customer's phone number
    private String creditCard; // customer's credit information
    private String expiry; // credit card expiration date
    private String id; // customer's id number

    public static LinkedList<Customer> customerList = new LinkedList<Customer>();

    /**
     * 
     * @param name    of customer
     * @param address customer address
     * @param phone   customer phone number
     * 
     * @param id      customer ID // testing purposes
     */
    @SuppressWarnings("unchecked")
    public Customer(String name, String address, String phone, String id) {
	this.name = name;
	this.address = address;
	this.phone = phone;
	this.id = id;
    }

    public String getId() {
	return id;
    }

    /**
     * 
     * This method creates a string version out of the customerList and the
     * customerCards
     * 
     */
    public String toString() {
	String StrOutput = "";
	StrOutput = "Name: " + name + "\nAddress: " + address + "\nPhone Number: " + phone + "\nID: " + id + "\n";
	return StrOutput;
    }

}
