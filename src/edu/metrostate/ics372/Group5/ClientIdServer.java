package edu.metrostate.ics372.Group5;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Random;

public class ClientIdServer implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static ClientIdServer server;
	private static ArrayList<String> idList;

	private ClientIdServer() {
		idList = new ArrayList<String>();
	}

	public static ClientIdServer instance() {
		if (server == null) {
			return (server = new ClientIdServer());
		} else {
			return server;
		}
	}

	public String getId() {
		Random rnd = new Random();
		char[] digits = new char[6];
		digits[0] = (char) (rnd.nextInt(9) + '1');
		for (int i = 1; i < digits.length; i++) {
			digits[i] = (char) (rnd.nextInt(10) + '0');
		}
		idList.add(new String(digits));
		return new String(digits);
	}

	@Override
	public String toString() {
		return ("IdServer: " + toString() );
	}

	public static void retrieve(ObjectInputStream input) {
		try {
			server = (ClientIdServer) input.readObject();
		} catch (IOException ioe) {
			ioe.printStackTrace();
		} catch (Exception cnfe) {
			cnfe.printStackTrace();
		}
	}

	public static void writeObject(java.io.ObjectOutputStream output) throws IOException {
		try {
			output.writeObject(idList);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

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

	public static boolean contains(String id) {
		return idList.contains(id);
	}

	public static boolean remove(String id) {
		return idList.remove(id);
	}
}
