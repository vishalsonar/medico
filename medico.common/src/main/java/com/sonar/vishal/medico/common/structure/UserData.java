package com.sonar.vishal.medico.common.structure;

import com.google.gson.annotations.SerializedName;
import com.sonar.vishal.medico.common.pojo.User;

public class UserData extends Data {

	@SerializedName(value = "User")
	private User user;

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

}
