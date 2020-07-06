package edu.metrostate.ics372.Group5;

import java.io.Serializable;

//Isiah, 2,3,4,10,11
public class Client implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String name, address, phoneNumber;
	private String id;
	private double balance;

	// Constructor for the client object
	public Client(String name, String address, String phoneNumber) {
		this.name = name;
		this.address = address;
		this.phoneNumber = phoneNumber;
		balance = 0;
		// this is to make sure that there are not dublicates
		this.id = (ClientIdServer.instance()).getId();
		System.out.println("New Client: " + toString());
	}

	/**
	 * String of the Client Onjects ID
	 */
	public String getId() {
		return id; 
	}

	/**
	 * Compares the ids of the Client Objects
	 */
	@Override
	public boolean equals(Object obj) {
		if (obj == null) {
			return false;
		}
		if (!(obj instanceof Client)) {
			return false;
		}
		if (obj == this) {
			return true;
		}
		return this.getId().compareTo(((Client) obj).getId()) == 0;
	}

	@Override
	public int hashCode() {
		return Integer.parseInt(this.id);
	}

	@Override
	public String toString() {
		return "Client [name=" + name + ", id=" + id + "]";
	}

}
