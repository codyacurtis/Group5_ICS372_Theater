package edu.metrostate.ics372.Group5;
import java.io.Serializable;
import java.util.Date;

public abstract class Ticket implements Serializable, Matchable<String> {

    private static final long serialVersionUID = 1L;
    private String customerID;
    protected Date date;

    private String ticketID;
    private String showID;
    private String showName;

    public Ticket(Date date, String customerID, String showID, String showName) {
	this.customerID = customerID;
	this.date = date;
	this.showID = showID;
	this.showName = showName;
    }

    public String getCustomerID() {
	return customerID;
    }

    public String getShowName() {
	return showName;
    }

    public String getShowID() {
	return showID;
    }

    public Date getShowDate() {
	return date;
    }

    public boolean matches(String id) {
	return (this.ticketID.equals(id));
    }

    @Override
    public String toString() {
	return "Ticket Show: " + showName + "\nDate of Show: " + date + "\nCustomer ID: " + customerID;
    }

}
