package edu.metrostate.ics372.Group5;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.Serializable;
import java.util.Random;

public class CustomerIdServer implements Serializable {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String idCounter;
    private static CustomerIdServer server;

    /*
     * Private constructor for singleton pattern
     * 
     */
    private CustomerIdServer() {
	idCounter = generateID();
    }

    /**
     * Supports the singleton pattern
     * 
     * @return the singleton object
     */
    public static CustomerIdServer instance() {
	if (server == null) {
	    return (server = new CustomerIdServer());
	} else {
	    return server;
	}
    }

    /**
     * Getter for id
     * 
     * @return id of the member
     */
    public String getId() {
	return idCounter;
    }

    /**
     * String form of the collection
     * 
     */
    @Override
    public String toString() {
	return ("IdServer" + idCounter);
    }

    /**
     * This method generates a random 6 digits number to assign a new customer
     * 
     * @return random ID number
     */
    public String generateID() {
	Random rnd = new Random();
	char[] digits = new char[6];
	digits[0] = (char) (rnd.nextInt(9) + '1');
	for (int i = 1; i < digits.length; i++) {
	    digits[i] = (char) (rnd.nextInt(10) + '0');
	}
	return new String(digits);
    }

    /**
     * Retrieves the server object
     * 
     * @param input inputstream for deserialization
     */
    public static void retrieve(ObjectInputStream input) {
	try {
	    server = (CustomerIdServer) input.readObject();
	} catch (IOException ioe) {
	    ioe.printStackTrace();
	} catch (Exception cnfe) {
	    cnfe.printStackTrace();
	}
    }

    /*
     * Supports serialization
     * 
     * @param output the stream to be written to
     */
    private void writeObject(java.io.ObjectOutputStream output) throws IOException {
	try {
	    output.defaultWriteObject();
	    output.writeObject(server);
	} catch (IOException ioe) {
	    ioe.printStackTrace();
	}
    }

    /*
     * Supports serialization
     * 
     * @param input the stream to be read from
     */
    private void readObject(java.io.ObjectInputStream input) throws IOException, ClassNotFoundException {
	try {
	    input.defaultReadObject();
	    if (server == null) {
		server = (CustomerIdServer) input.readObject();
	    } else {
		input.readObject();
	    }
	} catch (IOException ioe) {
	    ioe.printStackTrace();
	}
    }

}
