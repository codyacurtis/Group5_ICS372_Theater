package edu.metrostate.ics372.Group5;

import java.io.Serializable;
import java.util.ArrayList;
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
	
	public static void addShow( int id,String name, Date start, Date end) {
		// Asks the user for the name date and a valid
		// Need to close on exit
		if (ClientList.search(id) != null) {
			if(canAdd(start,end)) {
				showArray.add(new TheaterShow(id,name,start,end));
			}else {
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


	private static boolean canAdd(Date start, Date end) {
		boolean output = true;
		for(TheaterShow i : showArray) {
			if(i.getStart().compareTo(end) > 0 || i.getEnd().compareTo(start) < 0) {
				output = false;
			}
		}
		return output;
	}

	public static boolean canRemove(int id) {
		// Tests to see that the client can be removed and does not have any shows that
		// are open at this time.
		boolean output = true;
		Date currentDate = new Date();
		for (TheaterShow i : showArray) {
			if (i.getcId() == id && i.getEnd().compareTo(currentDate) > 0) {
				output = false;
			}
		}
		return output;
	}
}
