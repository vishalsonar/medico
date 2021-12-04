package com.sonar.vishal.ui.structure;

import com.sonar.vishal.medico.common.structure.LoginData;
import com.sonar.vishal.ui.definition.Structure;
import com.sonar.vishal.ui.listener.ForgotPasswordLinkListener;
import com.sonar.vishal.ui.listener.LoginListener;
import com.sonar.vishal.ui.util.UIConstant;
import com.vaadin.data.Binder;
import com.vaadin.icons.VaadinIcons;
import com.vaadin.ui.Button;
import com.vaadin.ui.FormLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.Panel;
import com.vaadin.ui.PasswordField;
import com.vaadin.ui.TextField;
import com.vaadin.ui.themes.ValoTheme;

public class LoginStructure implements Structure {

	private Panel panel = new Panel();
	private FormLayout formLayout = new FormLayout();
	private Label login = COMPONENT.getLabel(UIConstant.LOGIN);
	private Binder<LoginData> binder = new Binder<>();
	private LoginData data = new LoginData();

	public LoginStructure() {
		login.addStyleName(ValoTheme.LABEL_BOLD);
		login.addStyleName(ValoTheme.LABEL_H2);
		formLayout.setSizeFull();
		formLayout.setMargin(true);
		panel.setWidth(UIConstant.NUMBER_400_PX);
		panel.setContent(formLayout);
		panel.addStyleName(ValoTheme.PANEL_BORDERLESS);
	}

	@Override
	public Object get() {
		TextField userName = COMPONENT.getTextField(UIConstant.USER_NAME, UIConstant.USER_NAME, UIConstant.NUMBER_200_PX);
		PasswordField password = COMPONENT.getPasswordField(UIConstant.PASSWORD, UIConstant.PASSWORD, UIConstant.NUMBER_200_PX);
		Button loginButton = COMPONENT.getFriendlyButton(UIConstant.LOGIN, UIConstant.NUMBER_200);
		Button forgotPasswordButton = COMPONENT.getButton(UIConstant.FORGOT_PASSWORD, UIConstant.NUMBER_200);
		forgotPasswordButton.addStyleName(ValoTheme.BUTTON_LINK);
		loginButton.setIcon(VaadinIcons.SIGN_IN);
		formLayout.addComponents(login, userName, password, loginButton, forgotPasswordButton);
		binder.bind(userName, LoginData::getUserName, LoginData::setUserName);
		binder.bind(password, LoginData::getPassword, LoginData::setPassword);
		loginButton.addClickListener(new LoginListener(binder, data));
		forgotPasswordButton.addClickListener(new ForgotPasswordLinkListener(userName, password));
		return panel;
	}
}
