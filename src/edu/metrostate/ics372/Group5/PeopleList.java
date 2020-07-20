package edu.metrostate.ics372.Group5;

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;

/**
 * This is a collection class of object people
 *
 */
public class PeopleList implements Serializable {

    private static final long serialVersionUID = 1L;
    private static PeopleList peopleList;
    private static ArrayList<People> peopleArray = new ArrayList<People>();

    /**
     * Returns the only instance of the class.
     * 
     * @return the instance
     */
    public static PeopleList instance() {
	if (peopleList != null) {
	    peopleList = new PeopleList();
	}
	return peopleList;
    }

    /**
     * searches for a person in IDserver
     * 
     * @param clientId target
     * @return people object if found
     */
    public static People search(String clientId) {
	if (IdServer.contains(clientId)) {
	    for (People i : peopleArray) {
		if (i.getId().compareTo(clientId) == 0) {
		    return i;
		}
	    }
	}
	return null;
    }

    /**
     * insert new people into peopleArray
     * 
     * @param people person to be added
     * @return true if added
     */
    public static boolean insert(People people) {
	peopleArray.add(people);
	return true;
    }

    /**
     * remove people from list
     * 
     * @param clientId target
     * @return true if removed
     */
    public static boolean removeFromList(String clientId) {
	if (TheaterShowList.canRemove(clientId)) {
	    peopleArray.remove(search(clientId));
	    IdServer.remove(clientId);
	    return true;
	} else {
	    return false;
	}
    }

    /**
     * list everyone in the collection
     * 
     * @param type the object
     */
    public static void listPeople(Object type) {
	if (peopleArray.isEmpty()) {
	    System.out.println("Empty List");
	} else {
	    for (People i : peopleArray) {
		if (i.getClass().equals(type))
		    System.out.println(i.toString());
	    }
	}
    }

    /**
     * saves object to file
     * 
     * @param output file to save to
     */
    public static void writeObject(java.io.ObjectOutputStream output) {
	try {
	    output.writeObject(peopleArray);
	} catch (IOException e) {
	    // TODO Auto-generated catch block
	    e.printStackTrace();
	}
    }

    /**
     * reads object from file
     * 
     * @param input file to be read from
     */
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

    /**
     * checks if person is already in the collection class
     * 
     * @param newPerson target
     * @return true if person in the collection
     */
    public static boolean personExists(People newPerson) {
	for (People person : peopleArray) {
	    if (person.getClass().equals(newPerson.getClass())) {
		if (person.getName().compareToIgnoreCase(newPerson.getName()) == 0
			&& person.getAddress().compareToIgnoreCase(newPerson.getAddress()) == 0
			&& person.getPhoneNumber().compareToIgnoreCase(newPerson.getPhoneNumber()) == 0) {
		    return true;
		}
	    }
	}
	return false;
    }

    /**
     * check if credit card is already in the collection
     * 
     * @param creditCard target
     * @return customer if found
     */
    public static Customer customerCreditCard(String creditCard) {
	for (People person : peopleArray) {
	    if (person.getClass().equals(Customer.class)) {
		if (((Customer) person).checkCreditCardDuplicate(creditCard))
		    return (Customer) person;
	    }
	}
	return null;
    }
}
