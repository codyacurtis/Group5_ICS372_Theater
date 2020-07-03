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
	private int idCounter;
	private static ClientIdServer server;
	private static ArrayList<String> ids = new ArrayList<String>();

	private ClientIdServer() {
	}

	public static ClientIdServer instance() {
		if (server == null) {
			return (server = new ClientIdServer());
		} else {
			return server;
		}
	}

	public String getId() {
		Random rand = new Random();
		int randInt = (int) ((rand.nextDouble()) * 100000);
		String output = Integer.toString(randInt);
		while (ids.contains(output)) {
			randInt = (int) ((rand.nextDouble()) * 100000);
			output = Integer.toString(randInt);
		}
		return output;
	}

	public static void removeId(String id) {
		ids.remove(id);
	}
	

	@Override
	public String toString() {
		return ("IdServer" + idCounter);
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

	private void writeObject(java.io.ObjectOutputStream output) throws IOException {
		try {
			output.defaultWriteObject();
			output.writeObject(server);
		} catch (IOException ioe) {
			ioe.printStackTrace();
		}
	}

	private void readObject(java.io.ObjectInputStream input) throws IOException, ClassNotFoundException {
		try {
			input.defaultReadObject();
			if (server == null) {
				server = (ClientIdServer) input.readObject();
			} else {
				input.readObject();
			}
		} catch (IOException ioe) {
			ioe.printStackTrace();
		}
	}

	public static boolean contains(String clientID) {
		return ids.contains(clientID);
	}

}
