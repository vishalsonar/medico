package com.sonar.vishal.medico.common.pojo;

public class Password {

	private String oldPassword;
	private String newPassword;
	private String confirmPassword;

	public String getPassword() {
		return oldPassword;
	}

	public void setPassword(String password) {
		this.oldPassword = password;
	}

	public String getNewPassword() {
		return newPassword;
	}

	public void setNewPassword(String newPassword) {
		this.newPassword = newPassword;
	}

	public String getConfirmPassword() {
		return confirmPassword;
	}

	public void setConfirmPassword(String confirmPassword) {
		this.confirmPassword = confirmPassword;
	}

}
