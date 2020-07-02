package edu.metrostate.ics372.Group5;

import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;

//Isiah, 2,3,4,10,11
public class TheaterShow implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int cId;
	private String name;
	private Date start;
	private Date end;
	private static SimpleDateFormat myFormatObj = new SimpleDateFormat("dd-MM-yyyy");
	
	public TheaterShow(int cId, String name, Date start, Date end) {
		this.cId = cId;
		this.name = name;
		this.start = start;
		this.end = end;
	}

	public int getcId() {
		return cId;
	}

	public String getName() {
		return name;
	}

	public Date getStart() {
		return start;
	}

	public Date getEnd() {
		return end;
	}

	public void setcId(int cId) {
		this.cId = cId;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setStart(Date start) {
		this.start = start;
	}

	public void setEnd(Date end) {
		this.end = end;
	}
	


}