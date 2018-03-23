package com.mkyong.model;

import java.sql.Timestamp;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="cas_old_logs")
public class LogTable {

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
	
	@Column(name="cas_server_name")
	private String casServerName;
	
	@Column(name="application")
	private String application;
		
	@Column(name="ticketId")
	private String ticketId;
	
	@Column(name="logged_user")
	private String loggedUser ;
		
	@Column(name="logged_date")
	//private Date loggedDate;
	private Timestamp loggedDate;

	@Column(name="service_url")
	private String serviceURL;
	

	public String getCasServerName() {
		return casServerName;
	}

	public void setCasServerName(String casServerName) {
		this.casServerName = casServerName;
	}

	public String getApplication() {
		return application;
	}

	public void setApplication(String application) {
		this.application = application;
	}

	public String getServiceURL() {
		return serviceURL;
	}

	public void setServiceURL(String serviceURL) {
		this.serviceURL = serviceURL;
	}

	public String getTicketId() {
		return ticketId;
	}

	public void setTicketId(String ticketId) {
		this.ticketId = ticketId;
	}

	public String getLoggedUser() {
		return loggedUser;
	}

	public void setLoggedUser(String loggedUser) {
		this.loggedUser = loggedUser;
	}

	public Timestamp getLoggedDate() {
		return loggedDate;
	}

	public void setLoggedDate(Timestamp loggedDate) {
		this.loggedDate = loggedDate;
	}

	public LogTable() {
		
	}
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	

	@Override
	public String toString() {
		return "LogTable [id=" + id + ", casServerName=" + casServerName
				+ ", application=" + application + ", ticketId=" + ticketId
				+ ", loggedUser=" + loggedUser + ", loggedDate=" + loggedDate
				+ ", serviceURL=" + serviceURL + "]";
	}
}
