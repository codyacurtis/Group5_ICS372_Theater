package edu.metrostate.ics372.Group5;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.Serializable;

import org.junit.jupiter.api.MethodOrderer.Random;

/**
 * This class is for the id collection and generates a random Id for a new user
 * being created
 * 
 * @author Anthony Nguyen
 * @since 2020.04.07
 */

public class CustomerIdServer implements Serializable {

    private static final long serialVersionUID = 1L;
    private String ID; // Customer's ID
    private static CustomerIdServer server;

    /*
     * Private constructor for singleton pattern
     * 
     */
    private CustomerIdServer() {
	ID = randomID();
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
	return ID;
    }

    /**
     * String form of the collection
     * 
     */
    @Override
    public String toString() {
	return ("IdServer" + ID);
    }

    /**
     * This method generates a random 6 digits number to assign a new customer
     * 
     * @return random ID number
     */
    public static String randomID() {
	// It will generate 6 digit random Number.
	// from 0 to 999999
	Random rnd = new Random();
	int number = rnd.nextInt(999999);

	// this will convert any number sequence into 6 character.
	return String.format("%06d", number);
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
