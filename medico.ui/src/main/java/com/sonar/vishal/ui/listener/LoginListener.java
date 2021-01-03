package com.sonar.vishal.ui.listener;

import java.security.Key;
import java.util.Base64;

import javax.crypto.spec.SecretKeySpec;

import com.mysql.cj.util.StringUtils;
import com.sonar.vishal.MedicoUI;
import com.sonar.vishal.medico.common.message.common.Constant;
import com.sonar.vishal.medico.common.structure.KeyData;
import com.sonar.vishal.medico.common.structure.LoginData;
import com.sonar.vishal.ui.backend.RestBackend;
import com.sonar.vishal.ui.component.Component;
import com.sonar.vishal.ui.exception.ValidationException;
import com.vaadin.data.Binder;
import com.vaadin.server.Page;
import com.vaadin.server.VaadinSession;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.Notification;

public class LoginListener implements ClickListener {

	private static final long serialVersionUID = -1992503875386942892L;
	private Binder<LoginData> binder;
	private LoginData data;
	private RestBackend backend;
	private Notification notification;

	public LoginListener(Binder<LoginData> binder, LoginData data) {
		this.binder = binder;
		this.data = data;
		this.backend = new RestBackend(Constant.GET_KEY);
		KeyData keyData = (KeyData) this.backend.doPostRespondData(KeyData.class);
		byte[] decodedKey = Base64.getDecoder().decode(keyData.getKey());
		Key key = new SecretKeySpec(decodedKey, 0, decodedKey.length, "AES");
		this.backend.setKey(key);
	}

	@Override
	public void buttonClick(ClickEvent event) {
		try {
			binder.writeBean(data);
			if (StringUtils.isEmptyOrWhitespaceOnly(data.getUserName()) || StringUtils.isEmptyOrWhitespaceOnly(data.getPassword())) {
				throw new ValidationException();
			}
			this.backend = new RestBackend(Constant.LOGIN);
			RestBackend.message.setData(data);
			boolean result = this.backend.doPostRespondHeader();
			if (result) {
				notification = Component.getInstance().getSuccessNotification(Constant.SUCCESS, "Logged In");
				VaadinSession.getCurrent().setAttribute("UserName", data.getUserName());
				MedicoUI.navigator.navigateTo("optionpage");
			} else {
				notification = Component.getInstance().getFailureNotification(Constant.ERROR, Constant.LOGIN_FAILURE_MESSAGE);
			}
		} catch(ValidationException e) {
			notification = Component.getInstance().getFailureNotification(Constant.ERROR, Constant.VALIDATION_EXCEPTION);
		} catch (Exception e) {
			notification = Component.getInstance().getFailureNotification(Constant.ERROR, Constant.LOGIN_FAILURE_MESSAGE);
		}
		notification.show(Page.getCurrent());
	}

}
