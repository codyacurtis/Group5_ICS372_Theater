package edu.metrostate.ics372.Group5;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;

public abstract class Ticket implements Serializable, Matchable<String> {

    private static final long serialVersionUID = 1L;
    private String customerID;
    protected Date date;

    private String ticketID;
    private String showName;

    public Ticket(Date date, String customerID, String showName) {
	this.customerID = customerID;
	this.date = date;
	this.showName = showName;
    }

    public double getPrice() {
	return getPrice();
    }

    public String getCustomerID() {
	return customerID;
    }

    public String getShowName() {
	return showName;
    }

    public Date getShowDate() {
	return date;
    }

    public boolean matches(String id) {
	return (this.ticketID.equals(id));
    }

    @Override
    public String toString() {
	SimpleDateFormat DateFor = new SimpleDateFormat("MM/dd/yy");
	String stringDate = DateFor.format(date);
	return "Ticket Show: " + showName + "\nDate of Show: " + stringDate + "\nCustomer ID: " + customerID;
    }

}
