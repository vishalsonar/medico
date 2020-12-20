package com.sonar.vishal.medico.common.structure;

import com.google.gson.annotations.SerializedName;

public class Header {

	@SerializedName(value = "DateTime")
	private String dateTime;
	@SerializedName(value = "Function")
	private String function;
	@SerializedName(value = "Type")
	private String type;
	@SerializedName(value = "UUID")
	private String uuid;
	@SerializedName(value = "MAC")
	private String mac;
	@SerializedName(value = "Result")
	private String result;
	@SerializedName(value = "Message")
	private String message;

	public String getDateTime() {
		return dateTime;
	}

	public void setDateTime(String dateTime) {
		this.dateTime = dateTime;
	}

	public String getFunction() {
		return function;
	}

	public void setFunction(String function) {
		this.function = function;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getUuid() {
		return uuid;
	}

	public void setUuid(String uuid) {
		this.uuid = uuid;
	}

	public String getMac() {
		return mac;
	}

	public void setMac(String mac) {
		this.mac = mac;
	}

	public String getResult() {
		return result;
	}

	public void setResult(String result) {
		this.result = result;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

}
