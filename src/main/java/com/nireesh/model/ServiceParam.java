package com.mkyong.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
//@Table(name="st_cas_registered_services")
@Table(name="RegisteredServiceImpl")
public class ServiceParam {
	

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
	
	@Column(name="description")
	private String description;
	
	@Column(name="evaluation_order")
	private int evaluationOrder;
	
	@Column(name="status")
	private String status;

	@Column(name="service_manager")
	private String serviceMgr;

	@Column(name="language")
	private String language;
	
	@Column(name="server")
	private String server;
	

	@Column(name="logo")
	private String logo;
	
	@Column(name="logout_type")
	private int logoutType;
	
	@Column(name="logout_url")
	private String logoutUrl ;
		
	@Column(name="name")
	String name;
	
	@Column(name="serviceId")
	private String service;
	
	@Column(name="theme")
	private String theme;
	
	@Column(name = "requestor", nullable = false)
	String requestor;
    
    @Column(name = "domain")
	String domain;
    
	@Column(name = "remedy")
	String remedy;
	
    @Column(name = "comments")
	String comments;
    
    @Column(name = "created_date")
	Date createdDate;
    
    @Column(name = "created_by")
	String createdBy;
    
    @Column(name = "last_updated_date")
	Date lastUpdatedDate;    
    	
	
	
	  public int getId() {
			return id;
		}

		public void setId(int id) {
			this.id = id;
		}

		public String getRequestor() {
			return requestor;
		}

		public void setRequestor(String requestor) {
			this.requestor = requestor;
		}

		public String getDomain() {
			return domain;
		}

		public void setDomain(String domain) {
			this.domain = domain;
		}

		public String getRemedy() {
			return remedy;
		}

		public void setRemedy(String remedy) {
			this.remedy = remedy;
		}

		public String getComments() {
			return comments;
		}

		public void setComments(String comments) {
			this.comments = comments;
		}

		public Date getCreatedDate() {
			return createdDate;
		}

		public void setCreatedDate(Date createdDate) {
			this.createdDate = createdDate;
		}

		public String getCreatedBy() {
			return createdBy;
		}

		public void setCreatedBy(String createdBy) {
			this.createdBy = createdBy;
		}

		public Date getLastUpdatedDate() {
			return lastUpdatedDate;
		}

		public void setLastUpdatedDate(Date lastUpdatedDate) {
			this.lastUpdatedDate = lastUpdatedDate;
		}


		public String getDescription() {
			return description;
		}

		public void setDescription(String description) {
			this.description = description;
		}

		public int getEvaluationOrder() {
			return evaluationOrder;
		}

		public void setEvaluationOrder(int evaluationOrder) {
			this.evaluationOrder = evaluationOrder;
		}

		public String getService() {
			return service;
		}

		public void setService(String service) {
			this.service = service;
		}


		public String getLogo() {
			return logo;
		}

		public void setLogo(String logo) {
			this.logo = logo;
		}

		public int getLogoutType() {
			return logoutType;
		}

		public void setLogoutType(int logoutType) {
			this.logoutType = logoutType;
		}

		public ServiceParam() {
			
		}

		public String getLogoutUrl() {
			return logoutUrl;
		}

		public void setLogoutUrl(String logoutUrl) {
			this.logoutUrl = logoutUrl;
		}


		public String getTheme() {
			return theme;
		}

		public void setTheme(String theme) {
			this.theme = theme;
		}

	
	    public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}

		public String getStatus() {
			return status;
		}

		public void setStatus(String status) {
			this.status = status;
		}
		
		public String getServiceMgr() {
			return serviceMgr;
		}

		public void setServiceMgr(String serviceMgr) {
			this.serviceMgr = serviceMgr;
		}
		
		public String getLanguage() {
			return language;
		}

		public void setLanguage(String language) {
			this.language = language;
		}

		public String getServer() {
			return server;
		}

		public void setServer(String server) {
			this.server = server;
		}

		
		@Override
		public String toString() {
			return "ServiceParam [id=" + id + ", description=" + description
					+ ", evaluationOrder=" + evaluationOrder + ", status="
					+ status + ", serviceMgr=" + serviceMgr + ", logo=" + logo
					+ ", logoutType=" + logoutType + ", logoutUrl=" + logoutUrl
					+ ", name=" + name + ", service=" + service + ", theme="
					+ theme + ", requestor=" + requestor + ", domain=" + domain
					+ ", remedy=" + remedy + ", comments=" + comments
					+ ", createdDate=" + createdDate + ", createdBy="
					+ createdBy + ", lastUpdatedDate=" + lastUpdatedDate + "]";
		}
		
		
	}
		
