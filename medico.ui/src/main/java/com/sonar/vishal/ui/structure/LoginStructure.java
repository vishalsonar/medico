package com.sonar.vishal.ui.structure;

import com.sonar.vishal.medico.common.structure.LoginData;
import com.sonar.vishal.ui.definition.Structure;
import com.sonar.vishal.ui.listener.LoginListener;
import com.vaadin.data.Binder;
import com.vaadin.ui.Button;
import com.vaadin.ui.FormLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.Link;
import com.vaadin.ui.Panel;
import com.vaadin.ui.PasswordField;
import com.vaadin.ui.TextField;
import com.vaadin.ui.themes.ValoTheme;

public class LoginStructure implements Structure {

	private Panel panel = new Panel();
	private FormLayout formLayout = new FormLayout();
	private Label login = COMPONENT.getLabel("Login");
	private Binder<LoginData> binder = new Binder<LoginData>();
	private LoginData data = new LoginData();

	public LoginStructure() {
		login.addStyleName(ValoTheme.LABEL_BOLD);
		login.addStyleName(ValoTheme.LABEL_H2);
		formLayout.setSizeFull();
		formLayout.setMargin(true);
		panel.setWidth("400px");
		panel.setContent(formLayout);
		panel.addStyleName(ValoTheme.PANEL_BORDERLESS);
	}

	@Override
	public Object get() {
		TextField userName = COMPONENT.getTextField("User Name", "User Name", "200");
		PasswordField password = COMPONENT.getPasswordField("Password", "Password", "200");
		Button loginButton = COMPONENT.getFriendlyButton("Login", "100");
		Link link = COMPONENT.getLink("forgot password!", "./OptionPage");
		formLayout.addComponents(login, userName, password, loginButton, link);
		binder.bind(userName, LoginData::getUserName, LoginData::setUserName);
		binder.bind(password, LoginData::getPassword, LoginData::setPassword);
		loginButton.addClickListener(new LoginListener(binder, data));
		return panel;
	}
}
