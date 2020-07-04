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

	public static Client search(String clientId) {
		if(ClientList.contains(clientId)) {
		return clientArray.get(clientArray.indexOf(new Client(clientId)));
		}else {
			return null;
		}
	}

	public static boolean insertClient(Client client) {
		clientArray.add(client);
		return true;
	}
	
	public static boolean removeClientFromList(String clientId) {
		if(TheaterShowList.canRemove(clientId)) {
			clientArray.remove(new Client(clientId));
			ClientIdServer.remove(clientId);
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

	@Override
	public String toString() {
		return clientArray.toString();
	}

	public static boolean contains(String id) {
		return ClientIdServer.contains(id);
	}
}
