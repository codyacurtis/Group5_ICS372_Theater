package edu.metrostate.ics372.Group5;

import java.io.Serializable;

public class People implements Serializable {

    private static final long serialVersionUID = 1L;
    protected String name, address, phoneNumber, id;

    /**
     * constructor
     * 
     * @param name        person name
     * @param address     person address
     * @param phoneNumber person phone number
     */
    public People(String name, String address, String phoneNumber) {
	super();
	this.name = name;
	this.address = address;
	this.phoneNumber = phoneNumber;
    }

    /**
     * Compares the id's of the Client Objects
     */
    @Override
    public boolean equals(Object obj) {
	if (obj == null) {
	    return false;
	}
	if (!(obj instanceof People)) {
	    return false;
	}
	if (obj == this) {
	    return true;
	}
	return this.getId().compareTo(((People) obj).getId()) == 0;
    }

    /**
     * getter for name
     * 
     * @return name of person
     */
    public String getName() {
	return name;
    }

    /**
     * getter for address
     * 
     * @return address of person
     */
    public String getAddress() {
	return address;
    }

    /**
     * getter for phone number
     * 
     * @return person's phone number
     */
    public String getPhoneNumber() {
	return phoneNumber;
    }

    /**
     * getter for ID
     * 
     * @return person id number
     */
    public String getId() {
	return id;
    }

    /**
     * setter for name
     * 
     * @param name person new name
     */
    public void setName(String name) {
	this.name = name;
    }

    /**
     * setter for address
     * 
     * @param address person's new address
     */
    public void setAddress(String address) {
	this.address = address;
    }

    /**
     * setter for phone number
     * 
     * @param phoneNumber person's new phone number
     */
    public void setPhoneNumber(String phoneNumber) {
	this.phoneNumber = phoneNumber;
    }

    /**
     * string data of people
     * 
     * @return string data
     */
    @Override
    public String toString() {
	return "People [name=" + name + ", address=" + address + ", phoneNumber=" + phoneNumber + ", id=" + id + "]";
    }

}
