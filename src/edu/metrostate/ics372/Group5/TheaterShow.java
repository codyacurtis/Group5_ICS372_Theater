package edu.metrostate.ics372.Group5;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;

//Isiah, 2,3,4,10,11
public class TheaterShow implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String name, cId;
	private Date start;
	private Date end;
	private double price;
	SimpleDateFormat formatter = new SimpleDateFormat("MM/dd/yy");

	public TheaterShow(String id, String name, Date startDate, Date endDate, double price) {
		this.cId = id;
		this.name = name;
		this.start = startDate;
		this.end = endDate;
		this.price = price;
	}

	/**
	 * Getters and Setters
	 */
	public String getcId() {
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

	public double getPrice() {
		return price;
	}

	public void setcId(String cId) {
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

	@Override
	public String toString() {
		return "TheaterShow [name=" + name + ", cId=" + cId + ", price=$" + price + ", start=" + formatter.format(start)
				+ ", end=" + formatter.format(end) + "]";
	}

}