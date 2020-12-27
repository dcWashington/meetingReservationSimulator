package com.washingtonmartins;

import java.util.Date;

public class Meeting {

	private int id;
	private String subject;
	private Date startDay;   //using date and time here
	private String priority;
//	private Date endDay;
//	private String endTime;
	private static int idNumber = 1;

	public Meeting(int id, String subject, Date startDay, 
			Date endDay, String startTime, String endTime,
			String priority, boolean cancelable) {
		super();
		this.id = id;
		this.subject = subject;
		this.startDay = startDay;
		this.priority = priority;
		this.setId(idNumber++);
	}

	public Meeting() {
		this.setId(idNumber++);
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public Date getStartDay() {
		return startDay;
	}

	public void setStartDay(Date startDay) {
		this.startDay = startDay;
	}

	public String getPriority() {
		return priority;
	}

	public void setPriority(String priority) {
		this.priority = priority;
	}

	@Override
	public String toString() {
		return "\nMeeting [id=" + id + ", subject=" + subject 
				+ ", startDay=" + startDay + "]\n";
	}
}