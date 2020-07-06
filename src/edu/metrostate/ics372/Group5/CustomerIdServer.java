package edu.metrostate.ics372.Group5;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.Serializable;
import java.util.ArrayList;

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
    private static CustomerIdServer server;
    private static ArrayList<String> customerIdList;

    private CustomerIdServer() {
	customerIdList = new ArrayList<String>();
    }

    /**
     * Singleton practices
     */
    public static CustomerIdServer instance() {
	if (server == null) {
	    return (server = new CustomerIdServer());
	} else {
	    return server;
	}
    }

    /*
     * Generates a random String ID that does not already Exist
     */
    public String getId() {
	Random rnd = new Random();
	char[] digits = new char[6];
	String output = "";

	do {
	    digits[0] = (char) (rnd.nextInt(9) + '1');
	    for (int i = 1; i < digits.length; i++) {
		digits[i] = (char) (rnd.nextInt(10) + '0');
	    }
	    output = new String(digits);
	} while (customerIdList.contains(output));
	customerIdList.add(output);
	return new String(digits);
    }

    /**
     * String form of the collection
     * 
     */
    @Override
    public String toString() {
	return ("IdServer: " + toString());
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
    public static void writeObject(java.io.ObjectOutputStream output) throws IOException {
	try {
	    output.writeObject(customerIdList);
	} catch (IOException e) {
	    // TODO Auto-generated catch block
	    e.printStackTrace();
	}
    }

    /*
     * Supports serialization
     * 
     * @param input the stream to be read from
     */
    @SuppressWarnings("unchecked")
    public static void readObject(java.io.ObjectInputStream input) {
	try {
	    customerIdList = (ArrayList<String>) input.readObject();
	} catch (ClassNotFoundException e) {
	    // TODO Auto-generated catch block
	    e.printStackTrace();
	} catch (IOException e) {
	    // TODO Auto-generated catch block
	    e.printStackTrace();
	}
    }

    /*
     * Checks to see if the ID is stored in the server
     */
    public static boolean contains(String id) {
	return customerIdList.contains(id);
    }

    /*
     * Static method that removes an ID from the ID server
     */
    public static boolean remove(String id) {
	return customerIdList.remove(id);
    }
}
