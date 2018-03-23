package com.mkyong.model;

import java.io.Serializable;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;

public class SearchCriteria implements Serializable{
		
		int id;
		
		@NotEmpty @Size(min=5, max=100)
		String name;
		@NotEmpty @Size(min=5, max=250)
		String serviceId;
		@Email @NotEmpty
		private String requestor;
		@NotEmpty @Size(min=5, max=250)
		String domain;
		String remedy;
		@NotEmpty @Size(min=5, max=250)
		String comments;
		@NotEmpty @Email
		private String serviceMgr;
		
		String prglanguage;		
		String serverdetails;
		/*String className;
		String createdDate;*/
			
		public int getId() {
			return id;
		}

/*		public void setId(int id) {
			this.id = id;
		}*/
		
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

		public String getServiceMgr() {
			return serviceMgr;
		}

		public void setServiceMgr(String serviceMgr) {
			this.serviceMgr = serviceMgr;
		}

		public String getPrglanguage() {
			return prglanguage;
		}

		public void setPrglanguage(String prglanguage) {
			this.prglanguage = prglanguage;
		}

		public String getServerdetails() {
			return serverdetails;
		}

		public void setServerdetails(String serverdetails) {
			this.serverdetails = serverdetails;
		}

		
		@Override
		public String toString() {
			return "SearchCriteria [id=" + id + ", name=" + name
					+ ", serviceId=" + serviceId + ", requestor=" + requestor
					+ ", domain=" + domain + ", remedy=" + remedy
					+ ", comments=" + comments + ", serviceMgr=" + serviceMgr
					+ ", prglanguage=" + prglanguage + ", serverdetails="
					+ serverdetails + "]";
		}
		
	}

