package edu.metrostate.ics372.Group5;

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;

public class PeopleList implements Serializable {

	private static final long serialVersionUID = 1L;
	private static PeopleList peopleList;
	private static ArrayList<People> peopleArray = new ArrayList<People>();

	public static PeopleList instance() {
		if (peopleList != null) {
			peopleList = new PeopleList();
		}
		return peopleList;
	}

	public static People search(String clientId) {
		if (ClientIdServer.contains(clientId)) {
			for (People i : peopleArray) {
				if (i.getId().compareTo(clientId) == 0) {
					return i;
				}
			}
		}
		return null;
	}

	public static boolean insert(People people) {
		peopleArray.add(people);
		return true;
	}
	
	public static boolean removeFromList(String clientId) {
		if (TheaterShowList.canRemove(clientId)) {
			peopleArray.remove(search(clientId));
			ClientIdServer.remove(clientId);
			return true;
		} else {
			return false;
		}
	}
	
	public static void listPeople() {
		for (People i : peopleArray) {
			System.out.println(i);
		}
	}

	public static void writeObject(java.io.ObjectOutputStream output) {
		try {
			output.writeObject(peopleArray);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@SuppressWarnings("unchecked")
	public static void readObject(java.io.ObjectInputStream input) {
		try {
			peopleArray = (ArrayList<People>) input.readObject();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
