package com.mkyong.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Table;

@Entity
@Table(name="RegisteredServiceImpl")
@Inheritance(strategy=InheritanceType.TABLE_PER_CLASS)
public class CASRegisteredServices {

	@Id
	@Column(name="id")
	private int id;
	
	@Column(name="expression_type")
	private String expressionType;
	
	@Column(name="description")
	private String description;
	
	@Column(name="status")
	private String status;
	
	@Column(name="evaluation_order")
	private int evaluationOrder;
	
	@Column(name="name")
	private String name;
	
	@Column(name="serviceId")
	private String service;
	
	@Column(name="access_strategy")
	private long accessStrategy;
	
	@Column(name="attribute_release")
	private long attributeRelease;
	
	@Column(name="logo")
	private String logo;
	
	@Column(name="logout_type")
	private int logoutType;
	
	@Column(name="logout_url")
	private String logoutUrl ; 
	
	@Column(name="proxy_policy")
	private long proxyPolicy;
	
	@Column(name="public_key")
	private long publicKey;
	
	@Column(name="required_handlers")
	private long reqHeaders;
	
	@Column(name="theme")
	private String theme;
	
	@Column(name="username_attr")
	private long userNameAttr;
	
		
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getExpressionType() {
		return expressionType;
	}

	public void setExpressionType(String expressionType) {
		this.expressionType = expressionType;
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

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getService() {
		return service;
	}

	public void setService(String service) {
		this.service = service;
	}

	public long getAccessStrategy() {
		return accessStrategy;
	}

	public void setAccessStrategy(long accessStrategy) {
		this.accessStrategy = accessStrategy;
	}

	public long getAttributeRelease() {
		return attributeRelease;
	}

	public void setAttributeRelease(long attributeRelease) {
		this.attributeRelease = attributeRelease;
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

	public String getLogoutUrl() {
		return logoutUrl;
	}

	public void setLogoutUrl(String logoutUrl) {
		this.logoutUrl = logoutUrl;
	}

	public long getProxyPolicy() {
		return proxyPolicy;
	}

	public void setProxyPolicy(long proxyPolicy) {
		this.proxyPolicy = proxyPolicy;
	}

	public long getPublicKey() {
		return publicKey;
	}

	public void setPublicKey(long publicKey) {
		this.publicKey = publicKey;
	}

	public long getReqHeaders() {
		return reqHeaders;
	}

	public void setReqHeaders(long reqHeaders) {
		this.reqHeaders = reqHeaders;
	}

	public String getTheme() {
		return theme;
	}

	public void setTheme(String theme) {
		this.theme = theme;
	}

	public long getUserNameAttr() {
		return userNameAttr;
	}

	public void setUserNameAttr(long userNameAttr) {
		this.userNameAttr = userNameAttr;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
	
	
	public CASRegisteredServices() {

	}

	@Override
	public String toString() {
		return "CASRegisteredServices [id=" + id + ", expressionType="
				+ expressionType + ", description=" + description
				+ ", evaluationOrder=" + evaluationOrder + ", name=" + name
				+ ", service=" + service + ", accessStrategy=" + accessStrategy
				+ ", attributeRelease=" + attributeRelease + ", logo=" + logo
				+ ", logoutType=" + logoutType + ", logoutUrl=" + logoutUrl
				+ ", proxyPolicy=" + proxyPolicy + ", publicKey=" + publicKey
				+ ", reqHeaders=" + reqHeaders + ", theme=" + theme
				+ ", userNameAttr=" + userNameAttr + "]";
	}
	
	
}
