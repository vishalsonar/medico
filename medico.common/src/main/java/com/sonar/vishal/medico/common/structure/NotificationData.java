package com.sonar.vishal.medico.common.structure;

import com.google.gson.annotations.SerializedName;
import com.sonar.vishal.medico.common.pojo.Notification;

public class NotificationData extends Data {

	@SerializedName(value = "Notification")
	private Notification notification;

	public Notification getNotification() {
		return notification;
	}

	public void setNotification(Notification notification) {
		this.notification = notification;
	}

}
