package com.sonar.vishal.ui.listener;

import com.sonar.vishal.MedicoUI;
import com.sonar.vishal.ui.util.UIConstant;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.PasswordField;
import com.vaadin.ui.TextField;

public class ForgotPasswordLinkListener implements ClickListener {

	private static final long serialVersionUID = 2097268492764246681L;
	TextField userName;
	PasswordField password;

	public ForgotPasswordLinkListener(TextField userName, PasswordField password) {
		this.userName = userName;
		this.password = password;
	}

	@Override
	public void buttonClick(ClickEvent event) {
		userName.setValue(UIConstant.EMPTY);
		password.setValue(UIConstant.EMPTY);
		MedicoUI.getNavigatorUI().navigateTo(UIConstant.S_FORGOT_PASSWORD_URL);
	}

}
