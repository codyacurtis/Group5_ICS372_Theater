package edu.metrostate.ics372.Group5;

import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.Random;
import java.util.Scanner;

//Isiah, 2,3,4,10,11
public class Client {
	private Random rand = new Random();
	private String name, adress, phoneNumber;
	private int id;
	private double balance;
	private static Scanner scanner = new Scanner(System.in);

	private static ArrayList<Client> clientArray = new ArrayList<Client>();

	private Client(String name, String adress, String phoneNumber) {
		this.name = name;
		this.adress = adress;
		this.phoneNumber = phoneNumber;
		balance = 0;
		// this is to make sure that there are not dublicates
		int sId = (int) (rand.nextDouble() * 1000000);
		while (clientArray.contains(sId)) {
			sId = (int) (rand.nextDouble() * 1000000);
		}
		this.id = sId;
		System.out.println("New Client ID: " + id);

	}

	public static void addClient() {
		System.out.print("Enter Name:");
		String name = scanner.nextLine();
		System.out.print("Enter Adress:");
		String adress = scanner.nextLine();
		System.out.print("Enter Phone Number:");
		String phoneNumber = scanner.nextLine();

		clientArray.add(new Client(name, adress, phoneNumber));
		System.out.println();
	}

	public static void removeClient() {
		// Checks if the client can be removed and if they can they it will
		System.out.print("Enter Client ID:");
		int cId = scanner.nextInt();
		if (TheaterShow.canRemove(cId)) {
			clientArray.remove(clientArray.indexOf(cId));
		} else {
			System.out.println("Unable to Remove Client");
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

	private int getId() {
		return id;
	}

	@Override
	public boolean equals(Object obj) {
		if (obj == null) {
			return false;
		}
		if (!(obj instanceof Client)) {
			return false;
		}
		if (obj == this) {
			return true;
		}
		return this.getId() == ((Client) obj).getId();
	}

	@Override
	public int hashCode() {
		return this.id;
	}

	@Override
	public String toString() {
		return "Client [name=" + name + ", id=" + id + "]";
	}

}
