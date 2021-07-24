package com.sonar.vishal.ui.listener;

import java.security.Key;
import java.util.Base64;

import javax.crypto.spec.SecretKeySpec;

import com.sonar.vishal.MedicoUI;
import com.sonar.vishal.medico.common.message.common.Constant;
import com.sonar.vishal.medico.common.rest.Backend;
import com.sonar.vishal.medico.common.rest.RestBackend;
import com.sonar.vishal.medico.common.structure.KeyData;
import com.sonar.vishal.medico.common.structure.LoginData;
import com.sonar.vishal.medico.common.structure.UserData;
import com.sonar.vishal.medico.common.util.LoggerApi;
import com.sonar.vishal.ui.component.Component;
import com.sonar.vishal.ui.exception.MedicoValidationException;
import com.sonar.vishal.ui.util.UIConstant;
import com.sonar.vishal.ui.validator.LoginDataValidator;
import com.vaadin.data.Binder;
import com.vaadin.server.Page;
import com.vaadin.server.VaadinSession;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.Notification;

public class LoginListener implements ClickListener {

	private static final long serialVersionUID = -1992503875386942892L;
	private Binder<LoginData> binder;
	private transient LoginData data;
	private transient RestBackend backend;

	public LoginListener(Binder<LoginData> binder, LoginData data) {
		this.binder = binder;
		this.data = data;
		this.backend = new RestBackend(Constant.GET_KEY);
		Key key = getKey();
		this.backend.setKey(key);
	}
	
	private Key getKey() {
		Key key = null;
		try {
			KeyData keyData = (KeyData) this.backend.doPostRespondData(KeyData.class);
			byte[] decodedKey = Base64.getDecoder().decode(keyData.getKey());
			key = new SecretKeySpec(decodedKey, 0, decodedKey.length, UIConstant.AES);
		} catch (Exception e) {
			Component.getInstance().getServerFailureNotification(UIConstant.SERVER_INITIALIZE_FAILED).show(Page.getCurrent());
		}
		return key;
	}

	@Override
	public void buttonClick(ClickEvent event) {
		Notification notification = null;
		try {
			binder.writeBean(data);
			new LoginDataValidator().doValidation(data);
			this.backend = new RestBackend(Constant.LOGIN);
			Backend.message.setData(data);
			UserData userData = (UserData) this.backend.doPostRespondData(UserData.class);
			if (userData != null && userData.getUser() != null && userData.getUser().getRole() != null) {
				LoggerApi.setUserId(String.valueOf(userData.getUser().getId()));
				notification = Component.getInstance().getSuccessNotification(Constant.SUCCESS, UIConstant.LOGGED_IN_SUCCESS);
				VaadinSession.getCurrent().getSession().setAttribute(UIConstant.S_USER, userData.getUser());
				MedicoUI.getNavigatorUI().navigateTo(UIConstant.OPTION_PAGE);
			} else {
				notification = Component.getInstance().getFailureNotification(Constant.ERROR, Constant.LOGIN_FAILURE_MESSAGE);
			}
		} catch(MedicoValidationException e) {
			notification = Component.getInstance().getFailureNotification(Constant.ERROR, e.getMessage());
		} catch (Exception e) {
			LoggerApi.error(getClass().getName(), e.getMessage());
			notification = Component.getInstance().getFailureNotification(Constant.ERROR, Constant.LOGIN_FAILURE_MESSAGE);
		}
		notification.show(Page.getCurrent());
	}

}
