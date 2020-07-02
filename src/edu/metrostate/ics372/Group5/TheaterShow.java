package edu.metrostate.ics372.Group5;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;

//Isiah, 2,3,4,10,11

public class TheaterShow {
	private int cId;
	private String name;
	private Date start;
	private Date end;
	private static SimpleDateFormat myFormatObj = new SimpleDateFormat("dd-MM-yyyy");
	private static ArrayList<TheaterShow> showArray = new ArrayList<TheaterShow>();
	private static Scanner scanner = new Scanner(System.in);

	private TheaterShow(int cId, String name, Date start, Date end) {
		this.cId = cId;
		this.name = name;
		this.start = start;
		this.end = end;
	}

	public static void listShows() {
		// Print each Show
		for (TheaterShow i : showArray) {
			System.out.println(i);
		}
	}

	public static void addShow() {
		// Asks the user for the name date and a valid
		// Need to close on exit
		System.out.print("Enter Show Name: ");
		String name = scanner.nextLine();

		System.out.print("Enter Show Client ID: ");
		int id = scanner.nextInt();

		boolean clientExist = false;
		for (Client i : Client.getClientArray()) {
			if (i.hashCode() == id) {
				clientExist = true;
			}
		}
		if (clientExist) {
			try {
				System.out.print("Enter Show Start Date (\"dd-MM-yyyy\"): ");
				String strDate = scanner.next();
				Date start = myFormatObj.parse(strDate);

				System.out.print("Enter Show End Date (\"dd-MM-yyyy\"): ");
				strDate = scanner.next();
				Date end = myFormatObj.parse(strDate);

				showArray.add(new TheaterShow(id, name, start, end));
			} catch (Exception e) {
				System.out.println("Error parsing date");
			}
		} else {
			System.out.println("Client does not exist");
		}

	}

	public static boolean canRemove(int id) {
		// Tests to see that the client can be removed and does not have any shows that
		// are open at this time.
		boolean output = true;
		Date currentDate = new Date();
		for (TheaterShow i : showArray) {
			if (i.cId == id && i.end.compareTo(currentDate) > 0) {
				output = false;
			}
		}
		return output;
	}
}