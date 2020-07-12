package edu.metrostate.ics372.Group5;

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;

public class ClientList implements Serializable {

	private static final long serialVersionUID = 1L;
	private static ArrayList<Client> clientArray = new ArrayList<Client>();
	private static ClientList clientList;

	private ClientList() {
		super();
	}

	/**
	 * Singleton practices
	 */
	public static ClientList instance() {
		if (clientList == null) {
			return (clientList = new ClientList());
		} else {
			return clientList;
		}
	}

	/*
	 * Searches by ID string, returns a Client object
	 */
	public static Client search(String clientId) {
		if (ClientIdServer.contains(clientId)) {
			for (Client i : clientArray) {
				if (i.getId().compareTo(clientId) == 0) {
					return i;
				}
			}
		}
		return null;
	}

	/*
	 * Adds a client to the client arrayList
	 */
	public static boolean insertClient(Client client) {
		clientArray.add(client);
		return true;
	}

	/*
	 * Takes in a client ID, if the client has no current dates then the client will
	 * be able to be removed
	 */
	public static boolean removeClientFromList(String clientId) {
		if (TheaterShowList.canRemove(clientId)) {
			clientArray.remove(search(clientId));
			ClientIdServer.remove(clientId);
			return true;
		} else {
			return false;
		}
	}
	
	/*
	 * Prints out each client in the clientArray
	 */
	public static void listClients() {
		for (Client i : clientArray) {
			System.out.println(i);
		}
	}

	public static void writeObject(java.io.ObjectOutputStream output) {
		try {
			output.writeObject(clientArray);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@SuppressWarnings("unchecked")
	public static void readObject(java.io.ObjectInputStream input) {
		try {
			clientArray = (ArrayList<Client>) input.readObject();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
