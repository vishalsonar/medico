package com.sonar.vishal.medico.common.pojo;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "log")
public class Log implements Serializable {

	private static final long serialVersionUID = 9204700573829870997L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	@Column(name = "component", nullable = false, length = 20)
	private String component;
	@Column(name = "userid", nullable = true, length = 10)
	private String userId;
	@Column(name = "ip", nullable = false, length = 20)
	private String ip;
	@Column(name = "datetime", nullable = false, length = 20)
	private String dateTime;
	@Column(name = "severity", nullable = false, length = 20)
	private String severity;
	@Column(name = "message", nullable = false, length = 500)
	private String message;
	@Column(name = "classname", nullable = false, length = 100)
	private String className;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getComponent() {
		return component;
	}

	public void setComponent(String component) {
		this.component = component;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	public String getDateTime() {
		return dateTime;
	}

	public void setDateTime(String dateTime) {
		this.dateTime = dateTime;
	}

	public String getSeverity() {
		return severity;
	}

	public void setSeverity(String severity) {
		this.severity = severity;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getClassName() {
		return className;
	}

	public void setClassName(String className) {
		this.className = className;
	}

}
