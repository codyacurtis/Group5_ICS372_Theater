package edu.metrostate.ics372.Group5;

import java.io.Serializable;

public class People implements Serializable {

	private static final long serialVersionUID = 1L;
	protected String name, address, phoneNumber, id;

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

	public String getName() {
		return name;
	}

	public String getAddress() {
		return address;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public String getId() {
		return id;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}


	@Override
	public String toString() {
		return this.getClass().toString() + " [name=" + name + ", id=" + id + "]";
	}
}
