package edu.metrostate.ics372.Group5;

import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Scanner;

//Isiah, 2,3,4,10,11
public class TheaterShow implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String name,cId;
	private Calendar start;
	private Calendar end;
	private static SimpleDateFormat myFormatObj = new SimpleDateFormat("dd-MM-yyyy");
	
	public TheaterShow(String id, String name, Calendar startDate, Calendar endDate) {
		this.cId = id;
		this.name = name;
		this.start = startDate;
		this.end = endDate;
	}

	public String getcId() {
		return cId;
	}

	public String getName() {
		return name;
	}

	public Calendar getStart() {
		return start;
	}

	public Calendar getEnd() {
		return end;
	}

	public void setcId(String cId) {
		this.cId = cId;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setStart(Calendar start) {
		this.start = start;
	}

	public void setEnd(Calendar end) {
		this.end = end;
	}
	


}