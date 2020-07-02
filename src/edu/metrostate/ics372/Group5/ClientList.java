package edu.metrostate.ics372.Group5;

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;

public class ClientList implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static ArrayList<Client> clientArray = new ArrayList<Client>();
	private static ClientList clientList;

	private ClientList() {
	}

	public static ClientList instance() {
		if (clientList == null) {
			return (clientList = new ClientList());
		} else {
			return clientList;
		}
	}

	public static Client search(int clientId) {
		if(clientArray.contains(clientId)) {
		return clientArray.get(clientArray.indexOf(clientId));
		}else {
			return null;
		}
	}

	public static boolean insertClient(Client client) {
		clientArray.add(client);
		return true;
	}
	
	public static boolean removeClient(int clientId) {
		if(TheaterShowList.canRemove(clientId)) {
			ClientList.removeClient(clientId);
			System.out.println("Client Removed");
			return true;
		}else {
			System.out.println("Unable to remove Client");
			return false;
		}
	}

	public static void listClients() {
		for (Client i : clientArray) {
			System.out.println(i);
		}
	}

	public static ArrayList<Client> getClientArray() {
		return clientArray;
	}
	
 	private void writeObject(java.io.ObjectOutputStream output) {
		try {
			output.defaultWriteObject();
			output.writeObject(clientList);
		} catch (IOException ioe) {
			ioe.printStackTrace();
		}
	}

	private void readObject(java.io.ObjectInputStream input) {
		try {
			if (clientList != null) {
				return;
			} else {
				input.defaultReadObject();
				if (clientList == null) {
					clientList = (ClientList) input.readObject();
				} else {
					input.readObject();
				}
			}
		} catch (IOException ioe) {
			ioe.printStackTrace();
		} catch (ClassNotFoundException cnfe) {
			cnfe.printStackTrace();
		}
	}

	@Override
	public String toString() {
		return clientArray.toString();
	}
}
