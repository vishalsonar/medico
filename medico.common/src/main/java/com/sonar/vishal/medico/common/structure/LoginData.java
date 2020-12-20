package com.sonar.vishal.medico.common.structure;

import com.google.gson.annotations.SerializedName;

public class LoginData extends Data {

	@SerializedName(value = "username")
	private String userName;
	@SerializedName(value = "password")
	private String password;

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

}
