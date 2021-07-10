package com.sonar.vishal.medico.common.pojo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "product")
public class Product {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private int id;
	@Column(name = "description", nullable = false, length = 50)
	private String description;
	@Column(name = "pack", nullable = false, length = 10)
	private String pack;
	@Column(name = "hsncode", nullable = false, length = 10)
	private String hsnCode;
	@Column(name = "lsq", nullable = true, length = 10)
	private String lsq;
	@Column(name = "quantity", nullable = false, length = 10)
	private String quantity;
	@Column(name = "batchnumber", nullable = false, length = 10)
	private String batchNumber;
	@Column(name = "expirydate", nullable = false, length = 10)
	private String expiryDate;
	@Column(name = "mrp", nullable = false, length = 10)
	private String mrp;
	@Column(name = "rate", nullable = false, length = 10)
	private String rate;
	@Column(name = "gst", nullable = false, length = 10)
	private String gst;
	@Column(name = "amount", nullable = false, length = 10)
	private String amount;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getPack() {
		return pack;
	}

	public void setPack(String pack) {
		this.pack = pack;
	}

	public String getHsnCode() {
		return hsnCode;
	}

	public void setHsnCode(String hsnCode) {
		this.hsnCode = hsnCode;
	}

	public String getLsq() {
		return lsq;
	}

	public void setLsq(String lsq) {
		this.lsq = lsq;
	}

	public String getQuantity() {
		return quantity;
	}

	public void setQuantity(String quantity) {
		this.quantity = quantity;
	}

	public String getBatchNumber() {
		return batchNumber;
	}

	public void setBatchNumber(String batchNumber) {
		this.batchNumber = batchNumber;
	}

	public String getExpiryDate() {
		return expiryDate;
	}

	public void setExpiryDate(String expiryDate) {
		this.expiryDate = expiryDate;
	}

	public String getMrp() {
		return mrp;
	}

	public void setMrp(String mrp) {
		this.mrp = mrp;
	}

	public String getRate() {
		return rate;
	}

	public void setRate(String rate) {
		this.rate = rate;
	}

	public String getGst() {
		return gst;
	}

	public void setGst(String gst) {
		this.gst = gst;
	}

	public String getAmount() {
		return amount;
	}

	public void setAmount(String amount) {
		this.amount = amount;
	}

}
