package edu.metrostate.ics372.Group5;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.Serializable;
import java.util.ArrayList;

import org.junit.jupiter.api.MethodOrderer.Random;

public class IdServer implements Serializable {

    private static final long serialVersionUID = 1L;
    protected static IdServer server;
    protected static ArrayList<String> idList = new ArrayList<String>();

    protected IdServer() {
	super();
    }

    /**
     * Returns the only instance of the class.
     * 
     * @return the instance
     */
    public static IdServer instance() {
	if (server == null) {
	    return server = new IdServer();
	} else {
	    return server;
	}
    }

    /**
     * creates random ID number
     * 
     * @return string of numbers
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
	} while (idList.contains(output));
	idList.add(output);
	return new String(digits);
    }

    /**
     * Retrieves a deserialized version of the Theater from disk
     * 
     * @param input file to retreive
     */
    public static void retrieve(ObjectInputStream input) {
	try {
	    server = (IdServer) input.readObject();
	} catch (IOException ioe) {
	    ioe.printStackTrace();
	} catch (Exception cnfe) {
	    cnfe.printStackTrace();
	}
    }

    /**
     * remove id string from idList
     * 
     * @param id to be removed
     * @return true if removed
     */
    public static boolean remove(String id) {
	return idList.remove(id);
    }

    /**
     * check if have this id in in idlist
     * 
     * @param id target
     * @return true if have id
     */
    public static boolean contains(String id) {
	return idList.contains(id);
    }

    /**
     * reads objects
     * 
     * @param input file to read
     */
    @SuppressWarnings("unchecked")
    public static void readObject(java.io.ObjectInputStream input) {
	try {
	    idList = (ArrayList<String>) input.readObject();
	} catch (ClassNotFoundException e) {
	    // TODO Auto-generated catch block
	    e.printStackTrace();
	} catch (IOException e) {
	    // TODO Auto-generated catch block
	    e.printStackTrace();
	}
    }

    /**
     * Writes the object to the output stream
     * 
     * @param output the stream to be written to
     */
    public static void writeObject(java.io.ObjectOutputStream output) throws IOException {
	try {
	    output.writeObject(idList);
	} catch (IOException e) {
	    // TODO Auto-generated catch block
	    e.printStackTrace();
	}
    }

}
