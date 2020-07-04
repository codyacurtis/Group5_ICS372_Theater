package edu.metrostate.ics372.Group5;

import java.io.Serializable;

//Isiah, 2,3,4,10,11
public class Client implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String name, adress, phoneNumber;
	private String id;
	private double balance;

	public Client(String name, String adress, String phoneNumber) {
		this.name = name;
		this.adress = adress;
		this.phoneNumber = phoneNumber;
		balance = 0;
		// this is to make sure that there are not dublicates
		this.id = (ClientIdServer.instance()).getId();
		System.out.println("New Client: " + toString());
	}

	public Client(String clientId) {
		id = clientId;
	}

	public String getId() {
		return id;
	}
	
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
		return this.getId() == ((Client) obj).getId();
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


