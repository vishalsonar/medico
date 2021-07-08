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
@Table(name = "patient")
public class Patient implements Serializable {

	private static final long serialVersionUID = -1239804934180253107L;

	@Id
	@PrimaryKeyJoinColumn
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id")
	private int id;
	@Column(name = "patientname", nullable = true, length = 50)
	private String patientName;
	@Column(name = "phone", nullable = false, length = 15)
	private String phoneNumber;
	@Column(name = "doctorname", nullable = true, length = 50)
	private String doctorName;
	@OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER, orphanRemoval = true)
	private Address address;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}

	public String getDoctorName() {
		return doctorName;
	}

	public void setDoctorName(String doctorName) {
		this.doctorName = doctorName;
	}

	public String getPatientName() {
		return patientName;
	}

	public void setPatientName(String patientName) {
		this.patientName = patientName;
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
