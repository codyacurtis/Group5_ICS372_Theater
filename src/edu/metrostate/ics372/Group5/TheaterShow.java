package edu.metrostate.ics372.Group5;

import java.io.Serializable;
import java.util.Calendar;

//Isiah, 2,3,4,10,11
public class TheaterShow implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String cId;
	private String name;
	private Calendar start;
	private Calendar end;
	//private static SimpleDateFormat myFormatObj = new SimpleDateFormat("dd-MM-yyyy");
	
	public TheaterShow(String cId, String name, Calendar start, Calendar end) {
		this.cId = cId;
		this.name = name;
		this.start = start;
		this.end = end;
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