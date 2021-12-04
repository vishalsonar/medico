package com.sonar.vishal.ui.structure;

import com.sonar.vishal.medico.common.structure.LoginData;
import com.sonar.vishal.ui.definition.Structure;
import com.sonar.vishal.ui.listener.ForgotPasswordListener;
import com.sonar.vishal.ui.listener.SignUpLinkListener;
import com.sonar.vishal.ui.util.UIConstant;
import com.vaadin.data.Binder;
import com.vaadin.icons.VaadinIcons;
import com.vaadin.ui.Button;
import com.vaadin.ui.FormLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.Panel;
import com.vaadin.ui.TextField;
import com.vaadin.ui.themes.ValoTheme;

public class ForgotPasswordStructure implements Structure {

	private Panel panel = new Panel();
	private FormLayout formLayout = new FormLayout();
	private Label label = COMPONENT.getLabel(UIConstant.RESET_PASSWORD);
	private Binder<LoginData> binder = new Binder<>();

	public ForgotPasswordStructure() {
		label.addStyleName(ValoTheme.LABEL_BOLD);
		label.addStyleName(ValoTheme.LABEL_H2);
		formLayout.setSizeFull();
		formLayout.setMargin(true);
		panel.setWidth(UIConstant.NUMBER_400_PX);
		panel.setContent(formLayout);
		panel.addStyleName(ValoTheme.PANEL_BORDERLESS);
	}

	@Override
	public Object get() {
		TextField userName = COMPONENT.getTextField(UIConstant.USER_NAME, UIConstant.USER_NAME, UIConstant.NUMBER_200_PX);
		Button resetPassword = COMPONENT.getFriendlyButton(UIConstant.SUBMIT, UIConstant.NUMBER_200);
		Button signInButton = COMPONENT.getButton(UIConstant.SIGN_IN, UIConstant.NUMBER_200);
		resetPassword.setIcon(VaadinIcons.CHECK);
		signInButton.addStyleName(ValoTheme.BUTTON_LINK);
		signInButton.setIcon(VaadinIcons.SIGN_IN);
		formLayout.addComponents(label, userName, resetPassword, signInButton);
		binder.bind(userName, LoginData::getUserName, LoginData::setUserName);
		signInButton.addClickListener(new SignUpLinkListener(userName));
		resetPassword.addClickListener(new ForgotPasswordListener(binder, userName));
		return panel;
	}

}
