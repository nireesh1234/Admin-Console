package com.mkyong.model;

import java.sql.Timestamp;

public class LogObjectModel {

	private String application;
	private String startDate;
	private String endDate;
	
	public LogObjectModel() {
		
	}

	@Override
	public String toString() {
		return "LogObjectModel [application=" + application + ", startDate="
				+ startDate + ", endDate=" + endDate + "]";
	}

	public String getApplication() {
		return application;
	}

	public void setApplication(String application) {
		this.application = application;
	}

	public String getStartDate() {
		return startDate;
	}

	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}

	public String getEndDate() {
		return endDate;
	}

	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}

	
	
	
}
