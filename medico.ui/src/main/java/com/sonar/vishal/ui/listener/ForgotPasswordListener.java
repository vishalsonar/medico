package com.sonar.vishal.ui.listener;

import com.sonar.vishal.MedicoUI;
import com.sonar.vishal.medico.common.message.common.Constant;
import com.sonar.vishal.medico.common.pojo.Notification;
import com.sonar.vishal.medico.common.rest.Backend;
import com.sonar.vishal.medico.common.rest.RestBackend;
import com.sonar.vishal.medico.common.structure.LoginData;
import com.sonar.vishal.medico.common.structure.NotificationData;
import com.sonar.vishal.medico.common.util.LoggerApi;
import com.sonar.vishal.ui.component.Component;
import com.sonar.vishal.ui.exception.MedicoValidationException;
import com.sonar.vishal.ui.util.UIConstant;
import com.sonar.vishal.ui.util.UIUtil;
import com.sonar.vishal.ui.validator.ForgotPasswordValidator;
import com.vaadin.data.Binder;
import com.vaadin.server.Page;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.TextField;

public class ForgotPasswordListener implements ClickListener {

	private static final long serialVersionUID = -4879241403529463453L;
	private transient RestBackend backend;
	private Binder<LoginData> binder;
	private TextField userName;

	public ForgotPasswordListener(Binder<LoginData> binder, TextField userName) {
		this.binder = binder;
		this.userName = userName;
		this.backend = new RestBackend(Constant.ADD_NOTIFICATION);
	}

	@Override
	public void buttonClick(ClickEvent event) {
		com.vaadin.ui.Notification notification = null;
		try {
			LoginData data = new LoginData();
			binder.writeBean(data);
			new ForgotPasswordValidator().doValidation(data);
			Notification notificationInput = new Notification();
			notificationInput.setMessage(UIUtil.getMessageDateTime() + UIConstant.COLON_COLON + UIConstant.PASSWORD_RESET_REQUIRED + data.getUserName());
			NotificationData notificationData = new NotificationData();
			notificationData.setNotification(notificationInput);
			Backend.message.setData(notificationData);
			backend.doPostRespondHeader();
			notification = Component.getInstance().getSuccessNotification(Constant.SUCCESS, UIConstant.RESET_PASSWORD_SUBMIT);
			userName.setValue(UIConstant.EMPTY);
			MedicoUI.getNavigatorUI().navigateTo(UIConstant.EMPTY);
		} catch (MedicoValidationException e) {
			notification = Component.getInstance().getFailureNotification(Constant.ERROR, e.getMessage());
		} catch (Exception e) {
			LoggerApi.error(getClass().getName(), e.getMessage());
			notification = Component.getInstance().getFailureNotification(Constant.ERROR, Constant.INVALID_USER_NAME);
		}
		notification.show(Page.getCurrent());
	}

}
