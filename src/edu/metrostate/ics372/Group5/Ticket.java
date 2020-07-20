package edu.metrostate.ics372.Group5;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * This is the super class of all tickets, this implements methods for price,
 * customer id, show name and toString
 * 
 * @author Anthony Nguyen
 *
 */
public abstract class Ticket implements Serializable, Matchable<String> {

    private static final long serialVersionUID = 1L;
    private String customerID; // customer ID
    private String ticketID; // ticket ID
    private String showName; // name of show
    protected Date date; // date of show

    /**
     * constructor
     * 
     * @param date       show date
     * @param customerID customer ID
     * @param showName   name of show
     */
    public Ticket(Date date, String customerID, String showName) {
	this.customerID = customerID;
	this.date = date;
	this.showName = showName;
    }

    /**
     * getter for price
     * 
     * @return ticket price
     */
    public double getPrice() {
	return getPrice();
    }

    /**
     * getter for customer ID
     * 
     * @return customer ID
     */
    public String getCustomerID() {
	return customerID;
    }

    /**
     * getter for show name
     * 
     * @return show name
     */
    public String getShowName() {
	return showName;
    }

    /**
     * getter for show date
     * 
     * @return show date
     */
    public Date getShowDate() {
	return date;
    }

    /**
     * checks if equals
     * 
     * @return true if equals
     */
    public boolean matches(String id) {
	return (this.ticketID.equals(id));
    }

    /**
     * String form of ticket
     * 
     * @return string data
     */
    @Override
    public String toString() {
	SimpleDateFormat DateFor = new SimpleDateFormat("MM/dd/yy");
	String stringDate = DateFor.format(date);
	return "Ticket Show: " + showName + "\nDate of Show: " + stringDate + "\nCustomer ID: " + customerID;
    }

}
