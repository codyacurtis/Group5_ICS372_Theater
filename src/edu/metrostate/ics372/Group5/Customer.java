package edu.metrostate.ics372.Group5;

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

public class Customer implements Serializable {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;
    private String name; // name of customer
    private String address; // address of customer
    private String phone; // customer's phone number
    private String id; // customer's id number

    /**
     * 
     * @param name    customer's name
     * @param address customer's address
     * @param phone   customer's phone number
     * @param id      customer ID
     */
    @SuppressWarnings("unchecked")
    public Customer(String name, String address, String phone) {
	this.name = name;
	this.address = address;
	this.phone = phone;
	// this.id = id;
	id = (CustomerIdServer.instance()).getId();
    }

    /**
     * getter for name
     * 
     * @return member's name
     */
    public String getName() {
	return name;
    }

    /**
     * getter for address
     * 
     * @return member' address
     */

    public String getAddress() {
	return address;
    }

    /**
     * getter for phone number
     * 
     * @return member's phone
     */
    public String getPhone() {
	return phone;
    }

    /**
     * getter for ID
     * 
     * @return member's Id
     */
    public String getId() {
	return id;
    }

    /**
     * setter for name
     * 
     * @param newName member's new name
     */
    public void setName(String newName) {
	name = newName;
    }

    /**
     * 
     * setter for address
     * 
     * @param newAddress member's new address
     */
    public void setAddresss(String newAddress) {
	address = newAddress;
    }

    /**
     * setter for phone
     * 
     * @param newPhone members' new phone
     */
    public void setPhone(String newPhone) {
	phone = newPhone;
    }

    /**
     * setter for Id
     * 
     * @param newId member's new ID
     */
    public void setId(String newId) {
	id = newId;
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
