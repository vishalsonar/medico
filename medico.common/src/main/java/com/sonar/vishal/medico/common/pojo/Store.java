package com.sonar.vishal.medico.common.pojo;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

@Entity
@Table(name = "store")
public class Store implements Serializable {

	private static final long serialVersionUID = -6390761219157275455L;

	@Id
	@PrimaryKeyJoinColumn
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id")
	private int id;
	@Column(name = "name", nullable = false, length = 50)
	private String name;
	@OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true)
	private Address address;
	@Column(name = "phone", nullable = false, length = 15)
	private String phoneNumber;
	@Column(name = "dl", nullable = false, length = 10)
	private String dlNumber;
	@Column(name = "dlexpiry", nullable = false, length = 10)
	private String dlExpiry;
	@Column(name = "sxdl", nullable = true, length = 10)
	private String sxDlNumber;
	@Column(name = "sxdlexpiry", nullable = true, length = 10)
	private String sxDlExpiry;
	@Column(name = "pan", nullable = false, length = 10)
	private String panNumber;
	@Column(name = "cin", nullable = false, length = 10)
	private String cinNumber;
	@Column(name = "gstin", nullable = false, length = 20)
	private String gstin;
	@Column(name = "fassai", nullable = true, length = 10)
	private String fassaiNumber;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getDlNumber() {
		return dlNumber;
	}

	public void setDlNumber(String dlNumber) {
		this.dlNumber = dlNumber;
	}

	public String getDlExpiry() {
		return dlExpiry;
	}

	public void setDlExpiry(String dlExpiry) {
		this.dlExpiry = dlExpiry;
	}

	public String getSxDlNumber() {
		return sxDlNumber;
	}

	public void setSxDlNumber(String sxDlNumber) {
		this.sxDlNumber = sxDlNumber;
	}

	public String getSxDlExpiry() {
		return sxDlExpiry;
	}

	public void setSxDlExpiry(String sxDlExpiry) {
		this.sxDlExpiry = sxDlExpiry;
	}

	public String getPanNumber() {
		return panNumber;
	}

	public void setPanNumber(String panNumber) {
		this.panNumber = panNumber;
	}

	public String getCinNumber() {
		return cinNumber;
	}

	public void setCinNumber(String cinNumber) {
		this.cinNumber = cinNumber;
	}

	public String getGstin() {
		return gstin;
	}

	public void setGstin(String gstin) {
		this.gstin = gstin;
	}

	public String getFassaiNumber() {
		return fassaiNumber;
	}

	public void setFassaiNumber(String fassaiNumber) {
		this.fassaiNumber = fassaiNumber;
	}
	
	public String getAddressString() {
		String seperator = ", ";
		StringBuilder builder = new StringBuilder();
		builder.append(address.getLine1()).append(seperator).append(address.getLine2()).append(seperator)
			   .append(address.getCity()).append(seperator).append(address.getPinCode()).append(seperator)
			   .append(address.getState());
		return builder.toString();
	}

}
