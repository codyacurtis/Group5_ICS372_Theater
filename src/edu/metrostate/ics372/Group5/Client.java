package edu.metrostate.ics372.Group5;

import java.io.Serializable;
import java.text.DecimalFormat;

public class Client extends People implements Serializable {
    protected double balance;
    private static final long serialVersionUID = 1L;
    private static DecimalFormat df2 = new DecimalFormat("#.00");

    // Constructor for the client object
    public Client(String name, String address, String phoneNumber) {
	super(name, address, phoneNumber);
	balance = 0;
	// this is to make sure that there are not duplicates
	this.id = (ClientIdServer.instance()).getId();
	System.out.println("New Client: " + toString());
    }

    public double getBalance() {
	return balance;
    }

    public void setBalance(double balance) {
	this.balance = balance;
    }

    public void addBalance(double addBalance) {
	balance += addBalance;
    }

    public void subBalance(double subBalance) {
	this.balance += subBalance;
    }

    public String toString() {
	return super.toString() + "Balance:$" + df2.format(balance);
    }

}
