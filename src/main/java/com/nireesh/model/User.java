package com.mkyong.model;

import com.fasterxml.jackson.annotation.JsonView;
import com.mkyong.web.jsonview.Views;

public class User {


	String name;	
	String serviceId;
	String requestor;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getServiceId() {
		return serviceId;
	}

	public void setServiceId(String serviceId) {
		this.serviceId = serviceId;
	}

	public String getRequestor() {
		return requestor;
	}

	public void setRequestor(String requestor) {
		this.requestor = requestor;
	}

	public User() {
	}

	public User(String name, String serviceId, String requestor ) {
		super();
		this.name = name;
		this.serviceId = serviceId;
		this.requestor = requestor;
	}

	
	@Override
	public String toString() {
		return "User [name=" + name + ", serviceId=" + serviceId
				+ ", requestor=" + requestor + "]";
	}

}
