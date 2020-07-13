package edu.metrostate.ics372.Group5;

import java.io.Serializable;

public class Client extends People implements Serializable {
	protected double balance;
	private static final long serialVersionUID = 1L;

	// Constructor for the client object
	public Client(String name, String address, String phoneNumber) {
		super(name, address, phoneNumber);
		balance = 0;
		// this is to make sure that there are not duplicates
		this.id = (ClientIdServer.instance()).getId();
		System.out.println("New Client: " + toString());
	}

}
