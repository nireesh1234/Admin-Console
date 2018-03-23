package com.mkyong.model;

import java.util.ArrayList;
import java.util.List;

public class ApplicationList {

	 private List<Application> applications = new ArrayList<Application>();

	public List<Application> getApplications() {
		return applications;
	}

	public void setApplications(List<Application> applications) {
		this.applications = applications;
	}

	public String toString() {
		return "ApplicationList [applications=" + applications + "]";
	}
	 
	 
}
