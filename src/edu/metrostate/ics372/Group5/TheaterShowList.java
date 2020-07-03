package edu.metrostate.ics372.Group5;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;

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

	public static boolean addShow(String clientID, String name, Calendar start, Calendar end) {
		// Asks the user for the name date and a valid
		// Need to close on exit
		boolean output = false;
		if (ClientIdServer.contains(clientID)) {
			if (canAdd(start, end)) {
				showArray.add(new TheaterShow(clientID, name, start, end));
				output = true;
			}
		}
		return output;

	}

	public static void listShows() {
		// Print each Show
		for (TheaterShow i : showArray) {
			System.out.println(i);
		}
	}

	private static boolean canAdd(Calendar start, Calendar end) {
		boolean output = true;
		for (TheaterShow i : showArray) {
			if (i.getStart().compareTo(end) > 0 || i.getEnd().compareTo(start) < 0) {
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
			if (ClientIdServer.contains(clientId) && i.getEnd().compareTo(currentDate) > 0) {
				output = false;
			}
		}
		return output;
	}
}
