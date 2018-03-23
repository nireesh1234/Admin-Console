package com.mkyong.model;

import java.sql.Date;

import org.springframework.stereotype.Component;

import com.fasterxml.jackson.annotation.JsonView;

public class Application {
	
	int id;
	String name;	
	String serviceId;	
	String created_date;	
	String status;	
	String remedy ;	
	String description ;	
	String organisation;	
	String serviceManager ;	
	String requestor ;
	
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
	public String getCreated_date() {
		return created_date;
	}
	public void setCreated_date(String created_date) {
		this.created_date = created_date;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getRemedy() {
		return remedy;
	}
	
	public void setRemedy(String remedy) {
		this.remedy = remedy;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getOrganisation() {
		return organisation;
	}
	public void setOrganisation(String organisation) {
		this.organisation = organisation;
	}
	public String getServiceManager() {
		return serviceManager;
	}
	public void setServiceManager(String serviceManager) {
		this.serviceManager = serviceManager;
	}
	public String getRequestor() {
		return requestor;
	}
	public void setRequestor(String requestor) {
		this.requestor = requestor;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}

	@Override
	public String toString() {
		return "Application [id=" + id + ", name=" + name + ", serviceId="
				+ serviceId + ", created_date=" + created_date + ", status="
				+ status + ", remedy=" + remedy + ", description="
				+ description + ", organisation=" + organisation
				+ ", serviceManager=" + serviceManager + ", requestor="
				+ requestor + "]";
	}

}
