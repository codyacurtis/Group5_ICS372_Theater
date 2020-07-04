package edu.metrostate.ics372.Group5;

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class TheaterShowList implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static ArrayList<TheaterShow> showArray = new ArrayList<TheaterShow>();
	private static TheaterShowList theaterShowList;

	private TheaterShowList() {
	}

	public static TheaterShowList instance() {
		if (theaterShowList == null) {
			return (theaterShowList = new TheaterShowList());
		} else {
			return theaterShowList;
		}
	}

	public static void addShow(String id, String name, Calendar startDate, Calendar endDate) {
		// Asks the user for the name date and a valid
		// Need to close on exit
		if (ClientList.contains(id)) {
			if (canAdd(startDate, endDate)) {
				showArray.add(new TheaterShow(id, name, startDate, endDate));
			} else {
				System.out.println("Can't add show, overlapping times");
			}
		} else {
			System.out.println("Client does not exist");
		}

	}

	public static void listShows() {
		// Print each Show
		for (TheaterShow i : showArray) {
			System.out.println(i);
		}
	}

	private static boolean canAdd(Calendar startDate, Calendar endDate) {
		boolean output = true;
		for (TheaterShow i : showArray) {
			if (i.getStart().compareTo(endDate) > 0 || i.getEnd().compareTo(startDate) < 0) {
				output = false;
			}
		}
		return output;
	}

	public static boolean canRemove(String clientId) {
		// Tests to see that the client can be removed and does not have any shows that
		// are open at this time.
		boolean output = true;
		Calendar currentDate = Calendar.getInstance();
		for (TheaterShow i : showArray) {
			if (ClientList.contains(i.getcId()) && i.getEnd().compareTo(currentDate) > 0) {
				output = false;
			}
		}
		return output;
	}

	public static void writeObject(java.io.ObjectOutputStream output) {
		try {
			output.writeObject(showArray);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	// read object from file work around
	@SuppressWarnings("unchecked")
	public static void readObject(java.io.ObjectInputStream input) {
		try {
			showArray = (ArrayList<TheaterShow>) input.readObject();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
